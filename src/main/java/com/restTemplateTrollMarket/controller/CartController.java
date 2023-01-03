package com.restTemplateTrollMarket.controller;

import com.restTemplateTrollMarket.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    ShipmentController shipmentController;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping()
    public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO){

        HttpHeaders headers = new HttpHeaders();
        String token = shipmentController.GetToken("john", "john123", "Buyer");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer" + token);

        HttpEntity<CartDTO> request = new HttpEntity<>(cartDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange("http://192.168.0.85:8080/troll-market/api/shop",
                HttpMethod.POST, request, String.class);

        System.out.println(response.getBody());

        return new ResponseEntity<>("success add to cart", HttpStatus.CREATED);
    }



}
