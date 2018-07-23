package com.pasterz.hotel.springangulardemo.config;

import com.pasterz.hotel.springangulardemo.converter.ReservationEntityToTreservationResponseConverter;
import com.pasterz.hotel.springangulardemo.converter.ReservationRequestToReservationEntityConverter;
import com.pasterz.hotel.springangulardemo.converter.RoomEntityToRoomReservationResponseConverter;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConversionConfig {

  private Set<Converter> getConverters() {
    Set<Converter> converters = new HashSet<Converter>();
    converters.add(new RoomEntityToRoomReservationResponseConverter());
    converters.add(new ReservationRequestToReservationEntityConverter());
    converters.add(new ReservationEntityToTreservationResponseConverter());

    return converters;
  }

  @Bean
  public ConversionService conversionService() {
    ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
    bean.setConverters(getConverters());
    bean.afterPropertiesSet();

    return bean.getObject();
  }
}
