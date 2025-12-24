package com.example.controller;

import java.util.List;
import java.util.Map;

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

import com.example.model.OrderMethod;
import com.example.repo.OrderMethodRepository;
import com.example.service.IOrderMethodService;

@RestController
@RequestMapping("/order")
public class OrderMethodRestController {

    @Autowired
    private IOrderMethodService orderMethodService;

    @Autowired
    private OrderMethodRepository orderMethodRepo;

    @PostMapping("/create")
    public ResponseEntity<String> createOrderMethod(@RequestBody OrderMethod orderMethod) {
        Integer id = orderMethodService.createOrderMethod(orderMethod);
        return new ResponseEntity<>("OrderMethod created with id: " + id, HttpStatus.CREATED);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<OrderMethod> getOneOrderMethod(@PathVariable Integer id) {
        return new ResponseEntity<>(orderMethodService.getOneOrderMethod(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<OrderMethod>> getAllOrderMethod() {
        return new ResponseEntity<>(orderMethodService.getAllOrderMethod(), HttpStatus.OK);
    }
    
    @GetMapping("/findAllOrderIdAndCodeByOrderMode/{orderMode}")
    public ResponseEntity<Map<Integer,String>> gettAllOrderIdAndCodeByOrderMode(@PathVariable String orderMode) {
        return new ResponseEntity<>(orderMethodService.findAllOrderIdAndCodeByOrderMode(orderMode), HttpStatus.OK);
    }

    @GetMapping("/validate/{orderCode}")
    public ResponseEntity<String> validateOrderCode(@PathVariable String orderCode) {
        if (orderMethodRepo.existsByOrderCode(orderCode)) {
            return new ResponseEntity<>("OrderMethod code already exists", HttpStatus.OK);
        }
        return new ResponseEntity<>("OrderMethod code available", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrderMethod(@RequestBody OrderMethod orderMethod) {
        orderMethodService.updateOrderMethod(orderMethod);
        return new ResponseEntity<>("OrderMethod updated with id: " + orderMethod.getOrderId(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderMethod(@PathVariable Integer id) {
        orderMethodService.deleteOrderMethod(id);
        return new ResponseEntity<>("OrderMethod deleted for id: " + id, HttpStatus.OK);
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> excelExport() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=ordermethods.xlsx");

        return new ResponseEntity<>(orderMethodService.excelExport(), headers, HttpStatus.OK);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> pdfExport() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=ordermethods.pdf");

        return new ResponseEntity<>(orderMethodService.pdfExport(), headers, HttpStatus.OK);
    }
}
