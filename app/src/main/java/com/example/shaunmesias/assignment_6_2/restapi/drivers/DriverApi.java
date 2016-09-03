package com.example.shaunmesias.assignment_6_2.restapi.drivers;

import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.restapi.DriverRestApi;

import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/08/30.
 */
public class DriverApi implements DriverRestApi<Driver, Long> {
//jdbc:mysql
    final String BASE_URL = "http://trucker-mdjansen.rhcloud.com";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Driver findById(long id) {
        final String url = BASE_URL+id;
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(requestHeaders);
        ResponseEntity<Driver> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Driver.class);
        Driver driver = responseEntity.getBody();
        return driver;
    }

    @Override
    public Driver save(Driver entity) {
        final String url = BASE_URL;
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(entity,requestHeaders);
        try {
            ResponseEntity<Driver> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Driver.class);
            Driver result = responseEntity.getBody();
            return result;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public void delete(Driver entity) {
        final String url = BASE_URL+entity.getId() ;
        restTemplate.delete(url,entity.getId());
    }

    @Override
    public Driver update(Driver entity) {
        final String url = BASE_URL+entity.getId();
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(entity, requestHeaders);
        ResponseEntity<Driver> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Driver.class);
        Driver result = responseEntity.getBody();
        return result;
    }

    @Override
    public Set<Driver> findAll() {
        Set<Driver> menuItems = new HashSet<>();
        final String url = BASE_URL;
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Driver[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Driver[].class);
        Driver[] results = responseEntity.getBody();

        for (Driver driver : results) {
            menuItems.add(driver);
        }
        return menuItems;
    }

}
