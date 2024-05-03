package com.entity.vo;

import com.entity.SheyingshiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 摄影师
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("sheyingshi")
public class SheyingshiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 摄影师工号
     */

    @TableField(value = "sheyingshi_uuid_number")
    private String sheyingshiUuidNumber;


    /**
     * 摄影师姓名
     */

    @TableField(value = "sheyingshi_name")
    private String sheyingshiName;


    /**
     * 摄影师手机号
     */

    @TableField(value = "sheyingshi_phone")
    private String sheyingshiPhone;


    /**
     * 摄影师身份证号
     */

    @TableField(value = "sheyingshi_id_number")
    private String sheyingshiIdNumber;


    /**
     * 摄影师头像
     */

    @TableField(value = "sheyingshi_photo")
    private String sheyingshiPhoto;


    /**
     * 摄影师擅长
     */

    @TableField(value = "sheyingshi_shanchang")
    private String sheyingshiShanchang;


    /**
     * 摄影师预约定金
     */

    @TableField(value = "sheyingshi_dingjin")
    private Double sheyingshiDingjin;


    /**
     * 摄影价格/次
     */

    @TableField(value = "sheyingshi_jiage")
    private Double sheyingshiJiage;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 电子邮箱
     */

    @TableField(value = "sheyingshi_email")
    private String sheyingshiEmail;


    /**
     * 摄影师详细介绍
     */

    @TableField(value = "sheyingshi_content")
    private String sheyingshiContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：摄影师工号
	 */
    public String getSheyingshiUuidNumber() {
        return sheyingshiUuidNumber;
    }


    /**
	 * 获取：摄影师工号
	 */

    public void setSheyingshiUuidNumber(String sheyingshiUuidNumber) {
        this.sheyingshiUuidNumber = sheyingshiUuidNumber;
    }
    /**
	 * 设置：摄影师姓名
	 */
    public String getSheyingshiName() {
        return sheyingshiName;
    }


    /**
	 * 获取：摄影师姓名
	 */

    public void setSheyingshiName(String sheyingshiName) {
        this.sheyingshiName = sheyingshiName;
    }
    /**
	 * 设置：摄影师手机号
	 */
    public String getSheyingshiPhone() {
        return sheyingshiPhone;
    }


    /**
	 * 获取：摄影师手机号
	 */

    public void setSheyingshiPhone(String sheyingshiPhone) {
        this.sheyingshiPhone = sheyingshiPhone;
    }
    /**
	 * 设置：摄影师身份证号
	 */
    public String getSheyingshiIdNumber() {
        return sheyingshiIdNumber;
    }


    /**
	 * 获取：摄影师身份证号
	 */

    public void setSheyingshiIdNumber(String sheyingshiIdNumber) {
        this.sheyingshiIdNumber = sheyingshiIdNumber;
    }
    /**
	 * 设置：摄影师头像
	 */
    public String getSheyingshiPhoto() {
        return sheyingshiPhoto;
    }


    /**
	 * 获取：摄影师头像
	 */

    public void setSheyingshiPhoto(String sheyingshiPhoto) {
        this.sheyingshiPhoto = sheyingshiPhoto;
    }
    /**
	 * 设置：摄影师擅长
	 */
    public String getSheyingshiShanchang() {
        return sheyingshiShanchang;
    }


    /**
	 * 获取：摄影师擅长
	 */

    public void setSheyingshiShanchang(String sheyingshiShanchang) {
        this.sheyingshiShanchang = sheyingshiShanchang;
    }
    /**
	 * 设置：摄影师预约定金
	 */
    public Double getSheyingshiDingjin() {
        return sheyingshiDingjin;
    }


    /**
	 * 获取：摄影师预约定金
	 */

    public void setSheyingshiDingjin(Double sheyingshiDingjin) {
        this.sheyingshiDingjin = sheyingshiDingjin;
    }
    /**
	 * 设置：摄影价格/次
	 */
    public Double getSheyingshiJiage() {
        return sheyingshiJiage;
    }


    /**
	 * 获取：摄影价格/次
	 */

    public void setSheyingshiJiage(Double sheyingshiJiage) {
        this.sheyingshiJiage = sheyingshiJiage;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getSheyingshiEmail() {
        return sheyingshiEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setSheyingshiEmail(String sheyingshiEmail) {
        this.sheyingshiEmail = sheyingshiEmail;
    }
    /**
	 * 设置：摄影师详细介绍
	 */
    public String getSheyingshiContent() {
        return sheyingshiContent;
    }


    /**
	 * 获取：摄影师详细介绍
	 */

    public void setSheyingshiContent(String sheyingshiContent) {
        this.sheyingshiContent = sheyingshiContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
