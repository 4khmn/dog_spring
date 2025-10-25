package com.example.demo.controller;

import com.example.demo.service.DogService;
import com.example.demo.service.GetDogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainPageController {

    private final GetDogService getDogService;
    private final DogService dogService;
    public MainPageController(GetDogService getDogService, DogService dogService) {
        this.getDogService = getDogService;
        this.dogService = dogService;
    }

    @GetMapping("/dog")
    public String getRandomDog(Model model){

        String url = getDogService.getDogUrl();
        List<String> comments = dogService.getCommentsForCurrentDog(url);
        model.addAttribute("comments", comments);
        model.addAttribute("url", url);

        return "dog.html";
    }

    @PostMapping("/dog")
    public String postComment(@RequestParam(required = false) String message,
                              @RequestParam(required = false) String dogUrl,
                              @RequestParam(required = false) String liked, Model model){

        dogService.saveDogAndComment(message, dogUrl, liked);

        ////////////////////////////





        /// ДОДЕЛАТЬ JOIN
        String url = getDogService.getDogUrl();
        while (url == dogUrl){
            url = getDogService.getDogUrl();
        }
        boolean next_liked = dogService.getDogByUrl(url).isLiked();
        List<String> comments = dogService.getCommentsForCurrentDog(url);
        model.addAttribute("comments", comments);
        model.addAttribute("liked", next_liked);
        model.addAttribute("url", url);
        return "dog.html";
    }
}
