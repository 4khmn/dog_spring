package com.example.demo.DogRepository.POJO;

public class DogInfo {
    private static int global_id=1;
    private int id;
    private String url;
    private boolean liked;

    public DogInfo() {
        this.id = global_id++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
