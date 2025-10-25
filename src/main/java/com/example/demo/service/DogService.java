package com.example.demo.service;

import com.example.demo.DogRepository.DogRepository;
import com.example.demo.DogRepository.POJO.DogComment;
import com.example.demo.DogRepository.POJO.DogInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }
    public void updateLike(String url, boolean liked){
        dogRepository.updateLike(url, liked);
    }

    public DogInfo getDogByUrl(String url){
        return dogRepository.getDogByUrl(url);
    }
    @Transactional
    public void saveDogAndComment(String message,
                                  String dogUrl,
                                  String liked){
        boolean isLiked = liked != null;
        DogInfo dogInfo = new DogInfo();
        dogInfo.setUrl(dogUrl);
        dogInfo.setLiked(isLiked);
        if (!dogRepository.isThisDogExistInTable(dogUrl)){
            dogRepository.storeInfoAboutDog(dogInfo);
        }
        else{
            dogRepository.updateLike(dogUrl, isLiked);
        }
        //////
        DogComment dogComment = new DogComment();
        dogComment.setComment(message);
        dogComment.setDog_id(dogRepository.getDogByUrl(dogUrl).getId());
        dogRepository.storeInfoAboutDogComments(dogComment);
    }

    public List<String> getCommentsForCurrentDog(String url){
        List<DogComment> comments_objects = dogRepository.getAllCommentsForCurrentDog(url);
        List<String> comments = new ArrayList<>();
        for (var v: comments_objects){
            if (!v.getComment().equals("")) {
                comments.add(v.getComment());
            }
        }
        return comments;
    }


}
