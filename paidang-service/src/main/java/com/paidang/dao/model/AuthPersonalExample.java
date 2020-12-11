package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class AuthPersonalExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public AuthPersonalExample(){
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

		public Criteria andLogoIsNull() {
			addCriterion("logo is null");
			return (Criteria) this;
		}

		public Criteria andLogoIsNotNull() {
			addCriterion("logo is not null");
			return (Criteria) this;
		}

		public Criteria andLogoEqualTo(String value) {
			addCriterion("logo =", value, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoNotEqualTo(String value) {
			addCriterion("logo <>", value, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoGreaterThan(String value) {
			addCriterion("logo >", value, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoGreaterThanOrEqualTo(String value) {
			addCriterion("logo >=", value, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoLessThan(String value) {
			addCriterion("logo <", value, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoLessThanOrEqualTo(String value) {
			addCriterion("logo <=", value, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoLike(String value) {
			addCriterion("logo like", value, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoNotLike(String value) {
			addCriterion("logo not like", value, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoIn(List<String> values) {
			addCriterion("logo in", values, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoNotIn(List<String> values) {
			addCriterion("logo not in", values, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoBetween(String value1, String value2) {
			addCriterion("logo between", value1, value2, "logo");
			return (Criteria) this;
		}

		public Criteria andLogoNotBetween(String value1, String value2) {
			addCriterion("logo not between", value1, value2, "logo");
			return (Criteria) this;
		}

		public Criteria andStoreNameIsNull() {
			addCriterion("store_name is null");
			return (Criteria) this;
		}

		public Criteria andStoreNameIsNotNull() {
			addCriterion("store_name is not null");
			return (Criteria) this;
		}

		public Criteria andStoreNameEqualTo(String value) {
			addCriterion("store_name =", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameNotEqualTo(String value) {
			addCriterion("store_name <>", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameGreaterThan(String value) {
			addCriterion("store_name >", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
			addCriterion("store_name >=", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameLessThan(String value) {
			addCriterion("store_name <", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameLessThanOrEqualTo(String value) {
			addCriterion("store_name <=", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameLike(String value) {
			addCriterion("store_name like", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameNotLike(String value) {
			addCriterion("store_name not like", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameIn(List<String> values) {
			addCriterion("store_name in", values, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameNotIn(List<String> values) {
			addCriterion("store_name not in", values, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameBetween(String value1, String value2) {
			addCriterion("store_name between", value1, value2, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameNotBetween(String value1, String value2) {
			addCriterion("store_name not between", value1, value2, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceIsNull() {
			addCriterion("store_introduce is null");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceIsNotNull() {
			addCriterion("store_introduce is not null");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceEqualTo(String value) {
			addCriterion("store_introduce =", value, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceNotEqualTo(String value) {
			addCriterion("store_introduce <>", value, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceGreaterThan(String value) {
			addCriterion("store_introduce >", value, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceGreaterThanOrEqualTo(String value) {
			addCriterion("store_introduce >=", value, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceLessThan(String value) {
			addCriterion("store_introduce <", value, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceLessThanOrEqualTo(String value) {
			addCriterion("store_introduce <=", value, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceLike(String value) {
			addCriterion("store_introduce like", value, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceNotLike(String value) {
			addCriterion("store_introduce not like", value, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceIn(List<String> values) {
			addCriterion("store_introduce in", values, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceNotIn(List<String> values) {
			addCriterion("store_introduce not in", values, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceBetween(String value1, String value2) {
			addCriterion("store_introduce between", value1, value2, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andStoreIntroduceNotBetween(String value1, String value2) {
			addCriterion("store_introduce not between", value1, value2, "storeIntroduce");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andIdCardIsNull() {
			addCriterion("id_card is null");
			return (Criteria) this;
		}

		public Criteria andIdCardIsNotNull() {
			addCriterion("id_card is not null");
			return (Criteria) this;
		}

		public Criteria andIdCardEqualTo(String value) {
			addCriterion("id_card =", value, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardNotEqualTo(String value) {
			addCriterion("id_card <>", value, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardGreaterThan(String value) {
			addCriterion("id_card >", value, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardGreaterThanOrEqualTo(String value) {
			addCriterion("id_card >=", value, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardLessThan(String value) {
			addCriterion("id_card <", value, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardLessThanOrEqualTo(String value) {
			addCriterion("id_card <=", value, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardLike(String value) {
			addCriterion("id_card like", value, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardNotLike(String value) {
			addCriterion("id_card not like", value, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardIn(List<String> values) {
			addCriterion("id_card in", values, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardNotIn(List<String> values) {
			addCriterion("id_card not in", values, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardBetween(String value1, String value2) {
			addCriterion("id_card between", value1, value2, "idCard");
			return (Criteria) this;
		}

		public Criteria andIdCardNotBetween(String value1, String value2) {
			addCriterion("id_card not between", value1, value2, "idCard");
			return (Criteria) this;
		}

		public Criteria andPhoneIsNull() {
			addCriterion("phone is null");
			return (Criteria) this;
		}

		public Criteria andPhoneIsNotNull() {
			addCriterion("phone is not null");
			return (Criteria) this;
		}

		public Criteria andPhoneEqualTo(String value) {
			addCriterion("phone =", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotEqualTo(String value) {
			addCriterion("phone <>", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThan(String value) {
			addCriterion("phone >", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("phone >=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThan(String value) {
			addCriterion("phone <", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThanOrEqualTo(String value) {
			addCriterion("phone <=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLike(String value) {
			addCriterion("phone like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotLike(String value) {
			addCriterion("phone not like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneIn(List<String> values) {
			addCriterion("phone in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotIn(List<String> values) {
			addCriterion("phone not in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneBetween(String value1, String value2) {
			addCriterion("phone between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotBetween(String value1, String value2) {
			addCriterion("phone not between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontIsNull() {
			addCriterion("id_card_front is null");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontIsNotNull() {
			addCriterion("id_card_front is not null");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontEqualTo(String value) {
			addCriterion("id_card_front =", value, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontNotEqualTo(String value) {
			addCriterion("id_card_front <>", value, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontGreaterThan(String value) {
			addCriterion("id_card_front >", value, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontGreaterThanOrEqualTo(String value) {
			addCriterion("id_card_front >=", value, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontLessThan(String value) {
			addCriterion("id_card_front <", value, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontLessThanOrEqualTo(String value) {
			addCriterion("id_card_front <=", value, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontLike(String value) {
			addCriterion("id_card_front like", value, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontNotLike(String value) {
			addCriterion("id_card_front not like", value, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontIn(List<String> values) {
			addCriterion("id_card_front in", values, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontNotIn(List<String> values) {
			addCriterion("id_card_front not in", values, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontBetween(String value1, String value2) {
			addCriterion("id_card_front between", value1, value2, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardFrontNotBetween(String value1, String value2) {
			addCriterion("id_card_front not between", value1, value2, "idCardFront");
			return (Criteria) this;
		}

		public Criteria andIdCardBackIsNull() {
			addCriterion("id_card_back is null");
			return (Criteria) this;
		}

		public Criteria andIdCardBackIsNotNull() {
			addCriterion("id_card_back is not null");
			return (Criteria) this;
		}

		public Criteria andIdCardBackEqualTo(String value) {
			addCriterion("id_card_back =", value, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackNotEqualTo(String value) {
			addCriterion("id_card_back <>", value, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackGreaterThan(String value) {
			addCriterion("id_card_back >", value, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackGreaterThanOrEqualTo(String value) {
			addCriterion("id_card_back >=", value, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackLessThan(String value) {
			addCriterion("id_card_back <", value, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackLessThanOrEqualTo(String value) {
			addCriterion("id_card_back <=", value, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackLike(String value) {
			addCriterion("id_card_back like", value, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackNotLike(String value) {
			addCriterion("id_card_back not like", value, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackIn(List<String> values) {
			addCriterion("id_card_back in", values, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackNotIn(List<String> values) {
			addCriterion("id_card_back not in", values, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackBetween(String value1, String value2) {
			addCriterion("id_card_back between", value1, value2, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andIdCardBackNotBetween(String value1, String value2) {
			addCriterion("id_card_back not between", value1, value2, "idCardBack");
			return (Criteria) this;
		}

		public Criteria andCreateUserIsNull() {
			addCriterion("create_user is null");
			return (Criteria) this;
		}

		public Criteria andCreateUserIsNotNull() {
			addCriterion("create_user is not null");
			return (Criteria) this;
		}

		public Criteria andCreateUserEqualTo(Integer value) {
			addCriterion("create_user =", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserNotEqualTo(Integer value) {
			addCriterion("create_user <>", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserGreaterThan(Integer value) {
			addCriterion("create_user >", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserGreaterThanOrEqualTo(Integer value) {
			addCriterion("create_user >=", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserLessThan(Integer value) {
			addCriterion("create_user <", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserLessThanOrEqualTo(Integer value) {
			addCriterion("create_user <=", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserIn(List<Integer> values) {
			addCriterion("create_user in", values, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserNotIn(List<Integer> values) {
			addCriterion("create_user not in", values, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserBetween(Integer value1, Integer value2) {
			addCriterion("create_user between", value1, value2, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserNotBetween(Integer value1, Integer value2) {
			addCriterion("create_user not between", value1, value2, "createUser");
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

		public Criteria andStateIsNull() {
			addCriterion("state is null");
			return (Criteria) this;
		}

		public Criteria andStateIsNotNull() {
			addCriterion("state is not null");
			return (Criteria) this;
		}

		public Criteria andStateEqualTo(String value) {
			addCriterion("state =", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotEqualTo(String value) {
			addCriterion("state <>", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThan(String value) {
			addCriterion("state >", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThanOrEqualTo(String value) {
			addCriterion("state >=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThan(String value) {
			addCriterion("state <", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThanOrEqualTo(String value) {
			addCriterion("state <=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLike(String value) {
			addCriterion("state like", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotLike(String value) {
			addCriterion("state not like", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateIn(List<String> values) {
			addCriterion("state in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotIn(List<String> values) {
			addCriterion("state not in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateBetween(String value1, String value2) {
			addCriterion("state between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotBetween(String value1, String value2) {
			addCriterion("state not between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoIsNull() {
			addCriterion("refuse_info is null");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoIsNotNull() {
			addCriterion("refuse_info is not null");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoEqualTo(String value) {
			addCriterion("refuse_info =", value, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoNotEqualTo(String value) {
			addCriterion("refuse_info <>", value, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoGreaterThan(String value) {
			addCriterion("refuse_info >", value, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoGreaterThanOrEqualTo(String value) {
			addCriterion("refuse_info >=", value, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoLessThan(String value) {
			addCriterion("refuse_info <", value, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoLessThanOrEqualTo(String value) {
			addCriterion("refuse_info <=", value, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoLike(String value) {
			addCriterion("refuse_info like", value, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoNotLike(String value) {
			addCriterion("refuse_info not like", value, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoIn(List<String> values) {
			addCriterion("refuse_info in", values, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoNotIn(List<String> values) {
			addCriterion("refuse_info not in", values, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoBetween(String value1, String value2) {
			addCriterion("refuse_info between", value1, value2, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andRefuseInfoNotBetween(String value1, String value2) {
			addCriterion("refuse_info not between", value1, value2, "refuseInfo");
			return (Criteria) this;
		}

		public Criteria andOrgIdIsNull() {
			addCriterion("org_id is null");
			return (Criteria) this;
		}

		public Criteria andOrgIdIsNotNull() {
			addCriterion("org_id is not null");
			return (Criteria) this;
		}

		public Criteria andOrgIdEqualTo(Integer value) {
			addCriterion("org_id =", value, "orgId");
			return (Criteria) this;
		}

		public Criteria andOrgIdNotEqualTo(Integer value) {
			addCriterion("org_id <>", value, "orgId");
			return (Criteria) this;
		}

		public Criteria andOrgIdGreaterThan(Integer value) {
			addCriterion("org_id >", value, "orgId");
			return (Criteria) this;
		}

		public Criteria andOrgIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("org_id >=", value, "orgId");
			return (Criteria) this;
		}

		public Criteria andOrgIdLessThan(Integer value) {
			addCriterion("org_id <", value, "orgId");
			return (Criteria) this;
		}

		public Criteria andOrgIdLessThanOrEqualTo(Integer value) {
			addCriterion("org_id <=", value, "orgId");
			return (Criteria) this;
		}

		public Criteria andOrgIdIn(List<Integer> values) {
			addCriterion("org_id in", values, "orgId");
			return (Criteria) this;
		}

		public Criteria andOrgIdNotIn(List<Integer> values) {
			addCriterion("org_id not in", values, "orgId");
			return (Criteria) this;
		}

		public Criteria andOrgIdBetween(Integer value1, Integer value2) {
			addCriterion("org_id between", value1, value2, "orgId");
			return (Criteria) this;
		}

		public Criteria andOrgIdNotBetween(Integer value1, Integer value2) {
			addCriterion("org_id not between", value1, value2, "orgId");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontIsNull() {
			addCriterion("hand_id_card_front is null");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontIsNotNull() {
			addCriterion("hand_id_card_front is not null");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontEqualTo(String value) {
			addCriterion("hand_id_card_front =", value, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontNotEqualTo(String value) {
			addCriterion("hand_id_card_front <>", value, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontGreaterThan(String value) {
			addCriterion("hand_id_card_front >", value, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontGreaterThanOrEqualTo(String value) {
			addCriterion("hand_id_card_front >=", value, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontLessThan(String value) {
			addCriterion("hand_id_card_front <", value, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontLessThanOrEqualTo(String value) {
			addCriterion("hand_id_card_front <=", value, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontLike(String value) {
			addCriterion("hand_id_card_front like", value, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontNotLike(String value) {
			addCriterion("hand_id_card_front not like", value, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontIn(List<String> values) {
			addCriterion("hand_id_card_front in", values, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontNotIn(List<String> values) {
			addCriterion("hand_id_card_front not in", values, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontBetween(String value1, String value2) {
			addCriterion("hand_id_card_front between", value1, value2, "handIdCardFront");
			return (Criteria) this;
		}

		public Criteria andHandIdCardFrontNotBetween(String value1, String value2) {
			addCriterion("hand_id_card_front not between", value1, value2, "handIdCardFront");
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