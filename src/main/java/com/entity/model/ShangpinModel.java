package com.entity.model;

import com.entity.ShangpinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 作品
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShangpinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 摄影师
     */
    private Integer sheyingshiId;


    /**
     * 作品名称
     */
    private String shangpinName;


    /**
     * 作品编号
     */
    private String shangpinUuidNumber;


    /**
     * 作品照片
     */
    private String shangpinPhoto;


    /**
     * 拍摄时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date paisheTime;


    /**
     * 拍摄地
     */
    private Integer paishediTypes;


    /**
     * 作品类型
     */
    private Integer shangpinTypes;


    /**
     * 点击次数
     */
    private Integer shangpinClicknum;


    /**
     * 作品介绍
     */
    private String shangpinContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer shangpinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：摄影师
	 */
    public Integer getSheyingshiId() {
        return sheyingshiId;
    }


    /**
	 * 设置：摄影师
	 */
    public void setSheyingshiId(Integer sheyingshiId) {
        this.sheyingshiId = sheyingshiId;
    }
    /**
	 * 获取：作品名称
	 */
    public String getShangpinName() {
        return shangpinName;
    }


    /**
	 * 设置：作品名称
	 */
    public void setShangpinName(String shangpinName) {
        this.shangpinName = shangpinName;
    }
    /**
	 * 获取：作品编号
	 */
    public String getShangpinUuidNumber() {
        return shangpinUuidNumber;
    }


    /**
	 * 设置：作品编号
	 */
    public void setShangpinUuidNumber(String shangpinUuidNumber) {
        this.shangpinUuidNumber = shangpinUuidNumber;
    }
    /**
	 * 获取：作品照片
	 */
    public String getShangpinPhoto() {
        return shangpinPhoto;
    }


    /**
	 * 设置：作品照片
	 */
    public void setShangpinPhoto(String shangpinPhoto) {
        this.shangpinPhoto = shangpinPhoto;
    }
    /**
	 * 获取：拍摄时间
	 */
    public Date getPaisheTime() {
        return paisheTime;
    }


    /**
	 * 设置：拍摄时间
	 */
    public void setPaisheTime(Date paisheTime) {
        this.paisheTime = paisheTime;
    }
    /**
	 * 获取：拍摄地
	 */
    public Integer getPaishediTypes() {
        return paishediTypes;
    }


    /**
	 * 设置：拍摄地
	 */
    public void setPaishediTypes(Integer paishediTypes) {
        this.paishediTypes = paishediTypes;
    }
    /**
	 * 获取：作品类型
	 */
    public Integer getShangpinTypes() {
        return shangpinTypes;
    }


    /**
	 * 设置：作品类型
	 */
    public void setShangpinTypes(Integer shangpinTypes) {
        this.shangpinTypes = shangpinTypes;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getShangpinClicknum() {
        return shangpinClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setShangpinClicknum(Integer shangpinClicknum) {
        this.shangpinClicknum = shangpinClicknum;
    }
    /**
	 * 获取：作品介绍
	 */
    public String getShangpinContent() {
        return shangpinContent;
    }


    /**
	 * 设置：作品介绍
	 */
    public void setShangpinContent(String shangpinContent) {
        this.shangpinContent = shangpinContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getShangpinDelete() {
        return shangpinDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setShangpinDelete(Integer shangpinDelete) {
        this.shangpinDelete = shangpinDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
