package com.dao;

import com.entity.SheyingshiLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SheyingshiLiuyanView;

/**
 * 摄影师留言 Dao 接口
 *
 * @author 
 */
public interface SheyingshiLiuyanDao extends BaseMapper<SheyingshiLiuyanEntity> {

   List<SheyingshiLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
