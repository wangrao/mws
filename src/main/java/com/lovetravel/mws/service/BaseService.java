package com.lovetravel.mws.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, PK extends Serializable> {

    public T add(T t);
    
    public List<T> batchAdd(List<T> list);

    public void delete(T t);

    public T update(T t);

    public T get(PK id);

    public T load(PK id);

    public List<T> listAll();

}