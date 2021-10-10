package com.fincas.app.crud.messages;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class messageQuery {
    @Autowired
    private messageRepository msgRepository;

    public List<messageEntity> getAllMessages(){
        return (List<messageEntity>) msgRepository.findAll();
    }
    public Optional<messageEntity> getMessageByID(Long id){
        return msgRepository.findById(id);
    }
    public messageEntity save(messageEntity message){
        return msgRepository.save(message);
    }
    public void delete(messageEntity message){
        msgRepository.delete(message);
    }
    public void deleteById(long id){
        msgRepository.deleteById(id);
    }
}
