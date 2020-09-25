package com.paidang.dao.model;

import java.util.List;
import java.util.ArrayList;

public class AuthEnterpriseExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public AuthEnterpriseExample(){
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

		public Criteria andEnterpriseNameIsNull() {
			addCriterion("enterprise_name is null");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameIsNotNull() {
			addCriterion("enterprise_name is not null");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameEqualTo(String value) {
			addCriterion("enterprise_name =", value, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameNotEqualTo(String value) {
			addCriterion("enterprise_name <>", value, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameGreaterThan(String value) {
			addCriterion("enterprise_name >", value, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameGreaterThanOrEqualTo(String value) {
			addCriterion("enterprise_name >=", value, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameLessThan(String value) {
			addCriterion("enterprise_name <", value, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameLessThanOrEqualTo(String value) {
			addCriterion("enterprise_name <=", value, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameLike(String value) {
			addCriterion("enterprise_name like", value, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameNotLike(String value) {
			addCriterion("enterprise_name not like", value, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameIn(List<String> values) {
			addCriterion("enterprise_name in", values, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameNotIn(List<String> values) {
			addCriterion("enterprise_name not in", values, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameBetween(String value1, String value2) {
			addCriterion("enterprise_name between", value1, value2, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andEnterpriseNameNotBetween(String value1, String value2) {
			addCriterion("enterprise_name not between", value1, value2, "enterpriseName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameIsNull() {
			addCriterion("legal_person_name is null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameIsNotNull() {
			addCriterion("legal_person_name is not null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameEqualTo(String value) {
			addCriterion("legal_person_name =", value, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameNotEqualTo(String value) {
			addCriterion("legal_person_name <>", value, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameGreaterThan(String value) {
			addCriterion("legal_person_name >", value, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameGreaterThanOrEqualTo(String value) {
			addCriterion("legal_person_name >=", value, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameLessThan(String value) {
			addCriterion("legal_person_name <", value, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameLessThanOrEqualTo(String value) {
			addCriterion("legal_person_name <=", value, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameLike(String value) {
			addCriterion("legal_person_name like", value, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameNotLike(String value) {
			addCriterion("legal_person_name not like", value, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameIn(List<String> values) {
			addCriterion("legal_person_name in", values, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameNotIn(List<String> values) {
			addCriterion("legal_person_name not in", values, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameBetween(String value1, String value2) {
			addCriterion("legal_person_name between", value1, value2, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonNameNotBetween(String value1, String value2) {
			addCriterion("legal_person_name not between", value1, value2, "legalPersonName");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardIsNull() {
			addCriterion("legal_person_card is null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardIsNotNull() {
			addCriterion("legal_person_card is not null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardEqualTo(String value) {
			addCriterion("legal_person_card =", value, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardNotEqualTo(String value) {
			addCriterion("legal_person_card <>", value, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardGreaterThan(String value) {
			addCriterion("legal_person_card >", value, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardGreaterThanOrEqualTo(String value) {
			addCriterion("legal_person_card >=", value, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardLessThan(String value) {
			addCriterion("legal_person_card <", value, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardLessThanOrEqualTo(String value) {
			addCriterion("legal_person_card <=", value, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardLike(String value) {
			addCriterion("legal_person_card like", value, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardNotLike(String value) {
			addCriterion("legal_person_card not like", value, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardIn(List<String> values) {
			addCriterion("legal_person_card in", values, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardNotIn(List<String> values) {
			addCriterion("legal_person_card not in", values, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBetween(String value1, String value2) {
			addCriterion("legal_person_card between", value1, value2, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardNotBetween(String value1, String value2) {
			addCriterion("legal_person_card not between", value1, value2, "legalPersonCard");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoIsNull() {
			addCriterion("business_license_photo is null");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoIsNotNull() {
			addCriterion("business_license_photo is not null");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoEqualTo(String value) {
			addCriterion("business_license_photo =", value, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoNotEqualTo(String value) {
			addCriterion("business_license_photo <>", value, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoGreaterThan(String value) {
			addCriterion("business_license_photo >", value, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoGreaterThanOrEqualTo(String value) {
			addCriterion("business_license_photo >=", value, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoLessThan(String value) {
			addCriterion("business_license_photo <", value, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoLessThanOrEqualTo(String value) {
			addCriterion("business_license_photo <=", value, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoLike(String value) {
			addCriterion("business_license_photo like", value, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoNotLike(String value) {
			addCriterion("business_license_photo not like", value, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoIn(List<String> values) {
			addCriterion("business_license_photo in", values, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoNotIn(List<String> values) {
			addCriterion("business_license_photo not in", values, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoBetween(String value1, String value2) {
			addCriterion("business_license_photo between", value1, value2, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andBusinessLicensePhotoNotBetween(String value1, String value2) {
			addCriterion("business_license_photo not between", value1, value2, "businessLicensePhoto");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontIsNull() {
			addCriterion("legal_person_card_front is null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontIsNotNull() {
			addCriterion("legal_person_card_front is not null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontEqualTo(String value) {
			addCriterion("legal_person_card_front =", value, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontNotEqualTo(String value) {
			addCriterion("legal_person_card_front <>", value, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontGreaterThan(String value) {
			addCriterion("legal_person_card_front >", value, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontGreaterThanOrEqualTo(String value) {
			addCriterion("legal_person_card_front >=", value, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontLessThan(String value) {
			addCriterion("legal_person_card_front <", value, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontLessThanOrEqualTo(String value) {
			addCriterion("legal_person_card_front <=", value, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontLike(String value) {
			addCriterion("legal_person_card_front like", value, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontNotLike(String value) {
			addCriterion("legal_person_card_front not like", value, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontIn(List<String> values) {
			addCriterion("legal_person_card_front in", values, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontNotIn(List<String> values) {
			addCriterion("legal_person_card_front not in", values, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontBetween(String value1, String value2) {
			addCriterion("legal_person_card_front between", value1, value2, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardFrontNotBetween(String value1, String value2) {
			addCriterion("legal_person_card_front not between", value1, value2, "legalPersonCardFront");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackIsNull() {
			addCriterion("legal_person_card_back is null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackIsNotNull() {
			addCriterion("legal_person_card_back is not null");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackEqualTo(String value) {
			addCriterion("legal_person_card_back =", value, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackNotEqualTo(String value) {
			addCriterion("legal_person_card_back <>", value, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackGreaterThan(String value) {
			addCriterion("legal_person_card_back >", value, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackGreaterThanOrEqualTo(String value) {
			addCriterion("legal_person_card_back >=", value, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackLessThan(String value) {
			addCriterion("legal_person_card_back <", value, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackLessThanOrEqualTo(String value) {
			addCriterion("legal_person_card_back <=", value, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackLike(String value) {
			addCriterion("legal_person_card_back like", value, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackNotLike(String value) {
			addCriterion("legal_person_card_back not like", value, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackIn(List<String> values) {
			addCriterion("legal_person_card_back in", values, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackNotIn(List<String> values) {
			addCriterion("legal_person_card_back not in", values, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackBetween(String value1, String value2) {
			addCriterion("legal_person_card_back between", value1, value2, "legalPersonCardBack");
			return (Criteria) this;
		}

		public Criteria andLegalPersonCardBackNotBetween(String value1, String value2) {
			addCriterion("legal_person_card_back not between", value1, value2, "legalPersonCardBack");
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