package com.example.shaunmesias.assignment_6_2.domain.person;

import java.io.Serializable;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class PersonContact implements Serializable {
    private String id;
    private String contactValue;
    private PersonContact(){}

    public String getId() {
        return id;
    }

    public String getContactValue() {
        return contactValue;
    }


    public PersonContact(Builder builder) {
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

        public Builder copy(PersonContact value){
            this.id = value.id;
            this.contactValue = value.contactValue;
            return  this;
        }

        public PersonContact build(){
            return new PersonContact(this);
        }
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonContact that = (PersonContact) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
