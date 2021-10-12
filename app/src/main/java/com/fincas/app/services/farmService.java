package com.fincas.app.services;

import java.util.List;
import java.util.Optional;

import com.fincas.app.crud.farms.farmEntity;
import com.fincas.app.crud.farms.farmQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class farmService {
    @Autowired
    private  farmQuery farmRep;

    public List<farmEntity> getAllFarms(){
        return farmRep.getAllFarms();
    }

    public Optional<farmEntity> getFarmByID(long id){
        return farmRep.getFarmByID(id);
    }
    
    public farmEntity saveFarm(farmEntity farm){
        if(farm.getId() != null){
            Optional<farmEntity> thisFarm = farmRep.getFarmByID(farm.getId());
            if(thisFarm.isEmpty()){
                return farmRep.save(farm);
            }
            return null;
        }
        return farmRep.save(farm);
    }

    public farmEntity update(farmEntity farm){
        if(farm.getId() != null){
            Optional<farmEntity> opFarm = farmRep.getFarmByID(farm.getId());
            if(opFarm.isPresent()){
                farmEntity thisFarm = opFarm.get();
                if(farm.getAddress() != null){
                    thisFarm.setAddress(farm.getAddress());
                }
                if(farm.getDescription() != null){
                    thisFarm.setDescription(farm.getDescription());
                }
                if(farm.getCategory() != null){
                    thisFarm.setCategory(farm.getCategory());
                }
                if(farm.getExtension() != null){
                    thisFarm.setExtension(farm.getExtension());
                }
                if(farm.getName() != null){
                    thisFarm.setName(farm.getName());
                }
                return farmRep.save(thisFarm);
            }
       }
       return null;
    }

    public boolean delete(long id){
        Boolean isDeleted = this.farmRep.getFarmByID(id).map(farm ->{
            farmRep.delete(farm);
            return true;
        }).orElse(false);
        return isDeleted;
    }
}