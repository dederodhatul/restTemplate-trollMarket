package com.restTemplateTrollMarket;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestTemplateController {

    RestTemplate restTemplate = new RestTemplate();

    private String secretKey = "liberate-tutume-ex-inferis-ad-astra-per-aspera";

    @GetMapping("/getShipment")
    public ResponseEntity<List<Object>> getData() {
        HttpHeaders headers = new HttpHeaders();
        String token = GetToken("admin", "admin123", "Administrator");
        headers.set("Authorization", "Bearer" + token);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<List<Object>> response = restTemplate.exchange("http://192.168.0.85:8080/troll-market/api/shipment",
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

        ResponseEntity<Map> response = restTemplate.exchange("http://192.168.0.85:8080/troll-market/api/authenticate",
                HttpMethod.POST, request, Map.class);
        Map<String, Object> responseBody = response.getBody();

        return (String) responseBody.get("token");
    }

    @PostMapping("/addShipment")
    public ResponseEntity<Object> addShipment(@RequestBody ShipmentDTO shipmentDTO) throws NoSuchFieldException {

        HttpHeaders headers = new HttpHeaders();
        String token = GetToken("admin", "admin123", "Administrator");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer" + token);
        HttpEntity<ShipmentDTO> request = new HttpEntity<>(shipmentDTO, headers);

        ResponseEntity<ShipmentDTO> response = restTemplate.exchange("http://192.168.0.85:8080/troll-market/api/shipment",
                HttpMethod.POST, request, ShipmentDTO.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
    }

//    @PostMapping("/addToCart")
//    public ResponseEntity<String> addToCart(@RequestBody ProductDTO productDTO){
//
//    }


}
