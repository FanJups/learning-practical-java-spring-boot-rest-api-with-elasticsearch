package com.course.practicaljava.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"color", "serial_num"})
public class Engine {
    private String fuelType;
    private int horsePower;
    private String color = "Black";
    private String serialNum = "12345678";

    public Engine() {}

    public Engine(String fuelType, int horsePower) {
        this.fuelType = fuelType;
        this.horsePower = horsePower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "fuelType='" + fuelType + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }
}
