package com.vladislav.spring.jpa.postgresql.model;

import jakarta.persistence.*;

@Entity
@Table(name = "logs")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "Author")
  private String author;

  @Column(name = "QRdescription")
  private String description;

  @Column(name = "Link")
  private boolean isLink;

  public User() {

  }

  public User(String author, String description, boolean isLink) {
    this.author = author;
    this.description = description;
    this.isLink = isLink;
  }

  public long getId() {
    return id;
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
    return isLink;
  }

  public void setLink(boolean isLink) {
    this.isLink = isLink;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", author=" + author + ", desc=" + description + ", isLink=" + isLink + "]";
  }

}
