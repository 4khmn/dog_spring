package com.example.demo.DogRepository.POJO;

public class DogComment {
    private int id;
    private int dog_id;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDog_id() {
        return dog_id;
    }

    public void setDog_id(int dog_id) {
        this.dog_id = dog_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
