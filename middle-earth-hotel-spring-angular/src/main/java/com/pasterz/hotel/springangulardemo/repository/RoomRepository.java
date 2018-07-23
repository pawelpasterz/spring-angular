package com.pasterz.hotel.springangulardemo.repository;

import com.pasterz.hotel.springangulardemo.entity.RoomEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

  Optional<RoomEntity> findById(Long id);
}
