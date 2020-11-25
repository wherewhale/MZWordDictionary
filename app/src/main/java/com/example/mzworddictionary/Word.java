package com.example.mzworddictionary;

public class Word {
    private String word;
    private String detail;
    private String origin;
    private String ex;
    private int likes;

    public Word() { }

    public Word(String word, String detail, String origin, String example, int likes) {
        this.word = word;
        this.detail = detail;
        this.origin = origin;
        this.ex = ex;
        this.likes = likes;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
