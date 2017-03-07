package com.lovetravel.mws.dao;

import java.io.Serializable;
import java.util.List;
/**
 * BaseDao Class, All xxxDao interface's parent interface.
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T, PK extends Serializable> {
    /**
     * add method.
     * <p>add data object.</P>
     * @param t
     * @return T
     */
    public T add(T t);
    
    public List<T> batchAdd(List<T> list);
    /**
     * delete method.
     * <p>delete data object.</P>
     * @param t
     */
    public void delete(T t);
    /**
     * update method.
     * <p>update data object.</P>
     * @param t
     * @return T
     */
    public T update(T t);

    /**
     * get method.
     * <p>get data object</P>
     * @param id
     * @return T
     */
    public T get(PK id);

    /**
     * load method.
     * <p>load data object, lazy data object feature.</P>
     * @param id
     * @return T
     */
    public T load(PK id);

    /**
     * loadAll method.
     * <p>load all data object.</P>
     * @return List<T>
     */
    public List<T> listAll();

}
