package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.dao.SheyingshiCommentbackDao;
import com.entity.SheyingshiCommentbackEntity;
import com.service.SheyingshiCommentbackService;
import com.entity.view.SheyingshiCommentbackView;

/**
 * 摄影师评价 服务实现类
 */
@Service("sheyingshiCommentbackService")
@Transactional
public class SheyingshiCommentbackServiceImpl extends ServiceImpl<SheyingshiCommentbackDao, SheyingshiCommentbackEntity> implements SheyingshiCommentbackService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<SheyingshiCommentbackView> page =new Query<SheyingshiCommentbackView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
