package com.edevstudios.driverstandings.repositories.rest;

import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repositories.RestAPI;

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
 * Created by Edmund.Simons on 2016/08/23.
 */
public class RestCarAPI implements RestAPI<Car,Long>
{
    final String BASE_URL = "http://driverstandings-tpproject.rhcloud.com/api/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Car findById(Long id) {
        final String url = BASE_URL+"car/"+id.toString();
        HttpEntity<Car> requestEntity = new HttpEntity<Car>(requestHeaders);
        ResponseEntity<Car> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Car.class);
        Car subject = responseEntity.getBody();
        return subject;
    }

    @Override
    public Car save(Car entity) {
        final String url = BASE_URL+"car/create";
        HttpEntity<Car> requestEntity = new HttpEntity<Car>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return entity;
    }

    @Override
    public Car update(Car entity)
    {
        final String url = BASE_URL+"car/update/"+entity.getId().toString();
        HttpEntity<Car> requestEntity = new HttpEntity<Car>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

        return entity;
    }

    @Override
    public void delete(Car entity)
    {
        final String url = BASE_URL+"car/delete/"+entity.getId().toString();
        HttpEntity<Car> requestEntity = new HttpEntity<Car>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }

    @Override
    public Set<Car> findAll()
    {
        Set<Car> subjects = new HashSet<>();
        final String url = BASE_URL+"cars";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Car[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Car[].class);
        Car[] results = responseEntity.getBody();

        for (Car subject : results) {
            subjects.add(subject);
        }
        return subjects;
    }
}
