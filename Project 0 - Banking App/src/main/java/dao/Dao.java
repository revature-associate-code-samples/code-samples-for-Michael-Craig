package dao;

import java.util.List;

import pojos.User;

public interface Dao<T, I> {
	

	List<T> findAll();
	T save(T obj);
	T update(T obj);

	User findByUserName(String userName);
	

}
