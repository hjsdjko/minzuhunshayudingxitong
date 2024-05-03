package com.entity.view;

import com.entity.SheyingshiOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 摄影师预约
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("sheyingshi_order")
public class SheyingshiOrderView extends SheyingshiOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 拍摄地的值
		*/
		private String paishediValue;
		/**
		* 预约拍摄时间段的值
		*/
		private String shijianduanValue;
		/**
		* 预约状态的值
		*/
		private String sheyingshiOrderValue;



		//级联表 yonghu
			/**
			* 用户唯一编号
			*/
			private String yonghuUuidNumber;
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;

		//级联表 sheyingshi
			/**
			* 摄影师工号
			*/
			private String sheyingshiUuidNumber;
			/**
			* 摄影师姓名
			*/
			private String sheyingshiName;
			/**
			* 摄影师手机号
			*/
			private String sheyingshiPhone;
			/**
			* 摄影师身份证号
			*/
			private String sheyingshiIdNumber;
			/**
			* 摄影师头像
			*/
			private String sheyingshiPhoto;
			/**
			* 摄影师擅长
			*/
			private String sheyingshiShanchang;
			/**
			* 摄影师预约定金
			*/
			private Double sheyingshiDingjin;
			/**
			* 摄影价格/次
			*/
			private Double sheyingshiJiage;
			/**
			* 电子邮箱
			*/
			private String sheyingshiEmail;
			/**
			* 摄影师详细介绍
			*/
			private String sheyingshiContent;

	public SheyingshiOrderView() {

	}

	public SheyingshiOrderView(SheyingshiOrderEntity sheyingshiOrderEntity) {
		try {
			BeanUtils.copyProperties(this, sheyingshiOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 拍摄地的值
			*/
			public String getPaishediValue() {
				return paishediValue;
			}
			/**
			* 设置： 拍摄地的值
			*/
			public void setPaishediValue(String paishediValue) {
				this.paishediValue = paishediValue;
			}
			/**
			* 获取： 预约拍摄时间段的值
			*/
			public String getShijianduanValue() {
				return shijianduanValue;
			}
			/**
			* 设置： 预约拍摄时间段的值
			*/
			public void setShijianduanValue(String shijianduanValue) {
				this.shijianduanValue = shijianduanValue;
			}
			/**
			* 获取： 预约状态的值
			*/
			public String getSheyingshiOrderValue() {
				return sheyingshiOrderValue;
			}
			/**
			* 设置： 预约状态的值
			*/
			public void setSheyingshiOrderValue(String sheyingshiOrderValue) {
				this.sheyingshiOrderValue = sheyingshiOrderValue;
			}
































				//级联表的get和set yonghu

					/**
					* 获取： 用户唯一编号
					*/
					public String getYonghuUuidNumber() {
						return yonghuUuidNumber;
					}
					/**
					* 设置： 用户唯一编号
					*/
					public void setYonghuUuidNumber(String yonghuUuidNumber) {
						this.yonghuUuidNumber = yonghuUuidNumber;
					}

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}

					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}

					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}


				//级联表的get和set sheyingshi

					/**
					* 获取： 摄影师工号
					*/
					public String getSheyingshiUuidNumber() {
						return sheyingshiUuidNumber;
					}
					/**
					* 设置： 摄影师工号
					*/
					public void setSheyingshiUuidNumber(String sheyingshiUuidNumber) {
						this.sheyingshiUuidNumber = sheyingshiUuidNumber;
					}

					/**
					* 获取： 摄影师姓名
					*/
					public String getSheyingshiName() {
						return sheyingshiName;
					}
					/**
					* 设置： 摄影师姓名
					*/
					public void setSheyingshiName(String sheyingshiName) {
						this.sheyingshiName = sheyingshiName;
					}

					/**
					* 获取： 摄影师手机号
					*/
					public String getSheyingshiPhone() {
						return sheyingshiPhone;
					}
					/**
					* 设置： 摄影师手机号
					*/
					public void setSheyingshiPhone(String sheyingshiPhone) {
						this.sheyingshiPhone = sheyingshiPhone;
					}

					/**
					* 获取： 摄影师身份证号
					*/
					public String getSheyingshiIdNumber() {
						return sheyingshiIdNumber;
					}
					/**
					* 设置： 摄影师身份证号
					*/
					public void setSheyingshiIdNumber(String sheyingshiIdNumber) {
						this.sheyingshiIdNumber = sheyingshiIdNumber;
					}

					/**
					* 获取： 摄影师头像
					*/
					public String getSheyingshiPhoto() {
						return sheyingshiPhoto;
					}
					/**
					* 设置： 摄影师头像
					*/
					public void setSheyingshiPhoto(String sheyingshiPhoto) {
						this.sheyingshiPhoto = sheyingshiPhoto;
					}

					/**
					* 获取： 摄影师擅长
					*/
					public String getSheyingshiShanchang() {
						return sheyingshiShanchang;
					}
					/**
					* 设置： 摄影师擅长
					*/
					public void setSheyingshiShanchang(String sheyingshiShanchang) {
						this.sheyingshiShanchang = sheyingshiShanchang;
					}

					/**
					* 获取： 摄影师预约定金
					*/
					public Double getSheyingshiDingjin() {
						return sheyingshiDingjin;
					}
					/**
					* 设置： 摄影师预约定金
					*/
					public void setSheyingshiDingjin(Double sheyingshiDingjin) {
						this.sheyingshiDingjin = sheyingshiDingjin;
					}

					/**
					* 获取： 摄影价格/次
					*/
					public Double getSheyingshiJiage() {
						return sheyingshiJiage;
					}
					/**
					* 设置： 摄影价格/次
					*/
					public void setSheyingshiJiage(Double sheyingshiJiage) {
						this.sheyingshiJiage = sheyingshiJiage;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getSheyingshiEmail() {
						return sheyingshiEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setSheyingshiEmail(String sheyingshiEmail) {
						this.sheyingshiEmail = sheyingshiEmail;
					}

					/**
					* 获取： 摄影师详细介绍
					*/
					public String getSheyingshiContent() {
						return sheyingshiContent;
					}
					/**
					* 设置： 摄影师详细介绍
					*/
					public void setSheyingshiContent(String sheyingshiContent) {
						this.sheyingshiContent = sheyingshiContent;
					}




}
