package com.fincas.app.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import com.fincas.app.crud.farms.farmEntity;
import com.fincas.app.services.farmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/Farm")
@CrossOrigin(origins = "*")
public class farmApi {
    @Autowired
    private farmService farmService;

    @GetMapping(value="all")
    public List<farmEntity> getAllFarms() {
        return farmService.getAllFarms();
    }
    @PostMapping(value="save")
    public String createFarm(@RequestBody Map<String, Object> body){
        // String name = body.get("name").toString();
        // String address = body.get("address").toString();
        return body.toString();
    }
}
