package com.fincas.app.crud.reservations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class reservationQuery {
    @Autowired
    private reservationRepository reservRep;

    public List<reservationEntity> getAllReservations(){
        return (List<reservationEntity>) reservRep.findAll();
    }
    public Optional<reservationEntity> getReservationById(long id){
        return reservRep.findById(id);
    }
    public List<reservationEntity> getReservationsByClientAndFarm(long farm, long client){
        return reservRep.findByFarmAndClient(farm, client);
    }
    public reservationEntity save(reservationEntity reservation){
        return reservRep.save(reservation);
    }
    public void delete(reservationEntity reservation){
        reservRep.delete(reservation);
    }
    public void deleteById(long id){
        reservRep.deleteById(id);
    }
}
