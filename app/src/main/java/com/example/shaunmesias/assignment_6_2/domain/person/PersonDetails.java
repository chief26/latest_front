package com.example.shaunmesias.assignment_6_2.domain.person;

import java.io.Serializable;

/**
 * Created by Shaun Mesias on 2016/04/12.
 */
public class PersonDetails implements Serializable{
    private String id;
    private String ownerName;
    private String carName;
    private String carType;
    private String status;
    private String state;
    private PersonDetails(){}

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarType() {
        return carType;
    }

    public String getStatus() {
        return status;
    }

    public String getState() {
        return state;
    }

    public PersonDetails(Builder builder) {
        this.id = builder.id;
        this.ownerName = builder.ownerName;
        this.carName = builder.carName;
        this.carType = builder.carType;
        this.status = builder.status;
        this.state = builder.state;
    }

    public static class Builder{
        private String id;
        private String ownerName;
        private String carName;
        private String carType;
        private String status;
        private String state;

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder ownerName(String value){
            this.ownerName = value;
            return this;
        }

        public Builder carName(String value){
            this.carName = value;
            return this;
        }

        public Builder carType(String value){
            this.carType = value;
            return this;
        }

        public Builder status(String value){
            this.status = value;
            return this;
        }

        public Builder state(String value){
            this.state = value;
            return this;
        }

        public Builder copy(PersonDetails value){
            this.id = value.id;
            this.ownerName = value.ownerName;
            this.carName = value.carName;
            this.carType = value.carType;
            this.status = value.status;
            this.state = value.state;
            return  this;
        }

        public PersonDetails build(){
            return new PersonDetails(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDetails that = (PersonDetails) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
