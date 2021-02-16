package com.example.restsecurity.service;


import java.util.List;

public interface GetSupported<ID,RESPONSE> {

    public  RESPONSE get(ID id) ;


    List<RESPONSE> getAll();


}
