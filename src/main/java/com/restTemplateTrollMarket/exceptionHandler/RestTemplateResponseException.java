package com.restTemplateTrollMarket.exceptionHandler;


public class RestTemplateResponseException extends RuntimeException{
    public RestTemplateResponseException(){

    }

    public RestTemplateResponseException(String msg){
        super(msg);
    }

}
