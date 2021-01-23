package com.item.dao.model;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public UserExample(){
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

		public Criteria andAccountIsNull() {
			addCriterion("account is null");
			return (Criteria) this;
		}

		public Criteria andAccountIsNotNull() {
			addCriterion("account is not null");
			return (Criteria) this;
		}

		public Criteria andAccountEqualTo(String value) {
			addCriterion("account =", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotEqualTo(String value) {
			addCriterion("account <>", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountGreaterThan(String value) {
			addCriterion("account >", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountGreaterThanOrEqualTo(String value) {
			addCriterion("account >=", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountLessThan(String value) {
			addCriterion("account <", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountLessThanOrEqualTo(String value) {
			addCriterion("account <=", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountLike(String value) {
			addCriterion("account like", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotLike(String value) {
			addCriterion("account not like", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountIn(List<String> values) {
			addCriterion("account in", values, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotIn(List<String> values) {
			addCriterion("account not in", values, "account");
			return (Criteria) this;
		}

		public Criteria andAccountBetween(String value1, String value2) {
			addCriterion("account between", value1, value2, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotBetween(String value1, String value2) {
			addCriterion("account not between", value1, value2, "account");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNull() {
			addCriterion("password is null");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNotNull() {
			addCriterion("password is not null");
			return (Criteria) this;
		}

		public Criteria andPasswordEqualTo(String value) {
			addCriterion("password =", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotEqualTo(String value) {
			addCriterion("password <>", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThan(String value) {
			addCriterion("password >", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("password >=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThan(String value) {
			addCriterion("password <", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThanOrEqualTo(String value) {
			addCriterion("password <=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLike(String value) {
			addCriterion("password like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotLike(String value) {
			addCriterion("password not like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordIn(List<String> values) {
			addCriterion("password in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotIn(List<String> values) {
			addCriterion("password not in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordBetween(String value1, String value2) {
			addCriterion("password between", value1, value2, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotBetween(String value1, String value2) {
			addCriterion("password not between", value1, value2, "password");
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

		public Criteria andNickNameIsNull() {
			addCriterion("nick_name is null");
			return (Criteria) this;
		}

		public Criteria andNickNameIsNotNull() {
			addCriterion("nick_name is not null");
			return (Criteria) this;
		}

		public Criteria andNickNameEqualTo(String value) {
			addCriterion("nick_name =", value, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameNotEqualTo(String value) {
			addCriterion("nick_name <>", value, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameGreaterThan(String value) {
			addCriterion("nick_name >", value, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameGreaterThanOrEqualTo(String value) {
			addCriterion("nick_name >=", value, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameLessThan(String value) {
			addCriterion("nick_name <", value, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameLessThanOrEqualTo(String value) {
			addCriterion("nick_name <=", value, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameLike(String value) {
			addCriterion("nick_name like", value, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameNotLike(String value) {
			addCriterion("nick_name not like", value, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameIn(List<String> values) {
			addCriterion("nick_name in", values, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameNotIn(List<String> values) {
			addCriterion("nick_name not in", values, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameBetween(String value1, String value2) {
			addCriterion("nick_name between", value1, value2, "nickName");
			return (Criteria) this;
		}

		public Criteria andNickNameNotBetween(String value1, String value2) {
			addCriterion("nick_name not between", value1, value2, "nickName");
			return (Criteria) this;
		}

		public Criteria andHeadImgIsNull() {
			addCriterion("head_img is null");
			return (Criteria) this;
		}

		public Criteria andHeadImgIsNotNull() {
			addCriterion("head_img is not null");
			return (Criteria) this;
		}

		public Criteria andHeadImgEqualTo(String value) {
			addCriterion("head_img =", value, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgNotEqualTo(String value) {
			addCriterion("head_img <>", value, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgGreaterThan(String value) {
			addCriterion("head_img >", value, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgGreaterThanOrEqualTo(String value) {
			addCriterion("head_img >=", value, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgLessThan(String value) {
			addCriterion("head_img <", value, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgLessThanOrEqualTo(String value) {
			addCriterion("head_img <=", value, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgLike(String value) {
			addCriterion("head_img like", value, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgNotLike(String value) {
			addCriterion("head_img not like", value, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgIn(List<String> values) {
			addCriterion("head_img in", values, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgNotIn(List<String> values) {
			addCriterion("head_img not in", values, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgBetween(String value1, String value2) {
			addCriterion("head_img between", value1, value2, "headImg");
			return (Criteria) this;
		}

		public Criteria andHeadImgNotBetween(String value1, String value2) {
			addCriterion("head_img not between", value1, value2, "headImg");
			return (Criteria) this;
		}

		public Criteria andSexIsNull() {
			addCriterion("sex is null");
			return (Criteria) this;
		}

		public Criteria andSexIsNotNull() {
			addCriterion("sex is not null");
			return (Criteria) this;
		}

		public Criteria andSexEqualTo(Integer value) {
			addCriterion("sex =", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotEqualTo(Integer value) {
			addCriterion("sex <>", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexGreaterThan(Integer value) {
			addCriterion("sex >", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexGreaterThanOrEqualTo(Integer value) {
			addCriterion("sex >=", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLessThan(Integer value) {
			addCriterion("sex <", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLessThanOrEqualTo(Integer value) {
			addCriterion("sex <=", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexIn(List<Integer> values) {
			addCriterion("sex in", values, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotIn(List<Integer> values) {
			addCriterion("sex not in", values, "sex");
			return (Criteria) this;
		}

		public Criteria andSexBetween(Integer value1, Integer value2) {
			addCriterion("sex between", value1, value2, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotBetween(Integer value1, Integer value2) {
			addCriterion("sex not between", value1, value2, "sex");
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

		public Criteria andIdCardImgIsNull() {
			addCriterion("id_card_img is null");
			return (Criteria) this;
		}

		public Criteria andIdCardImgIsNotNull() {
			addCriterion("id_card_img is not null");
			return (Criteria) this;
		}

		public Criteria andIdCardImgEqualTo(String value) {
			addCriterion("id_card_img =", value, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgNotEqualTo(String value) {
			addCriterion("id_card_img <>", value, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgGreaterThan(String value) {
			addCriterion("id_card_img >", value, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgGreaterThanOrEqualTo(String value) {
			addCriterion("id_card_img >=", value, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgLessThan(String value) {
			addCriterion("id_card_img <", value, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgLessThanOrEqualTo(String value) {
			addCriterion("id_card_img <=", value, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgLike(String value) {
			addCriterion("id_card_img like", value, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgNotLike(String value) {
			addCriterion("id_card_img not like", value, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgIn(List<String> values) {
			addCriterion("id_card_img in", values, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgNotIn(List<String> values) {
			addCriterion("id_card_img not in", values, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgBetween(String value1, String value2) {
			addCriterion("id_card_img between", value1, value2, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardImgNotBetween(String value1, String value2) {
			addCriterion("id_card_img not between", value1, value2, "idCardImg");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseIsNull() {
			addCriterion("id_card_reverse is null");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseIsNotNull() {
			addCriterion("id_card_reverse is not null");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseEqualTo(String value) {
			addCriterion("id_card_reverse =", value, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseNotEqualTo(String value) {
			addCriterion("id_card_reverse <>", value, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseGreaterThan(String value) {
			addCriterion("id_card_reverse >", value, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseGreaterThanOrEqualTo(String value) {
			addCriterion("id_card_reverse >=", value, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseLessThan(String value) {
			addCriterion("id_card_reverse <", value, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseLessThanOrEqualTo(String value) {
			addCriterion("id_card_reverse <=", value, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseLike(String value) {
			addCriterion("id_card_reverse like", value, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseNotLike(String value) {
			addCriterion("id_card_reverse not like", value, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseIn(List<String> values) {
			addCriterion("id_card_reverse in", values, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseNotIn(List<String> values) {
			addCriterion("id_card_reverse not in", values, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseBetween(String value1, String value2) {
			addCriterion("id_card_reverse between", value1, value2, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardReverseNotBetween(String value1, String value2) {
			addCriterion("id_card_reverse not between", value1, value2, "idCardReverse");
			return (Criteria) this;
		}

		public Criteria andIdCardHandIsNull() {
			addCriterion("id_card_hand is null");
			return (Criteria) this;
		}

		public Criteria andIdCardHandIsNotNull() {
			addCriterion("id_card_hand is not null");
			return (Criteria) this;
		}

		public Criteria andIdCardHandEqualTo(String value) {
			addCriterion("id_card_hand =", value, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandNotEqualTo(String value) {
			addCriterion("id_card_hand <>", value, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandGreaterThan(String value) {
			addCriterion("id_card_hand >", value, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandGreaterThanOrEqualTo(String value) {
			addCriterion("id_card_hand >=", value, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandLessThan(String value) {
			addCriterion("id_card_hand <", value, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandLessThanOrEqualTo(String value) {
			addCriterion("id_card_hand <=", value, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandLike(String value) {
			addCriterion("id_card_hand like", value, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandNotLike(String value) {
			addCriterion("id_card_hand not like", value, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandIn(List<String> values) {
			addCriterion("id_card_hand in", values, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandNotIn(List<String> values) {
			addCriterion("id_card_hand not in", values, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandBetween(String value1, String value2) {
			addCriterion("id_card_hand between", value1, value2, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andIdCardHandNotBetween(String value1, String value2) {
			addCriterion("id_card_hand not between", value1, value2, "idCardHand");
			return (Criteria) this;
		}

		public Criteria andHeadShakeIsNull() {
			addCriterion("head_shake is null");
			return (Criteria) this;
		}

		public Criteria andHeadShakeIsNotNull() {
			addCriterion("head_shake is not null");
			return (Criteria) this;
		}

		public Criteria andHeadShakeEqualTo(String value) {
			addCriterion("head_shake =", value, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeNotEqualTo(String value) {
			addCriterion("head_shake <>", value, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeGreaterThan(String value) {
			addCriterion("head_shake >", value, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeGreaterThanOrEqualTo(String value) {
			addCriterion("head_shake >=", value, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeLessThan(String value) {
			addCriterion("head_shake <", value, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeLessThanOrEqualTo(String value) {
			addCriterion("head_shake <=", value, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeLike(String value) {
			addCriterion("head_shake like", value, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeNotLike(String value) {
			addCriterion("head_shake not like", value, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeIn(List<String> values) {
			addCriterion("head_shake in", values, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeNotIn(List<String> values) {
			addCriterion("head_shake not in", values, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeBetween(String value1, String value2) {
			addCriterion("head_shake between", value1, value2, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadShakeNotBetween(String value1, String value2) {
			addCriterion("head_shake not between", value1, value2, "headShake");
			return (Criteria) this;
		}

		public Criteria andHeadNodIsNull() {
			addCriterion("head_nod is null");
			return (Criteria) this;
		}

		public Criteria andHeadNodIsNotNull() {
			addCriterion("head_nod is not null");
			return (Criteria) this;
		}

		public Criteria andHeadNodEqualTo(String value) {
			addCriterion("head_nod =", value, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodNotEqualTo(String value) {
			addCriterion("head_nod <>", value, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodGreaterThan(String value) {
			addCriterion("head_nod >", value, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodGreaterThanOrEqualTo(String value) {
			addCriterion("head_nod >=", value, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodLessThan(String value) {
			addCriterion("head_nod <", value, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodLessThanOrEqualTo(String value) {
			addCriterion("head_nod <=", value, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodLike(String value) {
			addCriterion("head_nod like", value, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodNotLike(String value) {
			addCriterion("head_nod not like", value, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodIn(List<String> values) {
			addCriterion("head_nod in", values, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodNotIn(List<String> values) {
			addCriterion("head_nod not in", values, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodBetween(String value1, String value2) {
			addCriterion("head_nod between", value1, value2, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadNodNotBetween(String value1, String value2) {
			addCriterion("head_nod not between", value1, value2, "headNod");
			return (Criteria) this;
		}

		public Criteria andHeadEyeIsNull() {
			addCriterion("head_eye is null");
			return (Criteria) this;
		}

		public Criteria andHeadEyeIsNotNull() {
			addCriterion("head_eye is not null");
			return (Criteria) this;
		}

		public Criteria andHeadEyeEqualTo(String value) {
			addCriterion("head_eye =", value, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeNotEqualTo(String value) {
			addCriterion("head_eye <>", value, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeGreaterThan(String value) {
			addCriterion("head_eye >", value, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeGreaterThanOrEqualTo(String value) {
			addCriterion("head_eye >=", value, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeLessThan(String value) {
			addCriterion("head_eye <", value, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeLessThanOrEqualTo(String value) {
			addCriterion("head_eye <=", value, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeLike(String value) {
			addCriterion("head_eye like", value, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeNotLike(String value) {
			addCriterion("head_eye not like", value, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeIn(List<String> values) {
			addCriterion("head_eye in", values, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeNotIn(List<String> values) {
			addCriterion("head_eye not in", values, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeBetween(String value1, String value2) {
			addCriterion("head_eye between", value1, value2, "headEye");
			return (Criteria) this;
		}

		public Criteria andHeadEyeNotBetween(String value1, String value2) {
			addCriterion("head_eye not between", value1, value2, "headEye");
			return (Criteria) this;
		}

		public Criteria andIsBindIsNull() {
			addCriterion("is_bind is null");
			return (Criteria) this;
		}

		public Criteria andIsBindIsNotNull() {
			addCriterion("is_bind is not null");
			return (Criteria) this;
		}

		public Criteria andIsBindEqualTo(Integer value) {
			addCriterion("is_bind =", value, "isBind");
			return (Criteria) this;
		}

		public Criteria andIsBindNotEqualTo(Integer value) {
			addCriterion("is_bind <>", value, "isBind");
			return (Criteria) this;
		}

		public Criteria andIsBindGreaterThan(Integer value) {
			addCriterion("is_bind >", value, "isBind");
			return (Criteria) this;
		}

		public Criteria andIsBindGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_bind >=", value, "isBind");
			return (Criteria) this;
		}

		public Criteria andIsBindLessThan(Integer value) {
			addCriterion("is_bind <", value, "isBind");
			return (Criteria) this;
		}

		public Criteria andIsBindLessThanOrEqualTo(Integer value) {
			addCriterion("is_bind <=", value, "isBind");
			return (Criteria) this;
		}

		public Criteria andIsBindIn(List<Integer> values) {
			addCriterion("is_bind in", values, "isBind");
			return (Criteria) this;
		}

		public Criteria andIsBindNotIn(List<Integer> values) {
			addCriterion("is_bind not in", values, "isBind");
			return (Criteria) this;
		}

		public Criteria andIsBindBetween(Integer value1, Integer value2) {
			addCriterion("is_bind between", value1, value2, "isBind");
			return (Criteria) this;
		}

		public Criteria andIsBindNotBetween(Integer value1, Integer value2) {
			addCriterion("is_bind not between", value1, value2, "isBind");
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

		public Criteria andIsCompleteIsNull() {
			addCriterion("is_complete is null");
			return (Criteria) this;
		}

		public Criteria andIsCompleteIsNotNull() {
			addCriterion("is_complete is not null");
			return (Criteria) this;
		}

		public Criteria andIsCompleteEqualTo(Integer value) {
			addCriterion("is_complete =", value, "isComplete");
			return (Criteria) this;
		}

		public Criteria andIsCompleteNotEqualTo(Integer value) {
			addCriterion("is_complete <>", value, "isComplete");
			return (Criteria) this;
		}

		public Criteria andIsCompleteGreaterThan(Integer value) {
			addCriterion("is_complete >", value, "isComplete");
			return (Criteria) this;
		}

		public Criteria andIsCompleteGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_complete >=", value, "isComplete");
			return (Criteria) this;
		}

		public Criteria andIsCompleteLessThan(Integer value) {
			addCriterion("is_complete <", value, "isComplete");
			return (Criteria) this;
		}

		public Criteria andIsCompleteLessThanOrEqualTo(Integer value) {
			addCriterion("is_complete <=", value, "isComplete");
			return (Criteria) this;
		}

		public Criteria andIsCompleteIn(List<Integer> values) {
			addCriterion("is_complete in", values, "isComplete");
			return (Criteria) this;
		}

		public Criteria andIsCompleteNotIn(List<Integer> values) {
			addCriterion("is_complete not in", values, "isComplete");
			return (Criteria) this;
		}

		public Criteria andIsCompleteBetween(Integer value1, Integer value2) {
			addCriterion("is_complete between", value1, value2, "isComplete");
			return (Criteria) this;
		}

		public Criteria andIsCompleteNotBetween(Integer value1, Integer value2) {
			addCriterion("is_complete not between", value1, value2, "isComplete");
			return (Criteria) this;
		}

		public Criteria andBalanceIsNull() {
			addCriterion("balance is null");
			return (Criteria) this;
		}

		public Criteria andBalanceIsNotNull() {
			addCriterion("balance is not null");
			return (Criteria) this;
		}

		public Criteria andBalanceEqualTo(java.math.BigDecimal value) {
			addCriterion("balance =", value, "balance");
			return (Criteria) this;
		}

		public Criteria andBalanceNotEqualTo(java.math.BigDecimal value) {
			addCriterion("balance <>", value, "balance");
			return (Criteria) this;
		}

		public Criteria andBalanceGreaterThan(java.math.BigDecimal value) {
			addCriterion("balance >", value, "balance");
			return (Criteria) this;
		}

		public Criteria andBalanceGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("balance >=", value, "balance");
			return (Criteria) this;
		}

		public Criteria andBalanceLessThan(java.math.BigDecimal value) {
			addCriterion("balance <", value, "balance");
			return (Criteria) this;
		}

		public Criteria andBalanceLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("balance <=", value, "balance");
			return (Criteria) this;
		}

		public Criteria andBalanceIn(List<java.math.BigDecimal> values) {
			addCriterion("balance in", values, "balance");
			return (Criteria) this;
		}

		public Criteria andBalanceNotIn(List<java.math.BigDecimal> values) {
			addCriterion("balance not in", values, "balance");
			return (Criteria) this;
		}

		public Criteria andBalanceBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("balance between", value1, value2, "balance");
			return (Criteria) this;
		}

		public Criteria andBalanceNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("balance not between", value1, value2, "balance");
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

		public Criteria andStateEqualTo(Integer value) {
			addCriterion("state =", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotEqualTo(Integer value) {
			addCriterion("state <>", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThan(Integer value) {
			addCriterion("state >", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThanOrEqualTo(Integer value) {
			addCriterion("state >=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThan(Integer value) {
			addCriterion("state <", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThanOrEqualTo(Integer value) {
			addCriterion("state <=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateIn(List<Integer> values) {
			addCriterion("state in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotIn(List<Integer> values) {
			addCriterion("state not in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateBetween(Integer value1, Integer value2) {
			addCriterion("state between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotBetween(Integer value1, Integer value2) {
			addCriterion("state not between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andCreditIsNull() {
			addCriterion("credit is null");
			return (Criteria) this;
		}

		public Criteria andCreditIsNotNull() {
			addCriterion("credit is not null");
			return (Criteria) this;
		}

		public Criteria andCreditEqualTo(Integer value) {
			addCriterion("credit =", value, "credit");
			return (Criteria) this;
		}

		public Criteria andCreditNotEqualTo(Integer value) {
			addCriterion("credit <>", value, "credit");
			return (Criteria) this;
		}

		public Criteria andCreditGreaterThan(Integer value) {
			addCriterion("credit >", value, "credit");
			return (Criteria) this;
		}

		public Criteria andCreditGreaterThanOrEqualTo(Integer value) {
			addCriterion("credit >=", value, "credit");
			return (Criteria) this;
		}

		public Criteria andCreditLessThan(Integer value) {
			addCriterion("credit <", value, "credit");
			return (Criteria) this;
		}

		public Criteria andCreditLessThanOrEqualTo(Integer value) {
			addCriterion("credit <=", value, "credit");
			return (Criteria) this;
		}

		public Criteria andCreditIn(List<Integer> values) {
			addCriterion("credit in", values, "credit");
			return (Criteria) this;
		}

		public Criteria andCreditNotIn(List<Integer> values) {
			addCriterion("credit not in", values, "credit");
			return (Criteria) this;
		}

		public Criteria andCreditBetween(Integer value1, Integer value2) {
			addCriterion("credit between", value1, value2, "credit");
			return (Criteria) this;
		}

		public Criteria andCreditNotBetween(Integer value1, Integer value2) {
			addCriterion("credit not between", value1, value2, "credit");
			return (Criteria) this;
		}

		public Criteria andLoginTimeIsNull() {
			addCriterion("login_time is null");
			return (Criteria) this;
		}

		public Criteria andLoginTimeIsNotNull() {
			addCriterion("login_time is not null");
			return (Criteria) this;
		}

		public Criteria andLoginTimeEqualTo(java.util.Date value) {
			addCriterion("login_time =", value, "loginTime");
			return (Criteria) this;
		}

		public Criteria andLoginTimeNotEqualTo(java.util.Date value) {
			addCriterion("login_time <>", value, "loginTime");
			return (Criteria) this;
		}

		public Criteria andLoginTimeGreaterThan(java.util.Date value) {
			addCriterion("login_time >", value, "loginTime");
			return (Criteria) this;
		}

		public Criteria andLoginTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("login_time >=", value, "loginTime");
			return (Criteria) this;
		}

		public Criteria andLoginTimeLessThan(java.util.Date value) {
			addCriterion("login_time <", value, "loginTime");
			return (Criteria) this;
		}

		public Criteria andLoginTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("login_time <=", value, "loginTime");
			return (Criteria) this;
		}

		public Criteria andLoginTimeIn(List<java.util.Date> values) {
			addCriterion("login_time in", values, "loginTime");
			return (Criteria) this;
		}

		public Criteria andLoginTimeNotIn(List<java.util.Date> values) {
			addCriterion("login_time not in", values, "loginTime");
			return (Criteria) this;
		}

		public Criteria andLoginTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("login_time between", value1, value2, "loginTime");
			return (Criteria) this;
		}

		public Criteria andLoginTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("login_time not between", value1, value2, "loginTime");
			return (Criteria) this;
		}

		public Criteria andSobotTokenIsNull() {
			addCriterion("sobot_token is null");
			return (Criteria) this;
		}

		public Criteria andSobotTokenIsNotNull() {
			addCriterion("sobot_token is not null");
			return (Criteria) this;
		}

		public Criteria andSobotTokenEqualTo(String value) {
			addCriterion("sobot_token =", value, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenNotEqualTo(String value) {
			addCriterion("sobot_token <>", value, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenGreaterThan(String value) {
			addCriterion("sobot_token >", value, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenGreaterThanOrEqualTo(String value) {
			addCriterion("sobot_token >=", value, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenLessThan(String value) {
			addCriterion("sobot_token <", value, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenLessThanOrEqualTo(String value) {
			addCriterion("sobot_token <=", value, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenLike(String value) {
			addCriterion("sobot_token like", value, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenNotLike(String value) {
			addCriterion("sobot_token not like", value, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenIn(List<String> values) {
			addCriterion("sobot_token in", values, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenNotIn(List<String> values) {
			addCriterion("sobot_token not in", values, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenBetween(String value1, String value2) {
			addCriterion("sobot_token between", value1, value2, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andSobotTokenNotBetween(String value1, String value2) {
			addCriterion("sobot_token not between", value1, value2, "sobotToken");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeIsNull() {
			addCriterion("last_sobot_token_time is null");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeIsNotNull() {
			addCriterion("last_sobot_token_time is not null");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeEqualTo(java.util.Date value) {
			addCriterion("last_sobot_token_time =", value, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeNotEqualTo(java.util.Date value) {
			addCriterion("last_sobot_token_time <>", value, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeGreaterThan(java.util.Date value) {
			addCriterion("last_sobot_token_time >", value, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("last_sobot_token_time >=", value, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeLessThan(java.util.Date value) {
			addCriterion("last_sobot_token_time <", value, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("last_sobot_token_time <=", value, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeIn(List<java.util.Date> values) {
			addCriterion("last_sobot_token_time in", values, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeNotIn(List<java.util.Date> values) {
			addCriterion("last_sobot_token_time not in", values, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("last_sobot_token_time between", value1, value2, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andLastSobotTokenTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("last_sobot_token_time not between", value1, value2, "lastSobotTokenTime");
			return (Criteria) this;
		}

		public Criteria andImTokenIsNull() {
			addCriterion("im_token is null");
			return (Criteria) this;
		}

		public Criteria andImTokenIsNotNull() {
			addCriterion("im_token is not null");
			return (Criteria) this;
		}

		public Criteria andImTokenEqualTo(String value) {
			addCriterion("im_token =", value, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenNotEqualTo(String value) {
			addCriterion("im_token <>", value, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenGreaterThan(String value) {
			addCriterion("im_token >", value, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenGreaterThanOrEqualTo(String value) {
			addCriterion("im_token >=", value, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenLessThan(String value) {
			addCriterion("im_token <", value, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenLessThanOrEqualTo(String value) {
			addCriterion("im_token <=", value, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenLike(String value) {
			addCriterion("im_token like", value, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenNotLike(String value) {
			addCriterion("im_token not like", value, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenIn(List<String> values) {
			addCriterion("im_token in", values, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenNotIn(List<String> values) {
			addCriterion("im_token not in", values, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenBetween(String value1, String value2) {
			addCriterion("im_token between", value1, value2, "imToken");
			return (Criteria) this;
		}

		public Criteria andImTokenNotBetween(String value1, String value2) {
			addCriterion("im_token not between", value1, value2, "imToken");
			return (Criteria) this;
		}

		public Criteria andWxOpenidIsNull() {
			addCriterion("wx_openid is null");
			return (Criteria) this;
		}

		public Criteria andWxOpenidIsNotNull() {
			addCriterion("wx_openid is not null");
			return (Criteria) this;
		}

		public Criteria andWxOpenidEqualTo(String value) {
			addCriterion("wx_openid =", value, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidNotEqualTo(String value) {
			addCriterion("wx_openid <>", value, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidGreaterThan(String value) {
			addCriterion("wx_openid >", value, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidGreaterThanOrEqualTo(String value) {
			addCriterion("wx_openid >=", value, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidLessThan(String value) {
			addCriterion("wx_openid <", value, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidLessThanOrEqualTo(String value) {
			addCriterion("wx_openid <=", value, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidLike(String value) {
			addCriterion("wx_openid like", value, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidNotLike(String value) {
			addCriterion("wx_openid not like", value, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidIn(List<String> values) {
			addCriterion("wx_openid in", values, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidNotIn(List<String> values) {
			addCriterion("wx_openid not in", values, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidBetween(String value1, String value2) {
			addCriterion("wx_openid between", value1, value2, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andWxOpenidNotBetween(String value1, String value2) {
			addCriterion("wx_openid not between", value1, value2, "wxOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidIsNull() {
			addCriterion("qq_openid is null");
			return (Criteria) this;
		}

		public Criteria andQqOpenidIsNotNull() {
			addCriterion("qq_openid is not null");
			return (Criteria) this;
		}

		public Criteria andQqOpenidEqualTo(String value) {
			addCriterion("qq_openid =", value, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidNotEqualTo(String value) {
			addCriterion("qq_openid <>", value, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidGreaterThan(String value) {
			addCriterion("qq_openid >", value, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidGreaterThanOrEqualTo(String value) {
			addCriterion("qq_openid >=", value, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidLessThan(String value) {
			addCriterion("qq_openid <", value, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidLessThanOrEqualTo(String value) {
			addCriterion("qq_openid <=", value, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidLike(String value) {
			addCriterion("qq_openid like", value, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidNotLike(String value) {
			addCriterion("qq_openid not like", value, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidIn(List<String> values) {
			addCriterion("qq_openid in", values, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidNotIn(List<String> values) {
			addCriterion("qq_openid not in", values, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidBetween(String value1, String value2) {
			addCriterion("qq_openid between", value1, value2, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andQqOpenidNotBetween(String value1, String value2) {
			addCriterion("qq_openid not between", value1, value2, "qqOpenid");
			return (Criteria) this;
		}

		public Criteria andRecommendSortIsNull() {
			addCriterion("recommend_sort is null");
			return (Criteria) this;
		}

		public Criteria andRecommendSortIsNotNull() {
			addCriterion("recommend_sort is not null");
			return (Criteria) this;
		}

		public Criteria andRecommendSortEqualTo(Integer value) {
			addCriterion("recommend_sort =", value, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andRecommendSortNotEqualTo(Integer value) {
			addCriterion("recommend_sort <>", value, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andRecommendSortGreaterThan(Integer value) {
			addCriterion("recommend_sort >", value, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andRecommendSortGreaterThanOrEqualTo(Integer value) {
			addCriterion("recommend_sort >=", value, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andRecommendSortLessThan(Integer value) {
			addCriterion("recommend_sort <", value, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andRecommendSortLessThanOrEqualTo(Integer value) {
			addCriterion("recommend_sort <=", value, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andRecommendSortIn(List<Integer> values) {
			addCriterion("recommend_sort in", values, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andRecommendSortNotIn(List<Integer> values) {
			addCriterion("recommend_sort not in", values, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andRecommendSortBetween(Integer value1, Integer value2) {
			addCriterion("recommend_sort between", value1, value2, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andRecommendSortNotBetween(Integer value1, Integer value2) {
			addCriterion("recommend_sort not between", value1, value2, "recommendSort");
			return (Criteria) this;
		}

		public Criteria andArticleCountIsNull() {
			addCriterion("article_count is null");
			return (Criteria) this;
		}

		public Criteria andArticleCountIsNotNull() {
			addCriterion("article_count is not null");
			return (Criteria) this;
		}

		public Criteria andArticleCountEqualTo(Integer value) {
			addCriterion("article_count =", value, "articleCount");
			return (Criteria) this;
		}

		public Criteria andArticleCountNotEqualTo(Integer value) {
			addCriterion("article_count <>", value, "articleCount");
			return (Criteria) this;
		}

		public Criteria andArticleCountGreaterThan(Integer value) {
			addCriterion("article_count >", value, "articleCount");
			return (Criteria) this;
		}

		public Criteria andArticleCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("article_count >=", value, "articleCount");
			return (Criteria) this;
		}

		public Criteria andArticleCountLessThan(Integer value) {
			addCriterion("article_count <", value, "articleCount");
			return (Criteria) this;
		}

		public Criteria andArticleCountLessThanOrEqualTo(Integer value) {
			addCriterion("article_count <=", value, "articleCount");
			return (Criteria) this;
		}

		public Criteria andArticleCountIn(List<Integer> values) {
			addCriterion("article_count in", values, "articleCount");
			return (Criteria) this;
		}

		public Criteria andArticleCountNotIn(List<Integer> values) {
			addCriterion("article_count not in", values, "articleCount");
			return (Criteria) this;
		}

		public Criteria andArticleCountBetween(Integer value1, Integer value2) {
			addCriterion("article_count between", value1, value2, "articleCount");
			return (Criteria) this;
		}

		public Criteria andArticleCountNotBetween(Integer value1, Integer value2) {
			addCriterion("article_count not between", value1, value2, "articleCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountIsNull() {
			addCriterion("follow_count is null");
			return (Criteria) this;
		}

		public Criteria andFollowCountIsNotNull() {
			addCriterion("follow_count is not null");
			return (Criteria) this;
		}

		public Criteria andFollowCountEqualTo(Integer value) {
			addCriterion("follow_count =", value, "followCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountNotEqualTo(Integer value) {
			addCriterion("follow_count <>", value, "followCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountGreaterThan(Integer value) {
			addCriterion("follow_count >", value, "followCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("follow_count >=", value, "followCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountLessThan(Integer value) {
			addCriterion("follow_count <", value, "followCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountLessThanOrEqualTo(Integer value) {
			addCriterion("follow_count <=", value, "followCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountIn(List<Integer> values) {
			addCriterion("follow_count in", values, "followCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountNotIn(List<Integer> values) {
			addCriterion("follow_count not in", values, "followCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountBetween(Integer value1, Integer value2) {
			addCriterion("follow_count between", value1, value2, "followCount");
			return (Criteria) this;
		}

		public Criteria andFollowCountNotBetween(Integer value1, Integer value2) {
			addCriterion("follow_count not between", value1, value2, "followCount");
			return (Criteria) this;
		}

		public Criteria andFansCountIsNull() {
			addCriterion("fans_count is null");
			return (Criteria) this;
		}

		public Criteria andFansCountIsNotNull() {
			addCriterion("fans_count is not null");
			return (Criteria) this;
		}

		public Criteria andFansCountEqualTo(Integer value) {
			addCriterion("fans_count =", value, "fansCount");
			return (Criteria) this;
		}

		public Criteria andFansCountNotEqualTo(Integer value) {
			addCriterion("fans_count <>", value, "fansCount");
			return (Criteria) this;
		}

		public Criteria andFansCountGreaterThan(Integer value) {
			addCriterion("fans_count >", value, "fansCount");
			return (Criteria) this;
		}

		public Criteria andFansCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("fans_count >=", value, "fansCount");
			return (Criteria) this;
		}

		public Criteria andFansCountLessThan(Integer value) {
			addCriterion("fans_count <", value, "fansCount");
			return (Criteria) this;
		}

		public Criteria andFansCountLessThanOrEqualTo(Integer value) {
			addCriterion("fans_count <=", value, "fansCount");
			return (Criteria) this;
		}

		public Criteria andFansCountIn(List<Integer> values) {
			addCriterion("fans_count in", values, "fansCount");
			return (Criteria) this;
		}

		public Criteria andFansCountNotIn(List<Integer> values) {
			addCriterion("fans_count not in", values, "fansCount");
			return (Criteria) this;
		}

		public Criteria andFansCountBetween(Integer value1, Integer value2) {
			addCriterion("fans_count between", value1, value2, "fansCount");
			return (Criteria) this;
		}

		public Criteria andFansCountNotBetween(Integer value1, Integer value2) {
			addCriterion("fans_count not between", value1, value2, "fansCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountIsNull() {
			addCriterion("order_count is null");
			return (Criteria) this;
		}

		public Criteria andOrderCountIsNotNull() {
			addCriterion("order_count is not null");
			return (Criteria) this;
		}

		public Criteria andOrderCountEqualTo(Integer value) {
			addCriterion("order_count =", value, "orderCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountNotEqualTo(Integer value) {
			addCriterion("order_count <>", value, "orderCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountGreaterThan(Integer value) {
			addCriterion("order_count >", value, "orderCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("order_count >=", value, "orderCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountLessThan(Integer value) {
			addCriterion("order_count <", value, "orderCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountLessThanOrEqualTo(Integer value) {
			addCriterion("order_count <=", value, "orderCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountIn(List<Integer> values) {
			addCriterion("order_count in", values, "orderCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountNotIn(List<Integer> values) {
			addCriterion("order_count not in", values, "orderCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountBetween(Integer value1, Integer value2) {
			addCriterion("order_count between", value1, value2, "orderCount");
			return (Criteria) this;
		}

		public Criteria andOrderCountNotBetween(Integer value1, Integer value2) {
			addCriterion("order_count not between", value1, value2, "orderCount");
			return (Criteria) this;
		}

		public Criteria andAuthStatusIsNull() {
			addCriterion("auth_status is null");
			return (Criteria) this;
		}

		public Criteria andAuthStatusIsNotNull() {
			addCriterion("auth_status is not null");
			return (Criteria) this;
		}

		public Criteria andAuthStatusEqualTo(Integer value) {
			addCriterion("auth_status =", value, "authStatus");
			return (Criteria) this;
		}

		public Criteria andAuthStatusNotEqualTo(Integer value) {
			addCriterion("auth_status <>", value, "authStatus");
			return (Criteria) this;
		}

		public Criteria andAuthStatusGreaterThan(Integer value) {
			addCriterion("auth_status >", value, "authStatus");
			return (Criteria) this;
		}

		public Criteria andAuthStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("auth_status >=", value, "authStatus");
			return (Criteria) this;
		}

		public Criteria andAuthStatusLessThan(Integer value) {
			addCriterion("auth_status <", value, "authStatus");
			return (Criteria) this;
		}

		public Criteria andAuthStatusLessThanOrEqualTo(Integer value) {
			addCriterion("auth_status <=", value, "authStatus");
			return (Criteria) this;
		}

		public Criteria andAuthStatusIn(List<Integer> values) {
			addCriterion("auth_status in", values, "authStatus");
			return (Criteria) this;
		}

		public Criteria andAuthStatusNotIn(List<Integer> values) {
			addCriterion("auth_status not in", values, "authStatus");
			return (Criteria) this;
		}

		public Criteria andAuthStatusBetween(Integer value1, Integer value2) {
			addCriterion("auth_status between", value1, value2, "authStatus");
			return (Criteria) this;
		}

		public Criteria andAuthStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("auth_status not between", value1, value2, "authStatus");
			return (Criteria) this;
		}

		public Criteria andTxsnIsNull() {
			addCriterion("txsn is null");
			return (Criteria) this;
		}

		public Criteria andTxsnIsNotNull() {
			addCriterion("txsn is not null");
			return (Criteria) this;
		}

		public Criteria andTxsnEqualTo(String value) {
			addCriterion("txsn =", value, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnNotEqualTo(String value) {
			addCriterion("txsn <>", value, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnGreaterThan(String value) {
			addCriterion("txsn >", value, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnGreaterThanOrEqualTo(String value) {
			addCriterion("txsn >=", value, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnLessThan(String value) {
			addCriterion("txsn <", value, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnLessThanOrEqualTo(String value) {
			addCriterion("txsn <=", value, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnLike(String value) {
			addCriterion("txsn like", value, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnNotLike(String value) {
			addCriterion("txsn not like", value, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnIn(List<String> values) {
			addCriterion("txsn in", values, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnNotIn(List<String> values) {
			addCriterion("txsn not in", values, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnBetween(String value1, String value2) {
			addCriterion("txsn between", value1, value2, "txsn");
			return (Criteria) this;
		}

		public Criteria andTxsnNotBetween(String value1, String value2) {
			addCriterion("txsn not between", value1, value2, "txsn");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdIsNull() {
			addCriterion("anxinsign_id is null");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdIsNotNull() {
			addCriterion("anxinsign_id is not null");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdEqualTo(String value) {
			addCriterion("anxinsign_id =", value, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdNotEqualTo(String value) {
			addCriterion("anxinsign_id <>", value, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdGreaterThan(String value) {
			addCriterion("anxinsign_id >", value, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdGreaterThanOrEqualTo(String value) {
			addCriterion("anxinsign_id >=", value, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdLessThan(String value) {
			addCriterion("anxinsign_id <", value, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdLessThanOrEqualTo(String value) {
			addCriterion("anxinsign_id <=", value, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdLike(String value) {
			addCriterion("anxinsign_id like", value, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdNotLike(String value) {
			addCriterion("anxinsign_id not like", value, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdIn(List<String> values) {
			addCriterion("anxinsign_id in", values, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdNotIn(List<String> values) {
			addCriterion("anxinsign_id not in", values, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdBetween(String value1, String value2) {
			addCriterion("anxinsign_id between", value1, value2, "anxinsignId");
			return (Criteria) this;
		}

		public Criteria andAnxinsignIdNotBetween(String value1, String value2) {
			addCriterion("anxinsign_id not between", value1, value2, "anxinsignId");
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