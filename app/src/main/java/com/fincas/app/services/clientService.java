package com.fincas.app.services;

import java.util.List;
import java.util.Optional;

import com.fincas.app.crud.clients.clientEntity;
import com.fincas.app.crud.clients.clientQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class clientService{
    @Autowired
    private clientQuery clientRep;

    public List<clientEntity> getAllClients(){
        return this.clientRep.getAllClients();
    }
    
    public Optional<clientEntity> getClientByID(long id){
        return  clientRep.getClientByID(id);
    }
    
    public clientEntity saveClient(clientEntity client){
        if(client.getId() != null){
            Optional<clientEntity> thisClient = clientRep.getClientByID(client.getId());

            if(thisClient.isEmpty()){
                return clientRep.save(client);
            }
            return null;
        }else{
            return clientRep.save(client);
        }
    }
    
    public clientEntity update(clientEntity client){
        if(client.getId() != null){
            Optional<clientEntity> opClient = clientRep.getClientByID(client.getId());
            if(opClient.isPresent()){
                clientEntity thisClient = opClient.get();
                if(client.getAge() != null){
                    thisClient.setAge(client.getAge());
                }
                if(client.getEmail() != null){
                    thisClient.setEmail(client.getEmail());
                }
                if(client.getName() != null){
                    thisClient.setName(client.getName());;
                }
                if(client.getPassword() != null){
                    thisClient.setPassword(client.getPassword());
                }
                return this.clientRep.save(thisClient);
            }
        }
        return null;
    }

    public boolean delete(long id){
        Boolean isDeleted = this.clientRep.getClientByID(id).map(client -> {
            clientRep.delete(client);
            return true;
        }).orElse(false);
        return isDeleted;
    } 
}