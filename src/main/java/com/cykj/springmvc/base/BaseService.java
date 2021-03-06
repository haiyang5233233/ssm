package com.cykj.springmvc.base;

import java.io.Serializable;


public interface BaseService<T> {
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public void save(T entity);
	
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	public T update(T entity);
	/**
	 * 根据id获取对象
	 * @param id
	 * @param dataType 主键的类型 {I,S,L}-->{Integer,String,Long}
	 * @return
	 */
	public T get(Serializable id);
	
	/**
	 * 根据ID删除对象
	 * @param id
	 * @return
	 */
	public void delete(Serializable id);
	
	/**
	 * 删除指定对象
	 * @param entity
	 * @return
	 */
	public void remove(T entity);
	

	
}
