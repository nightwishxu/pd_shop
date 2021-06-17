package com.paidang.dao.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 优惠券对象 p_coupon
 *
 * @author WBC
 * @date 2021-06-10
 */
public class Coupon extends BaseEntity {
	private static final Long serialVersionUID = 1L;

	/** $column.columnComment */
	private Integer id;

	/**
	 *满
	 */
	@ApiModelProperty(value = "满多少")
	@ApiParam(value="满多少")
	@Column(name = "id")
	private java.math.BigDecimal full;

	@ApiModelProperty(value = "机构id")
	@ApiParam(value="机构id")
	@Column(name = "org_id")
	private Integer orgId;

	@ApiModelProperty(value = "机构名")
	@ApiParam(value="机构名")
	@Column(name = "org_name")
	private String orgName;

	/**
	 *减
	 */
	@ApiModelProperty(value = "减多少")
	@ApiParam(value="减多少")
	@Column(name = "value")
	private java.math.BigDecimal value;

	@ApiModelProperty(value = "发放张数")
	@ApiParam(value="发放张数")
	@Column(name = "num")
	private Integer num;

	@ApiModelProperty(value = "每人限领")
	@ApiParam(value="每人限领")
	@Column(name = "per_num")
	private Integer perNum;

	@ApiModelProperty(value = "开始使用日期")
	@ApiParam(value="开始使用日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_day")
	private Date startDay;

	@ApiModelProperty(value = "结束使用日期")
	@ApiParam(value="结束使用日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_day")
	private Date endDay;

	@ApiModelProperty(value = "0普通券1私密券")
	@ApiParam(value="0普通券1私密券")
	@Column(name = "form")
	private Integer form;

	@ApiModelProperty(value = "备注")
	@ApiParam(value="备注")
	@Column(name = "remark")
	private String remark;

	@ApiParam(value="0 正常，1 停用")
	@Column(name = "status")
	private Integer status; // 0 正常，1 停用

	/** $column.columnComment */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
	private Date modifyTime;

	/** 领取后有效天数 */
	@Excel(name = "领取后有效天数")
	private Integer days;

	/** 视频id--若为0则所有 */
	@Excel(name = "视频id--若为0则所有")
	private Integer fid;

	/** 1新用户注册2看视频3进入首页 */
	@Excel(name = "1新用户注册2看视频3进入首页")
	private Integer type;

	@ApiModelProperty(value = "私密券用户手机号")
	@ApiParam(value="私密券用户手机号")
	private String phone;

	@Transient
	private String today;

	@Transient
	private Integer userId;

	@Transient
	private Integer userState;

	@Transient
	private String startDayStr;

	@Transient
	private String endDayStr;

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}
	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
	}

	public Integer getOrgId()
	{
		return orgId;
	}
	public void setOrgName(String orgName)
	{
		this.orgName = orgName;
	}

	public String getOrgName()
	{
		return orgName;
	}
	public void setFull(BigDecimal full)
	{
		this.full = full;
	}

	public BigDecimal getFull()
	{
		return full;
	}
	public void setValue(BigDecimal value)
	{
		this.value = value;
	}

	public BigDecimal getValue()
	{
		return value;
	}
	public void setNum(Integer num)
	{
		this.num = num;
	}

	public Integer getNum()
	{
		return num;
	}
	public void setPerNum(Integer perNum)
	{
		this.perNum = perNum;
	}

	public Integer getPerNum()
	{
		return perNum;
	}
	public void setStartDay(Date startDay)
	{
		this.startDay = startDay;
	}

	public Date getStartDay()
	{
		return startDay;
	}
	public void setEndDay(Date endDay)
	{
		this.endDay = endDay;
	}

	public Date getEndDay()
	{
		return endDay;
	}
	public void setForm(Integer form)
	{
		this.form = form;
	}

	public Integer getForm()
	{
		return form;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}
	public void setModifyTime(Date modifyTime)
	{
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime()
	{
		return modifyTime;
	}
	public void setDays(Integer days)
	{
		this.days = days;
	}

	public Integer getDays()
	{
		return days;
	}
	public void setFid(Integer fid)
	{
		this.fid = fid;
	}

	public Integer getFid()
	{
		return fid;
	}
	public void setType(Integer type)
	{
		this.type = type;
	}

	public Integer getType()
	{
		return type;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("orgId", getOrgId())
				.append("orgName", getOrgName())
				.append("full", getFull())
				.append("value", getValue())
				.append("num", getNum())
				.append("perNum", getPerNum())
				.append("startDay", getStartDay())
				.append("endDay", getEndDay())
				.append("form", getForm())
				.append("remark", getRemark())
				.append("status", getStatus())
				.append("createTime", getCreateTime())
				.append("modifyTime", getModifyTime())
				.append("days", getDays())
				.append("fid", getFid())
				.append("type", getType())
				.toString();
	}

	public String getPhone() {
		return phone;
	}

	public String getToday() {
		return today;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public String getStartDayStr() {
		return startDayStr;
	}

	public void setStartDayStr(String startDayStr) {
		this.startDayStr = startDayStr;
	}

	public String getEndDayStr() {
		return endDayStr;
	}

	public void setEndDayStr(String endDayStr) {
		this.endDayStr = endDayStr;
	}
}