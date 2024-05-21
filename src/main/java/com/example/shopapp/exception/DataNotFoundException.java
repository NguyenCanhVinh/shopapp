package com.example.shopapp.exception;

import lombok.Data;

public class DataNotFoundException extends Exception{

  public DataNotFoundException(String message){
    super(message);
  }
}
