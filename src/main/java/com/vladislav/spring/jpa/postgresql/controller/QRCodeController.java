package com.vladislav.spring.jpa.postgresql.controller;

import com.vladislav.spring.jpa.postgresql.service.QRCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class QRCodeController {

    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/generateQRCode")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam("text") String text) {
        return qrCodeService.generateQRCode(text);
    }

    @GetMapping("/qrCodeDescription/{id}")
    public ResponseEntity<byte[]> getQRCodeDescriptionById(@PathVariable("id") long id) {
        return qrCodeService.getQRCodeDescriptionById(id);
    }
}
