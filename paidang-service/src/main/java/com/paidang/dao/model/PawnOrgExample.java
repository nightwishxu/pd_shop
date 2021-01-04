package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class PawnOrgExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public PawnOrgExample(){
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

		public Criteria andRoleCodeIsNull() {
			addCriterion("role_code is null");
			return (Criteria) this;
		}

		public Criteria andRoleCodeIsNotNull() {
			addCriterion("role_code is not null");
			return (Criteria) this;
		}

		public Criteria andRoleCodeEqualTo(String value) {
			addCriterion("role_code =", value, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeNotEqualTo(String value) {
			addCriterion("role_code <>", value, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeGreaterThan(String value) {
			addCriterion("role_code >", value, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeGreaterThanOrEqualTo(String value) {
			addCriterion("role_code >=", value, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeLessThan(String value) {
			addCriterion("role_code <", value, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeLessThanOrEqualTo(String value) {
			addCriterion("role_code <=", value, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeLike(String value) {
			addCriterion("role_code like", value, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeNotLike(String value) {
			addCriterion("role_code not like", value, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeIn(List<String> values) {
			addCriterion("role_code in", values, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeNotIn(List<String> values) {
			addCriterion("role_code not in", values, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeBetween(String value1, String value2) {
			addCriterion("role_code between", value1, value2, "roleCode");
			return (Criteria) this;
		}

		public Criteria andRoleCodeNotBetween(String value1, String value2) {
			addCriterion("role_code not between", value1, value2, "roleCode");
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

		public Criteria andLegalPersonIsNull() {
			addCriterion("legal_person is null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonIsNotNull() {
			addCriterion("legal_person is not null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonEqualTo(String value) {
			addCriterion("legal_person =", value, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNotEqualTo(String value) {
			addCriterion("legal_person <>", value, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonGreaterThan(String value) {
			addCriterion("legal_person >", value, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonGreaterThanOrEqualTo(String value) {
			addCriterion("legal_person >=", value, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonLessThan(String value) {
			addCriterion("legal_person <", value, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonLessThanOrEqualTo(String value) {
			addCriterion("legal_person <=", value, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonLike(String value) {
			addCriterion("legal_person like", value, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNotLike(String value) {
			addCriterion("legal_person not like", value, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonIn(List<String> values) {
			addCriterion("legal_person in", values, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNotIn(List<String> values) {
			addCriterion("legal_person not in", values, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonBetween(String value1, String value2) {
			addCriterion("legal_person between", value1, value2, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNotBetween(String value1, String value2) {
			addCriterion("legal_person not between", value1, value2, "legalPerson");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalIsNull() {
			addCriterion("registered_capital is null");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalIsNotNull() {
			addCriterion("registered_capital is not null");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalEqualTo(String value) {
			addCriterion("registered_capital =", value, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalNotEqualTo(String value) {
			addCriterion("registered_capital <>", value, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalGreaterThan(String value) {
			addCriterion("registered_capital >", value, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalGreaterThanOrEqualTo(String value) {
			addCriterion("registered_capital >=", value, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalLessThan(String value) {
			addCriterion("registered_capital <", value, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalLessThanOrEqualTo(String value) {
			addCriterion("registered_capital <=", value, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalLike(String value) {
			addCriterion("registered_capital like", value, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalNotLike(String value) {
			addCriterion("registered_capital not like", value, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalIn(List<String> values) {
			addCriterion("registered_capital in", values, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalNotIn(List<String> values) {
			addCriterion("registered_capital not in", values, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalBetween(String value1, String value2) {
			addCriterion("registered_capital between", value1, value2, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andRegisteredCapitalNotBetween(String value1, String value2) {
			addCriterion("registered_capital not between", value1, value2, "registeredCapital");
			return (Criteria) this;
		}

		public Criteria andAdressIsNull() {
			addCriterion("adress is null");
			return (Criteria) this;
		}

		public Criteria andAdressIsNotNull() {
			addCriterion("adress is not null");
			return (Criteria) this;
		}

		public Criteria andAdressEqualTo(String value) {
			addCriterion("adress =", value, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressNotEqualTo(String value) {
			addCriterion("adress <>", value, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressGreaterThan(String value) {
			addCriterion("adress >", value, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressGreaterThanOrEqualTo(String value) {
			addCriterion("adress >=", value, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressLessThan(String value) {
			addCriterion("adress <", value, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressLessThanOrEqualTo(String value) {
			addCriterion("adress <=", value, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressLike(String value) {
			addCriterion("adress like", value, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressNotLike(String value) {
			addCriterion("adress not like", value, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressIn(List<String> values) {
			addCriterion("adress in", values, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressNotIn(List<String> values) {
			addCriterion("adress not in", values, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressBetween(String value1, String value2) {
			addCriterion("adress between", value1, value2, "adress");
			return (Criteria) this;
		}

		public Criteria andAdressNotBetween(String value1, String value2) {
			addCriterion("adress not between", value1, value2, "adress");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeIsNull() {
			addCriterion("business_license_code is null");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeIsNotNull() {
			addCriterion("business_license_code is not null");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeEqualTo(String value) {
			addCriterion("business_license_code =", value, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeNotEqualTo(String value) {
			addCriterion("business_license_code <>", value, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeGreaterThan(String value) {
			addCriterion("business_license_code >", value, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeGreaterThanOrEqualTo(String value) {
			addCriterion("business_license_code >=", value, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeLessThan(String value) {
			addCriterion("business_license_code <", value, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeLessThanOrEqualTo(String value) {
			addCriterion("business_license_code <=", value, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeLike(String value) {
			addCriterion("business_license_code like", value, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeNotLike(String value) {
			addCriterion("business_license_code not like", value, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeIn(List<String> values) {
			addCriterion("business_license_code in", values, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeNotIn(List<String> values) {
			addCriterion("business_license_code not in", values, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeBetween(String value1, String value2) {
			addCriterion("business_license_code between", value1, value2, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseCodeNotBetween(String value1, String value2) {
			addCriterion("business_license_code not between", value1, value2, "businessLicenseCode");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseIsNull() {
			addCriterion("business_license is null");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseIsNotNull() {
			addCriterion("business_license is not null");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseEqualTo(String value) {
			addCriterion("business_license =", value, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseNotEqualTo(String value) {
			addCriterion("business_license <>", value, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseGreaterThan(String value) {
			addCriterion("business_license >", value, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseGreaterThanOrEqualTo(String value) {
			addCriterion("business_license >=", value, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseLessThan(String value) {
			addCriterion("business_license <", value, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseLessThanOrEqualTo(String value) {
			addCriterion("business_license <=", value, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseLike(String value) {
			addCriterion("business_license like", value, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseNotLike(String value) {
			addCriterion("business_license not like", value, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseIn(List<String> values) {
			addCriterion("business_license in", values, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseNotIn(List<String> values) {
			addCriterion("business_license not in", values, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseBetween(String value1, String value2) {
			addCriterion("business_license between", value1, value2, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andBusinessLicenseNotBetween(String value1, String value2) {
			addCriterion("business_license not between", value1, value2, "businessLicense");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeIsNull() {
			addCriterion("expiration_remind_type is null");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeIsNotNull() {
			addCriterion("expiration_remind_type is not null");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeEqualTo(Integer value) {
			addCriterion("expiration_remind_type =", value, "expirationRemindType");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeNotEqualTo(Integer value) {
			addCriterion("expiration_remind_type <>", value, "expirationRemindType");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeGreaterThan(Integer value) {
			addCriterion("expiration_remind_type >", value, "expirationRemindType");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("expiration_remind_type >=", value, "expirationRemindType");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeLessThan(Integer value) {
			addCriterion("expiration_remind_type <", value, "expirationRemindType");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeLessThanOrEqualTo(Integer value) {
			addCriterion("expiration_remind_type <=", value, "expirationRemindType");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeIn(List<Integer> values) {
			addCriterion("expiration_remind_type in", values, "expirationRemindType");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeNotIn(List<Integer> values) {
			addCriterion("expiration_remind_type not in", values, "expirationRemindType");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeBetween(Integer value1, Integer value2) {
			addCriterion("expiration_remind_type between", value1, value2, "expirationRemindType");
			return (Criteria) this;
		}

		public Criteria andExpirationRemindTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("expiration_remind_type not between", value1, value2, "expirationRemindType");
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

		public Criteria andPawnStateIsNull() {
			addCriterion("pawn_state is null");
			return (Criteria) this;
		}

		public Criteria andPawnStateIsNotNull() {
			addCriterion("pawn_state is not null");
			return (Criteria) this;
		}

		public Criteria andPawnStateEqualTo(Integer value) {
			addCriterion("pawn_state =", value, "pawnState");
			return (Criteria) this;
		}

		public Criteria andPawnStateNotEqualTo(Integer value) {
			addCriterion("pawn_state <>", value, "pawnState");
			return (Criteria) this;
		}

		public Criteria andPawnStateGreaterThan(Integer value) {
			addCriterion("pawn_state >", value, "pawnState");
			return (Criteria) this;
		}

		public Criteria andPawnStateGreaterThanOrEqualTo(Integer value) {
			addCriterion("pawn_state >=", value, "pawnState");
			return (Criteria) this;
		}

		public Criteria andPawnStateLessThan(Integer value) {
			addCriterion("pawn_state <", value, "pawnState");
			return (Criteria) this;
		}

		public Criteria andPawnStateLessThanOrEqualTo(Integer value) {
			addCriterion("pawn_state <=", value, "pawnState");
			return (Criteria) this;
		}

		public Criteria andPawnStateIn(List<Integer> values) {
			addCriterion("pawn_state in", values, "pawnState");
			return (Criteria) this;
		}

		public Criteria andPawnStateNotIn(List<Integer> values) {
			addCriterion("pawn_state not in", values, "pawnState");
			return (Criteria) this;
		}

		public Criteria andPawnStateBetween(Integer value1, Integer value2) {
			addCriterion("pawn_state between", value1, value2, "pawnState");
			return (Criteria) this;
		}

		public Criteria andPawnStateNotBetween(Integer value1, Integer value2) {
			addCriterion("pawn_state not between", value1, value2, "pawnState");
			return (Criteria) this;
		}

		public Criteria andOverdueRateIsNull() {
			addCriterion("overdue_rate is null");
			return (Criteria) this;
		}

		public Criteria andOverdueRateIsNotNull() {
			addCriterion("overdue_rate is not null");
			return (Criteria) this;
		}

		public Criteria andOverdueRateEqualTo(java.math.BigDecimal value) {
			addCriterion("overdue_rate =", value, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andOverdueRateNotEqualTo(java.math.BigDecimal value) {
			addCriterion("overdue_rate <>", value, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andOverdueRateGreaterThan(java.math.BigDecimal value) {
			addCriterion("overdue_rate >", value, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andOverdueRateGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("overdue_rate >=", value, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andOverdueRateLessThan(java.math.BigDecimal value) {
			addCriterion("overdue_rate <", value, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andOverdueRateLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("overdue_rate <=", value, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andOverdueRateIn(List<java.math.BigDecimal> values) {
			addCriterion("overdue_rate in", values, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andOverdueRateNotIn(List<java.math.BigDecimal> values) {
			addCriterion("overdue_rate not in", values, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andOverdueRateBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("overdue_rate between", value1, value2, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andOverdueRateNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("overdue_rate not between", value1, value2, "overdueRate");
			return (Criteria) this;
		}

		public Criteria andSealIsNull() {
			addCriterion("seal is null");
			return (Criteria) this;
		}

		public Criteria andSealIsNotNull() {
			addCriterion("seal is not null");
			return (Criteria) this;
		}

		public Criteria andSealEqualTo(String value) {
			addCriterion("seal =", value, "seal");
			return (Criteria) this;
		}

		public Criteria andSealNotEqualTo(String value) {
			addCriterion("seal <>", value, "seal");
			return (Criteria) this;
		}

		public Criteria andSealGreaterThan(String value) {
			addCriterion("seal >", value, "seal");
			return (Criteria) this;
		}

		public Criteria andSealGreaterThanOrEqualTo(String value) {
			addCriterion("seal >=", value, "seal");
			return (Criteria) this;
		}

		public Criteria andSealLessThan(String value) {
			addCriterion("seal <", value, "seal");
			return (Criteria) this;
		}

		public Criteria andSealLessThanOrEqualTo(String value) {
			addCriterion("seal <=", value, "seal");
			return (Criteria) this;
		}

		public Criteria andSealLike(String value) {
			addCriterion("seal like", value, "seal");
			return (Criteria) this;
		}

		public Criteria andSealNotLike(String value) {
			addCriterion("seal not like", value, "seal");
			return (Criteria) this;
		}

		public Criteria andSealIn(List<String> values) {
			addCriterion("seal in", values, "seal");
			return (Criteria) this;
		}

		public Criteria andSealNotIn(List<String> values) {
			addCriterion("seal not in", values, "seal");
			return (Criteria) this;
		}

		public Criteria andSealBetween(String value1, String value2) {
			addCriterion("seal between", value1, value2, "seal");
			return (Criteria) this;
		}

		public Criteria andSealNotBetween(String value1, String value2) {
			addCriterion("seal not between", value1, value2, "seal");
			return (Criteria) this;
		}

		public Criteria andIntroductionIsNull() {
			addCriterion("introduction is null");
			return (Criteria) this;
		}

		public Criteria andIntroductionIsNotNull() {
			addCriterion("introduction is not null");
			return (Criteria) this;
		}

		public Criteria andIntroductionEqualTo(String value) {
			addCriterion("introduction =", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionNotEqualTo(String value) {
			addCriterion("introduction <>", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionGreaterThan(String value) {
			addCriterion("introduction >", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionGreaterThanOrEqualTo(String value) {
			addCriterion("introduction >=", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionLessThan(String value) {
			addCriterion("introduction <", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionLessThanOrEqualTo(String value) {
			addCriterion("introduction <=", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionLike(String value) {
			addCriterion("introduction like", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionNotLike(String value) {
			addCriterion("introduction not like", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionIn(List<String> values) {
			addCriterion("introduction in", values, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionNotIn(List<String> values) {
			addCriterion("introduction not in", values, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionBetween(String value1, String value2) {
			addCriterion("introduction between", value1, value2, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionNotBetween(String value1, String value2) {
			addCriterion("introduction not between", value1, value2, "introduction");
			return (Criteria) this;
		}

		public Criteria andOrgImagesIsNull() {
			addCriterion("org_images is null");
			return (Criteria) this;
		}

		public Criteria andOrgImagesIsNotNull() {
			addCriterion("org_images is not null");
			return (Criteria) this;
		}

		public Criteria andOrgImagesEqualTo(String value) {
			addCriterion("org_images =", value, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesNotEqualTo(String value) {
			addCriterion("org_images <>", value, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesGreaterThan(String value) {
			addCriterion("org_images >", value, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesGreaterThanOrEqualTo(String value) {
			addCriterion("org_images >=", value, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesLessThan(String value) {
			addCriterion("org_images <", value, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesLessThanOrEqualTo(String value) {
			addCriterion("org_images <=", value, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesLike(String value) {
			addCriterion("org_images like", value, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesNotLike(String value) {
			addCriterion("org_images not like", value, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesIn(List<String> values) {
			addCriterion("org_images in", values, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesNotIn(List<String> values) {
			addCriterion("org_images not in", values, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesBetween(String value1, String value2) {
			addCriterion("org_images between", value1, value2, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgImagesNotBetween(String value1, String value2) {
			addCriterion("org_images not between", value1, value2, "orgImages");
			return (Criteria) this;
		}

		public Criteria andOrgLogoIsNull() {
			addCriterion("org_logo is null");
			return (Criteria) this;
		}

		public Criteria andOrgLogoIsNotNull() {
			addCriterion("org_logo is not null");
			return (Criteria) this;
		}

		public Criteria andOrgLogoEqualTo(String value) {
			addCriterion("org_logo =", value, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoNotEqualTo(String value) {
			addCriterion("org_logo <>", value, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoGreaterThan(String value) {
			addCriterion("org_logo >", value, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoGreaterThanOrEqualTo(String value) {
			addCriterion("org_logo >=", value, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoLessThan(String value) {
			addCriterion("org_logo <", value, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoLessThanOrEqualTo(String value) {
			addCriterion("org_logo <=", value, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoLike(String value) {
			addCriterion("org_logo like", value, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoNotLike(String value) {
			addCriterion("org_logo not like", value, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoIn(List<String> values) {
			addCriterion("org_logo in", values, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoNotIn(List<String> values) {
			addCriterion("org_logo not in", values, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoBetween(String value1, String value2) {
			addCriterion("org_logo between", value1, value2, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andOrgLogoNotBetween(String value1, String value2) {
			addCriterion("org_logo not between", value1, value2, "orgLogo");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateIsNull() {
			addCriterion("redeem_overrate is null");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateIsNotNull() {
			addCriterion("redeem_overrate is not null");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateEqualTo(java.math.BigDecimal value) {
			addCriterion("redeem_overrate =", value, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateNotEqualTo(java.math.BigDecimal value) {
			addCriterion("redeem_overrate <>", value, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateGreaterThan(java.math.BigDecimal value) {
			addCriterion("redeem_overrate >", value, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("redeem_overrate >=", value, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateLessThan(java.math.BigDecimal value) {
			addCriterion("redeem_overrate <", value, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("redeem_overrate <=", value, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateIn(List<java.math.BigDecimal> values) {
			addCriterion("redeem_overrate in", values, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateNotIn(List<java.math.BigDecimal> values) {
			addCriterion("redeem_overrate not in", values, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("redeem_overrate between", value1, value2, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andRedeemOverrateNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("redeem_overrate not between", value1, value2, "redeemOverrate");
			return (Criteria) this;
		}

		public Criteria andLabelsIsNull() {
			addCriterion("labels is null");
			return (Criteria) this;
		}

		public Criteria andLabelsIsNotNull() {
			addCriterion("labels is not null");
			return (Criteria) this;
		}

		public Criteria andLabelsEqualTo(String value) {
			addCriterion("labels =", value, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsNotEqualTo(String value) {
			addCriterion("labels <>", value, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsGreaterThan(String value) {
			addCriterion("labels >", value, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsGreaterThanOrEqualTo(String value) {
			addCriterion("labels >=", value, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsLessThan(String value) {
			addCriterion("labels <", value, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsLessThanOrEqualTo(String value) {
			addCriterion("labels <=", value, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsLike(String value) {
			addCriterion("labels like", value, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsNotLike(String value) {
			addCriterion("labels not like", value, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsIn(List<String> values) {
			addCriterion("labels in", values, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsNotIn(List<String> values) {
			addCriterion("labels not in", values, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsBetween(String value1, String value2) {
			addCriterion("labels between", value1, value2, "labels");
			return (Criteria) this;
		}

		public Criteria andLabelsNotBetween(String value1, String value2) {
			addCriterion("labels not between", value1, value2, "labels");
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

		public Criteria andScoreIsNull() {
			addCriterion("score is null");
			return (Criteria) this;
		}

		public Criteria andScoreIsNotNull() {
			addCriterion("score is not null");
			return (Criteria) this;
		}

		public Criteria andScoreEqualTo(java.math.BigDecimal value) {
			addCriterion("score =", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotEqualTo(java.math.BigDecimal value) {
			addCriterion("score <>", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThan(java.math.BigDecimal value) {
			addCriterion("score >", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("score >=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThan(java.math.BigDecimal value) {
			addCriterion("score <", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("score <=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreIn(List<java.math.BigDecimal> values) {
			addCriterion("score in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotIn(List<java.math.BigDecimal> values) {
			addCriterion("score not in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("score between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("score not between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andCommentCountIsNull() {
			addCriterion("comment_count is null");
			return (Criteria) this;
		}

		public Criteria andCommentCountIsNotNull() {
			addCriterion("comment_count is not null");
			return (Criteria) this;
		}

		public Criteria andCommentCountEqualTo(Integer value) {
			addCriterion("comment_count =", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountNotEqualTo(Integer value) {
			addCriterion("comment_count <>", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountGreaterThan(Integer value) {
			addCriterion("comment_count >", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("comment_count >=", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountLessThan(Integer value) {
			addCriterion("comment_count <", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountLessThanOrEqualTo(Integer value) {
			addCriterion("comment_count <=", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountIn(List<Integer> values) {
			addCriterion("comment_count in", values, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountNotIn(List<Integer> values) {
			addCriterion("comment_count not in", values, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountBetween(Integer value1, Integer value2) {
			addCriterion("comment_count between", value1, value2, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountNotBetween(Integer value1, Integer value2) {
			addCriterion("comment_count not between", value1, value2, "commentCount");
			return (Criteria) this;
		}

		public Criteria andExpressScoreIsNull() {
			addCriterion("express_score is null");
			return (Criteria) this;
		}

		public Criteria andExpressScoreIsNotNull() {
			addCriterion("express_score is not null");
			return (Criteria) this;
		}

		public Criteria andExpressScoreEqualTo(java.math.BigDecimal value) {
			addCriterion("express_score =", value, "expressScore");
			return (Criteria) this;
		}

		public Criteria andExpressScoreNotEqualTo(java.math.BigDecimal value) {
			addCriterion("express_score <>", value, "expressScore");
			return (Criteria) this;
		}

		public Criteria andExpressScoreGreaterThan(java.math.BigDecimal value) {
			addCriterion("express_score >", value, "expressScore");
			return (Criteria) this;
		}

		public Criteria andExpressScoreGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("express_score >=", value, "expressScore");
			return (Criteria) this;
		}

		public Criteria andExpressScoreLessThan(java.math.BigDecimal value) {
			addCriterion("express_score <", value, "expressScore");
			return (Criteria) this;
		}

		public Criteria andExpressScoreLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("express_score <=", value, "expressScore");
			return (Criteria) this;
		}

		public Criteria andExpressScoreIn(List<java.math.BigDecimal> values) {
			addCriterion("express_score in", values, "expressScore");
			return (Criteria) this;
		}

		public Criteria andExpressScoreNotIn(List<java.math.BigDecimal> values) {
			addCriterion("express_score not in", values, "expressScore");
			return (Criteria) this;
		}

		public Criteria andExpressScoreBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("express_score between", value1, value2, "expressScore");
			return (Criteria) this;
		}

		public Criteria andExpressScoreNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("express_score not between", value1, value2, "expressScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreIsNull() {
			addCriterion("service_score is null");
			return (Criteria) this;
		}

		public Criteria andServiceScoreIsNotNull() {
			addCriterion("service_score is not null");
			return (Criteria) this;
		}

		public Criteria andServiceScoreEqualTo(java.math.BigDecimal value) {
			addCriterion("service_score =", value, "serviceScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreNotEqualTo(java.math.BigDecimal value) {
			addCriterion("service_score <>", value, "serviceScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreGreaterThan(java.math.BigDecimal value) {
			addCriterion("service_score >", value, "serviceScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("service_score >=", value, "serviceScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreLessThan(java.math.BigDecimal value) {
			addCriterion("service_score <", value, "serviceScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("service_score <=", value, "serviceScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreIn(List<java.math.BigDecimal> values) {
			addCriterion("service_score in", values, "serviceScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreNotIn(List<java.math.BigDecimal> values) {
			addCriterion("service_score not in", values, "serviceScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("service_score between", value1, value2, "serviceScore");
			return (Criteria) this;
		}

		public Criteria andServiceScoreNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("service_score not between", value1, value2, "serviceScore");
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

		public Criteria andPawnExequaturIsNull() {
			addCriterion("pawn_exequatur is null");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturIsNotNull() {
			addCriterion("pawn_exequatur is not null");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturEqualTo(String value) {
			addCriterion("pawn_exequatur =", value, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturNotEqualTo(String value) {
			addCriterion("pawn_exequatur <>", value, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturGreaterThan(String value) {
			addCriterion("pawn_exequatur >", value, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturGreaterThanOrEqualTo(String value) {
			addCriterion("pawn_exequatur >=", value, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturLessThan(String value) {
			addCriterion("pawn_exequatur <", value, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturLessThanOrEqualTo(String value) {
			addCriterion("pawn_exequatur <=", value, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturLike(String value) {
			addCriterion("pawn_exequatur like", value, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturNotLike(String value) {
			addCriterion("pawn_exequatur not like", value, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturIn(List<String> values) {
			addCriterion("pawn_exequatur in", values, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturNotIn(List<String> values) {
			addCriterion("pawn_exequatur not in", values, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturBetween(String value1, String value2) {
			addCriterion("pawn_exequatur between", value1, value2, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andPawnExequaturNotBetween(String value1, String value2) {
			addCriterion("pawn_exequatur not between", value1, value2, "pawnExequatur");
			return (Criteria) this;
		}

		public Criteria andOtherFileIsNull() {
			addCriterion("other_file is null");
			return (Criteria) this;
		}

		public Criteria andOtherFileIsNotNull() {
			addCriterion("other_file is not null");
			return (Criteria) this;
		}

		public Criteria andOtherFileEqualTo(String value) {
			addCriterion("other_file =", value, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileNotEqualTo(String value) {
			addCriterion("other_file <>", value, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileGreaterThan(String value) {
			addCriterion("other_file >", value, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileGreaterThanOrEqualTo(String value) {
			addCriterion("other_file >=", value, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileLessThan(String value) {
			addCriterion("other_file <", value, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileLessThanOrEqualTo(String value) {
			addCriterion("other_file <=", value, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileLike(String value) {
			addCriterion("other_file like", value, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileNotLike(String value) {
			addCriterion("other_file not like", value, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileIn(List<String> values) {
			addCriterion("other_file in", values, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileNotIn(List<String> values) {
			addCriterion("other_file not in", values, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileBetween(String value1, String value2) {
			addCriterion("other_file between", value1, value2, "otherFile");
			return (Criteria) this;
		}

		public Criteria andOtherFileNotBetween(String value1, String value2) {
			addCriterion("other_file not between", value1, value2, "otherFile");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneIsNull() {
			addCriterion("legal_person_phone is null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneIsNotNull() {
			addCriterion("legal_person_phone is not null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneEqualTo(String value) {
			addCriterion("legal_person_phone =", value, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneNotEqualTo(String value) {
			addCriterion("legal_person_phone <>", value, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneGreaterThan(String value) {
			addCriterion("legal_person_phone >", value, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("legal_person_phone >=", value, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneLessThan(String value) {
			addCriterion("legal_person_phone <", value, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneLessThanOrEqualTo(String value) {
			addCriterion("legal_person_phone <=", value, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneLike(String value) {
			addCriterion("legal_person_phone like", value, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneNotLike(String value) {
			addCriterion("legal_person_phone not like", value, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneIn(List<String> values) {
			addCriterion("legal_person_phone in", values, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneNotIn(List<String> values) {
			addCriterion("legal_person_phone not in", values, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneBetween(String value1, String value2) {
			addCriterion("legal_person_phone between", value1, value2, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andLegalPersonPhoneNotBetween(String value1, String value2) {
			addCriterion("legal_person_phone not between", value1, value2, "legalPersonPhone");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositIsNull() {
			addCriterion("is_store_deposit is null");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositIsNotNull() {
			addCriterion("is_store_deposit is not null");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositEqualTo(Integer value) {
			addCriterion("is_store_deposit =", value, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositNotEqualTo(Integer value) {
			addCriterion("is_store_deposit <>", value, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositGreaterThan(Integer value) {
			addCriterion("is_store_deposit >", value, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_store_deposit >=", value, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositLessThan(Integer value) {
			addCriterion("is_store_deposit <", value, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositLessThanOrEqualTo(Integer value) {
			addCriterion("is_store_deposit <=", value, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositIn(List<Integer> values) {
			addCriterion("is_store_deposit in", values, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositNotIn(List<Integer> values) {
			addCriterion("is_store_deposit not in", values, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositBetween(Integer value1, Integer value2) {
			addCriterion("is_store_deposit between", value1, value2, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andIsStoreDepositNotBetween(Integer value1, Integer value2) {
			addCriterion("is_store_deposit not between", value1, value2, "isStoreDeposit");
			return (Criteria) this;
		}

		public Criteria andDepositAmountIsNull() {
			addCriterion("deposit_amount is null");
			return (Criteria) this;
		}

		public Criteria andDepositAmountIsNotNull() {
			addCriterion("deposit_amount is not null");
			return (Criteria) this;
		}

		public Criteria andDepositAmountEqualTo(java.math.BigDecimal value) {
			addCriterion("deposit_amount =", value, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andDepositAmountNotEqualTo(java.math.BigDecimal value) {
			addCriterion("deposit_amount <>", value, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andDepositAmountGreaterThan(java.math.BigDecimal value) {
			addCriterion("deposit_amount >", value, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andDepositAmountGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("deposit_amount >=", value, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andDepositAmountLessThan(java.math.BigDecimal value) {
			addCriterion("deposit_amount <", value, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andDepositAmountLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("deposit_amount <=", value, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andDepositAmountIn(List<java.math.BigDecimal> values) {
			addCriterion("deposit_amount in", values, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andDepositAmountNotIn(List<java.math.BigDecimal> values) {
			addCriterion("deposit_amount not in", values, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andDepositAmountBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("deposit_amount between", value1, value2, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andDepositAmountNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("deposit_amount not between", value1, value2, "depositAmount");
			return (Criteria) this;
		}

		public Criteria andSignatureIsNull() {
			addCriterion("signature is null");
			return (Criteria) this;
		}

		public Criteria andSignatureIsNotNull() {
			addCriterion("signature is not null");
			return (Criteria) this;
		}

		public Criteria andSignatureEqualTo(String value) {
			addCriterion("signature =", value, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureNotEqualTo(String value) {
			addCriterion("signature <>", value, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureGreaterThan(String value) {
			addCriterion("signature >", value, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureGreaterThanOrEqualTo(String value) {
			addCriterion("signature >=", value, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureLessThan(String value) {
			addCriterion("signature <", value, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureLessThanOrEqualTo(String value) {
			addCriterion("signature <=", value, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureLike(String value) {
			addCriterion("signature like", value, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureNotLike(String value) {
			addCriterion("signature not like", value, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureIn(List<String> values) {
			addCriterion("signature in", values, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureNotIn(List<String> values) {
			addCriterion("signature not in", values, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureBetween(String value1, String value2) {
			addCriterion("signature between", value1, value2, "signature");
			return (Criteria) this;
		}

		public Criteria andSignatureNotBetween(String value1, String value2) {
			addCriterion("signature not between", value1, value2, "signature");
			return (Criteria) this;
		}

		public Criteria andStoreContactsIsNull() {
			addCriterion("store_contacts is null");
			return (Criteria) this;
		}

		public Criteria andStoreContactsIsNotNull() {
			addCriterion("store_contacts is not null");
			return (Criteria) this;
		}

		public Criteria andStoreContactsEqualTo(String value) {
			addCriterion("store_contacts =", value, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsNotEqualTo(String value) {
			addCriterion("store_contacts <>", value, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsGreaterThan(String value) {
			addCriterion("store_contacts >", value, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsGreaterThanOrEqualTo(String value) {
			addCriterion("store_contacts >=", value, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsLessThan(String value) {
			addCriterion("store_contacts <", value, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsLessThanOrEqualTo(String value) {
			addCriterion("store_contacts <=", value, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsLike(String value) {
			addCriterion("store_contacts like", value, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsNotLike(String value) {
			addCriterion("store_contacts not like", value, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsIn(List<String> values) {
			addCriterion("store_contacts in", values, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsNotIn(List<String> values) {
			addCriterion("store_contacts not in", values, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsBetween(String value1, String value2) {
			addCriterion("store_contacts between", value1, value2, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStoreContactsNotBetween(String value1, String value2) {
			addCriterion("store_contacts not between", value1, value2, "storeContacts");
			return (Criteria) this;
		}

		public Criteria andStorePhoneIsNull() {
			addCriterion("store_phone is null");
			return (Criteria) this;
		}

		public Criteria andStorePhoneIsNotNull() {
			addCriterion("store_phone is not null");
			return (Criteria) this;
		}

		public Criteria andStorePhoneEqualTo(String value) {
			addCriterion("store_phone =", value, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneNotEqualTo(String value) {
			addCriterion("store_phone <>", value, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneGreaterThan(String value) {
			addCriterion("store_phone >", value, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneGreaterThanOrEqualTo(String value) {
			addCriterion("store_phone >=", value, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneLessThan(String value) {
			addCriterion("store_phone <", value, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneLessThanOrEqualTo(String value) {
			addCriterion("store_phone <=", value, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneLike(String value) {
			addCriterion("store_phone like", value, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneNotLike(String value) {
			addCriterion("store_phone not like", value, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneIn(List<String> values) {
			addCriterion("store_phone in", values, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneNotIn(List<String> values) {
			addCriterion("store_phone not in", values, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneBetween(String value1, String value2) {
			addCriterion("store_phone between", value1, value2, "storePhone");
			return (Criteria) this;
		}

		public Criteria andStorePhoneNotBetween(String value1, String value2) {
			addCriterion("store_phone not between", value1, value2, "storePhone");
			return (Criteria) this;
		}

		public Criteria andAmountIsNull() {
			addCriterion("amount is null");
			return (Criteria) this;
		}

		public Criteria andAmountIsNotNull() {
			addCriterion("amount is not null");
			return (Criteria) this;
		}

		public Criteria andAmountEqualTo(java.math.BigDecimal value) {
			addCriterion("amount =", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotEqualTo(java.math.BigDecimal value) {
			addCriterion("amount <>", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountGreaterThan(java.math.BigDecimal value) {
			addCriterion("amount >", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("amount >=", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountLessThan(java.math.BigDecimal value) {
			addCriterion("amount <", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("amount <=", value, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountIn(List<java.math.BigDecimal> values) {
			addCriterion("amount in", values, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotIn(List<java.math.BigDecimal> values) {
			addCriterion("amount not in", values, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("amount between", value1, value2, "amount");
			return (Criteria) this;
		}

		public Criteria andAmountNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("amount not between", value1, value2, "amount");
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

		public Criteria andLandLinePhoneIsNull() {
			addCriterion("land_line_phone is null");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneIsNotNull() {
			addCriterion("land_line_phone is not null");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneEqualTo(String value) {
			addCriterion("land_line_phone =", value, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneNotEqualTo(String value) {
			addCriterion("land_line_phone <>", value, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneGreaterThan(String value) {
			addCriterion("land_line_phone >", value, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneGreaterThanOrEqualTo(String value) {
			addCriterion("land_line_phone >=", value, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneLessThan(String value) {
			addCriterion("land_line_phone <", value, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneLessThanOrEqualTo(String value) {
			addCriterion("land_line_phone <=", value, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneLike(String value) {
			addCriterion("land_line_phone like", value, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneNotLike(String value) {
			addCriterion("land_line_phone not like", value, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneIn(List<String> values) {
			addCriterion("land_line_phone in", values, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneNotIn(List<String> values) {
			addCriterion("land_line_phone not in", values, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneBetween(String value1, String value2) {
			addCriterion("land_line_phone between", value1, value2, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andLandLinePhoneNotBetween(String value1, String value2) {
			addCriterion("land_line_phone not between", value1, value2, "landLinePhone");
			return (Criteria) this;
		}

		public Criteria andServiceRatesIsNull() {
			addCriterion("service_rates is null");
			return (Criteria) this;
		}

		public Criteria andServiceRatesIsNotNull() {
			addCriterion("service_rates is not null");
			return (Criteria) this;
		}

		public Criteria andServiceRatesEqualTo(java.math.BigDecimal value) {
			addCriterion("service_rates =", value, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andServiceRatesNotEqualTo(java.math.BigDecimal value) {
			addCriterion("service_rates <>", value, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andServiceRatesGreaterThan(java.math.BigDecimal value) {
			addCriterion("service_rates >", value, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andServiceRatesGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("service_rates >=", value, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andServiceRatesLessThan(java.math.BigDecimal value) {
			addCriterion("service_rates <", value, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andServiceRatesLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("service_rates <=", value, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andServiceRatesIn(List<java.math.BigDecimal> values) {
			addCriterion("service_rates in", values, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andServiceRatesNotIn(List<java.math.BigDecimal> values) {
			addCriterion("service_rates not in", values, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andServiceRatesBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("service_rates between", value1, value2, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andServiceRatesNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("service_rates not between", value1, value2, "serviceRates");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneIsNull() {
			addCriterion("anxin_phone is null");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneIsNotNull() {
			addCriterion("anxin_phone is not null");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneEqualTo(String value) {
			addCriterion("anxin_phone =", value, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneNotEqualTo(String value) {
			addCriterion("anxin_phone <>", value, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneGreaterThan(String value) {
			addCriterion("anxin_phone >", value, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("anxin_phone >=", value, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneLessThan(String value) {
			addCriterion("anxin_phone <", value, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneLessThanOrEqualTo(String value) {
			addCriterion("anxin_phone <=", value, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneLike(String value) {
			addCriterion("anxin_phone like", value, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneNotLike(String value) {
			addCriterion("anxin_phone not like", value, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneIn(List<String> values) {
			addCriterion("anxin_phone in", values, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneNotIn(List<String> values) {
			addCriterion("anxin_phone not in", values, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneBetween(String value1, String value2) {
			addCriterion("anxin_phone between", value1, value2, "anxinPhone");
			return (Criteria) this;
		}

		public Criteria andAnxinPhoneNotBetween(String value1, String value2) {
			addCriterion("anxin_phone not between", value1, value2, "anxinPhone");
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