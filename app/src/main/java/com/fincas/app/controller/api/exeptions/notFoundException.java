package com.fincas.app.controller.api.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.NOT_FOUND,
    reason = "Request entity does not exist"
)
public class notFoundException extends RuntimeException{
    public notFoundException(String e){
        super(new Throwable(e));
    }
}
