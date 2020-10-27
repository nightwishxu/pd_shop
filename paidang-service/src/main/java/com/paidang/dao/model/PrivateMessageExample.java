package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class PrivateMessageExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public PrivateMessageExample(){
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

		public Criteria andFriendIdIsNull() {
			addCriterion("friend_id is null");
			return (Criteria) this;
		}

		public Criteria andFriendIdIsNotNull() {
			addCriterion("friend_id is not null");
			return (Criteria) this;
		}

		public Criteria andFriendIdEqualTo(Integer value) {
			addCriterion("friend_id =", value, "friendId");
			return (Criteria) this;
		}

		public Criteria andFriendIdNotEqualTo(Integer value) {
			addCriterion("friend_id <>", value, "friendId");
			return (Criteria) this;
		}

		public Criteria andFriendIdGreaterThan(Integer value) {
			addCriterion("friend_id >", value, "friendId");
			return (Criteria) this;
		}

		public Criteria andFriendIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("friend_id >=", value, "friendId");
			return (Criteria) this;
		}

		public Criteria andFriendIdLessThan(Integer value) {
			addCriterion("friend_id <", value, "friendId");
			return (Criteria) this;
		}

		public Criteria andFriendIdLessThanOrEqualTo(Integer value) {
			addCriterion("friend_id <=", value, "friendId");
			return (Criteria) this;
		}

		public Criteria andFriendIdIn(List<Integer> values) {
			addCriterion("friend_id in", values, "friendId");
			return (Criteria) this;
		}

		public Criteria andFriendIdNotIn(List<Integer> values) {
			addCriterion("friend_id not in", values, "friendId");
			return (Criteria) this;
		}

		public Criteria andFriendIdBetween(Integer value1, Integer value2) {
			addCriterion("friend_id between", value1, value2, "friendId");
			return (Criteria) this;
		}

		public Criteria andFriendIdNotBetween(Integer value1, Integer value2) {
			addCriterion("friend_id not between", value1, value2, "friendId");
			return (Criteria) this;
		}

		public Criteria andSenderIdIsNull() {
			addCriterion("sender_id is null");
			return (Criteria) this;
		}

		public Criteria andSenderIdIsNotNull() {
			addCriterion("sender_id is not null");
			return (Criteria) this;
		}

		public Criteria andSenderIdEqualTo(Integer value) {
			addCriterion("sender_id =", value, "senderId");
			return (Criteria) this;
		}

		public Criteria andSenderIdNotEqualTo(Integer value) {
			addCriterion("sender_id <>", value, "senderId");
			return (Criteria) this;
		}

		public Criteria andSenderIdGreaterThan(Integer value) {
			addCriterion("sender_id >", value, "senderId");
			return (Criteria) this;
		}

		public Criteria andSenderIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("sender_id >=", value, "senderId");
			return (Criteria) this;
		}

		public Criteria andSenderIdLessThan(Integer value) {
			addCriterion("sender_id <", value, "senderId");
			return (Criteria) this;
		}

		public Criteria andSenderIdLessThanOrEqualTo(Integer value) {
			addCriterion("sender_id <=", value, "senderId");
			return (Criteria) this;
		}

		public Criteria andSenderIdIn(List<Integer> values) {
			addCriterion("sender_id in", values, "senderId");
			return (Criteria) this;
		}

		public Criteria andSenderIdNotIn(List<Integer> values) {
			addCriterion("sender_id not in", values, "senderId");
			return (Criteria) this;
		}

		public Criteria andSenderIdBetween(Integer value1, Integer value2) {
			addCriterion("sender_id between", value1, value2, "senderId");
			return (Criteria) this;
		}

		public Criteria andSenderIdNotBetween(Integer value1, Integer value2) {
			addCriterion("sender_id not between", value1, value2, "senderId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdIsNull() {
			addCriterion("receiver_id is null");
			return (Criteria) this;
		}

		public Criteria andReceiverIdIsNotNull() {
			addCriterion("receiver_id is not null");
			return (Criteria) this;
		}

		public Criteria andReceiverIdEqualTo(Integer value) {
			addCriterion("receiver_id =", value, "receiverId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdNotEqualTo(Integer value) {
			addCriterion("receiver_id <>", value, "receiverId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdGreaterThan(Integer value) {
			addCriterion("receiver_id >", value, "receiverId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("receiver_id >=", value, "receiverId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdLessThan(Integer value) {
			addCriterion("receiver_id <", value, "receiverId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdLessThanOrEqualTo(Integer value) {
			addCriterion("receiver_id <=", value, "receiverId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdIn(List<Integer> values) {
			addCriterion("receiver_id in", values, "receiverId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdNotIn(List<Integer> values) {
			addCriterion("receiver_id not in", values, "receiverId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdBetween(Integer value1, Integer value2) {
			addCriterion("receiver_id between", value1, value2, "receiverId");
			return (Criteria) this;
		}

		public Criteria andReceiverIdNotBetween(Integer value1, Integer value2) {
			addCriterion("receiver_id not between", value1, value2, "receiverId");
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

		public Criteria andMessageTypeIsNull() {
			addCriterion("message_type is null");
			return (Criteria) this;
		}

		public Criteria andMessageTypeIsNotNull() {
			addCriterion("message_type is not null");
			return (Criteria) this;
		}

		public Criteria andMessageTypeEqualTo(Integer value) {
			addCriterion("message_type =", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeNotEqualTo(Integer value) {
			addCriterion("message_type <>", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeGreaterThan(Integer value) {
			addCriterion("message_type >", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("message_type >=", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeLessThan(Integer value) {
			addCriterion("message_type <", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeLessThanOrEqualTo(Integer value) {
			addCriterion("message_type <=", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeIn(List<Integer> values) {
			addCriterion("message_type in", values, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeNotIn(List<Integer> values) {
			addCriterion("message_type not in", values, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeBetween(Integer value1, Integer value2) {
			addCriterion("message_type between", value1, value2, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("message_type not between", value1, value2, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageContentIsNull() {
			addCriterion("message_content is null");
			return (Criteria) this;
		}

		public Criteria andMessageContentIsNotNull() {
			addCriterion("message_content is not null");
			return (Criteria) this;
		}

		public Criteria andMessageContentEqualTo(String value) {
			addCriterion("message_content =", value, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentNotEqualTo(String value) {
			addCriterion("message_content <>", value, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentGreaterThan(String value) {
			addCriterion("message_content >", value, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentGreaterThanOrEqualTo(String value) {
			addCriterion("message_content >=", value, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentLessThan(String value) {
			addCriterion("message_content <", value, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentLessThanOrEqualTo(String value) {
			addCriterion("message_content <=", value, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentLike(String value) {
			addCriterion("message_content like", value, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentNotLike(String value) {
			addCriterion("message_content not like", value, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentIn(List<String> values) {
			addCriterion("message_content in", values, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentNotIn(List<String> values) {
			addCriterion("message_content not in", values, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentBetween(String value1, String value2) {
			addCriterion("message_content between", value1, value2, "messageContent");
			return (Criteria) this;
		}

		public Criteria andMessageContentNotBetween(String value1, String value2) {
			addCriterion("message_content not between", value1, value2, "messageContent");
			return (Criteria) this;
		}

		public Criteria andSendTimeIsNull() {
			addCriterion("send_time is null");
			return (Criteria) this;
		}

		public Criteria andSendTimeIsNotNull() {
			addCriterion("send_time is not null");
			return (Criteria) this;
		}

		public Criteria andSendTimeEqualTo(java.util.Date value) {
			addCriterion("send_time =", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeNotEqualTo(java.util.Date value) {
			addCriterion("send_time <>", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeGreaterThan(java.util.Date value) {
			addCriterion("send_time >", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("send_time >=", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeLessThan(java.util.Date value) {
			addCriterion("send_time <", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("send_time <=", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeIn(List<java.util.Date> values) {
			addCriterion("send_time in", values, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeNotIn(List<java.util.Date> values) {
			addCriterion("send_time not in", values, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("send_time between", value1, value2, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("send_time not between", value1, value2, "sendTime");
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

		public Criteria andDeleteTimeIsNull() {
			addCriterion("delete_time is null");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeIsNotNull() {
			addCriterion("delete_time is not null");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeEqualTo(java.util.Date value) {
			addCriterion("delete_time =", value, "deleteTime");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeNotEqualTo(java.util.Date value) {
			addCriterion("delete_time <>", value, "deleteTime");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeGreaterThan(java.util.Date value) {
			addCriterion("delete_time >", value, "deleteTime");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("delete_time >=", value, "deleteTime");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeLessThan(java.util.Date value) {
			addCriterion("delete_time <", value, "deleteTime");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("delete_time <=", value, "deleteTime");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeIn(List<java.util.Date> values) {
			addCriterion("delete_time in", values, "deleteTime");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeNotIn(List<java.util.Date> values) {
			addCriterion("delete_time not in", values, "deleteTime");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("delete_time between", value1, value2, "deleteTime");
			return (Criteria) this;
		}

		public Criteria andDeleteTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("delete_time not between", value1, value2, "deleteTime");
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