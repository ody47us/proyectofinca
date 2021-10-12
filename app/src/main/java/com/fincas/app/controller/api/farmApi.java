package com.fincas.app.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.fincas.app.controller.api.exeptions.notFoundException;
import com.fincas.app.controller.api.exeptions.unaceptableException;
import com.fincas.app.crud.farms.farmEntity;
import com.fincas.app.services.farmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public farmEntity createFarm(@RequestBody farmEntity body){
        return farmService.saveFarm(body);
    }
    // Normal request
    @GetMapping("/{id}")
    public farmEntity getFarm(@PathVariable long id){
        Optional<farmEntity> exist = farmService.getFarmByID(id);
        if(exist.isEmpty()){
            throw new notFoundException("Farm with id: " + id+ "not exist");
        }
        return exist.get();
    }
    @GetMapping
    public List<farmEntity> getEveryFarms() {
        return farmService.getAllFarms();
    }
    @PostMapping
    public void addFarm(@RequestBody farmEntity body){
        farmEntity exist = farmService.saveFarm(body);
        if(exist == null){
            throw new unaceptableException("There is already a farm with the id: " + body.getId());
        }
    }
    @PutMapping
    public void update(@RequestBody farmEntity body){
        farmEntity exsist = farmService.update(body);
        if(exsist == null){
            throw new notFoundException("Farm with id: " + body.getId() + "not exist");
        }
    }
    @DeleteMapping
    public void delete(@RequestBody farmEntity body){
        boolean isDeleted = farmService.delete(body.getId());

        if(!isDeleted){
            throw new notFoundException("Farm with id: " + body.getId() + "not exist");
        }
    }
}
