package com.demo.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_salary")
@ApiModel(value="Salary对象", description="")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "基本工资")
    @TableField("basicSalary")
    private BigDecimal basicSalary;

    @ApiModelProperty(value = "奖金")
    private BigDecimal bonus;

    @ApiModelProperty(value = "午餐补助")
    @TableField("lunchSalary")
    private BigDecimal lunchSalary;

    @ApiModelProperty(value = "交通补助")
    @TableField("trafficSalary")
    private BigDecimal trafficSalary;

    @ApiModelProperty(value = "应发工资")
    @TableField("allSalary")
    private BigDecimal allSalary;

    @ApiModelProperty(value = "养老金基数")
    @TableField("pensionBase")
    private BigDecimal pensionBase;

    @ApiModelProperty(value = "养老金比率")
    @TableField("pensionPer")
    private BigDecimal pensionPer;

    @ApiModelProperty(value = "启用时间")
    @TableField("createDate")
    private Date createDate;

    @ApiModelProperty(value = "医疗基数")
    @TableField("medicalBase")
    private BigDecimal medicalBase;

    @ApiModelProperty(value = "医疗保险比率")
    @TableField("medicalPer")
    private BigDecimal medicalPer;

    @ApiModelProperty(value = "公积金基数")
    @TableField("accumulationFundBase")
    private BigDecimal accumulationFundBase;

    @ApiModelProperty(value = "公积金比率")
    @TableField("accumulationFundPer")
    private BigDecimal accumulationFundPer;

    @ApiModelProperty(value = "名称")
    private String name;


}
