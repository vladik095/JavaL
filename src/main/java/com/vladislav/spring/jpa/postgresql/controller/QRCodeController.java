package com.vladislav.spring.jpa.postgresql.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import com.vladislav.spring.jpa.postgresql.model.User;
import com.vladislav.spring.jpa.postgresql.repository.UserRepository;

@RestController
public class QRCodeController {

    private final UserRepository userRepository;

    @Value("${qr.api-base-url}")
    private String qrApiBaseUrl;

    @Value("${qr.size}")
    private String qrSize;

    public QRCodeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/generateQRCode")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam("text") String text) {
        try {
            String url = qrApiBaseUrl + "?size=" + qrSize + "&data=" + text;

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(response.getBody(), headers, response.getStatusCode());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/qrCodeDescription/{id}")
    public ResponseEntity<byte[]> getQRCodeDescriptionById(@PathVariable("id") long id) {
        try {
            Optional<User> userData = userRepository.findById(id);

            if (userData.isPresent()) {
                User user = userData.get();
                String description = user.getDescription();

                String url = qrApiBaseUrl + "?size=" + qrSize + "&data=" + description;

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);

                return new ResponseEntity<>(response.getBody(), headers, response.getStatusCode());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
