package com.fincas.app.services;

import java.util.List;
import java.util.Optional;

import com.fincas.app.crud.messages.messageEntity;
import com.fincas.app.crud.messages.messageQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class messageServie {
    @Autowired
    private messageQuery msgRepo;

    public List<messageEntity> getAllMessages() {
        return msgRepo.getAllMessages();
    }

    public Optional<messageEntity> getMessageByID(long id) {
        return msgRepo.getMessageByID(id);
    }

    public messageEntity saveMessage(messageEntity message) {
        if (message.getId() != null) {
            Optional<messageEntity> thisMessage = msgRepo.getMessageByID(message.getId());
            if (thisMessage.isEmpty()) {
                return msgRepo.save(message);
            }
            return thisMessage.get();
        }
        return msgRepo.save(message);
    }

    public messageEntity update(messageEntity message){
        if(message.getId() != null){
            Optional<messageEntity> opMessage = msgRepo.getMessageByID(message.getId());
            if(opMessage.isPresent()){
                messageEntity thisMessage = opMessage.get();
                if(message.getMessageText() != null){
                    thisMessage.setMessageText(message.getMessageText());
                }
                return msgRepo.save(thisMessage);
            }
        }
        return message;
    }

    public boolean delete(long id){
        Boolean isDeleted = msgRepo.getMessageByID(id).map(msg ->{
            msgRepo.delete(msg);
            return true;
        }).orElse(false);
        return isDeleted;
    }
}