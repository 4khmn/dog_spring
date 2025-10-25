package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "getDog", url = "https://dog.ceo/api/breeds/image/random")
public interface ForeingAPI {
    @GetMapping()
    DogPicture getDog();
}
