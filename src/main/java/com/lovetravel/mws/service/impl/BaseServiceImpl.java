package com.lovetravel.mws.service.impl;

import java.io.Serializable;
import java.util.List;

import com.lovetravel.mws.dao.BaseDao;
import com.lovetravel.mws.service.BaseService;

public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    private BaseDao<T, PK> baseDao;

    public BaseDao<T, PK> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T, PK> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public T add(T t) {
        return baseDao.add(t);
    }
    
    @Override
    public List<T> batchAdd(List<T> list) {
    	return baseDao.batchAdd(list);
    }

    @Override
    public void delete(T t) {
        baseDao.delete(t);

    }

    @Override
    public T update(T t) {
        return baseDao.update(t);
    }

    @Override
    public T get(PK id) {
        return baseDao.get(id);
    }

    @Override
    public T load(PK id) {
        return baseDao.load(id);
    }

    @Override
    public List<T> listAll() {
        return baseDao.listAll();
    }
}