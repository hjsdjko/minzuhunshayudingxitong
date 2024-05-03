package com.dao;

import com.entity.SheyingshiCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SheyingshiCommentbackView;

/**
 * 摄影师评价 Dao 接口
 *
 * @author 
 */
public interface SheyingshiCommentbackDao extends BaseMapper<SheyingshiCommentbackEntity> {

   List<SheyingshiCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
