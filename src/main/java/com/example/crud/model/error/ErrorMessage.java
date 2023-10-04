package com.example.crud.model.error;

public class ErrorMessage {


    private String Titulo;

    private Integer Status;

    private String Message;

    public ErrorMessage(String titulo, Integer status, String message) {
        Titulo = titulo;
        Status = status;
        Message = message;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

}
