package com.p1.dao;

import java.io.Serializable;
import java.util.List;

import com.p1.pojos.Reimbursement;

public interface Dao<T, I extends Serializable> {
	
	List<T> findAll();
	T findById(I id);
	T save(T obj);
	T create(T obj);
	T update(T obj);
	void delete(T obj);

	default boolean isUnique(T obj) {
		return true;
	}

}