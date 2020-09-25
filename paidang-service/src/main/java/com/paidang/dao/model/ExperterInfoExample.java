package com.paidang.dao.model;

import java.util.List;
import java.util.ArrayList;

public class ExperterInfoExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ExperterInfoExample(){
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

		public Criteria andTitleIsNull() {
			addCriterion("title is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("title is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("title =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("title <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("title >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("title >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("title <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("title <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("title like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("title not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("title in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("title not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("title between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("title not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andExperterIdIsNull() {
			addCriterion("experter_id is null");
			return (Criteria) this;
		}

		public Criteria andExperterIdIsNotNull() {
			addCriterion("experter_id is not null");
			return (Criteria) this;
		}

		public Criteria andExperterIdEqualTo(Integer value) {
			addCriterion("experter_id =", value, "experterId");
			return (Criteria) this;
		}

		public Criteria andExperterIdNotEqualTo(Integer value) {
			addCriterion("experter_id <>", value, "experterId");
			return (Criteria) this;
		}

		public Criteria andExperterIdGreaterThan(Integer value) {
			addCriterion("experter_id >", value, "experterId");
			return (Criteria) this;
		}

		public Criteria andExperterIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("experter_id >=", value, "experterId");
			return (Criteria) this;
		}

		public Criteria andExperterIdLessThan(Integer value) {
			addCriterion("experter_id <", value, "experterId");
			return (Criteria) this;
		}

		public Criteria andExperterIdLessThanOrEqualTo(Integer value) {
			addCriterion("experter_id <=", value, "experterId");
			return (Criteria) this;
		}

		public Criteria andExperterIdIn(List<Integer> values) {
			addCriterion("experter_id in", values, "experterId");
			return (Criteria) this;
		}

		public Criteria andExperterIdNotIn(List<Integer> values) {
			addCriterion("experter_id not in", values, "experterId");
			return (Criteria) this;
		}

		public Criteria andExperterIdBetween(Integer value1, Integer value2) {
			addCriterion("experter_id between", value1, value2, "experterId");
			return (Criteria) this;
		}

		public Criteria andExperterIdNotBetween(Integer value1, Integer value2) {
			addCriterion("experter_id not between", value1, value2, "experterId");
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

		public Criteria andCcIsNull() {
			addCriterion("cc is null");
			return (Criteria) this;
		}

		public Criteria andCcIsNotNull() {
			addCriterion("cc is not null");
			return (Criteria) this;
		}

		public Criteria andCcEqualTo(String value) {
			addCriterion("cc =", value, "cc");
			return (Criteria) this;
		}

		public Criteria andCcNotEqualTo(String value) {
			addCriterion("cc <>", value, "cc");
			return (Criteria) this;
		}

		public Criteria andCcGreaterThan(String value) {
			addCriterion("cc >", value, "cc");
			return (Criteria) this;
		}

		public Criteria andCcGreaterThanOrEqualTo(String value) {
			addCriterion("cc >=", value, "cc");
			return (Criteria) this;
		}

		public Criteria andCcLessThan(String value) {
			addCriterion("cc <", value, "cc");
			return (Criteria) this;
		}

		public Criteria andCcLessThanOrEqualTo(String value) {
			addCriterion("cc <=", value, "cc");
			return (Criteria) this;
		}

		public Criteria andCcLike(String value) {
			addCriterion("cc like", value, "cc");
			return (Criteria) this;
		}

		public Criteria andCcNotLike(String value) {
			addCriterion("cc not like", value, "cc");
			return (Criteria) this;
		}

		public Criteria andCcIn(List<String> values) {
			addCriterion("cc in", values, "cc");
			return (Criteria) this;
		}

		public Criteria andCcNotIn(List<String> values) {
			addCriterion("cc not in", values, "cc");
			return (Criteria) this;
		}

		public Criteria andCcBetween(String value1, String value2) {
			addCriterion("cc between", value1, value2, "cc");
			return (Criteria) this;
		}

		public Criteria andCcNotBetween(String value1, String value2) {
			addCriterion("cc not between", value1, value2, "cc");
			return (Criteria) this;
		}

		public Criteria andWeightIsNull() {
			addCriterion("weight is null");
			return (Criteria) this;
		}

		public Criteria andWeightIsNotNull() {
			addCriterion("weight is not null");
			return (Criteria) this;
		}

		public Criteria andWeightEqualTo(String value) {
			addCriterion("weight =", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightNotEqualTo(String value) {
			addCriterion("weight <>", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightGreaterThan(String value) {
			addCriterion("weight >", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightGreaterThanOrEqualTo(String value) {
			addCriterion("weight >=", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightLessThan(String value) {
			addCriterion("weight <", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightLessThanOrEqualTo(String value) {
			addCriterion("weight <=", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightLike(String value) {
			addCriterion("weight like", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightNotLike(String value) {
			addCriterion("weight not like", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightIn(List<String> values) {
			addCriterion("weight in", values, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightNotIn(List<String> values) {
			addCriterion("weight not in", values, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightBetween(String value1, String value2) {
			addCriterion("weight between", value1, value2, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightNotBetween(String value1, String value2) {
			addCriterion("weight not between", value1, value2, "weight");
			return (Criteria) this;
		}

		public Criteria andMainMaterialIsNull() {
			addCriterion("main_material is null");
			return (Criteria) this;
		}

		public Criteria andMainMaterialIsNotNull() {
			addCriterion("main_material is not null");
			return (Criteria) this;
		}

		public Criteria andMainMaterialEqualTo(String value) {
			addCriterion("main_material =", value, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialNotEqualTo(String value) {
			addCriterion("main_material <>", value, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialGreaterThan(String value) {
			addCriterion("main_material >", value, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialGreaterThanOrEqualTo(String value) {
			addCriterion("main_material >=", value, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialLessThan(String value) {
			addCriterion("main_material <", value, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialLessThanOrEqualTo(String value) {
			addCriterion("main_material <=", value, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialLike(String value) {
			addCriterion("main_material like", value, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialNotLike(String value) {
			addCriterion("main_material not like", value, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialIn(List<String> values) {
			addCriterion("main_material in", values, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialNotIn(List<String> values) {
			addCriterion("main_material not in", values, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialBetween(String value1, String value2) {
			addCriterion("main_material between", value1, value2, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andMainMaterialNotBetween(String value1, String value2) {
			addCriterion("main_material not between", value1, value2, "mainMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialIsNull() {
			addCriterion("other_material is null");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialIsNotNull() {
			addCriterion("other_material is not null");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialEqualTo(String value) {
			addCriterion("other_material =", value, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialNotEqualTo(String value) {
			addCriterion("other_material <>", value, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialGreaterThan(String value) {
			addCriterion("other_material >", value, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialGreaterThanOrEqualTo(String value) {
			addCriterion("other_material >=", value, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialLessThan(String value) {
			addCriterion("other_material <", value, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialLessThanOrEqualTo(String value) {
			addCriterion("other_material <=", value, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialLike(String value) {
			addCriterion("other_material like", value, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialNotLike(String value) {
			addCriterion("other_material not like", value, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialIn(List<String> values) {
			addCriterion("other_material in", values, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialNotIn(List<String> values) {
			addCriterion("other_material not in", values, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialBetween(String value1, String value2) {
			addCriterion("other_material between", value1, value2, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andOtherMaterialNotBetween(String value1, String value2) {
			addCriterion("other_material not between", value1, value2, "otherMaterial");
			return (Criteria) this;
		}

		public Criteria andCreateYearIsNull() {
			addCriterion("create_year is null");
			return (Criteria) this;
		}

		public Criteria andCreateYearIsNotNull() {
			addCriterion("create_year is not null");
			return (Criteria) this;
		}

		public Criteria andCreateYearEqualTo(String value) {
			addCriterion("create_year =", value, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearNotEqualTo(String value) {
			addCriterion("create_year <>", value, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearGreaterThan(String value) {
			addCriterion("create_year >", value, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearGreaterThanOrEqualTo(String value) {
			addCriterion("create_year >=", value, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearLessThan(String value) {
			addCriterion("create_year <", value, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearLessThanOrEqualTo(String value) {
			addCriterion("create_year <=", value, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearLike(String value) {
			addCriterion("create_year like", value, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearNotLike(String value) {
			addCriterion("create_year not like", value, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearIn(List<String> values) {
			addCriterion("create_year in", values, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearNotIn(List<String> values) {
			addCriterion("create_year not in", values, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearBetween(String value1, String value2) {
			addCriterion("create_year between", value1, value2, "createYear");
			return (Criteria) this;
		}

		public Criteria andCreateYearNotBetween(String value1, String value2) {
			addCriterion("create_year not between", value1, value2, "createYear");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationIsNull() {
			addCriterion("market_circulation is null");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationIsNotNull() {
			addCriterion("market_circulation is not null");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationEqualTo(String value) {
			addCriterion("market_circulation =", value, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationNotEqualTo(String value) {
			addCriterion("market_circulation <>", value, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationGreaterThan(String value) {
			addCriterion("market_circulation >", value, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationGreaterThanOrEqualTo(String value) {
			addCriterion("market_circulation >=", value, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationLessThan(String value) {
			addCriterion("market_circulation <", value, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationLessThanOrEqualTo(String value) {
			addCriterion("market_circulation <=", value, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationLike(String value) {
			addCriterion("market_circulation like", value, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationNotLike(String value) {
			addCriterion("market_circulation not like", value, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationIn(List<String> values) {
			addCriterion("market_circulation in", values, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationNotIn(List<String> values) {
			addCriterion("market_circulation not in", values, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationBetween(String value1, String value2) {
			addCriterion("market_circulation between", value1, value2, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andMarketCirculationNotBetween(String value1, String value2) {
			addCriterion("market_circulation not between", value1, value2, "marketCirculation");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceIsNull() {
			addCriterion("stability_of_price is null");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceIsNotNull() {
			addCriterion("stability_of_price is not null");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceEqualTo(String value) {
			addCriterion("stability_of_price =", value, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceNotEqualTo(String value) {
			addCriterion("stability_of_price <>", value, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceGreaterThan(String value) {
			addCriterion("stability_of_price >", value, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceGreaterThanOrEqualTo(String value) {
			addCriterion("stability_of_price >=", value, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceLessThan(String value) {
			addCriterion("stability_of_price <", value, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceLessThanOrEqualTo(String value) {
			addCriterion("stability_of_price <=", value, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceLike(String value) {
			addCriterion("stability_of_price like", value, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceNotLike(String value) {
			addCriterion("stability_of_price not like", value, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceIn(List<String> values) {
			addCriterion("stability_of_price in", values, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceNotIn(List<String> values) {
			addCriterion("stability_of_price not in", values, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceBetween(String value1, String value2) {
			addCriterion("stability_of_price between", value1, value2, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andStabilityOfPriceNotBetween(String value1, String value2) {
			addCriterion("stability_of_price not between", value1, value2, "stabilityOfPrice");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableIsNull() {
			addCriterion("material_vulnerable is null");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableIsNotNull() {
			addCriterion("material_vulnerable is not null");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableEqualTo(String value) {
			addCriterion("material_vulnerable =", value, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableNotEqualTo(String value) {
			addCriterion("material_vulnerable <>", value, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableGreaterThan(String value) {
			addCriterion("material_vulnerable >", value, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableGreaterThanOrEqualTo(String value) {
			addCriterion("material_vulnerable >=", value, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableLessThan(String value) {
			addCriterion("material_vulnerable <", value, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableLessThanOrEqualTo(String value) {
			addCriterion("material_vulnerable <=", value, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableLike(String value) {
			addCriterion("material_vulnerable like", value, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableNotLike(String value) {
			addCriterion("material_vulnerable not like", value, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableIn(List<String> values) {
			addCriterion("material_vulnerable in", values, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableNotIn(List<String> values) {
			addCriterion("material_vulnerable not in", values, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableBetween(String value1, String value2) {
			addCriterion("material_vulnerable between", value1, value2, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andMaterialVulnerableNotBetween(String value1, String value2) {
			addCriterion("material_vulnerable not between", value1, value2, "materialVulnerable");
			return (Criteria) this;
		}

		public Criteria andExperterPriceIsNull() {
			addCriterion("experter_price is null");
			return (Criteria) this;
		}

		public Criteria andExperterPriceIsNotNull() {
			addCriterion("experter_price is not null");
			return (Criteria) this;
		}

		public Criteria andExperterPriceEqualTo(String value) {
			addCriterion("experter_price =", value, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceNotEqualTo(String value) {
			addCriterion("experter_price <>", value, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceGreaterThan(String value) {
			addCriterion("experter_price >", value, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceGreaterThanOrEqualTo(String value) {
			addCriterion("experter_price >=", value, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceLessThan(String value) {
			addCriterion("experter_price <", value, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceLessThanOrEqualTo(String value) {
			addCriterion("experter_price <=", value, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceLike(String value) {
			addCriterion("experter_price like", value, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceNotLike(String value) {
			addCriterion("experter_price not like", value, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceIn(List<String> values) {
			addCriterion("experter_price in", values, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceNotIn(List<String> values) {
			addCriterion("experter_price not in", values, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceBetween(String value1, String value2) {
			addCriterion("experter_price between", value1, value2, "experterPrice");
			return (Criteria) this;
		}

		public Criteria andExperterPriceNotBetween(String value1, String value2) {
			addCriterion("experter_price not between", value1, value2, "experterPrice");
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