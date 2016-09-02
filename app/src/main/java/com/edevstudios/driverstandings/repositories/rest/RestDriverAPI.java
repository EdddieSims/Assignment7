package com.edevstudios.driverstandings.repositories.rest;

import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repositories.RestAPI;
import com.edevstudios.driverstandings.repositories.RestAPIDriver;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/09/02.
 */
public class RestDriverAPI implements RestAPI<Driver, Long>
{
    final String BASE_URL = "http://driverstandings-tpproject.rhcloud.com/api/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();


    @Override
    public Driver get(Long id)
    {
        final String url = BASE_URL+"driver/"+id.toString();
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(requestHeaders);
        ResponseEntity<Driver> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Driver.class);
        Driver subject = responseEntity.getBody();
        return subject;
    }

    @Override
    public String post(Driver entity)
    {
        final String url = BASE_URL+"driver/create";
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Driver entity)
    {
        final String url = BASE_URL+"driver/update/"+entity.getId().toString();
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(Driver entity) {
        final String url = BASE_URL+"driver/delete/"+entity.getId().toString();
        HttpEntity<Driver> requestEntity = new HttpEntity<Driver>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public Set getAll() {
        Set<Driver> subjects = new HashSet<>();
        final String url = BASE_URL+"drivers/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Driver[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Driver[].class);
        Driver[] drivers = responseEntity.getBody();

        for (Driver driver : drivers) {
            subjects.add(driver);
        }

        return subjects;
    }
}
