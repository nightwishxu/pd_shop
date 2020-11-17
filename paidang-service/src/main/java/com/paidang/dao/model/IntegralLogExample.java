package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class IntegralLogExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public IntegralLogExample(){
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

		public Criteria andIntegralIsNull() {
			addCriterion("integral is null");
			return (Criteria) this;
		}

		public Criteria andIntegralIsNotNull() {
			addCriterion("integral is not null");
			return (Criteria) this;
		}

		public Criteria andIntegralEqualTo(java.math.BigDecimal value) {
			addCriterion("integral =", value, "integral");
			return (Criteria) this;
		}

		public Criteria andIntegralNotEqualTo(java.math.BigDecimal value) {
			addCriterion("integral <>", value, "integral");
			return (Criteria) this;
		}

		public Criteria andIntegralGreaterThan(java.math.BigDecimal value) {
			addCriterion("integral >", value, "integral");
			return (Criteria) this;
		}

		public Criteria andIntegralGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("integral >=", value, "integral");
			return (Criteria) this;
		}

		public Criteria andIntegralLessThan(java.math.BigDecimal value) {
			addCriterion("integral <", value, "integral");
			return (Criteria) this;
		}

		public Criteria andIntegralLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("integral <=", value, "integral");
			return (Criteria) this;
		}

		public Criteria andIntegralIn(List<java.math.BigDecimal> values) {
			addCriterion("integral in", values, "integral");
			return (Criteria) this;
		}

		public Criteria andIntegralNotIn(List<java.math.BigDecimal> values) {
			addCriterion("integral not in", values, "integral");
			return (Criteria) this;
		}

		public Criteria andIntegralBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("integral between", value1, value2, "integral");
			return (Criteria) this;
		}

		public Criteria andIntegralNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("integral not between", value1, value2, "integral");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andLogIdIsNull() {
			addCriterion("log_id is null");
			return (Criteria) this;
		}

		public Criteria andLogIdIsNotNull() {
			addCriterion("log_id is not null");
			return (Criteria) this;
		}

		public Criteria andLogIdEqualTo(String value) {
			addCriterion("log_id =", value, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdNotEqualTo(String value) {
			addCriterion("log_id <>", value, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdGreaterThan(String value) {
			addCriterion("log_id >", value, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdGreaterThanOrEqualTo(String value) {
			addCriterion("log_id >=", value, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdLessThan(String value) {
			addCriterion("log_id <", value, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdLessThanOrEqualTo(String value) {
			addCriterion("log_id <=", value, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdLike(String value) {
			addCriterion("log_id like", value, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdNotLike(String value) {
			addCriterion("log_id not like", value, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdIn(List<String> values) {
			addCriterion("log_id in", values, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdNotIn(List<String> values) {
			addCriterion("log_id not in", values, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdBetween(String value1, String value2) {
			addCriterion("log_id between", value1, value2, "logId");
			return (Criteria) this;
		}

		public Criteria andLogIdNotBetween(String value1, String value2) {
			addCriterion("log_id not between", value1, value2, "logId");
			return (Criteria) this;
		}

		public Criteria andInfoIsNull() {
			addCriterion("info is null");
			return (Criteria) this;
		}

		public Criteria andInfoIsNotNull() {
			addCriterion("info is not null");
			return (Criteria) this;
		}

		public Criteria andInfoEqualTo(String value) {
			addCriterion("info =", value, "info");
			return (Criteria) this;
		}

		public Criteria andInfoNotEqualTo(String value) {
			addCriterion("info <>", value, "info");
			return (Criteria) this;
		}

		public Criteria andInfoGreaterThan(String value) {
			addCriterion("info >", value, "info");
			return (Criteria) this;
		}

		public Criteria andInfoGreaterThanOrEqualTo(String value) {
			addCriterion("info >=", value, "info");
			return (Criteria) this;
		}

		public Criteria andInfoLessThan(String value) {
			addCriterion("info <", value, "info");
			return (Criteria) this;
		}

		public Criteria andInfoLessThanOrEqualTo(String value) {
			addCriterion("info <=", value, "info");
			return (Criteria) this;
		}

		public Criteria andInfoLike(String value) {
			addCriterion("info like", value, "info");
			return (Criteria) this;
		}

		public Criteria andInfoNotLike(String value) {
			addCriterion("info not like", value, "info");
			return (Criteria) this;
		}

		public Criteria andInfoIn(List<String> values) {
			addCriterion("info in", values, "info");
			return (Criteria) this;
		}

		public Criteria andInfoNotIn(List<String> values) {
			addCriterion("info not in", values, "info");
			return (Criteria) this;
		}

		public Criteria andInfoBetween(String value1, String value2) {
			addCriterion("info between", value1, value2, "info");
			return (Criteria) this;
		}

		public Criteria andInfoNotBetween(String value1, String value2) {
			addCriterion("info not between", value1, value2, "info");
			return (Criteria) this;
		}

		public Criteria andCreateAccountIsNull() {
			addCriterion("create_account is null");
			return (Criteria) this;
		}

		public Criteria andCreateAccountIsNotNull() {
			addCriterion("create_account is not null");
			return (Criteria) this;
		}

		public Criteria andCreateAccountEqualTo(String value) {
			addCriterion("create_account =", value, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountNotEqualTo(String value) {
			addCriterion("create_account <>", value, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountGreaterThan(String value) {
			addCriterion("create_account >", value, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountGreaterThanOrEqualTo(String value) {
			addCriterion("create_account >=", value, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountLessThan(String value) {
			addCriterion("create_account <", value, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountLessThanOrEqualTo(String value) {
			addCriterion("create_account <=", value, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountLike(String value) {
			addCriterion("create_account like", value, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountNotLike(String value) {
			addCriterion("create_account not like", value, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountIn(List<String> values) {
			addCriterion("create_account in", values, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountNotIn(List<String> values) {
			addCriterion("create_account not in", values, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountBetween(String value1, String value2) {
			addCriterion("create_account between", value1, value2, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateAccountNotBetween(String value1, String value2) {
			addCriterion("create_account not between", value1, value2, "createAccount");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("create_time is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("create_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(java.util.Date value) {
			addCriterion("create_time =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(java.util.Date value) {
			addCriterion("create_time <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(java.util.Date value) {
			addCriterion("create_time >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("create_time >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(java.util.Date value) {
			addCriterion("create_time <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("create_time <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<java.util.Date> values) {
			addCriterion("create_time in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<java.util.Date> values) {
			addCriterion("create_time not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("create_time between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("create_time not between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andAccountTypeIsNull() {
			addCriterion("account_type is null");
			return (Criteria) this;
		}

		public Criteria andAccountTypeIsNotNull() {
			addCriterion("account_type is not null");
			return (Criteria) this;
		}

		public Criteria andAccountTypeEqualTo(Integer value) {
			addCriterion("account_type =", value, "accountType");
			return (Criteria) this;
		}

		public Criteria andAccountTypeNotEqualTo(Integer value) {
			addCriterion("account_type <>", value, "accountType");
			return (Criteria) this;
		}

		public Criteria andAccountTypeGreaterThan(Integer value) {
			addCriterion("account_type >", value, "accountType");
			return (Criteria) this;
		}

		public Criteria andAccountTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("account_type >=", value, "accountType");
			return (Criteria) this;
		}

		public Criteria andAccountTypeLessThan(Integer value) {
			addCriterion("account_type <", value, "accountType");
			return (Criteria) this;
		}

		public Criteria andAccountTypeLessThanOrEqualTo(Integer value) {
			addCriterion("account_type <=", value, "accountType");
			return (Criteria) this;
		}

		public Criteria andAccountTypeIn(List<Integer> values) {
			addCriterion("account_type in", values, "accountType");
			return (Criteria) this;
		}

		public Criteria andAccountTypeNotIn(List<Integer> values) {
			addCriterion("account_type not in", values, "accountType");
			return (Criteria) this;
		}

		public Criteria andAccountTypeBetween(Integer value1, Integer value2) {
			addCriterion("account_type between", value1, value2, "accountType");
			return (Criteria) this;
		}

		public Criteria andAccountTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("account_type not between", value1, value2, "accountType");
			return (Criteria) this;
		}

		public Criteria andIntegralNewIsNull() {
			addCriterion("integral_new is null");
			return (Criteria) this;
		}

		public Criteria andIntegralNewIsNotNull() {
			addCriterion("integral_new is not null");
			return (Criteria) this;
		}

		public Criteria andIntegralNewEqualTo(java.math.BigDecimal value) {
			addCriterion("integral_new =", value, "integralNew");
			return (Criteria) this;
		}

		public Criteria andIntegralNewNotEqualTo(java.math.BigDecimal value) {
			addCriterion("integral_new <>", value, "integralNew");
			return (Criteria) this;
		}

		public Criteria andIntegralNewGreaterThan(java.math.BigDecimal value) {
			addCriterion("integral_new >", value, "integralNew");
			return (Criteria) this;
		}

		public Criteria andIntegralNewGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("integral_new >=", value, "integralNew");
			return (Criteria) this;
		}

		public Criteria andIntegralNewLessThan(java.math.BigDecimal value) {
			addCriterion("integral_new <", value, "integralNew");
			return (Criteria) this;
		}

		public Criteria andIntegralNewLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("integral_new <=", value, "integralNew");
			return (Criteria) this;
		}

		public Criteria andIntegralNewIn(List<java.math.BigDecimal> values) {
			addCriterion("integral_new in", values, "integralNew");
			return (Criteria) this;
		}

		public Criteria andIntegralNewNotIn(List<java.math.BigDecimal> values) {
			addCriterion("integral_new not in", values, "integralNew");
			return (Criteria) this;
		}

		public Criteria andIntegralNewBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("integral_new between", value1, value2, "integralNew");
			return (Criteria) this;
		}

		public Criteria andIntegralNewNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("integral_new not between", value1, value2, "integralNew");
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