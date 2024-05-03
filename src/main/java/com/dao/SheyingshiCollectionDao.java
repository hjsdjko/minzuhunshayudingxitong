package com.dao;

import com.entity.SheyingshiCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SheyingshiCollectionView;

/**
 * 摄影师收藏 Dao 接口
 *
 * @author 
 */
public interface SheyingshiCollectionDao extends BaseMapper<SheyingshiCollectionEntity> {

   List<SheyingshiCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
