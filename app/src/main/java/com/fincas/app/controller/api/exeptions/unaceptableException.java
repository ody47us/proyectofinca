package com.fincas.app.controller.api.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.NOT_ACCEPTABLE,
    reason = "The entity alreay exists"
)
public class unaceptableException extends RuntimeException{
    public unaceptableException(String e){
        super(new Throwable(e));
    }
}
