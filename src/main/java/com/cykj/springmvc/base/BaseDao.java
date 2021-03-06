package com.cykj.springmvc.base;

import java.io.Serializable;


/**
 * 基础数据库操作类
 * 
 */
public interface BaseDao<T> {
	
	/**
	 * 保存对象
	 * @param entity
	 * @return
	 */
	public void save(T entity);
	
	/**
	 * 更新对象
	 * @param entity
	 * @return
	 */
	public T update(T entity);
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public T get(Serializable id);
	
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void delete(Serializable id);
	
	/**
	 * 删除对象
	 * @param entity
	 */
	public void remove(T entity);
	


}