package com.fincas.app.crud.farms;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class farmQuery {
    @Autowired
    private farmRepository farmRep;

    public List<farmEntity> getAllFarms(){
        return (List<farmEntity>) farmRep.findAll();
    }
    public Optional<farmEntity> getFarmByID(long id){
        return farmRep.findById(id);
    }
    public farmEntity save(farmEntity farm){
        return farmRep.save(farm);
    }
    public void delete(farmEntity farm){
        farmRep.delete(farm);
    }
    public void deleteById(long id){
        farmRep.deleteById(id);
    }
}
