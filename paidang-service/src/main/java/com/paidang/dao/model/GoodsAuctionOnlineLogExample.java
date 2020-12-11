package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class GoodsAuctionOnlineLogExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public GoodsAuctionOnlineLogExample(){
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

		public Criteria andGoodsIdIsNull() {
			addCriterion("goods_id is null");
			return (Criteria) this;
		}

		public Criteria andGoodsIdIsNotNull() {
			addCriterion("goods_id is not null");
			return (Criteria) this;
		}

		public Criteria andGoodsIdEqualTo(Integer value) {
			addCriterion("goods_id =", value, "goodsId");
			return (Criteria) this;
		}

		public Criteria andGoodsIdNotEqualTo(Integer value) {
			addCriterion("goods_id <>", value, "goodsId");
			return (Criteria) this;
		}

		public Criteria andGoodsIdGreaterThan(Integer value) {
			addCriterion("goods_id >", value, "goodsId");
			return (Criteria) this;
		}

		public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("goods_id >=", value, "goodsId");
			return (Criteria) this;
		}

		public Criteria andGoodsIdLessThan(Integer value) {
			addCriterion("goods_id <", value, "goodsId");
			return (Criteria) this;
		}

		public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
			addCriterion("goods_id <=", value, "goodsId");
			return (Criteria) this;
		}

		public Criteria andGoodsIdIn(List<Integer> values) {
			addCriterion("goods_id in", values, "goodsId");
			return (Criteria) this;
		}

		public Criteria andGoodsIdNotIn(List<Integer> values) {
			addCriterion("goods_id not in", values, "goodsId");
			return (Criteria) this;
		}

		public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
			addCriterion("goods_id between", value1, value2, "goodsId");
			return (Criteria) this;
		}

		public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
			addCriterion("goods_id not between", value1, value2, "goodsId");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("status not between", value1, value2, "status");
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

		public Criteria andModifyTimeIsNull() {
			addCriterion("modify_time is null");
			return (Criteria) this;
		}

		public Criteria andModifyTimeIsNotNull() {
			addCriterion("modify_time is not null");
			return (Criteria) this;
		}

		public Criteria andModifyTimeEqualTo(java.util.Date value) {
			addCriterion("modify_time =", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeNotEqualTo(java.util.Date value) {
			addCriterion("modify_time <>", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeGreaterThan(java.util.Date value) {
			addCriterion("modify_time >", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("modify_time >=", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeLessThan(java.util.Date value) {
			addCriterion("modify_time <", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("modify_time <=", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeIn(List<java.util.Date> values) {
			addCriterion("modify_time in", values, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeNotIn(List<java.util.Date> values) {
			addCriterion("modify_time not in", values, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("modify_time between", value1, value2, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("modify_time not between", value1, value2, "modifyTime");
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

		public Criteria andModifyAccountIsNull() {
			addCriterion("modify_account is null");
			return (Criteria) this;
		}

		public Criteria andModifyAccountIsNotNull() {
			addCriterion("modify_account is not null");
			return (Criteria) this;
		}

		public Criteria andModifyAccountEqualTo(String value) {
			addCriterion("modify_account =", value, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountNotEqualTo(String value) {
			addCriterion("modify_account <>", value, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountGreaterThan(String value) {
			addCriterion("modify_account >", value, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountGreaterThanOrEqualTo(String value) {
			addCriterion("modify_account >=", value, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountLessThan(String value) {
			addCriterion("modify_account <", value, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountLessThanOrEqualTo(String value) {
			addCriterion("modify_account <=", value, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountLike(String value) {
			addCriterion("modify_account like", value, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountNotLike(String value) {
			addCriterion("modify_account not like", value, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountIn(List<String> values) {
			addCriterion("modify_account in", values, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountNotIn(List<String> values) {
			addCriterion("modify_account not in", values, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountBetween(String value1, String value2) {
			addCriterion("modify_account between", value1, value2, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andModifyAccountNotBetween(String value1, String value2) {
			addCriterion("modify_account not between", value1, value2, "modifyAccount");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeIsNull() {
			addCriterion("auction_start_time is null");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeIsNotNull() {
			addCriterion("auction_start_time is not null");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeEqualTo(java.util.Date value) {
			addCriterion("auction_start_time =", value, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeNotEqualTo(java.util.Date value) {
			addCriterion("auction_start_time <>", value, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeGreaterThan(java.util.Date value) {
			addCriterion("auction_start_time >", value, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("auction_start_time >=", value, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeLessThan(java.util.Date value) {
			addCriterion("auction_start_time <", value, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("auction_start_time <=", value, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeIn(List<java.util.Date> values) {
			addCriterion("auction_start_time in", values, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeNotIn(List<java.util.Date> values) {
			addCriterion("auction_start_time not in", values, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("auction_start_time between", value1, value2, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionStartTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("auction_start_time not between", value1, value2, "auctionStartTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeIsNull() {
			addCriterion("auction_end_time is null");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeIsNotNull() {
			addCriterion("auction_end_time is not null");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeEqualTo(java.util.Date value) {
			addCriterion("auction_end_time =", value, "auctionEndTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeNotEqualTo(java.util.Date value) {
			addCriterion("auction_end_time <>", value, "auctionEndTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeGreaterThan(java.util.Date value) {
			addCriterion("auction_end_time >", value, "auctionEndTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("auction_end_time >=", value, "auctionEndTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeLessThan(java.util.Date value) {
			addCriterion("auction_end_time <", value, "auctionEndTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("auction_end_time <=", value, "auctionEndTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeIn(List<java.util.Date> values) {
			addCriterion("auction_end_time in", values, "auctionEndTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeNotIn(List<java.util.Date> values) {
			addCriterion("auction_end_time not in", values, "auctionEndTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("auction_end_time between", value1, value2, "auctionEndTime");
			return (Criteria) this;
		}

		public Criteria andAuctionEndTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("auction_end_time not between", value1, value2, "auctionEndTime");
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