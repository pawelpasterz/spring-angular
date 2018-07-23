package com.pasterz.hotel.springangulardemo.rest;

import com.pasterz.hotel.springangulardemo.converter.RoomEntityToRoomReservationResponseConverter;
import com.pasterz.hotel.springangulardemo.entity.ReservationEntity;
import com.pasterz.hotel.springangulardemo.entity.RoomEntity;
import com.pasterz.hotel.springangulardemo.model.request.ReservationRequest;
import com.pasterz.hotel.springangulardemo.model.response.ReservableRoomResponse;
import com.pasterz.hotel.springangulardemo.model.response.ReservationResponse;
import com.pasterz.hotel.springangulardemo.repository.PageableRoomRepository;
import com.pasterz.hotel.springangulardemo.repository.ReservationRepository;
import com.pasterz.hotel.springangulardemo.repository.RoomRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

  @Autowired
  private PageableRoomRepository pagableRoomRepository;

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private ConversionService conversionService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Page<ReservableRoomResponse> getAvailableRooms(
      @RequestParam(value = "checkin") @DateTimeFormat(iso = ISO.DATE) LocalDate checkin,
      @RequestParam(value = "checkout") @DateTimeFormat(iso = ISO.DATE) LocalDate checkout,
      Pageable pageable) {

    Page<RoomEntity> roomEntities = pagableRoomRepository.findAll(pageable);

    return roomEntities.map(new RoomEntityToRoomReservationResponseConverter()::convert);
  }

  @GetMapping(path = "/{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long roomId) {

    RoomEntity room = roomRepository.findById(roomId).get();

    return new ResponseEntity<>(room, HttpStatus.OK);
  }

  @PostMapping(
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  public ResponseEntity<ReservationResponse> createReservation(
      @RequestBody
          ReservationRequest reservationRequest) {

    ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
    reservationRepository.save(reservationEntity);

    RoomEntity roomEntity = roomRepository.findById(reservationRequest.getRoomId()).get();
    roomEntity.addReservationEntity(reservationEntity);
    roomRepository.save(roomEntity);
    reservationEntity.setRoomEntity(roomEntity);

    ReservationResponse reservationResponse =
        conversionService.convert(reservationEntity, ReservationResponse.class);


    return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
  }

  @PutMapping(
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  public ResponseEntity<ReservableRoomResponse> updateReservation(
      @RequestBody ReservationRequest reservationRequest) {

    return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{reservationId}")
  public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
