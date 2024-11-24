package com.hotel.service.Services.Impl;

import com.hotel.service.Entity.Hotel;
import com.hotel.service.Exception.ResourceNotFoundException;
import com.hotel.service.PayLoad.HotelDto;
import com.hotel.service.Repository.HotelRepository;
import com.hotel.service.Services.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        Hotel hotel = mapToEntity(hotelDto);
        String s = UUID.randomUUID().toString();
        hotel.setId(s);
        Hotel  hot = hotelRepo.save(hotel);
        HotelDto hotelDto1 = mapToDto(hot);
        return hotelDto1;
    }

    @Override
    public HotelDto getHotelById(String id) {
        Hotel hotel = hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel","id","id"));
        HotelDto hotelDto = mapToDto(hotel);
        return hotelDto;
    }

    @Override
    public List<HotelDto> getAllList() {
        List<Hotel> all = hotelRepo.findAll();
        List<HotelDto> collect = all.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteHotelById(String id) {
        hotelRepo.deleteById(id);
    }

    @Override
    public HotelDto updateHotelById(HotelDto hotelDto, String id) {
        Hotel hotel = hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel","id","id"));
        Hotel hotel1 = mapToEntity(hotelDto);
        hotel.setId(hotel1.getId());
        hotel.setName(hotel1.getName());
        hotel.setLocation(hotel1.getLocation());
        hotel.setAbout(hotel1.getAbout());
        Hotel save = hotelRepo.save(hotel);
        HotelDto hotelDto1 = mapToDto(save);
        return hotelDto1;
    }

    public HotelDto mapToDto(Hotel hotel){
       HotelDto dto = modelMapper.map(hotel, HotelDto.class);
       return dto;
   }

   public Hotel mapToEntity(HotelDto dto){
       Hotel hotel = modelMapper.map(dto, Hotel.class);
       return hotel;
   }

}
