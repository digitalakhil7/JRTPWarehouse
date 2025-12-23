package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Part;
import com.example.repo.PartRepository;
import com.example.service.IPartService;

@RestController
@RequestMapping("/part")
public class PartRestController {

    @Autowired
    private IPartService partService;

    @Autowired
    private PartRepository partRepo;

    @PostMapping("/create")
    public ResponseEntity<String> createPart(@RequestBody Part part) {
        Integer id = partService.createPart(part);
        return new ResponseEntity<>("Part created with id: " + id, HttpStatus.CREATED);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Part> getOnePart(@PathVariable Integer id) {
        return new ResponseEntity<>(partService.getOnePart(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Part>> getAllPart() {
        return new ResponseEntity<>(partService.getAllPart(), HttpStatus.OK);
    }

    @GetMapping("/validate/{partCode}")
    public ResponseEntity<String> validatePartCode(@PathVariable String partCode) {
        if (partRepo.existsByPartCode(partCode)) {
            return new ResponseEntity<>("Part code already exists", HttpStatus.OK);
        }
        return new ResponseEntity<>("Part code available", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePart(@RequestBody Part part) {
        partService.updatePart(part);
        return new ResponseEntity<>("Part updated with id: " + part.getPartId(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePart(@PathVariable Integer id) {
        partService.deletePart(id);
        return new ResponseEntity<>("Part deleted for id: " + id, HttpStatus.OK);
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> excelExport() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=parts.xlsx");

        return new ResponseEntity<>(partService.excelExport(), headers, HttpStatus.OK);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> pdfExport() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=parts.pdf");

        return new ResponseEntity<>(partService.pdfExport(), headers, HttpStatus.OK);
    }
}
