package com.pasterz.hotel.springangulardemo.converter;

import com.pasterz.hotel.springangulardemo.entity.ReservationEntity;
import com.pasterz.hotel.springangulardemo.model.response.ReservationResponse;
import org.springframework.core.convert.converter.Converter;

public class ReservationEntityToTreservationResponseConverter
    implements Converter<ReservationEntity, ReservationResponse> {

  @Override
  public ReservationResponse convert(ReservationEntity reservationEntity) {

    ReservationResponse reservationResponse = new ReservationResponse();
    reservationResponse.setCheckin(reservationEntity.getCheckin());
    reservationResponse.setCheckout(reservationEntity.getCheckout());

    if (null != reservationEntity.getRoomEntity()) {
      reservationResponse.setId(reservationEntity.getRoomEntity().getId());
    }

    return reservationResponse;
  }
}
