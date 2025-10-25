package com.example.demo.service;

import com.example.demo.DogPicture;
import com.example.demo.DogRepository.DogRepository;
import com.example.demo.ForeingAPI;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class GetDogService {
    private final ForeingAPI foreingAPI;
    private final DogRepository dogRepository;

    public GetDogService(ForeingAPI foreingAPI, DogRepository dogRepository) {
        this.foreingAPI = foreingAPI;
        this.dogRepository = dogRepository;
    }


    public String getDogUrl() {
        if (dogRepository.getCountOfRecords()>10){
            return dogRepository.getRandomIngFromTable();
        }
        else {
            DogPicture dog = foreingAPI.getDog();
            return dog.getMessage();
        }
    }
}

