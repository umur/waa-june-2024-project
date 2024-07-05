package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.Category;
import com.example.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("")
    public List<Address> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address findCategoryById(@PathVariable Long id) {
        return addressService.findAddressById(id);
    }
}
