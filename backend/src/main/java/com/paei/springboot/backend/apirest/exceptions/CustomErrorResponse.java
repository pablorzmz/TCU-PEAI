package com.paei.springboot.backend.apirest.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class CustomErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp; // Representa el momento en el que se da el error en el servidor
    private int status;              // Representa el codigo de error HTTP
    private String error;            // Representa el mensaje de error

    CustomErrorResponse (LocalDateTime  localDateTime, HttpStatus httpStatus, String error){
        timestamp = localDateTime;
        status = httpStatus.value();
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}