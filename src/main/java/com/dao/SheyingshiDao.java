package com.dao;

import com.entity.SheyingshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SheyingshiView;

/**
 * 摄影师 Dao 接口
 *
 * @author 
 */
public interface SheyingshiDao extends BaseMapper<SheyingshiEntity> {

   List<SheyingshiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
