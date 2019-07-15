package com.qdu.mapper;

import java.util.List;

public interface BaseMapper<T> {

	/**
	 * 插入记录
	 * @param example
	 * @return 1-成功,0-失败
	 */
	int insert(T example);

	/**
	 * 根据物理主键id进行删除
	 * @param id
	 * @return 1-成功,0-失败
	 */
	int delete(Object id);

	/**
	 * 根据model中属性进行删除
	 * @param example
	 * @return 0-失败
	 */
	int deleteModel(T example);

	/**
	 * 根据主键修改全部属性
	 * @param example
	 * @return 1-成功,0-失败
	 */
	int update(T example);

	/**
	 * 根据主键修改不为空的属性
	 * @param example
	 * @return 1-成功,0-失败
	 */
	int updateActive(T example);

	/**
	 * 根据物理(逻辑)主键查询model
	 * @param id
	 * @return 1-成功,0-失败
	 */
	T selectUQ(Object id);

	/**
	 * 根据model中属性查询符合条件的model集合
	 */
	List<T> selectModel(T example);
	
	/**
	 * 根据model中属性分页查询符合条件的model集合
	 */
	List<T> selectModelPage(T example,int before,int after);

	/**
	 * 查询所有的model
	 */
	List<T> selectAll();

	/**
	 * 查询总的记录条数
	 */
	int selectCount();

}
