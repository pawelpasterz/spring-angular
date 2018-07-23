package com.pasterz.hotel.springangulardemo.model.request;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class ReservationRequest {

  private Long id;

  private Long roomId;

  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate checkin;

  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate checkout;

  public ReservationRequest() {}

  public ReservationRequest(Long roomId, LocalDate checkin, LocalDate checkout) {
    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }

  public Long getRoomId() {
    return roomId;
  }

  public void setRoomId(Long roomId) {
    this.roomId = roomId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getCheckin() {
    return checkin;
  }

  public void setCheckin(LocalDate checkin) {
    this.checkin = checkin;
  }

  public LocalDate getCheckout() {
    return checkout;
  }

  public void setCheckout(LocalDate checkout) {
    this.checkout = checkout;
  }

}
