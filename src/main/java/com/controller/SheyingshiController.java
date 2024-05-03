
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
 * 摄影师
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/sheyingshi")
public class SheyingshiController {
    private static final Logger logger = LoggerFactory.getLogger(SheyingshiController.class);

    @Autowired
    private SheyingshiService sheyingshiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


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
        PageUtils page = sheyingshiService.queryPage(params);

        //字典表数据转换
        List<SheyingshiView> list =(List<SheyingshiView>)page.getList();
        for(SheyingshiView c:list){
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
        SheyingshiEntity sheyingshi = sheyingshiService.selectById(id);
        if(sheyingshi !=null){
            //entity转view
            SheyingshiView view = new SheyingshiView();
            BeanUtils.copyProperties( sheyingshi , view );//把实体数据重构到view中

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
    public R save(@RequestBody SheyingshiEntity sheyingshi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,sheyingshi:{}",this.getClass().getName(),sheyingshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<SheyingshiEntity> queryWrapper = new EntityWrapper<SheyingshiEntity>()
            .eq("username", sheyingshi.getUsername())
            .or()
            .eq("sheyingshi_phone", sheyingshi.getSheyingshiPhone())
            .or()
            .eq("sheyingshi_id_number", sheyingshi.getSheyingshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SheyingshiEntity sheyingshiEntity = sheyingshiService.selectOne(queryWrapper);
        if(sheyingshiEntity==null){
            sheyingshi.setCreateTime(new Date());
            sheyingshi.setPassword("123456");
            sheyingshiService.insert(sheyingshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者摄影师手机号或者摄影师身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SheyingshiEntity sheyingshi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,sheyingshi:{}",this.getClass().getName(),sheyingshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<SheyingshiEntity> queryWrapper = new EntityWrapper<SheyingshiEntity>()
            .notIn("id",sheyingshi.getId())
            .andNew()
            .eq("username", sheyingshi.getUsername())
            .or()
            .eq("sheyingshi_phone", sheyingshi.getSheyingshiPhone())
            .or()
            .eq("sheyingshi_id_number", sheyingshi.getSheyingshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SheyingshiEntity sheyingshiEntity = sheyingshiService.selectOne(queryWrapper);
        if("".equals(sheyingshi.getSheyingshiPhoto()) || "null".equals(sheyingshi.getSheyingshiPhoto())){
                sheyingshi.setSheyingshiPhoto(null);
        }
        if(sheyingshiEntity==null){
            sheyingshiService.updateById(sheyingshi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者摄影师手机号或者摄影师身份证号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        sheyingshiService.deleteBatchIds(Arrays.asList(ids));
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
            List<SheyingshiEntity> sheyingshiList = new ArrayList<>();//上传的东西
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
                            SheyingshiEntity sheyingshiEntity = new SheyingshiEntity();
//                            sheyingshiEntity.setUsername(data.get(0));                    //账户 要改的
//                            //sheyingshiEntity.setPassword("123456");//密码
//                            sheyingshiEntity.setSheyingshiUuidNumber(data.get(0));                    //摄影师工号 要改的
//                            sheyingshiEntity.setSheyingshiName(data.get(0));                    //摄影师姓名 要改的
//                            sheyingshiEntity.setSheyingshiPhone(data.get(0));                    //摄影师手机号 要改的
//                            sheyingshiEntity.setSheyingshiIdNumber(data.get(0));                    //摄影师身份证号 要改的
//                            sheyingshiEntity.setSheyingshiPhoto("");//详情和图片
//                            sheyingshiEntity.setSheyingshiShanchang(data.get(0));                    //摄影师擅长 要改的
//                            sheyingshiEntity.setSheyingshiDingjin(data.get(0));                    //摄影师预约定金 要改的
//                            sheyingshiEntity.setSheyingshiJiage(data.get(0));                    //摄影价格/次 要改的
//                            sheyingshiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            sheyingshiEntity.setSheyingshiEmail(data.get(0));                    //电子邮箱 要改的
//                            sheyingshiEntity.setSheyingshiContent("");//详情和图片
//                            sheyingshiEntity.setCreateTime(date);//时间
                            sheyingshiList.add(sheyingshiEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //摄影师工号
                                if(seachFields.containsKey("sheyingshiUuidNumber")){
                                    List<String> sheyingshiUuidNumber = seachFields.get("sheyingshiUuidNumber");
                                    sheyingshiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> sheyingshiUuidNumber = new ArrayList<>();
                                    sheyingshiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("sheyingshiUuidNumber",sheyingshiUuidNumber);
                                }
                                //摄影师手机号
                                if(seachFields.containsKey("sheyingshiPhone")){
                                    List<String> sheyingshiPhone = seachFields.get("sheyingshiPhone");
                                    sheyingshiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> sheyingshiPhone = new ArrayList<>();
                                    sheyingshiPhone.add(data.get(0));//要改的
                                    seachFields.put("sheyingshiPhone",sheyingshiPhone);
                                }
                                //摄影师身份证号
                                if(seachFields.containsKey("sheyingshiIdNumber")){
                                    List<String> sheyingshiIdNumber = seachFields.get("sheyingshiIdNumber");
                                    sheyingshiIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> sheyingshiIdNumber = new ArrayList<>();
                                    sheyingshiIdNumber.add(data.get(0));//要改的
                                    seachFields.put("sheyingshiIdNumber",sheyingshiIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<SheyingshiEntity> sheyingshiEntities_username = sheyingshiService.selectList(new EntityWrapper<SheyingshiEntity>().in("username", seachFields.get("username")));
                        if(sheyingshiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SheyingshiEntity s:sheyingshiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //摄影师工号
                        List<SheyingshiEntity> sheyingshiEntities_sheyingshiUuidNumber = sheyingshiService.selectList(new EntityWrapper<SheyingshiEntity>().in("sheyingshi_uuid_number", seachFields.get("sheyingshiUuidNumber")));
                        if(sheyingshiEntities_sheyingshiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SheyingshiEntity s:sheyingshiEntities_sheyingshiUuidNumber){
                                repeatFields.add(s.getSheyingshiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [摄影师工号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //摄影师手机号
                        List<SheyingshiEntity> sheyingshiEntities_sheyingshiPhone = sheyingshiService.selectList(new EntityWrapper<SheyingshiEntity>().in("sheyingshi_phone", seachFields.get("sheyingshiPhone")));
                        if(sheyingshiEntities_sheyingshiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SheyingshiEntity s:sheyingshiEntities_sheyingshiPhone){
                                repeatFields.add(s.getSheyingshiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [摄影师手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //摄影师身份证号
                        List<SheyingshiEntity> sheyingshiEntities_sheyingshiIdNumber = sheyingshiService.selectList(new EntityWrapper<SheyingshiEntity>().in("sheyingshi_id_number", seachFields.get("sheyingshiIdNumber")));
                        if(sheyingshiEntities_sheyingshiIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SheyingshiEntity s:sheyingshiEntities_sheyingshiIdNumber){
                                repeatFields.add(s.getSheyingshiIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [摄影师身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        sheyingshiService.insertBatch(sheyingshiList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        SheyingshiEntity sheyingshi = sheyingshiService.selectOne(new EntityWrapper<SheyingshiEntity>().eq("username", username));
        if(sheyingshi==null || !sheyingshi.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(sheyingshi.getId(),username, "sheyingshi", "摄影师");
        R r = R.ok();
        r.put("token", token);
        r.put("role","摄影师");
        r.put("username",sheyingshi.getSheyingshiName());
        r.put("tableName","sheyingshi");
        r.put("userId",sheyingshi.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody SheyingshiEntity sheyingshi){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<SheyingshiEntity> queryWrapper = new EntityWrapper<SheyingshiEntity>()
            .eq("username", sheyingshi.getUsername())
            .or()
            .eq("sheyingshi_phone", sheyingshi.getSheyingshiPhone())
            .or()
            .eq("sheyingshi_id_number", sheyingshi.getSheyingshiIdNumber())
            ;
        SheyingshiEntity sheyingshiEntity = sheyingshiService.selectOne(queryWrapper);
        if(sheyingshiEntity != null)
            return R.error("账户或者摄影师手机号或者摄影师身份证号已经被使用");
        sheyingshi.setCreateTime(new Date());
        sheyingshiService.insert(sheyingshi);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        SheyingshiEntity sheyingshi = new SheyingshiEntity();
        sheyingshi.setPassword("123456");
        sheyingshi.setId(id);
        sheyingshiService.updateById(sheyingshi);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        SheyingshiEntity sheyingshi = sheyingshiService.selectOne(new EntityWrapper<SheyingshiEntity>().eq("username", username));
        if(sheyingshi!=null){
            sheyingshi.setPassword("123456");
            boolean b = sheyingshiService.updateById(sheyingshi);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrSheyingshi(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        SheyingshiEntity sheyingshi = sheyingshiService.selectById(id);
        if(sheyingshi !=null){
            //entity转view
            SheyingshiView view = new SheyingshiView();
            BeanUtils.copyProperties( sheyingshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
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
        PageUtils page = sheyingshiService.queryPage(params);

        //字典表数据转换
        List<SheyingshiView> list =(List<SheyingshiView>)page.getList();
        for(SheyingshiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SheyingshiEntity sheyingshi = sheyingshiService.selectById(id);
            if(sheyingshi !=null){


                //entity转view
                SheyingshiView view = new SheyingshiView();
                BeanUtils.copyProperties( sheyingshi , view );//把实体数据重构到view中

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
    public R add(@RequestBody SheyingshiEntity sheyingshi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,sheyingshi:{}",this.getClass().getName(),sheyingshi.toString());
        Wrapper<SheyingshiEntity> queryWrapper = new EntityWrapper<SheyingshiEntity>()
            .eq("username", sheyingshi.getUsername())
            .or()
            .eq("sheyingshi_phone", sheyingshi.getSheyingshiPhone())
            .or()
            .eq("sheyingshi_id_number", sheyingshi.getSheyingshiIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SheyingshiEntity sheyingshiEntity = sheyingshiService.selectOne(queryWrapper);
        if(sheyingshiEntity==null){
            sheyingshi.setCreateTime(new Date());
        sheyingshi.setPassword("123456");
        sheyingshiService.insert(sheyingshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者摄影师手机号或者摄影师身份证号已经被使用");
        }
    }


}
