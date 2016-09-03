package com.example.shaunmesias.assignment_6_2.factories.person;

import com.example.shaunmesias.assignment_6_2.conf.util.DomainState;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonDetails;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonDetailsFactory {
    public static PersonDetails getDetails(String owner, String carName, String carType)
    {
        return new PersonDetails.Builder()
                .state(DomainState.ACTIVE.name())
                .ownerName(owner)
                .carName(carName)
                .carType(carType)
                .status(DomainState.ACTIVE.name())
                .build();
    }
}
