package com.vladislav.spring.jpa.postgresql.dto;

public class UserDto {

    private String author;
    private String description;
    private boolean link;

    public UserDto() {
    }

    public UserDto(String author, String description, boolean link) {
        this.author = author;
        this.description = description;
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLink() {
        return link;
    }

    public void setLink(boolean link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", link=" + link +
                '}';
    }
}
