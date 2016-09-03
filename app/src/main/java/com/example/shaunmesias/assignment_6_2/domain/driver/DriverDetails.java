package com.example.shaunmesias.assignment_6_2.domain.driver;

import java.io.Serializable;

/**
 * Created by Shaun Mesias on 2016/04/12.
 */
public class DriverDetails implements Serializable{
    private String id;
    private String ownerName;
    private String carName;
    private DriverDetails(){}

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getCarName() {
        return carName;
    }

    public DriverDetails(Builder builder) {
        this.id = builder.id;
        this.ownerName = builder.ownerName;
        this.carName = builder.carName;
    }

    public static class Builder{
        private String id;
        private String ownerName;
        private String carName;

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


        public Builder copy(DriverDetails value){
            this.id = value.id;
            this.ownerName = value.ownerName;
            this.carName = value.carName;
            return  this;
        }

        public DriverDetails build(){
            return new DriverDetails(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverDetails that = (DriverDetails) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
