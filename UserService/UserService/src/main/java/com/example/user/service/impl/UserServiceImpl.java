package com.example.user.service.impl;

import com.example.user.service.entities.Hotel;
import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFoundException;
import com.example.user.service.external.services.HotelService;
import com.example.user.service.repositories.UserRepository;
import com.example.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get single user details with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id not found on server : " + userId));

        //fetch the rating of the above user from RATING service
        Rating[] ratingsList = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        logger.error("Ratings : {} " , ratingsList);

        List<Rating> ratings = Arrays.stream(ratingsList).toList();

        List<Rating> ratingWithHotelList = ratings.stream().map(rating -> {
            //api call to hotel service to get hotel
            //set the hotel to rating
            //return the rating
//            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            logger.error("Hotel : {} ", hotel);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingWithHotelList);
        return user;
    }
}
