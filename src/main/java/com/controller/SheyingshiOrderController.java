
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 摄影师预约
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/sheyingshiOrder")
public class SheyingshiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(SheyingshiOrderController.class);

    @Autowired
    private SheyingshiOrderService sheyingshiOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private SheyingshiService sheyingshiService;
@Autowired
private SheyingshiCommentbackService sheyingshiCommentbackService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("摄影师".equals(role))
            params.put("sheyingshiId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = sheyingshiOrderService.queryPage(params);

        //字典表数据转换
        List<SheyingshiOrderView> list =(List<SheyingshiOrderView>)page.getList();
        for(SheyingshiOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SheyingshiOrderEntity sheyingshiOrder = sheyingshiOrderService.selectById(id);
        if(sheyingshiOrder !=null){
            //entity转view
            SheyingshiOrderView view = new SheyingshiOrderView();
            BeanUtils.copyProperties( sheyingshiOrder , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(sheyingshiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                SheyingshiEntity sheyingshi = sheyingshiService.selectById(sheyingshiOrder.getSheyingshiId());
                if(sheyingshi != null){
                    BeanUtils.copyProperties( sheyingshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSheyingshiId(sheyingshi.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SheyingshiOrderEntity sheyingshiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,sheyingshiOrder:{}",this.getClass().getName(),sheyingshiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("摄影师".equals(role))
            sheyingshiOrder.setSheyingshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("用户".equals(role))
            sheyingshiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        sheyingshiOrder.setInsertTime(new Date());
        sheyingshiOrder.setCreateTime(new Date());
        sheyingshiOrderService.insert(sheyingshiOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SheyingshiOrderEntity sheyingshiOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,sheyingshiOrder:{}",this.getClass().getName(),sheyingshiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("摄影师".equals(role))
//            sheyingshiOrder.setSheyingshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("用户".equals(role))
//            sheyingshiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<SheyingshiOrderEntity> queryWrapper = new EntityWrapper<SheyingshiOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SheyingshiOrderEntity sheyingshiOrderEntity = sheyingshiOrderService.selectOne(queryWrapper);
        if(sheyingshiOrderEntity==null){
            sheyingshiOrderService.updateById(sheyingshiOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        sheyingshiOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<SheyingshiOrderEntity> sheyingshiOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            SheyingshiOrderEntity sheyingshiOrderEntity = new SheyingshiOrderEntity();
//                            sheyingshiOrderEntity.setSheyingshiOrderUuidNumber(data.get(0));                    //预约流水号 要改的
//                            sheyingshiOrderEntity.setSheyingshiId(Integer.valueOf(data.get(0)));   //摄影师 要改的
//                            sheyingshiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            sheyingshiOrderEntity.setPaishediTypes(Integer.valueOf(data.get(0)));   //拍摄地 要改的
//                            sheyingshiOrderEntity.setSheyingshiOrderTruePrice(data.get(0));                    //定金 要改的
//                            sheyingshiOrderEntity.setInsertTime(date);//时间
//                            sheyingshiOrderEntity.setPaisheTime(sdf.parse(data.get(0)));          //预约拍摄日期 要改的
//                            sheyingshiOrderEntity.setShijianduanTypes(Integer.valueOf(data.get(0)));   //预约拍摄时间段 要改的
//                            sheyingshiOrderEntity.setSheyingshiOrderTypes(Integer.valueOf(data.get(0)));   //预约状态 要改的
//                            sheyingshiOrderEntity.setCreateTime(date);//时间
                            sheyingshiOrderList.add(sheyingshiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //预约流水号
                                if(seachFields.containsKey("sheyingshiOrderUuidNumber")){
                                    List<String> sheyingshiOrderUuidNumber = seachFields.get("sheyingshiOrderUuidNumber");
                                    sheyingshiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> sheyingshiOrderUuidNumber = new ArrayList<>();
                                    sheyingshiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("sheyingshiOrderUuidNumber",sheyingshiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //预约流水号
                        List<SheyingshiOrderEntity> sheyingshiOrderEntities_sheyingshiOrderUuidNumber = sheyingshiOrderService.selectList(new EntityWrapper<SheyingshiOrderEntity>().in("sheyingshi_order_uuid_number", seachFields.get("sheyingshiOrderUuidNumber")));
                        if(sheyingshiOrderEntities_sheyingshiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SheyingshiOrderEntity s:sheyingshiOrderEntities_sheyingshiOrderUuidNumber){
                                repeatFields.add(s.getSheyingshiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [预约流水号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        sheyingshiOrderService.insertBatch(sheyingshiOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = sheyingshiOrderService.queryPage(params);

        //字典表数据转换
        List<SheyingshiOrderView> list =(List<SheyingshiOrderView>)page.getList();
        for(SheyingshiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SheyingshiOrderEntity sheyingshiOrder = sheyingshiOrderService.selectById(id);
            if(sheyingshiOrder !=null){


                //entity转view
                SheyingshiOrderView view = new SheyingshiOrderView();
                BeanUtils.copyProperties( sheyingshiOrder , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(sheyingshiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    SheyingshiEntity sheyingshi = sheyingshiService.selectById(sheyingshiOrder.getSheyingshiId());
                if(sheyingshi != null){
                    BeanUtils.copyProperties( sheyingshi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSheyingshiId(sheyingshi.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody SheyingshiOrderEntity sheyingshiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,sheyingshiOrder:{}",this.getClass().getName(),sheyingshiOrder.toString());
            SheyingshiEntity sheyingshiEntity = sheyingshiService.selectById(sheyingshiOrder.getSheyingshiId());
            if(sheyingshiEntity == null){
                return R.error(511,"查不到该摄影师");
            }
            // Double sheyingshiNewMoney = sheyingshiEntity.getSheyingshiNewMoney();

            if(false){
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - sheyingshiEntity.getSheyingshiDingjin();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            sheyingshiOrder.setSheyingshiOrderTypes(1); //设置订单状态为已支付
            sheyingshiOrder.setSheyingshiOrderTruePrice(sheyingshiEntity.getSheyingshiDingjin()); //设置实付价格
            sheyingshiOrder.setYonghuId(userId); //设置订单支付人id
            sheyingshiOrder.setSheyingshiOrderUuidNumber(String.valueOf(new Date().getTime()));
            sheyingshiOrder.setInsertTime(new Date());
            sheyingshiOrder.setCreateTime(new Date());
                sheyingshiOrderService.insert(sheyingshiOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);
            return R.ok();
    }

    /**
    * 取消预约
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            SheyingshiOrderEntity sheyingshiOrder = sheyingshiOrderService.selectById(id);
            Integer sheyingshiId = sheyingshiOrder.getSheyingshiId();
            if(sheyingshiId == null)
                return R.error(511,"查不到该摄影师");
            SheyingshiEntity sheyingshiEntity = sheyingshiService.selectById(sheyingshiId);
            if(sheyingshiEntity == null)
                return R.error(511,"查不到该摄影师");
            Double sheyingshiNewMoney = sheyingshiEntity.getSheyingshiDingjin();
            if(sheyingshiNewMoney == null)
                return R.error(511,"摄影师定金价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");



            //判断是什么支付方式 1代表余额 2代表积分
                //计算金额
                Double money = sheyingshiOrder.getSheyingshiOrderTruePrice();
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额





            sheyingshiOrder.setSheyingshiOrderTypes(2);//设置订单状态为取消预约
            sheyingshiOrderService.updateById(sheyingshiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            sheyingshiService.updateById(sheyingshiEntity);//更新订单中摄影师的信息
            return R.ok();
    }


    /**
     * 使用
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        SheyingshiOrderEntity  sheyingshiOrderEntity = new  SheyingshiOrderEntity();;
        sheyingshiOrderEntity.setId(id);
        sheyingshiOrderEntity.setSheyingshiOrderTypes(3);
        boolean b =  sheyingshiOrderService.updateById( sheyingshiOrderEntity);
        if(!b){
            return R.error("使用出错");
        }
        return R.ok();
    }














    /**
     * 用户确认
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        SheyingshiOrderEntity  sheyingshiOrderEntity = new  SheyingshiOrderEntity();
        sheyingshiOrderEntity.setId(id);
        sheyingshiOrderEntity.setSheyingshiOrderTypes(4);
        boolean b =  sheyingshiOrderService.updateById( sheyingshiOrderEntity);
        if(!b){
            return R.error("用户确认出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer sheyingshiCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            SheyingshiOrderEntity sheyingshiOrder = sheyingshiOrderService.selectById(id);
        if(sheyingshiOrder == null)
            return R.error(511,"查不到该订单");
        if(sheyingshiOrder.getSheyingshiOrderTypes() != 4)
            return R.error(511,"您不能评价");
        Integer sheyingshiId = sheyingshiOrder.getSheyingshiId();
        if(sheyingshiId == null)
            return R.error(511,"查不到该摄影师");

        SheyingshiCommentbackEntity sheyingshiCommentbackEntity = new SheyingshiCommentbackEntity();
            sheyingshiCommentbackEntity.setId(id);
            sheyingshiCommentbackEntity.setSheyingshiId(sheyingshiId);
            sheyingshiCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            sheyingshiCommentbackEntity.setSheyingshiCommentbackText(commentbackText);
            sheyingshiCommentbackEntity.setInsertTime(new Date());
            sheyingshiCommentbackEntity.setReplyText(null);
            sheyingshiCommentbackEntity.setUpdateTime(null);
            sheyingshiCommentbackEntity.setCreateTime(new Date());
            sheyingshiCommentbackService.insert(sheyingshiCommentbackEntity);

            sheyingshiOrder.setSheyingshiOrderTypes(5);//设置订单状态为已评价
            sheyingshiOrderService.updateById(sheyingshiOrder);//根据id更新
            return R.ok();
    }












}
