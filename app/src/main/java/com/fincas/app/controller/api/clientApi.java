package com.fincas.app.controller.api;

import java.util.List;
import java.util.Optional;

import com.fincas.app.controller.api.exeptions.notFoundException;
import com.fincas.app.crud.clients.clientEntity;
import com.fincas.app.services.clientService;

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
@RequestMapping("api/Client")
@CrossOrigin(origins = "*")
public class clientApi {
    @Autowired
    private clientService clientService;

    @GetMapping(value = "all")
    public List<clientEntity> getAllClients(){
        return clientService.getAllClients();
    }
    @PostMapping(value = "save")
    public clientEntity createClient(@RequestBody clientEntity body){
        return clientService.saveClient(body);
    }
    // Normal Request
    @GetMapping("/{id}")
    public clientEntity getFarm(@PathVariable long id){
        Optional<clientEntity> exist = clientService.getClientByID(id);
        if(exist.isEmpty()){
            throw new notFoundException("Client with id: " + id+ " not exist");
        }
        return exist.get();
    }
    @GetMapping List<clientEntity> getEveryClients(){
        return clientService.getAllClients();
    }
    @PutMapping
    public void update(@RequestBody clientEntity body){
        clientEntity exsist = clientService.update(body);
        if(exsist == null){
            throw new notFoundException("Client with id: " + body.getId() + " not exist");
        }
    }
    @DeleteMapping
    public void delete(@RequestBody clientEntity body){
        boolean isDeleted = clientService.delete(body.getId());

        if(!isDeleted){
            throw new notFoundException("Client with id: " + body.getId() + "not exist");
        }
    }
}
