package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 作品
 *
 * @author 
 * @email
 */
@TableName("shangpin")
public class ShangpinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShangpinEntity() {

	}

	public ShangpinEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 摄影师
     */
    @TableField(value = "sheyingshi_id")

    private Integer sheyingshiId;


    /**
     * 作品名称
     */
    @TableField(value = "shangpin_name")

    private String shangpinName;


    /**
     * 作品编号
     */
    @TableField(value = "shangpin_uuid_number")

    private String shangpinUuidNumber;


    /**
     * 作品照片
     */
    @TableField(value = "shangpin_photo")

    private String shangpinPhoto;


    /**
     * 拍摄时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "paishe_time")

    private Date paisheTime;


    /**
     * 拍摄地
     */
    @TableField(value = "paishedi_types")

    private Integer paishediTypes;


    /**
     * 作品类型
     */
    @TableField(value = "shangpin_types")

    private Integer shangpinTypes;


    /**
     * 点击次数
     */
    @TableField(value = "shangpin_clicknum")

    private Integer shangpinClicknum;


    /**
     * 作品介绍
     */
    @TableField(value = "shangpin_content")

    private String shangpinContent;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "shangpin_delete")

    private Integer shangpinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：摄影师
	 */
    public Integer getSheyingshiId() {
        return sheyingshiId;
    }
    /**
	 * 获取：摄影师
	 */

    public void setSheyingshiId(Integer sheyingshiId) {
        this.sheyingshiId = sheyingshiId;
    }
    /**
	 * 设置：作品名称
	 */
    public String getShangpinName() {
        return shangpinName;
    }
    /**
	 * 获取：作品名称
	 */

    public void setShangpinName(String shangpinName) {
        this.shangpinName = shangpinName;
    }
    /**
	 * 设置：作品编号
	 */
    public String getShangpinUuidNumber() {
        return shangpinUuidNumber;
    }
    /**
	 * 获取：作品编号
	 */

    public void setShangpinUuidNumber(String shangpinUuidNumber) {
        this.shangpinUuidNumber = shangpinUuidNumber;
    }
    /**
	 * 设置：作品照片
	 */
    public String getShangpinPhoto() {
        return shangpinPhoto;
    }
    /**
	 * 获取：作品照片
	 */

    public void setShangpinPhoto(String shangpinPhoto) {
        this.shangpinPhoto = shangpinPhoto;
    }
    /**
	 * 设置：拍摄时间
	 */
    public Date getPaisheTime() {
        return paisheTime;
    }
    /**
	 * 获取：拍摄时间
	 */

    public void setPaisheTime(Date paisheTime) {
        this.paisheTime = paisheTime;
    }
    /**
	 * 设置：拍摄地
	 */
    public Integer getPaishediTypes() {
        return paishediTypes;
    }
    /**
	 * 获取：拍摄地
	 */

    public void setPaishediTypes(Integer paishediTypes) {
        this.paishediTypes = paishediTypes;
    }
    /**
	 * 设置：作品类型
	 */
    public Integer getShangpinTypes() {
        return shangpinTypes;
    }
    /**
	 * 获取：作品类型
	 */

    public void setShangpinTypes(Integer shangpinTypes) {
        this.shangpinTypes = shangpinTypes;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getShangpinClicknum() {
        return shangpinClicknum;
    }
    /**
	 * 获取：点击次数
	 */

    public void setShangpinClicknum(Integer shangpinClicknum) {
        this.shangpinClicknum = shangpinClicknum;
    }
    /**
	 * 设置：作品介绍
	 */
    public String getShangpinContent() {
        return shangpinContent;
    }
    /**
	 * 获取：作品介绍
	 */

    public void setShangpinContent(String shangpinContent) {
        this.shangpinContent = shangpinContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getShangpinDelete() {
        return shangpinDelete;
    }
    /**
	 * 获取：逻辑删除
	 */

    public void setShangpinDelete(Integer shangpinDelete) {
        this.shangpinDelete = shangpinDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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

    @Override
    public String toString() {
        return "Shangpin{" +
            "id=" + id +
            ", sheyingshiId=" + sheyingshiId +
            ", shangpinName=" + shangpinName +
            ", shangpinUuidNumber=" + shangpinUuidNumber +
            ", shangpinPhoto=" + shangpinPhoto +
            ", paisheTime=" + paisheTime +
            ", paishediTypes=" + paishediTypes +
            ", shangpinTypes=" + shangpinTypes +
            ", shangpinClicknum=" + shangpinClicknum +
            ", shangpinContent=" + shangpinContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", shangpinDelete=" + shangpinDelete +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
