package com.example.shaunmesias.assignment_6_2.domain.driver;

import java.io.Serializable;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class DriverContact implements Serializable {
    private String id;
    private String contactValue;
    private DriverContact(){}

    public String getId() {
        return id;
    }

    public String getContactValue() {
        return contactValue;
    }


    public DriverContact(Builder builder) {
        this.id = builder.id;
        this.contactValue= builder.contactValue;

    }

    public static class Builder{
        private String id;
        private String contactValue;

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder contactValue(String value){
            this.contactValue = value;
            return this;
        }

        public Builder copy(DriverContact value){
            this.id = value.id;
            this.contactValue = value.contactValue;
            return  this;
        }

        public DriverContact build(){
            return new DriverContact(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverContact that = (DriverContact) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
