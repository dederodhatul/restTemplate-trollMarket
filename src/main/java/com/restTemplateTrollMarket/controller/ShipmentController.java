package com.restTemplateTrollMarket.controller;

import com.restTemplateTrollMarket.dto.ShipmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    @Autowired
    RestTemplate restTemplate;

    private String secretKey = "liberate-tutume-ex-inferis-ad-astra-per-aspera";

    @GetMapping(value = {"",
                        "{page}"
                })
    public ResponseEntity<List<Object>> getData(@PathVariable(required = false) Integer page) {
        page = page == null ? 1 : page;

        HttpHeaders headers = new HttpHeaders();
        String token = GetToken("admin", "admin123", "Administrator");
        headers.set("Authorization", "Bearer" + token);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<List<Object>> response = restTemplate.exchange("http://192.168.0.85:8080/troll-market/api/shipment/"+page,
                HttpMethod.GET, request, new ParameterizedTypeReference<List<Object>>() {
                });
        return new ResponseEntity<>(response.getBody(), HttpStatus.FOUND);
    }

    public String GetToken(String username, String password, String role) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);
        requestBody.put("secretKey", secretKey);
        requestBody.put("role", role);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange("http://192.168.0.85:8080/troll-market/api/authenticate",
                HttpMethod.POST, request, new ParameterizedTypeReference<Map<String, Object>>() {
                });

//        System.out.println(response);

        return (String) response.getBody().get("token");
    }

    @PostMapping()
    public ResponseEntity<Object> addShipment(@RequestBody ShipmentDTO shipmentDTO){

        HttpHeaders headers = new HttpHeaders();
        String token = GetToken("admin", "admin123", "Administrator");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer" + token);

        HttpEntity<ShipmentDTO> request = new HttpEntity<>(shipmentDTO, headers);

        ResponseEntity<ShipmentDTO> response = restTemplate.exchange("http://192.168.0.85:8080/troll-market/api/shipment",
                HttpMethod.POST, request, ShipmentDTO.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> editShipment(@RequestBody ShipmentDTO shipmentDTO){
        HttpHeaders headers = new HttpHeaders();
        String token = GetToken("admin", "admin123", "Administrator");
        headers.set("Authorization", "Bearer" + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ShipmentDTO> request = new HttpEntity<>(shipmentDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange("http://192.168.0.85:8080/troll-market/api/shipment",
                HttpMethod.PUT, request, String.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }



}
