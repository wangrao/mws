package com.lovetravel.mws.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lovetravel.mws.dao.BaseDao;

@Repository
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

    private Class<T> entityClass;

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) types[0];
        }
    }

    public Session getCurrentSeesion() {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        if (session == null) {
            session = hibernateTemplate.getSessionFactory().openSession();
        }
        return session;
    }

    @Override
    public T add(T t) {
        hibernateTemplate.save(t);
        return t;
    }
    
    @Override
    public List<T> batchAdd(List<T> list) {
    	for (T entity : list) {
    		hibernateTemplate.save(entity);
    	}
    	hibernateTemplate.flush();
    	return list;
    }
    
    

    @Override
    public void delete(T t) {
        hibernateTemplate.delete(t);
    }

    @Override
    public T update(T t) {
        hibernateTemplate.update(t);
        return t;
    }

    @Override
    public T get(PK id) {
        return hibernateTemplate.get(entityClass, id);
    }

    @Override
    public T load(PK id) {
        return hibernateTemplate.load(entityClass, id);
    }

    @Override
    public List<T> listAll() {
        return hibernateTemplate.loadAll(entityClass);
    }
}
