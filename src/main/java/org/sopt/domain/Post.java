package org.sopt.domain;

public class Post {

    final private int id;

    private String title;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public void updateTitle(String newTitle){     //Repository update를 위한 메서드
        this.title = newTitle;
    }
}
