package com.pasterz.hotel.springangulardemo.converter;

import com.pasterz.hotel.springangulardemo.entity.RoomEntity;
import com.pasterz.hotel.springangulardemo.model.Links;
import com.pasterz.hotel.springangulardemo.model.Self;
import com.pasterz.hotel.springangulardemo.model.response.ReservableRoomResponse;
import com.pasterz.hotel.springangulardemo.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;

public class RoomEntityToRoomReservationResponseConverter
    implements Converter<RoomEntity, ReservableRoomResponse> {

  @Override
  public ReservableRoomResponse convert(RoomEntity roomEntity) {

    ReservableRoomResponse reservableRoomResponse = new ReservableRoomResponse();
    reservableRoomResponse.setRoomNumber(roomEntity.getRoomNumber());
    reservableRoomResponse.setPrice(Integer.valueOf(roomEntity.getPrice()));
    reservableRoomResponse.setId(roomEntity.getId());

    Links links =  new Links();
    Self self = new Self();
    self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + roomEntity.getId());
    links.setSelf(self);

    reservableRoomResponse.setLinks(links);

    return reservableRoomResponse;

  }
}
