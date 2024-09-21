package com.example.hotel.services;

import com.example.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel create(Hotel hotel);

    //getall
    List<Hotel> getAll();

    //getSingle
    Hotel get(String id);


}
