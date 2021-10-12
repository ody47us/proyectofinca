package com.fincas.app.controller.api;

import java.util.List;
import java.util.Optional;

import com.fincas.app.controller.api.exeptions.notFoundException;
import com.fincas.app.controller.api.exeptions.unaceptableException;
import com.fincas.app.crud.reservations.reservationEntity;
import com.fincas.app.services.reservatioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin("*")
public class reservationApi {
    @Autowired
    private reservatioService reservService;

    @GetMapping(value = "all")
    public List<reservationEntity> getAllReservations() {
        return reservService.getAllReservations();
    }

    @PostMapping(value = "save")
    public reservationEntity createReservation(@RequestBody reservationEntity body) {
        return reservService.saveReservation(body);
    }

    // Normal Request
    @GetMapping("/{id}")
    public reservationEntity getReservation(@PathVariable long id) {
        Optional<reservationEntity> exist = reservService.getReservationById(id);
        if (exist.isEmpty()) {
            throw new notFoundException("Reservation with id: " + id + "not exist");
        }
        return exist.get();
    }
    @GetMapping
    public List<reservationEntity> getEveryReservations() {
        return reservService.getAllReservations();
    }
    @PostMapping
    public void addReservation(@RequestBody reservationEntity body){
        reservationEntity exist = reservService.saveReservation(body);
        if(exist == null){
            throw new unaceptableException("There is already a Reservation with the id: " + body.getId());
        }
    }
    @PutMapping
    public void update(@RequestBody reservationEntity body){
        reservationEntity exsist = reservService.update(body);
        if(exsist == null){
            throw new notFoundException("Resevation with id: " + body.getId() + "not exist");
        }
    }
    @DeleteMapping
    public void delete(@RequestBody reservationEntity body){
        boolean isDeleted = reservService.delete(body.getId());

        if(!isDeleted){
            throw new notFoundException("Reservation with id: " + body.getId() + "not exist");
        }
    }
}
