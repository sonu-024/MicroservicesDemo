package com.example.rating.impl;

import com.example.rating.entities.Rating;
import com.example.rating.repositories.RatingRepository;
import com.example.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getAllRatingsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }
}
