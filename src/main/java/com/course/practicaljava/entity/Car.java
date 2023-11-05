package com.course.practicaljava.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Document(indexName = "practicaljava")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Car {

    @Id
    private String id;
    private String brand;
    private String color;
    private String type;
    private int price;
    private boolean available;
    @Field(type = FieldType.Date, format = DateFormat.date)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    private LocalDate firstReleaseDate;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<String> additionalFeatures;
    @JsonUnwrapped
    private Engine engine;
    private List<Tire> tires;

    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Jakarta")
    private ZonedDateTime timestamp = ZonedDateTime.now();

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String secretFeature;

    public Car() {
    }

    public Car(String brand, String color, String type) {
        super();
        this.brand = brand;
        this.color = color;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(LocalDate firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public List<String> getAdditionalFeatures() {
        return additionalFeatures;
    }

    public void setAdditionalFeatures(List<String> additionalFeatures) {
        this.additionalFeatures = additionalFeatures;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public List<Tire> getTires() {
        return tires;
    }

    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSecretFeature() {
        return secretFeature;
    }

    public void setSecretFeature(String secretFeature) {
        this.secretFeature = secretFeature;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", firstReleaseDate=" + firstReleaseDate +
                ", additionalFeatures=" + additionalFeatures +
                ", engine=" + engine +
                ", tires=" + tires +
                ", timestamp=" + timestamp +
                ", secretFeature='" + secretFeature + '\'' +
                '}';
    }
}