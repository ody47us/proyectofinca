package com.fincas.app.controller.api;

import java.util.List;
import java.util.Optional;

import com.fincas.app.controller.api.exeptions.notFoundException;
import com.fincas.app.controller.api.exeptions.unaceptableException;
import com.fincas.app.crud.messages.messageEntity;
import com.fincas.app.services.messageServie;

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
@RequestMapping("/api/Message")
@CrossOrigin(origins =  "*")
public class messageApi {
    @Autowired
    private messageServie msgService;

    @GetMapping(value = "all")
    public List<messageEntity> getAllMessages(){
        return msgService.getAllMessages();
    }
    @PostMapping(value = "save")
    public messageEntity createMessage(@RequestBody messageEntity body){
        return msgService.saveMessage(body);
    }
    // Normal Request
    @GetMapping("/{id}")
    public messageEntity getFarm(@PathVariable long id){
        Optional<messageEntity> exist = msgService.getMessageByID(id);
        if(exist.isEmpty()){
            throw new notFoundException("Message with id: " + id+ " not exist");
        }
        return exist.get();
    }
    @GetMapping
    public List<messageEntity> getEveryMessages() {
        return msgService.getAllMessages();
    }
    @PostMapping
    public void addMessage(@RequestBody messageEntity body){
        messageEntity exist = msgService.saveMessage(body);
        if(exist == null){
            throw new unaceptableException("There is already a message with the id: " + body.getId());
        }
    }
    @PutMapping
    public void updaate(@RequestBody messageEntity body){
        messageEntity exsist = msgService.update(body);
        if(exsist == null){
            throw new notFoundException("Message with id: " + body.getId() + " not exist");
        }
    }
    @DeleteMapping
    public void delete(@RequestBody messageEntity body){
        boolean isDeleted = msgService.delete(body.getId());

        if(!isDeleted){
            throw new notFoundException("Message with id: " + body.getId() + " not exist");
        }
    }
}
