package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class BusinessUserInfoExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public BusinessUserInfoExample(){
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(Integer value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(Integer value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(Integer value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(Integer value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Integer value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<Integer> values) {
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<Integer> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(Integer value1, Integer value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andTotalIsNull() {
			addCriterion("total is null");
			return (Criteria) this;
		}

		public Criteria andTotalIsNotNull() {
			addCriterion("total is not null");
			return (Criteria) this;
		}

		public Criteria andTotalEqualTo(java.math.BigDecimal value) {
			addCriterion("total =", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotEqualTo(java.math.BigDecimal value) {
			addCriterion("total <>", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalGreaterThan(java.math.BigDecimal value) {
			addCriterion("total >", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("total >=", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalLessThan(java.math.BigDecimal value) {
			addCriterion("total <", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("total <=", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalIn(List<java.math.BigDecimal> values) {
			addCriterion("total in", values, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotIn(List<java.math.BigDecimal> values) {
			addCriterion("total not in", values, "total");
			return (Criteria) this;
		}

		public Criteria andTotalBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("total between", value1, value2, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("total not between", value1, value2, "total");
			return (Criteria) this;
		}

		public Criteria andAliAccountIsNull() {
			addCriterion("ali_account is null");
			return (Criteria) this;
		}

		public Criteria andAliAccountIsNotNull() {
			addCriterion("ali_account is not null");
			return (Criteria) this;
		}

		public Criteria andAliAccountEqualTo(String value) {
			addCriterion("ali_account =", value, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountNotEqualTo(String value) {
			addCriterion("ali_account <>", value, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountGreaterThan(String value) {
			addCriterion("ali_account >", value, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountGreaterThanOrEqualTo(String value) {
			addCriterion("ali_account >=", value, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountLessThan(String value) {
			addCriterion("ali_account <", value, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountLessThanOrEqualTo(String value) {
			addCriterion("ali_account <=", value, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountLike(String value) {
			addCriterion("ali_account like", value, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountNotLike(String value) {
			addCriterion("ali_account not like", value, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountIn(List<String> values) {
			addCriterion("ali_account in", values, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountNotIn(List<String> values) {
			addCriterion("ali_account not in", values, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountBetween(String value1, String value2) {
			addCriterion("ali_account between", value1, value2, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andAliAccountNotBetween(String value1, String value2) {
			addCriterion("ali_account not between", value1, value2, "aliAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountIsNull() {
			addCriterion("wx_account is null");
			return (Criteria) this;
		}

		public Criteria andWxAccountIsNotNull() {
			addCriterion("wx_account is not null");
			return (Criteria) this;
		}

		public Criteria andWxAccountEqualTo(String value) {
			addCriterion("wx_account =", value, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountNotEqualTo(String value) {
			addCriterion("wx_account <>", value, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountGreaterThan(String value) {
			addCriterion("wx_account >", value, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountGreaterThanOrEqualTo(String value) {
			addCriterion("wx_account >=", value, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountLessThan(String value) {
			addCriterion("wx_account <", value, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountLessThanOrEqualTo(String value) {
			addCriterion("wx_account <=", value, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountLike(String value) {
			addCriterion("wx_account like", value, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountNotLike(String value) {
			addCriterion("wx_account not like", value, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountIn(List<String> values) {
			addCriterion("wx_account in", values, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountNotIn(List<String> values) {
			addCriterion("wx_account not in", values, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountBetween(String value1, String value2) {
			addCriterion("wx_account between", value1, value2, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andWxAccountNotBetween(String value1, String value2) {
			addCriterion("wx_account not between", value1, value2, "wxAccount");
			return (Criteria) this;
		}

		public Criteria andPayPasswordIsNull() {
			addCriterion("pay_password is null");
			return (Criteria) this;
		}

		public Criteria andPayPasswordIsNotNull() {
			addCriterion("pay_password is not null");
			return (Criteria) this;
		}

		public Criteria andPayPasswordEqualTo(String value) {
			addCriterion("pay_password =", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordNotEqualTo(String value) {
			addCriterion("pay_password <>", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordGreaterThan(String value) {
			addCriterion("pay_password >", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("pay_password >=", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordLessThan(String value) {
			addCriterion("pay_password <", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordLessThanOrEqualTo(String value) {
			addCriterion("pay_password <=", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordLike(String value) {
			addCriterion("pay_password like", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordNotLike(String value) {
			addCriterion("pay_password not like", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordIn(List<String> values) {
			addCriterion("pay_password in", values, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordNotIn(List<String> values) {
			addCriterion("pay_password not in", values, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordBetween(String value1, String value2) {
			addCriterion("pay_password between", value1, value2, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordNotBetween(String value1, String value2) {
			addCriterion("pay_password not between", value1, value2, "payPassword");
			return (Criteria) this;
		}

}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
		return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}