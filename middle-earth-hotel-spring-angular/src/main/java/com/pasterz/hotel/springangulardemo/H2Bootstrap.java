package com.pasterz.hotel.springangulardemo;

import com.pasterz.hotel.springangulardemo.entity.RoomEntity;
import com.pasterz.hotel.springangulardemo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class H2Bootstrap implements CommandLineRunner {

  @Autowired
  private RoomRepository repository;

  @Override
  public void run(String... args) throws Exception {

    repository.save(new RoomEntity(405, "200"));
    repository.save(new RoomEntity(65, "24"));
    repository.save(new RoomEntity(111, "134"));
    repository.save(new RoomEntity(999, "674"));

    Iterable<RoomEntity> rooms = repository.findAll();
  }
}
