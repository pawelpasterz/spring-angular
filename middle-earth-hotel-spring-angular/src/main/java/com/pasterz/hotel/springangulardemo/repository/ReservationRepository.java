package com.pasterz.hotel.springangulardemo.repository;

import com.pasterz.hotel.springangulardemo.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {

}
