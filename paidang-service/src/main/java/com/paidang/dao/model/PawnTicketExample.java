package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class PawnTicketExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public PawnTicketExample(){
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

		public Criteria andPawnTicketCodeIsNull() {
			addCriterion("pawn_ticket_code is null");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeIsNotNull() {
			addCriterion("pawn_ticket_code is not null");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeEqualTo(String value) {
			addCriterion("pawn_ticket_code =", value, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeNotEqualTo(String value) {
			addCriterion("pawn_ticket_code <>", value, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeGreaterThan(String value) {
			addCriterion("pawn_ticket_code >", value, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeGreaterThanOrEqualTo(String value) {
			addCriterion("pawn_ticket_code >=", value, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeLessThan(String value) {
			addCriterion("pawn_ticket_code <", value, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeLessThanOrEqualTo(String value) {
			addCriterion("pawn_ticket_code <=", value, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeLike(String value) {
			addCriterion("pawn_ticket_code like", value, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeNotLike(String value) {
			addCriterion("pawn_ticket_code not like", value, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeIn(List<String> values) {
			addCriterion("pawn_ticket_code in", values, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeNotIn(List<String> values) {
			addCriterion("pawn_ticket_code not in", values, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeBetween(String value1, String value2) {
			addCriterion("pawn_ticket_code between", value1, value2, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andPawnTicketCodeNotBetween(String value1, String value2) {
			addCriterion("pawn_ticket_code not between", value1, value2, "pawnTicketCode");
			return (Criteria) this;
		}

		public Criteria andOrgNameIsNull() {
			addCriterion("org_name is null");
			return (Criteria) this;
		}

		public Criteria andOrgNameIsNotNull() {
			addCriterion("org_name is not null");
			return (Criteria) this;
		}

		public Criteria andOrgNameEqualTo(String value) {
			addCriterion("org_name =", value, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameNotEqualTo(String value) {
			addCriterion("org_name <>", value, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameGreaterThan(String value) {
			addCriterion("org_name >", value, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
			addCriterion("org_name >=", value, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameLessThan(String value) {
			addCriterion("org_name <", value, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameLessThanOrEqualTo(String value) {
			addCriterion("org_name <=", value, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameLike(String value) {
			addCriterion("org_name like", value, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameNotLike(String value) {
			addCriterion("org_name not like", value, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameIn(List<String> values) {
			addCriterion("org_name in", values, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameNotIn(List<String> values) {
			addCriterion("org_name not in", values, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameBetween(String value1, String value2) {
			addCriterion("org_name between", value1, value2, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgNameNotBetween(String value1, String value2) {
			addCriterion("org_name not between", value1, value2, "orgName");
			return (Criteria) this;
		}

		public Criteria andOrgTelIsNull() {
			addCriterion("org_tel is null");
			return (Criteria) this;
		}

		public Criteria andOrgTelIsNotNull() {
			addCriterion("org_tel is not null");
			return (Criteria) this;
		}

		public Criteria andOrgTelEqualTo(String value) {
			addCriterion("org_tel =", value, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelNotEqualTo(String value) {
			addCriterion("org_tel <>", value, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelGreaterThan(String value) {
			addCriterion("org_tel >", value, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelGreaterThanOrEqualTo(String value) {
			addCriterion("org_tel >=", value, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelLessThan(String value) {
			addCriterion("org_tel <", value, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelLessThanOrEqualTo(String value) {
			addCriterion("org_tel <=", value, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelLike(String value) {
			addCriterion("org_tel like", value, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelNotLike(String value) {
			addCriterion("org_tel not like", value, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelIn(List<String> values) {
			addCriterion("org_tel in", values, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelNotIn(List<String> values) {
			addCriterion("org_tel not in", values, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelBetween(String value1, String value2) {
			addCriterion("org_tel between", value1, value2, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgTelNotBetween(String value1, String value2) {
			addCriterion("org_tel not between", value1, value2, "orgTel");
			return (Criteria) this;
		}

		public Criteria andOrgAdressIsNull() {
			addCriterion("org_adress is null");
			return (Criteria) this;
		}

		public Criteria andOrgAdressIsNotNull() {
			addCriterion("org_adress is not null");
			return (Criteria) this;
		}

		public Criteria andOrgAdressEqualTo(String value) {
			addCriterion("org_adress =", value, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressNotEqualTo(String value) {
			addCriterion("org_adress <>", value, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressGreaterThan(String value) {
			addCriterion("org_adress >", value, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressGreaterThanOrEqualTo(String value) {
			addCriterion("org_adress >=", value, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressLessThan(String value) {
			addCriterion("org_adress <", value, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressLessThanOrEqualTo(String value) {
			addCriterion("org_adress <=", value, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressLike(String value) {
			addCriterion("org_adress like", value, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressNotLike(String value) {
			addCriterion("org_adress not like", value, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressIn(List<String> values) {
			addCriterion("org_adress in", values, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressNotIn(List<String> values) {
			addCriterion("org_adress not in", values, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressBetween(String value1, String value2) {
			addCriterion("org_adress between", value1, value2, "orgAdress");
			return (Criteria) this;
		}

		public Criteria andOrgAdressNotBetween(String value1, String value2) {
			addCriterion("org_adress not between", value1, value2, "orgAdress");
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

		public Criteria andPawnerNameIsNull() {
			addCriterion("pawner_name is null");
			return (Criteria) this;
		}

		public Criteria andPawnerNameIsNotNull() {
			addCriterion("pawner_name is not null");
			return (Criteria) this;
		}

		public Criteria andPawnerNameEqualTo(String value) {
			addCriterion("pawner_name =", value, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameNotEqualTo(String value) {
			addCriterion("pawner_name <>", value, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameGreaterThan(String value) {
			addCriterion("pawner_name >", value, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameGreaterThanOrEqualTo(String value) {
			addCriterion("pawner_name >=", value, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameLessThan(String value) {
			addCriterion("pawner_name <", value, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameLessThanOrEqualTo(String value) {
			addCriterion("pawner_name <=", value, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameLike(String value) {
			addCriterion("pawner_name like", value, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameNotLike(String value) {
			addCriterion("pawner_name not like", value, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameIn(List<String> values) {
			addCriterion("pawner_name in", values, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameNotIn(List<String> values) {
			addCriterion("pawner_name not in", values, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameBetween(String value1, String value2) {
			addCriterion("pawner_name between", value1, value2, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerNameNotBetween(String value1, String value2) {
			addCriterion("pawner_name not between", value1, value2, "pawnerName");
			return (Criteria) this;
		}

		public Criteria andPawnerTelIsNull() {
			addCriterion("pawner_tel is null");
			return (Criteria) this;
		}

		public Criteria andPawnerTelIsNotNull() {
			addCriterion("pawner_tel is not null");
			return (Criteria) this;
		}

		public Criteria andPawnerTelEqualTo(String value) {
			addCriterion("pawner_tel =", value, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelNotEqualTo(String value) {
			addCriterion("pawner_tel <>", value, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelGreaterThan(String value) {
			addCriterion("pawner_tel >", value, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelGreaterThanOrEqualTo(String value) {
			addCriterion("pawner_tel >=", value, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelLessThan(String value) {
			addCriterion("pawner_tel <", value, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelLessThanOrEqualTo(String value) {
			addCriterion("pawner_tel <=", value, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelLike(String value) {
			addCriterion("pawner_tel like", value, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelNotLike(String value) {
			addCriterion("pawner_tel not like", value, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelIn(List<String> values) {
			addCriterion("pawner_tel in", values, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelNotIn(List<String> values) {
			addCriterion("pawner_tel not in", values, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelBetween(String value1, String value2) {
			addCriterion("pawner_tel between", value1, value2, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerTelNotBetween(String value1, String value2) {
			addCriterion("pawner_tel not between", value1, value2, "pawnerTel");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardIsNull() {
			addCriterion("pawner_id_card is null");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardIsNotNull() {
			addCriterion("pawner_id_card is not null");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardEqualTo(String value) {
			addCriterion("pawner_id_card =", value, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardNotEqualTo(String value) {
			addCriterion("pawner_id_card <>", value, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardGreaterThan(String value) {
			addCriterion("pawner_id_card >", value, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardGreaterThanOrEqualTo(String value) {
			addCriterion("pawner_id_card >=", value, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardLessThan(String value) {
			addCriterion("pawner_id_card <", value, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardLessThanOrEqualTo(String value) {
			addCriterion("pawner_id_card <=", value, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardLike(String value) {
			addCriterion("pawner_id_card like", value, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardNotLike(String value) {
			addCriterion("pawner_id_card not like", value, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardIn(List<String> values) {
			addCriterion("pawner_id_card in", values, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardNotIn(List<String> values) {
			addCriterion("pawner_id_card not in", values, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardBetween(String value1, String value2) {
			addCriterion("pawner_id_card between", value1, value2, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerIdCardNotBetween(String value1, String value2) {
			addCriterion("pawner_id_card not between", value1, value2, "pawnerIdCard");
			return (Criteria) this;
		}

		public Criteria andPawnerCertIsNull() {
			addCriterion("pawner_cert is null");
			return (Criteria) this;
		}

		public Criteria andPawnerCertIsNotNull() {
			addCriterion("pawner_cert is not null");
			return (Criteria) this;
		}

		public Criteria andPawnerCertEqualTo(String value) {
			addCriterion("pawner_cert =", value, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertNotEqualTo(String value) {
			addCriterion("pawner_cert <>", value, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertGreaterThan(String value) {
			addCriterion("pawner_cert >", value, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertGreaterThanOrEqualTo(String value) {
			addCriterion("pawner_cert >=", value, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertLessThan(String value) {
			addCriterion("pawner_cert <", value, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertLessThanOrEqualTo(String value) {
			addCriterion("pawner_cert <=", value, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertLike(String value) {
			addCriterion("pawner_cert like", value, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertNotLike(String value) {
			addCriterion("pawner_cert not like", value, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertIn(List<String> values) {
			addCriterion("pawner_cert in", values, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertNotIn(List<String> values) {
			addCriterion("pawner_cert not in", values, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertBetween(String value1, String value2) {
			addCriterion("pawner_cert between", value1, value2, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andPawnerCertNotBetween(String value1, String value2) {
			addCriterion("pawner_cert not between", value1, value2, "pawnerCert");
			return (Criteria) this;
		}

		public Criteria andContactorIsNull() {
			addCriterion("contactor is null");
			return (Criteria) this;
		}

		public Criteria andContactorIsNotNull() {
			addCriterion("contactor is not null");
			return (Criteria) this;
		}

		public Criteria andContactorEqualTo(String value) {
			addCriterion("contactor =", value, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorNotEqualTo(String value) {
			addCriterion("contactor <>", value, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorGreaterThan(String value) {
			addCriterion("contactor >", value, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorGreaterThanOrEqualTo(String value) {
			addCriterion("contactor >=", value, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorLessThan(String value) {
			addCriterion("contactor <", value, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorLessThanOrEqualTo(String value) {
			addCriterion("contactor <=", value, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorLike(String value) {
			addCriterion("contactor like", value, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorNotLike(String value) {
			addCriterion("contactor not like", value, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorIn(List<String> values) {
			addCriterion("contactor in", values, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorNotIn(List<String> values) {
			addCriterion("contactor not in", values, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorBetween(String value1, String value2) {
			addCriterion("contactor between", value1, value2, "contactor");
			return (Criteria) this;
		}

		public Criteria andContactorNotBetween(String value1, String value2) {
			addCriterion("contactor not between", value1, value2, "contactor");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrIsNull() {
			addCriterion("pawner_addr is null");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrIsNotNull() {
			addCriterion("pawner_addr is not null");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrEqualTo(String value) {
			addCriterion("pawner_addr =", value, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrNotEqualTo(String value) {
			addCriterion("pawner_addr <>", value, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrGreaterThan(String value) {
			addCriterion("pawner_addr >", value, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrGreaterThanOrEqualTo(String value) {
			addCriterion("pawner_addr >=", value, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrLessThan(String value) {
			addCriterion("pawner_addr <", value, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrLessThanOrEqualTo(String value) {
			addCriterion("pawner_addr <=", value, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrLike(String value) {
			addCriterion("pawner_addr like", value, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrNotLike(String value) {
			addCriterion("pawner_addr not like", value, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrIn(List<String> values) {
			addCriterion("pawner_addr in", values, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrNotIn(List<String> values) {
			addCriterion("pawner_addr not in", values, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrBetween(String value1, String value2) {
			addCriterion("pawner_addr between", value1, value2, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andPawnerAddrNotBetween(String value1, String value2) {
			addCriterion("pawner_addr not between", value1, value2, "pawnerAddr");
			return (Criteria) this;
		}

		public Criteria andGoodsNameIsNull() {
			addCriterion("goods_name is null");
			return (Criteria) this;
		}

		public Criteria andGoodsNameIsNotNull() {
			addCriterion("goods_name is not null");
			return (Criteria) this;
		}

		public Criteria andGoodsNameEqualTo(String value) {
			addCriterion("goods_name =", value, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameNotEqualTo(String value) {
			addCriterion("goods_name <>", value, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameGreaterThan(String value) {
			addCriterion("goods_name >", value, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
			addCriterion("goods_name >=", value, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameLessThan(String value) {
			addCriterion("goods_name <", value, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameLessThanOrEqualTo(String value) {
			addCriterion("goods_name <=", value, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameLike(String value) {
			addCriterion("goods_name like", value, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameNotLike(String value) {
			addCriterion("goods_name not like", value, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameIn(List<String> values) {
			addCriterion("goods_name in", values, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameNotIn(List<String> values) {
			addCriterion("goods_name not in", values, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameBetween(String value1, String value2) {
			addCriterion("goods_name between", value1, value2, "goodsName");
			return (Criteria) this;
		}

		public Criteria andGoodsNameNotBetween(String value1, String value2) {
			addCriterion("goods_name not between", value1, value2, "goodsName");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestIsNull() {
			addCriterion("auth_price_test is null");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestIsNotNull() {
			addCriterion("auth_price_test is not null");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestEqualTo(String value) {
			addCriterion("auth_price_test =", value, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestNotEqualTo(String value) {
			addCriterion("auth_price_test <>", value, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestGreaterThan(String value) {
			addCriterion("auth_price_test >", value, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestGreaterThanOrEqualTo(String value) {
			addCriterion("auth_price_test >=", value, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestLessThan(String value) {
			addCriterion("auth_price_test <", value, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestLessThanOrEqualTo(String value) {
			addCriterion("auth_price_test <=", value, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestLike(String value) {
			addCriterion("auth_price_test like", value, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestNotLike(String value) {
			addCriterion("auth_price_test not like", value, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestIn(List<String> values) {
			addCriterion("auth_price_test in", values, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestNotIn(List<String> values) {
			addCriterion("auth_price_test not in", values, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestBetween(String value1, String value2) {
			addCriterion("auth_price_test between", value1, value2, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceTestNotBetween(String value1, String value2) {
			addCriterion("auth_price_test not between", value1, value2, "authPriceTest");
			return (Criteria) this;
		}

		public Criteria andAuthPriceIsNull() {
			addCriterion("auth_price is null");
			return (Criteria) this;
		}

		public Criteria andAuthPriceIsNotNull() {
			addCriterion("auth_price is not null");
			return (Criteria) this;
		}

		public Criteria andAuthPriceEqualTo(java.math.BigDecimal value) {
			addCriterion("auth_price =", value, "authPrice");
			return (Criteria) this;
		}

		public Criteria andAuthPriceNotEqualTo(java.math.BigDecimal value) {
			addCriterion("auth_price <>", value, "authPrice");
			return (Criteria) this;
		}

		public Criteria andAuthPriceGreaterThan(java.math.BigDecimal value) {
			addCriterion("auth_price >", value, "authPrice");
			return (Criteria) this;
		}

		public Criteria andAuthPriceGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("auth_price >=", value, "authPrice");
			return (Criteria) this;
		}

		public Criteria andAuthPriceLessThan(java.math.BigDecimal value) {
			addCriterion("auth_price <", value, "authPrice");
			return (Criteria) this;
		}

		public Criteria andAuthPriceLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("auth_price <=", value, "authPrice");
			return (Criteria) this;
		}

		public Criteria andAuthPriceIn(List<java.math.BigDecimal> values) {
			addCriterion("auth_price in", values, "authPrice");
			return (Criteria) this;
		}

		public Criteria andAuthPriceNotIn(List<java.math.BigDecimal> values) {
			addCriterion("auth_price not in", values, "authPrice");
			return (Criteria) this;
		}

		public Criteria andAuthPriceBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("auth_price between", value1, value2, "authPrice");
			return (Criteria) this;
		}

		public Criteria andAuthPriceNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("auth_price not between", value1, value2, "authPrice");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioIsNull() {
			addCriterion("equivalent_ratio is null");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioIsNotNull() {
			addCriterion("equivalent_ratio is not null");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioEqualTo(String value) {
			addCriterion("equivalent_ratio =", value, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioNotEqualTo(String value) {
			addCriterion("equivalent_ratio <>", value, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioGreaterThan(String value) {
			addCriterion("equivalent_ratio >", value, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioGreaterThanOrEqualTo(String value) {
			addCriterion("equivalent_ratio >=", value, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioLessThan(String value) {
			addCriterion("equivalent_ratio <", value, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioLessThanOrEqualTo(String value) {
			addCriterion("equivalent_ratio <=", value, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioLike(String value) {
			addCriterion("equivalent_ratio like", value, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioNotLike(String value) {
			addCriterion("equivalent_ratio not like", value, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioIn(List<String> values) {
			addCriterion("equivalent_ratio in", values, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioNotIn(List<String> values) {
			addCriterion("equivalent_ratio not in", values, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioBetween(String value1, String value2) {
			addCriterion("equivalent_ratio between", value1, value2, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andEquivalentRatioNotBetween(String value1, String value2) {
			addCriterion("equivalent_ratio not between", value1, value2, "equivalentRatio");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyIsNull() {
			addCriterion("loan_money is null");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyIsNotNull() {
			addCriterion("loan_money is not null");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyEqualTo(String value) {
			addCriterion("loan_money =", value, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyNotEqualTo(String value) {
			addCriterion("loan_money <>", value, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyGreaterThan(String value) {
			addCriterion("loan_money >", value, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyGreaterThanOrEqualTo(String value) {
			addCriterion("loan_money >=", value, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyLessThan(String value) {
			addCriterion("loan_money <", value, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyLessThanOrEqualTo(String value) {
			addCriterion("loan_money <=", value, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyLike(String value) {
			addCriterion("loan_money like", value, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyNotLike(String value) {
			addCriterion("loan_money not like", value, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyIn(List<String> values) {
			addCriterion("loan_money in", values, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyNotIn(List<String> values) {
			addCriterion("loan_money not in", values, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyBetween(String value1, String value2) {
			addCriterion("loan_money between", value1, value2, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyNotBetween(String value1, String value2) {
			addCriterion("loan_money not between", value1, value2, "loanMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyIsNull() {
			addCriterion("user_money is null");
			return (Criteria) this;
		}

		public Criteria andUserMoneyIsNotNull() {
			addCriterion("user_money is not null");
			return (Criteria) this;
		}

		public Criteria andUserMoneyEqualTo(String value) {
			addCriterion("user_money =", value, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyNotEqualTo(String value) {
			addCriterion("user_money <>", value, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyGreaterThan(String value) {
			addCriterion("user_money >", value, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyGreaterThanOrEqualTo(String value) {
			addCriterion("user_money >=", value, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyLessThan(String value) {
			addCriterion("user_money <", value, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyLessThanOrEqualTo(String value) {
			addCriterion("user_money <=", value, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyLike(String value) {
			addCriterion("user_money like", value, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyNotLike(String value) {
			addCriterion("user_money not like", value, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyIn(List<String> values) {
			addCriterion("user_money in", values, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyNotIn(List<String> values) {
			addCriterion("user_money not in", values, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyBetween(String value1, String value2) {
			addCriterion("user_money between", value1, value2, "userMoney");
			return (Criteria) this;
		}

		public Criteria andUserMoneyNotBetween(String value1, String value2) {
			addCriterion("user_money not between", value1, value2, "userMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyIsNull() {
			addCriterion("pawn_money is null");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyIsNotNull() {
			addCriterion("pawn_money is not null");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyEqualTo(String value) {
			addCriterion("pawn_money =", value, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyNotEqualTo(String value) {
			addCriterion("pawn_money <>", value, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyGreaterThan(String value) {
			addCriterion("pawn_money >", value, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyGreaterThanOrEqualTo(String value) {
			addCriterion("pawn_money >=", value, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyLessThan(String value) {
			addCriterion("pawn_money <", value, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyLessThanOrEqualTo(String value) {
			addCriterion("pawn_money <=", value, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyLike(String value) {
			addCriterion("pawn_money like", value, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyNotLike(String value) {
			addCriterion("pawn_money not like", value, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyIn(List<String> values) {
			addCriterion("pawn_money in", values, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyNotIn(List<String> values) {
			addCriterion("pawn_money not in", values, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyBetween(String value1, String value2) {
			addCriterion("pawn_money between", value1, value2, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyNotBetween(String value1, String value2) {
			addCriterion("pawn_money not between", value1, value2, "pawnMoney");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNIsNull() {
			addCriterion("loan_money_c_n is null");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNIsNotNull() {
			addCriterion("loan_money_c_n is not null");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNEqualTo(String value) {
			addCriterion("loan_money_c_n =", value, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNNotEqualTo(String value) {
			addCriterion("loan_money_c_n <>", value, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNGreaterThan(String value) {
			addCriterion("loan_money_c_n >", value, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNGreaterThanOrEqualTo(String value) {
			addCriterion("loan_money_c_n >=", value, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNLessThan(String value) {
			addCriterion("loan_money_c_n <", value, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNLessThanOrEqualTo(String value) {
			addCriterion("loan_money_c_n <=", value, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNLike(String value) {
			addCriterion("loan_money_c_n like", value, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNNotLike(String value) {
			addCriterion("loan_money_c_n not like", value, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNIn(List<String> values) {
			addCriterion("loan_money_c_n in", values, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNNotIn(List<String> values) {
			addCriterion("loan_money_c_n not in", values, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNBetween(String value1, String value2) {
			addCriterion("loan_money_c_n between", value1, value2, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andLoanMoneyCNNotBetween(String value1, String value2) {
			addCriterion("loan_money_c_n not between", value1, value2, "loanMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNIsNull() {
			addCriterion("user_money_c_n is null");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNIsNotNull() {
			addCriterion("user_money_c_n is not null");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNEqualTo(String value) {
			addCriterion("user_money_c_n =", value, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNNotEqualTo(String value) {
			addCriterion("user_money_c_n <>", value, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNGreaterThan(String value) {
			addCriterion("user_money_c_n >", value, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNGreaterThanOrEqualTo(String value) {
			addCriterion("user_money_c_n >=", value, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNLessThan(String value) {
			addCriterion("user_money_c_n <", value, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNLessThanOrEqualTo(String value) {
			addCriterion("user_money_c_n <=", value, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNLike(String value) {
			addCriterion("user_money_c_n like", value, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNNotLike(String value) {
			addCriterion("user_money_c_n not like", value, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNIn(List<String> values) {
			addCriterion("user_money_c_n in", values, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNNotIn(List<String> values) {
			addCriterion("user_money_c_n not in", values, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNBetween(String value1, String value2) {
			addCriterion("user_money_c_n between", value1, value2, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andUserMoneyCNNotBetween(String value1, String value2) {
			addCriterion("user_money_c_n not between", value1, value2, "userMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNIsNull() {
			addCriterion("pawn_money_c_n is null");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNIsNotNull() {
			addCriterion("pawn_money_c_n is not null");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNEqualTo(String value) {
			addCriterion("pawn_money_c_n =", value, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNNotEqualTo(String value) {
			addCriterion("pawn_money_c_n <>", value, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNGreaterThan(String value) {
			addCriterion("pawn_money_c_n >", value, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNGreaterThanOrEqualTo(String value) {
			addCriterion("pawn_money_c_n >=", value, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNLessThan(String value) {
			addCriterion("pawn_money_c_n <", value, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNLessThanOrEqualTo(String value) {
			addCriterion("pawn_money_c_n <=", value, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNLike(String value) {
			addCriterion("pawn_money_c_n like", value, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNNotLike(String value) {
			addCriterion("pawn_money_c_n not like", value, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNIn(List<String> values) {
			addCriterion("pawn_money_c_n in", values, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNNotIn(List<String> values) {
			addCriterion("pawn_money_c_n not in", values, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNBetween(String value1, String value2) {
			addCriterion("pawn_money_c_n between", value1, value2, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnMoneyCNNotBetween(String value1, String value2) {
			addCriterion("pawn_money_c_n not between", value1, value2, "pawnMoneyCN");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeIsNull() {
			addCriterion("pawn_begin_time is null");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeIsNotNull() {
			addCriterion("pawn_begin_time is not null");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeEqualTo(String value) {
			addCriterion("pawn_begin_time =", value, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeNotEqualTo(String value) {
			addCriterion("pawn_begin_time <>", value, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeGreaterThan(String value) {
			addCriterion("pawn_begin_time >", value, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeGreaterThanOrEqualTo(String value) {
			addCriterion("pawn_begin_time >=", value, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeLessThan(String value) {
			addCriterion("pawn_begin_time <", value, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeLessThanOrEqualTo(String value) {
			addCriterion("pawn_begin_time <=", value, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeLike(String value) {
			addCriterion("pawn_begin_time like", value, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeNotLike(String value) {
			addCriterion("pawn_begin_time not like", value, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeIn(List<String> values) {
			addCriterion("pawn_begin_time in", values, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeNotIn(List<String> values) {
			addCriterion("pawn_begin_time not in", values, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeBetween(String value1, String value2) {
			addCriterion("pawn_begin_time between", value1, value2, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnBeginTimeNotBetween(String value1, String value2) {
			addCriterion("pawn_begin_time not between", value1, value2, "pawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeIsNull() {
			addCriterion("pawn_end_time is null");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeIsNotNull() {
			addCriterion("pawn_end_time is not null");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeEqualTo(String value) {
			addCriterion("pawn_end_time =", value, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeNotEqualTo(String value) {
			addCriterion("pawn_end_time <>", value, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeGreaterThan(String value) {
			addCriterion("pawn_end_time >", value, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeGreaterThanOrEqualTo(String value) {
			addCriterion("pawn_end_time >=", value, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeLessThan(String value) {
			addCriterion("pawn_end_time <", value, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeLessThanOrEqualTo(String value) {
			addCriterion("pawn_end_time <=", value, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeLike(String value) {
			addCriterion("pawn_end_time like", value, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeNotLike(String value) {
			addCriterion("pawn_end_time not like", value, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeIn(List<String> values) {
			addCriterion("pawn_end_time in", values, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeNotIn(List<String> values) {
			addCriterion("pawn_end_time not in", values, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeBetween(String value1, String value2) {
			addCriterion("pawn_end_time between", value1, value2, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andPawnEndTimeNotBetween(String value1, String value2) {
			addCriterion("pawn_end_time not between", value1, value2, "pawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeIsNull() {
			addCriterion("begin_pawn_end_time is null");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeIsNotNull() {
			addCriterion("begin_pawn_end_time is not null");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeEqualTo(String value) {
			addCriterion("begin_pawn_end_time =", value, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeNotEqualTo(String value) {
			addCriterion("begin_pawn_end_time <>", value, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeGreaterThan(String value) {
			addCriterion("begin_pawn_end_time >", value, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeGreaterThanOrEqualTo(String value) {
			addCriterion("begin_pawn_end_time >=", value, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeLessThan(String value) {
			addCriterion("begin_pawn_end_time <", value, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeLessThanOrEqualTo(String value) {
			addCriterion("begin_pawn_end_time <=", value, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeLike(String value) {
			addCriterion("begin_pawn_end_time like", value, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeNotLike(String value) {
			addCriterion("begin_pawn_end_time not like", value, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeIn(List<String> values) {
			addCriterion("begin_pawn_end_time in", values, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeNotIn(List<String> values) {
			addCriterion("begin_pawn_end_time not in", values, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeBetween(String value1, String value2) {
			addCriterion("begin_pawn_end_time between", value1, value2, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andBeginPawnEndTimeNotBetween(String value1, String value2) {
			addCriterion("begin_pawn_end_time not between", value1, value2, "beginPawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRateIsNull() {
			addCriterion("rate is null");
			return (Criteria) this;
		}

		public Criteria andRateIsNotNull() {
			addCriterion("rate is not null");
			return (Criteria) this;
		}

		public Criteria andRateEqualTo(String value) {
			addCriterion("rate =", value, "rate");
			return (Criteria) this;
		}

		public Criteria andRateNotEqualTo(String value) {
			addCriterion("rate <>", value, "rate");
			return (Criteria) this;
		}

		public Criteria andRateGreaterThan(String value) {
			addCriterion("rate >", value, "rate");
			return (Criteria) this;
		}

		public Criteria andRateGreaterThanOrEqualTo(String value) {
			addCriterion("rate >=", value, "rate");
			return (Criteria) this;
		}

		public Criteria andRateLessThan(String value) {
			addCriterion("rate <", value, "rate");
			return (Criteria) this;
		}

		public Criteria andRateLessThanOrEqualTo(String value) {
			addCriterion("rate <=", value, "rate");
			return (Criteria) this;
		}

		public Criteria andRateLike(String value) {
			addCriterion("rate like", value, "rate");
			return (Criteria) this;
		}

		public Criteria andRateNotLike(String value) {
			addCriterion("rate not like", value, "rate");
			return (Criteria) this;
		}

		public Criteria andRateIn(List<String> values) {
			addCriterion("rate in", values, "rate");
			return (Criteria) this;
		}

		public Criteria andRateNotIn(List<String> values) {
			addCriterion("rate not in", values, "rate");
			return (Criteria) this;
		}

		public Criteria andRateBetween(String value1, String value2) {
			addCriterion("rate between", value1, value2, "rate");
			return (Criteria) this;
		}

		public Criteria andRateNotBetween(String value1, String value2) {
			addCriterion("rate not between", value1, value2, "rate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateIsNull() {
			addCriterion("money_rate is null");
			return (Criteria) this;
		}

		public Criteria andMoneyRateIsNotNull() {
			addCriterion("money_rate is not null");
			return (Criteria) this;
		}

		public Criteria andMoneyRateEqualTo(String value) {
			addCriterion("money_rate =", value, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateNotEqualTo(String value) {
			addCriterion("money_rate <>", value, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateGreaterThan(String value) {
			addCriterion("money_rate >", value, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateGreaterThanOrEqualTo(String value) {
			addCriterion("money_rate >=", value, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateLessThan(String value) {
			addCriterion("money_rate <", value, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateLessThanOrEqualTo(String value) {
			addCriterion("money_rate <=", value, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateLike(String value) {
			addCriterion("money_rate like", value, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateNotLike(String value) {
			addCriterion("money_rate not like", value, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateIn(List<String> values) {
			addCriterion("money_rate in", values, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateNotIn(List<String> values) {
			addCriterion("money_rate not in", values, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateBetween(String value1, String value2) {
			addCriterion("money_rate between", value1, value2, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andMoneyRateNotBetween(String value1, String value2) {
			addCriterion("money_rate not between", value1, value2, "moneyRate");
			return (Criteria) this;
		}

		public Criteria andCostIsNull() {
			addCriterion("cost is null");
			return (Criteria) this;
		}

		public Criteria andCostIsNotNull() {
			addCriterion("cost is not null");
			return (Criteria) this;
		}

		public Criteria andCostEqualTo(String value) {
			addCriterion("cost =", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostNotEqualTo(String value) {
			addCriterion("cost <>", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostGreaterThan(String value) {
			addCriterion("cost >", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostGreaterThanOrEqualTo(String value) {
			addCriterion("cost >=", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostLessThan(String value) {
			addCriterion("cost <", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostLessThanOrEqualTo(String value) {
			addCriterion("cost <=", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostLike(String value) {
			addCriterion("cost like", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostNotLike(String value) {
			addCriterion("cost not like", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostIn(List<String> values) {
			addCriterion("cost in", values, "cost");
			return (Criteria) this;
		}

		public Criteria andCostNotIn(List<String> values) {
			addCriterion("cost not in", values, "cost");
			return (Criteria) this;
		}

		public Criteria andCostBetween(String value1, String value2) {
			addCriterion("cost between", value1, value2, "cost");
			return (Criteria) this;
		}

		public Criteria andCostNotBetween(String value1, String value2) {
			addCriterion("cost not between", value1, value2, "cost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostIsNull() {
			addCriterion("money_cost is null");
			return (Criteria) this;
		}

		public Criteria andMoneyCostIsNotNull() {
			addCriterion("money_cost is not null");
			return (Criteria) this;
		}

		public Criteria andMoneyCostEqualTo(String value) {
			addCriterion("money_cost =", value, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostNotEqualTo(String value) {
			addCriterion("money_cost <>", value, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostGreaterThan(String value) {
			addCriterion("money_cost >", value, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostGreaterThanOrEqualTo(String value) {
			addCriterion("money_cost >=", value, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostLessThan(String value) {
			addCriterion("money_cost <", value, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostLessThanOrEqualTo(String value) {
			addCriterion("money_cost <=", value, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostLike(String value) {
			addCriterion("money_cost like", value, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostNotLike(String value) {
			addCriterion("money_cost not like", value, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostIn(List<String> values) {
			addCriterion("money_cost in", values, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostNotIn(List<String> values) {
			addCriterion("money_cost not in", values, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostBetween(String value1, String value2) {
			addCriterion("money_cost between", value1, value2, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andMoneyCostNotBetween(String value1, String value2) {
			addCriterion("money_cost not between", value1, value2, "moneyCost");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalIsNull() {
			addCriterion("repawn_total is null");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalIsNotNull() {
			addCriterion("repawn_total is not null");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalEqualTo(String value) {
			addCriterion("repawn_total =", value, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalNotEqualTo(String value) {
			addCriterion("repawn_total <>", value, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalGreaterThan(String value) {
			addCriterion("repawn_total >", value, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalGreaterThanOrEqualTo(String value) {
			addCriterion("repawn_total >=", value, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalLessThan(String value) {
			addCriterion("repawn_total <", value, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalLessThanOrEqualTo(String value) {
			addCriterion("repawn_total <=", value, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalLike(String value) {
			addCriterion("repawn_total like", value, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalNotLike(String value) {
			addCriterion("repawn_total not like", value, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalIn(List<String> values) {
			addCriterion("repawn_total in", values, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalNotIn(List<String> values) {
			addCriterion("repawn_total not in", values, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalBetween(String value1, String value2) {
			addCriterion("repawn_total between", value1, value2, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalNotBetween(String value1, String value2) {
			addCriterion("repawn_total not between", value1, value2, "repawnTotal");
			return (Criteria) this;
		}

		public Criteria andCostCNIsNull() {
			addCriterion("cost_c_n is null");
			return (Criteria) this;
		}

		public Criteria andCostCNIsNotNull() {
			addCriterion("cost_c_n is not null");
			return (Criteria) this;
		}

		public Criteria andCostCNEqualTo(String value) {
			addCriterion("cost_c_n =", value, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNNotEqualTo(String value) {
			addCriterion("cost_c_n <>", value, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNGreaterThan(String value) {
			addCriterion("cost_c_n >", value, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNGreaterThanOrEqualTo(String value) {
			addCriterion("cost_c_n >=", value, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNLessThan(String value) {
			addCriterion("cost_c_n <", value, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNLessThanOrEqualTo(String value) {
			addCriterion("cost_c_n <=", value, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNLike(String value) {
			addCriterion("cost_c_n like", value, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNNotLike(String value) {
			addCriterion("cost_c_n not like", value, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNIn(List<String> values) {
			addCriterion("cost_c_n in", values, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNNotIn(List<String> values) {
			addCriterion("cost_c_n not in", values, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNBetween(String value1, String value2) {
			addCriterion("cost_c_n between", value1, value2, "costCN");
			return (Criteria) this;
		}

		public Criteria andCostCNNotBetween(String value1, String value2) {
			addCriterion("cost_c_n not between", value1, value2, "costCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNIsNull() {
			addCriterion("repawn_total_c_n is null");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNIsNotNull() {
			addCriterion("repawn_total_c_n is not null");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNEqualTo(String value) {
			addCriterion("repawn_total_c_n =", value, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNNotEqualTo(String value) {
			addCriterion("repawn_total_c_n <>", value, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNGreaterThan(String value) {
			addCriterion("repawn_total_c_n >", value, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNGreaterThanOrEqualTo(String value) {
			addCriterion("repawn_total_c_n >=", value, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNLessThan(String value) {
			addCriterion("repawn_total_c_n <", value, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNLessThanOrEqualTo(String value) {
			addCriterion("repawn_total_c_n <=", value, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNLike(String value) {
			addCriterion("repawn_total_c_n like", value, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNNotLike(String value) {
			addCriterion("repawn_total_c_n not like", value, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNIn(List<String> values) {
			addCriterion("repawn_total_c_n in", values, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNNotIn(List<String> values) {
			addCriterion("repawn_total_c_n not in", values, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNBetween(String value1, String value2) {
			addCriterion("repawn_total_c_n between", value1, value2, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andRepawnTotalCNNotBetween(String value1, String value2) {
			addCriterion("repawn_total_c_n not between", value1, value2, "repawnTotalCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNIsNull() {
			addCriterion("money_cost_c_n is null");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNIsNotNull() {
			addCriterion("money_cost_c_n is not null");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNEqualTo(String value) {
			addCriterion("money_cost_c_n =", value, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNNotEqualTo(String value) {
			addCriterion("money_cost_c_n <>", value, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNGreaterThan(String value) {
			addCriterion("money_cost_c_n >", value, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNGreaterThanOrEqualTo(String value) {
			addCriterion("money_cost_c_n >=", value, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNLessThan(String value) {
			addCriterion("money_cost_c_n <", value, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNLessThanOrEqualTo(String value) {
			addCriterion("money_cost_c_n <=", value, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNLike(String value) {
			addCriterion("money_cost_c_n like", value, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNNotLike(String value) {
			addCriterion("money_cost_c_n not like", value, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNIn(List<String> values) {
			addCriterion("money_cost_c_n in", values, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNNotIn(List<String> values) {
			addCriterion("money_cost_c_n not in", values, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNBetween(String value1, String value2) {
			addCriterion("money_cost_c_n between", value1, value2, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andMoneyCostCNNotBetween(String value1, String value2) {
			addCriterion("money_cost_c_n not between", value1, value2, "moneyCostCN");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeIsNull() {
			addCriterion("repawn_begin_time is null");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeIsNotNull() {
			addCriterion("repawn_begin_time is not null");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeEqualTo(String value) {
			addCriterion("repawn_begin_time =", value, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeNotEqualTo(String value) {
			addCriterion("repawn_begin_time <>", value, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeGreaterThan(String value) {
			addCriterion("repawn_begin_time >", value, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeGreaterThanOrEqualTo(String value) {
			addCriterion("repawn_begin_time >=", value, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeLessThan(String value) {
			addCriterion("repawn_begin_time <", value, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeLessThanOrEqualTo(String value) {
			addCriterion("repawn_begin_time <=", value, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeLike(String value) {
			addCriterion("repawn_begin_time like", value, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeNotLike(String value) {
			addCriterion("repawn_begin_time not like", value, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeIn(List<String> values) {
			addCriterion("repawn_begin_time in", values, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeNotIn(List<String> values) {
			addCriterion("repawn_begin_time not in", values, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeBetween(String value1, String value2) {
			addCriterion("repawn_begin_time between", value1, value2, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnBeginTimeNotBetween(String value1, String value2) {
			addCriterion("repawn_begin_time not between", value1, value2, "repawnBeginTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeIsNull() {
			addCriterion("repawn_end_time is null");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeIsNotNull() {
			addCriterion("repawn_end_time is not null");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeEqualTo(String value) {
			addCriterion("repawn_end_time =", value, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeNotEqualTo(String value) {
			addCriterion("repawn_end_time <>", value, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeGreaterThan(String value) {
			addCriterion("repawn_end_time >", value, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeGreaterThanOrEqualTo(String value) {
			addCriterion("repawn_end_time >=", value, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeLessThan(String value) {
			addCriterion("repawn_end_time <", value, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeLessThanOrEqualTo(String value) {
			addCriterion("repawn_end_time <=", value, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeLike(String value) {
			addCriterion("repawn_end_time like", value, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeNotLike(String value) {
			addCriterion("repawn_end_time not like", value, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeIn(List<String> values) {
			addCriterion("repawn_end_time in", values, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeNotIn(List<String> values) {
			addCriterion("repawn_end_time not in", values, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeBetween(String value1, String value2) {
			addCriterion("repawn_end_time between", value1, value2, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andRepawnEndTimeNotBetween(String value1, String value2) {
			addCriterion("repawn_end_time not between", value1, value2, "repawnEndTime");
			return (Criteria) this;
		}

		public Criteria andCheckerIsNull() {
			addCriterion("checker is null");
			return (Criteria) this;
		}

		public Criteria andCheckerIsNotNull() {
			addCriterion("checker is not null");
			return (Criteria) this;
		}

		public Criteria andCheckerEqualTo(String value) {
			addCriterion("checker =", value, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerNotEqualTo(String value) {
			addCriterion("checker <>", value, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerGreaterThan(String value) {
			addCriterion("checker >", value, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerGreaterThanOrEqualTo(String value) {
			addCriterion("checker >=", value, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerLessThan(String value) {
			addCriterion("checker <", value, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerLessThanOrEqualTo(String value) {
			addCriterion("checker <=", value, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerLike(String value) {
			addCriterion("checker like", value, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerNotLike(String value) {
			addCriterion("checker not like", value, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerIn(List<String> values) {
			addCriterion("checker in", values, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerNotIn(List<String> values) {
			addCriterion("checker not in", values, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerBetween(String value1, String value2) {
			addCriterion("checker between", value1, value2, "checker");
			return (Criteria) this;
		}

		public Criteria andCheckerNotBetween(String value1, String value2) {
			addCriterion("checker not between", value1, value2, "checker");
			return (Criteria) this;
		}

		public Criteria andHandlerIsNull() {
			addCriterion("handler is null");
			return (Criteria) this;
		}

		public Criteria andHandlerIsNotNull() {
			addCriterion("handler is not null");
			return (Criteria) this;
		}

		public Criteria andHandlerEqualTo(String value) {
			addCriterion("handler =", value, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerNotEqualTo(String value) {
			addCriterion("handler <>", value, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerGreaterThan(String value) {
			addCriterion("handler >", value, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerGreaterThanOrEqualTo(String value) {
			addCriterion("handler >=", value, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerLessThan(String value) {
			addCriterion("handler <", value, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerLessThanOrEqualTo(String value) {
			addCriterion("handler <=", value, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerLike(String value) {
			addCriterion("handler like", value, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerNotLike(String value) {
			addCriterion("handler not like", value, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerIn(List<String> values) {
			addCriterion("handler in", values, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerNotIn(List<String> values) {
			addCriterion("handler not in", values, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerBetween(String value1, String value2) {
			addCriterion("handler between", value1, value2, "handler");
			return (Criteria) this;
		}

		public Criteria andHandlerNotBetween(String value1, String value2) {
			addCriterion("handler not between", value1, value2, "handler");
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

		public Criteria andRemarkIsNull() {
			addCriterion("remark is null");
			return (Criteria) this;
		}

		public Criteria andRemarkIsNotNull() {
			addCriterion("remark is not null");
			return (Criteria) this;
		}

		public Criteria andRemarkEqualTo(String value) {
			addCriterion("remark =", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkNotEqualTo(String value) {
			addCriterion("remark <>", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkGreaterThan(String value) {
			addCriterion("remark >", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkGreaterThanOrEqualTo(String value) {
			addCriterion("remark >=", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkLessThan(String value) {
			addCriterion("remark <", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkLessThanOrEqualTo(String value) {
			addCriterion("remark <=", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkLike(String value) {
			addCriterion("remark like", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkNotLike(String value) {
			addCriterion("remark not like", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkIn(List<String> values) {
			addCriterion("remark in", values, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkNotIn(List<String> values) {
			addCriterion("remark not in", values, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkBetween(String value1, String value2) {
			addCriterion("remark between", value1, value2, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkNotBetween(String value1, String value2) {
			addCriterion("remark not between", value1, value2, "remark");
			return (Criteria) this;
		}

		public Criteria andOtherOrderIsNull() {
			addCriterion("other_order is null");
			return (Criteria) this;
		}

		public Criteria andOtherOrderIsNotNull() {
			addCriterion("other_order is not null");
			return (Criteria) this;
		}

		public Criteria andOtherOrderEqualTo(String value) {
			addCriterion("other_order =", value, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderNotEqualTo(String value) {
			addCriterion("other_order <>", value, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderGreaterThan(String value) {
			addCriterion("other_order >", value, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderGreaterThanOrEqualTo(String value) {
			addCriterion("other_order >=", value, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderLessThan(String value) {
			addCriterion("other_order <", value, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderLessThanOrEqualTo(String value) {
			addCriterion("other_order <=", value, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderLike(String value) {
			addCriterion("other_order like", value, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderNotLike(String value) {
			addCriterion("other_order not like", value, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderIn(List<String> values) {
			addCriterion("other_order in", values, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderNotIn(List<String> values) {
			addCriterion("other_order not in", values, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderBetween(String value1, String value2) {
			addCriterion("other_order between", value1, value2, "otherOrder");
			return (Criteria) this;
		}

		public Criteria andOtherOrderNotBetween(String value1, String value2) {
			addCriterion("other_order not between", value1, value2, "otherOrder");
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

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(String value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(String value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(String value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(String value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(String value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(String value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLike(String value) {
			addCriterion("type like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotLike(String value) {
			addCriterion("type not like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<String> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<String> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(String value1, String value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(String value1, String value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andContractIdIsNull() {
			addCriterion("contract_id is null");
			return (Criteria) this;
		}

		public Criteria andContractIdIsNotNull() {
			addCriterion("contract_id is not null");
			return (Criteria) this;
		}

		public Criteria andContractIdEqualTo(String value) {
			addCriterion("contract_id =", value, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdNotEqualTo(String value) {
			addCriterion("contract_id <>", value, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdGreaterThan(String value) {
			addCriterion("contract_id >", value, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdGreaterThanOrEqualTo(String value) {
			addCriterion("contract_id >=", value, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdLessThan(String value) {
			addCriterion("contract_id <", value, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdLessThanOrEqualTo(String value) {
			addCriterion("contract_id <=", value, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdLike(String value) {
			addCriterion("contract_id like", value, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdNotLike(String value) {
			addCriterion("contract_id not like", value, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdIn(List<String> values) {
			addCriterion("contract_id in", values, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdNotIn(List<String> values) {
			addCriterion("contract_id not in", values, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdBetween(String value1, String value2) {
			addCriterion("contract_id between", value1, value2, "contractId");
			return (Criteria) this;
		}

		public Criteria andContractIdNotBetween(String value1, String value2) {
			addCriterion("contract_id not between", value1, value2, "contractId");
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

		public Criteria andStatusEqualTo(String value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(String value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(String value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(String value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(String value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(String value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLike(String value) {
			addCriterion("status like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotLike(String value) {
			addCriterion("status not like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<String> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<String> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(String value1, String value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(String value1, String value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andUserStatusIsNull() {
			addCriterion("user_status is null");
			return (Criteria) this;
		}

		public Criteria andUserStatusIsNotNull() {
			addCriterion("user_status is not null");
			return (Criteria) this;
		}

		public Criteria andUserStatusEqualTo(Integer value) {
			addCriterion("user_status =", value, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserStatusNotEqualTo(Integer value) {
			addCriterion("user_status <>", value, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserStatusGreaterThan(Integer value) {
			addCriterion("user_status >", value, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("user_status >=", value, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserStatusLessThan(Integer value) {
			addCriterion("user_status <", value, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserStatusLessThanOrEqualTo(Integer value) {
			addCriterion("user_status <=", value, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserStatusIn(List<Integer> values) {
			addCriterion("user_status in", values, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserStatusNotIn(List<Integer> values) {
			addCriterion("user_status not in", values, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserStatusBetween(Integer value1, Integer value2) {
			addCriterion("user_status between", value1, value2, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("user_status not between", value1, value2, "userStatus");
			return (Criteria) this;
		}

		public Criteria andUserLocationIsNull() {
			addCriterion("user_location is null");
			return (Criteria) this;
		}

		public Criteria andUserLocationIsNotNull() {
			addCriterion("user_location is not null");
			return (Criteria) this;
		}

		public Criteria andUserLocationEqualTo(String value) {
			addCriterion("user_location =", value, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationNotEqualTo(String value) {
			addCriterion("user_location <>", value, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationGreaterThan(String value) {
			addCriterion("user_location >", value, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationGreaterThanOrEqualTo(String value) {
			addCriterion("user_location >=", value, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationLessThan(String value) {
			addCriterion("user_location <", value, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationLessThanOrEqualTo(String value) {
			addCriterion("user_location <=", value, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationLike(String value) {
			addCriterion("user_location like", value, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationNotLike(String value) {
			addCriterion("user_location not like", value, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationIn(List<String> values) {
			addCriterion("user_location in", values, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationNotIn(List<String> values) {
			addCriterion("user_location not in", values, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationBetween(String value1, String value2) {
			addCriterion("user_location between", value1, value2, "userLocation");
			return (Criteria) this;
		}

		public Criteria andUserLocationNotBetween(String value1, String value2) {
			addCriterion("user_location not between", value1, value2, "userLocation");
			return (Criteria) this;
		}

		public Criteria andSignTimeIsNull() {
			addCriterion("sign_time is null");
			return (Criteria) this;
		}

		public Criteria andSignTimeIsNotNull() {
			addCriterion("sign_time is not null");
			return (Criteria) this;
		}

		public Criteria andSignTimeEqualTo(java.util.Date value) {
			addCriterion("sign_time =", value, "signTime");
			return (Criteria) this;
		}

		public Criteria andSignTimeNotEqualTo(java.util.Date value) {
			addCriterion("sign_time <>", value, "signTime");
			return (Criteria) this;
		}

		public Criteria andSignTimeGreaterThan(java.util.Date value) {
			addCriterion("sign_time >", value, "signTime");
			return (Criteria) this;
		}

		public Criteria andSignTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("sign_time >=", value, "signTime");
			return (Criteria) this;
		}

		public Criteria andSignTimeLessThan(java.util.Date value) {
			addCriterion("sign_time <", value, "signTime");
			return (Criteria) this;
		}

		public Criteria andSignTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("sign_time <=", value, "signTime");
			return (Criteria) this;
		}

		public Criteria andSignTimeIn(List<java.util.Date> values) {
			addCriterion("sign_time in", values, "signTime");
			return (Criteria) this;
		}

		public Criteria andSignTimeNotIn(List<java.util.Date> values) {
			addCriterion("sign_time not in", values, "signTime");
			return (Criteria) this;
		}

		public Criteria andSignTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("sign_time between", value1, value2, "signTime");
			return (Criteria) this;
		}

		public Criteria andSignTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("sign_time not between", value1, value2, "signTime");
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

		public Criteria andOrgStatusIsNull() {
			addCriterion("org_status is null");
			return (Criteria) this;
		}

		public Criteria andOrgStatusIsNotNull() {
			addCriterion("org_status is not null");
			return (Criteria) this;
		}

		public Criteria andOrgStatusEqualTo(Integer value) {
			addCriterion("org_status =", value, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgStatusNotEqualTo(Integer value) {
			addCriterion("org_status <>", value, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgStatusGreaterThan(Integer value) {
			addCriterion("org_status >", value, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("org_status >=", value, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgStatusLessThan(Integer value) {
			addCriterion("org_status <", value, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgStatusLessThanOrEqualTo(Integer value) {
			addCriterion("org_status <=", value, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgStatusIn(List<Integer> values) {
			addCriterion("org_status in", values, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgStatusNotIn(List<Integer> values) {
			addCriterion("org_status not in", values, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgStatusBetween(Integer value1, Integer value2) {
			addCriterion("org_status between", value1, value2, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("org_status not between", value1, value2, "orgStatus");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeIsNull() {
			addCriterion("org_sign_time is null");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeIsNotNull() {
			addCriterion("org_sign_time is not null");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeEqualTo(java.util.Date value) {
			addCriterion("org_sign_time =", value, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeNotEqualTo(java.util.Date value) {
			addCriterion("org_sign_time <>", value, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeGreaterThan(java.util.Date value) {
			addCriterion("org_sign_time >", value, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("org_sign_time >=", value, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeLessThan(java.util.Date value) {
			addCriterion("org_sign_time <", value, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("org_sign_time <=", value, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeIn(List<java.util.Date> values) {
			addCriterion("org_sign_time in", values, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeNotIn(List<java.util.Date> values) {
			addCriterion("org_sign_time not in", values, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("org_sign_time between", value1, value2, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andOrgSignTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("org_sign_time not between", value1, value2, "orgSignTime");
			return (Criteria) this;
		}

		public Criteria andProjectCodeIsNull() {
			addCriterion("project_code is null");
			return (Criteria) this;
		}

		public Criteria andProjectCodeIsNotNull() {
			addCriterion("project_code is not null");
			return (Criteria) this;
		}

		public Criteria andProjectCodeEqualTo(String value) {
			addCriterion("project_code =", value, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeNotEqualTo(String value) {
			addCriterion("project_code <>", value, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeGreaterThan(String value) {
			addCriterion("project_code >", value, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeGreaterThanOrEqualTo(String value) {
			addCriterion("project_code >=", value, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeLessThan(String value) {
			addCriterion("project_code <", value, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeLessThanOrEqualTo(String value) {
			addCriterion("project_code <=", value, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeLike(String value) {
			addCriterion("project_code like", value, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeNotLike(String value) {
			addCriterion("project_code not like", value, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeIn(List<String> values) {
			addCriterion("project_code in", values, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeNotIn(List<String> values) {
			addCriterion("project_code not in", values, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeBetween(String value1, String value2) {
			addCriterion("project_code between", value1, value2, "projectCode");
			return (Criteria) this;
		}

		public Criteria andProjectCodeNotBetween(String value1, String value2) {
			addCriterion("project_code not between", value1, value2, "projectCode");
			return (Criteria) this;
		}

		public Criteria andOrgLocationIsNull() {
			addCriterion("org_location is null");
			return (Criteria) this;
		}

		public Criteria andOrgLocationIsNotNull() {
			addCriterion("org_location is not null");
			return (Criteria) this;
		}

		public Criteria andOrgLocationEqualTo(String value) {
			addCriterion("org_location =", value, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationNotEqualTo(String value) {
			addCriterion("org_location <>", value, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationGreaterThan(String value) {
			addCriterion("org_location >", value, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationGreaterThanOrEqualTo(String value) {
			addCriterion("org_location >=", value, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationLessThan(String value) {
			addCriterion("org_location <", value, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationLessThanOrEqualTo(String value) {
			addCriterion("org_location <=", value, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationLike(String value) {
			addCriterion("org_location like", value, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationNotLike(String value) {
			addCriterion("org_location not like", value, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationIn(List<String> values) {
			addCriterion("org_location in", values, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationNotIn(List<String> values) {
			addCriterion("org_location not in", values, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationBetween(String value1, String value2) {
			addCriterion("org_location between", value1, value2, "orgLocation");
			return (Criteria) this;
		}

		public Criteria andOrgLocationNotBetween(String value1, String value2) {
			addCriterion("org_location not between", value1, value2, "orgLocation");
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