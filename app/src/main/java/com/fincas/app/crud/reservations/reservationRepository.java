package com.fincas.app.crud.reservations;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface reservationRepository extends CrudRepository<reservationEntity,Long>{
    public List<reservationEntity> findByFarmAndClient(long farm, long client);
}