package com.hotel.service.Services;

import com.hotel.service.Entity.Hotel;
import com.hotel.service.PayLoad.HotelDto;

import java.util.Collection;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);

    HotelDto getHotelById(String id);

    Collection<HotelDto> getAllList();

    void deleteHotelById(String id);

    HotelDto updateHotelById(HotelDto hotelDto, String id);
}
