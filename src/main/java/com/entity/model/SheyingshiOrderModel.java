package com.entity.model;

import com.entity.SheyingshiOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 摄影师预约
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class SheyingshiOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 预约流水号
     */
    private String sheyingshiOrderUuidNumber;


    /**
     * 摄影师
     */
    private Integer sheyingshiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 拍摄地
     */
    private Integer paishediTypes;


    /**
     * 定金
     */
    private Double sheyingshiOrderTruePrice;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 预约拍摄日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date paisheTime;


    /**
     * 预约拍摄时间段
     */
    private Integer shijianduanTypes;


    /**
     * 预约状态
     */
    private Integer sheyingshiOrderTypes;


    /**
     * 创建时间 show3
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
	 * 获取：预约流水号
	 */
    public String getSheyingshiOrderUuidNumber() {
        return sheyingshiOrderUuidNumber;
    }


    /**
	 * 设置：预约流水号
	 */
    public void setSheyingshiOrderUuidNumber(String sheyingshiOrderUuidNumber) {
        this.sheyingshiOrderUuidNumber = sheyingshiOrderUuidNumber;
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
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
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
	 * 获取：定金
	 */
    public Double getSheyingshiOrderTruePrice() {
        return sheyingshiOrderTruePrice;
    }


    /**
	 * 设置：定金
	 */
    public void setSheyingshiOrderTruePrice(Double sheyingshiOrderTruePrice) {
        this.sheyingshiOrderTruePrice = sheyingshiOrderTruePrice;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：预约拍摄日期
	 */
    public Date getPaisheTime() {
        return paisheTime;
    }


    /**
	 * 设置：预约拍摄日期
	 */
    public void setPaisheTime(Date paisheTime) {
        this.paisheTime = paisheTime;
    }
    /**
	 * 获取：预约拍摄时间段
	 */
    public Integer getShijianduanTypes() {
        return shijianduanTypes;
    }


    /**
	 * 设置：预约拍摄时间段
	 */
    public void setShijianduanTypes(Integer shijianduanTypes) {
        this.shijianduanTypes = shijianduanTypes;
    }
    /**
	 * 获取：预约状态
	 */
    public Integer getSheyingshiOrderTypes() {
        return sheyingshiOrderTypes;
    }


    /**
	 * 设置：预约状态
	 */
    public void setSheyingshiOrderTypes(Integer sheyingshiOrderTypes) {
        this.sheyingshiOrderTypes = sheyingshiOrderTypes;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
