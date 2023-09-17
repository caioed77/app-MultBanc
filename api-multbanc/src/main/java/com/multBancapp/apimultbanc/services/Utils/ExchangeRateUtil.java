package com.multBancapp.apimultbanc.services.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multBancapp.apimultbanc.exceptions.ResouceNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;


import java.net.URI;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ExchangeRateUtil {

      @Value("${ExApiKey}")
      private String apiKey;

      @Value("${ExApiUrl}")
      private String apiUrl;

      @Value("${ExApiHost}")
      private String apiHost;

     private BigDecimal brlExchangeRate;

     public BigDecimal returnValueRate() {

           HttpHeaders headers = new HttpHeaders();
           headers.add("x-rapidapi-host", apiHost);
           headers.add("x-rapidapi-key", apiKey);
           headers.setContentType(MediaType.APPLICATION_JSON);

           RestTemplate restTemplate = new RestTemplate();

           RequestEntity<?> requestEntity = RequestEntity
                   .get(URI.create(apiUrl))
                   .headers(headers)
                   .build();

           ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

           var statusCode = responseEntity.getStatusCodeValue();

           if (statusCode == 200) {
                 var responseBody = responseEntity.getBody();

                 try {
                       ObjectMapper objectMapper = new ObjectMapper();
                       JsonNode jsonNode = objectMapper.readTree(responseBody);
                       brlExchangeRate = BigDecimal.valueOf(jsonNode.get("rates").get("BRL").asDouble());
                 } catch (Exception e) {
                       e.printStackTrace();
                 }
           } else {
                 throw new ResouceNotFoundException("A chamada à API de taxa de câmbio falhou. status: " + statusCode);
           }
           return brlExchangeRate;
     }

}
