package org.reactive.programming.produmer.producer.exception;

public class NoValidFileException extends RuntimeException{
   public NoValidFileException(String message){
        super(message);
    }

    public NoValidFileException(Throwable throwable, String message){
       super(throwable);
    }
}
