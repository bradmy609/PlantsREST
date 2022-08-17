package com.codecademy.plants.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.regex.Pattern;
import java.util.function.Predicate;

@Entity
@Table(name = "ADVENTURES")
public class Adventure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "DATE")
  private String date;
  
  @Column(name = "COUNTRY")
  private String country;
  
  @Column(name = "CITY")
  private String city;
  
  @Column(name = "STATE")
  private String state;
  
  @Column(name = "NUM_PHOTOS")
  private Long numPhotos;
  
  @Column(name = "BLOG_COMPLETED")
  private Boolean blogCompleted;

  private final Pattern datePattern = Pattern.compile("\\d{2}?/\\d{2}?/\\d{4}?");
  private final Pattern numberPattern = Pattern.compile("\\d+");

  public Integer getId() {
    return this.id;
  }

  public String getDate() {
    return this.date;
  }

  public String getCountry() {
    return this.country;
  }
  
  public String getCity() {
    return this.city;
  }

  public String getState() {
    return this.state;
  }

  public Long getNumPhotos() {
    return this.numPhotos;
  }

  public Boolean getBlogCompleted() {
    return this.blogCompleted;
  }

  public void setBlogCompleted(Boolean blogCompleted) {
    this.blogCompleted = blogCompleted;
  }

  public void printAdventure() {
    System.out.println("id: " + this.id + "\n" + "date: " + this.date + "\n" + "country: " + this.country + "\n" + "city: " + this.city + "\n" + "state: " + this.state + "\n" + "numPhotos: " + this.numPhotos + "\n" + "blogCompleted: " + this.blogCompleted + "\n");
  }

  public Boolean validateDate(String date) {
    Predicate<String> predicate = datePattern.asMatchPredicate();
    Boolean result = predicate.test(date);
    return result;
  }

  public Boolean validatePhotos(String num) {
    Predicate<String> predicate = numberPattern.asMatchPredicate();
    Boolean result = predicate.test(num);
    return result;
  }

  public Boolean validateAdventure() throws Exception {
    Boolean validPhotos = validatePhotos(Long.toString(this.getNumPhotos()));
    Boolean validDate = validateDate(this.getDate());
    if (!validPhotos) {
      throw new Exception("numPhotos is invalid.");
    } else if (!validDate) {
      throw new Exception("Date is invalid.");
    }
    System.out.println("Both photos and date input are valid.\n" + validPhotos + "\n" + validDate);
    return (validPhotos && validDate);
  }

}