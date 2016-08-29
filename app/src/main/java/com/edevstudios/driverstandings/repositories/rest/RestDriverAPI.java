package com.edevstudios.driverstandings.repositories.rest;

import com.edevstudios.driverstandings.domain.Driver;
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
public class RestDriverAPI implements RestAPI<Driver, Long>
{
    final String BASE_URL = "http://driverbackend-tpproject.rhcloud.com/api/";
    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Driver findById(Long id)
    {
        final String url = BASE_URL+"driver/"+id.toString();
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(requestHeaders);
        ResponseEntity<Driver> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Driver.class);
        Driver subject = responseEntity.getBody();
        return subject;
    }

    @Override
    public Driver save(Driver entity)
    {
        final String url = BASE_URL+"driver/create/";
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        //Driver result = responseEntity.getBody();
        return entity;
    }

    @Override
    public Driver update(Driver entity)
    {
        final String url = BASE_URL+"driver/update/"+entity.getId().toString();
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

        return entity;
    }

    @Override
    public void delete(Driver entity)
    {
        final String url = BASE_URL+"driver/delete/"+entity.getId().toString();
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }

    @Override
    public Set<Driver> findAll()
    {
        Set<Driver> subjects = new HashSet<>();
        final String url = BASE_URL +"drivers/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Driver[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Driver[].class);
        Driver[] results = responseEntity.getBody();

        for (Driver subject : results)
        {
            subjects.add(subject);
        }
        return subjects;
    }
}
