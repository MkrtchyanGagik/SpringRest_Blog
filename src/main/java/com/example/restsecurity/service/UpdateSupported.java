package com.example.restsecurity.service;

public interface UpdateSupported<RESPONSE,REQUEST,ID>{

    RESPONSE update(REQUEST request, ID id);
}
