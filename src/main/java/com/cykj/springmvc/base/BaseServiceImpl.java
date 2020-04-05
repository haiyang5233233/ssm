package com.cykj.springmvc.base;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;
	

	public T get(Serializable id) {		
		return (T)baseDao.get(id);
	}

	public void save(T entity) {
		baseDao.save(entity);
	}
	
	public void delete(Serializable id){
		baseDao.delete(id);
	}
	
	public void remove(T entity){
		baseDao.remove(entity);
	}
	
	public T update(T entity) {
		return baseDao.update(entity);
	}
	

}
