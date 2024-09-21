package com.example.rating.services;

import com.example.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    //create
    Rating create(Rating rating);

    //get All Ratings
    List<Rating> getAllRatings();

    //get All By Hotel
    List<Rating> getAllRatingsByHotelId(String hotelId);


    //get All By UserId
    List<Rating> getAllRatingsByUserId(String userId);

}
