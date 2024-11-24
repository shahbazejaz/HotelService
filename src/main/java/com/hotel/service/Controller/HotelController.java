package com.hotel.service.Controller;

import com.hotel.service.PayLoad.HotelDto;
import com.hotel.service.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto){
        HotelDto dto = hotelService.createHotel(hotelDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable("id") String  id){
        HotelDto dto = hotelService.getHotelById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotel(){
        List<HotelDto> list = (List<HotelDto>) hotelService.getAllList();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String  id){
        hotelService.deleteHotelById(id);
        return new ResponseEntity<>("hotel deleted successfully",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotelById(@RequestBody HotelDto hotelDto,@PathVariable("id") String  id){
        HotelDto hotelDto1 = hotelService.updateHotelById(hotelDto, id);
        return new ResponseEntity<>(hotelDto1,HttpStatus.OK);
    }

}
