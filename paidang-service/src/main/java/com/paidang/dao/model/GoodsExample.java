package com.paidang.dao.model;

import java.util.List;
import java.util.ArrayList;

public class GoodsExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public GoodsExample(){
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

		public Criteria andSourceIsNull() {
			addCriterion("source is null");
			return (Criteria) this;
		}

		public Criteria andSourceIsNotNull() {
			addCriterion("source is not null");
			return (Criteria) this;
		}

		public Criteria andSourceEqualTo(Integer value) {
			addCriterion("source =", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotEqualTo(Integer value) {
			addCriterion("source <>", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceGreaterThan(Integer value) {
			addCriterion("source >", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceGreaterThanOrEqualTo(Integer value) {
			addCriterion("source >=", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceLessThan(Integer value) {
			addCriterion("source <", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceLessThanOrEqualTo(Integer value) {
			addCriterion("source <=", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceIn(List<Integer> values) {
			addCriterion("source in", values, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotIn(List<Integer> values) {
			addCriterion("source not in", values, "source");
			return (Criteria) this;
		}

		public Criteria andSourceBetween(Integer value1, Integer value2) {
			addCriterion("source between", value1, value2, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotBetween(Integer value1, Integer value2) {
			addCriterion("source not between", value1, value2, "source");
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

		public Criteria andGoodsOwnerIsNull() {
			addCriterion("goods_owner is null");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerIsNotNull() {
			addCriterion("goods_owner is not null");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerEqualTo(Integer value) {
			addCriterion("goods_owner =", value, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerNotEqualTo(Integer value) {
			addCriterion("goods_owner <>", value, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerGreaterThan(Integer value) {
			addCriterion("goods_owner >", value, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerGreaterThanOrEqualTo(Integer value) {
			addCriterion("goods_owner >=", value, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerLessThan(Integer value) {
			addCriterion("goods_owner <", value, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerLessThanOrEqualTo(Integer value) {
			addCriterion("goods_owner <=", value, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerIn(List<Integer> values) {
			addCriterion("goods_owner in", values, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerNotIn(List<Integer> values) {
			addCriterion("goods_owner not in", values, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerBetween(Integer value1, Integer value2) {
			addCriterion("goods_owner between", value1, value2, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andGoodsOwnerNotBetween(Integer value1, Integer value2) {
			addCriterion("goods_owner not between", value1, value2, "goodsOwner");
			return (Criteria) this;
		}

		public Criteria andCateCodeIsNull() {
			addCriterion("cate_code is null");
			return (Criteria) this;
		}

		public Criteria andCateCodeIsNotNull() {
			addCriterion("cate_code is not null");
			return (Criteria) this;
		}

		public Criteria andCateCodeEqualTo(Integer value) {
			addCriterion("cate_code =", value, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeNotEqualTo(Integer value) {
			addCriterion("cate_code <>", value, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeGreaterThan(Integer value) {
			addCriterion("cate_code >", value, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeGreaterThanOrEqualTo(Integer value) {
			addCriterion("cate_code >=", value, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeLessThan(Integer value) {
			addCriterion("cate_code <", value, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeLessThanOrEqualTo(Integer value) {
			addCriterion("cate_code <=", value, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeIn(List<Integer> values) {
			addCriterion("cate_code in", values, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeNotIn(List<Integer> values) {
			addCriterion("cate_code not in", values, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeBetween(Integer value1, Integer value2) {
			addCriterion("cate_code between", value1, value2, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeNotBetween(Integer value1, Integer value2) {
			addCriterion("cate_code not between", value1, value2, "cateCode");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonIsNull() {
			addCriterion("cate_code_son is null");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonIsNotNull() {
			addCriterion("cate_code_son is not null");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonEqualTo(String value) {
			addCriterion("cate_code_son =", value, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonNotEqualTo(String value) {
			addCriterion("cate_code_son <>", value, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonGreaterThan(String value) {
			addCriterion("cate_code_son >", value, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonGreaterThanOrEqualTo(String value) {
			addCriterion("cate_code_son >=", value, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonLessThan(String value) {
			addCriterion("cate_code_son <", value, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonLessThanOrEqualTo(String value) {
			addCriterion("cate_code_son <=", value, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonLike(String value) {
			addCriterion("cate_code_son like", value, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonNotLike(String value) {
			addCriterion("cate_code_son not like", value, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonIn(List<String> values) {
			addCriterion("cate_code_son in", values, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonNotIn(List<String> values) {
			addCriterion("cate_code_son not in", values, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonBetween(String value1, String value2) {
			addCriterion("cate_code_son between", value1, value2, "cateCodeSon");
			return (Criteria) this;
		}

		public Criteria andCateCodeSonNotBetween(String value1, String value2) {
			addCriterion("cate_code_son not between", value1, value2, "cateCodeSon");
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

		public Criteria andCostIsNull() {
			addCriterion("cost is null");
			return (Criteria) this;
		}

		public Criteria andCostIsNotNull() {
			addCriterion("cost is not null");
			return (Criteria) this;
		}

		public Criteria andCostEqualTo(java.math.BigDecimal value) {
			addCriterion("cost =", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostNotEqualTo(java.math.BigDecimal value) {
			addCriterion("cost <>", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostGreaterThan(java.math.BigDecimal value) {
			addCriterion("cost >", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("cost >=", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostLessThan(java.math.BigDecimal value) {
			addCriterion("cost <", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("cost <=", value, "cost");
			return (Criteria) this;
		}

		public Criteria andCostIn(List<java.math.BigDecimal> values) {
			addCriterion("cost in", values, "cost");
			return (Criteria) this;
		}

		public Criteria andCostNotIn(List<java.math.BigDecimal> values) {
			addCriterion("cost not in", values, "cost");
			return (Criteria) this;
		}

		public Criteria andCostBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("cost between", value1, value2, "cost");
			return (Criteria) this;
		}

		public Criteria andCostNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("cost not between", value1, value2, "cost");
			return (Criteria) this;
		}

		public Criteria andPriceIsNull() {
			addCriterion("price is null");
			return (Criteria) this;
		}

		public Criteria andPriceIsNotNull() {
			addCriterion("price is not null");
			return (Criteria) this;
		}

		public Criteria andPriceEqualTo(java.math.BigDecimal value) {
			addCriterion("price =", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotEqualTo(java.math.BigDecimal value) {
			addCriterion("price <>", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThan(java.math.BigDecimal value) {
			addCriterion("price >", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("price >=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThan(java.math.BigDecimal value) {
			addCriterion("price <", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("price <=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceIn(List<java.math.BigDecimal> values) {
			addCriterion("price in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotIn(List<java.math.BigDecimal> values) {
			addCriterion("price not in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("price between", value1, value2, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("price not between", value1, value2, "price");
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

		public Criteria andTotalEqualTo(Integer value) {
			addCriterion("total =", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotEqualTo(Integer value) {
			addCriterion("total <>", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalGreaterThan(Integer value) {
			addCriterion("total >", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
			addCriterion("total >=", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalLessThan(Integer value) {
			addCriterion("total <", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalLessThanOrEqualTo(Integer value) {
			addCriterion("total <=", value, "total");
			return (Criteria) this;
		}

		public Criteria andTotalIn(List<Integer> values) {
			addCriterion("total in", values, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotIn(List<Integer> values) {
			addCriterion("total not in", values, "total");
			return (Criteria) this;
		}

		public Criteria andTotalBetween(Integer value1, Integer value2) {
			addCriterion("total between", value1, value2, "total");
			return (Criteria) this;
		}

		public Criteria andTotalNotBetween(Integer value1, Integer value2) {
			addCriterion("total not between", value1, value2, "total");
			return (Criteria) this;
		}

		public Criteria andSoldOutIsNull() {
			addCriterion("sold_out is null");
			return (Criteria) this;
		}

		public Criteria andSoldOutIsNotNull() {
			addCriterion("sold_out is not null");
			return (Criteria) this;
		}

		public Criteria andSoldOutEqualTo(Integer value) {
			addCriterion("sold_out =", value, "soldOut");
			return (Criteria) this;
		}

		public Criteria andSoldOutNotEqualTo(Integer value) {
			addCriterion("sold_out <>", value, "soldOut");
			return (Criteria) this;
		}

		public Criteria andSoldOutGreaterThan(Integer value) {
			addCriterion("sold_out >", value, "soldOut");
			return (Criteria) this;
		}

		public Criteria andSoldOutGreaterThanOrEqualTo(Integer value) {
			addCriterion("sold_out >=", value, "soldOut");
			return (Criteria) this;
		}

		public Criteria andSoldOutLessThan(Integer value) {
			addCriterion("sold_out <", value, "soldOut");
			return (Criteria) this;
		}

		public Criteria andSoldOutLessThanOrEqualTo(Integer value) {
			addCriterion("sold_out <=", value, "soldOut");
			return (Criteria) this;
		}

		public Criteria andSoldOutIn(List<Integer> values) {
			addCriterion("sold_out in", values, "soldOut");
			return (Criteria) this;
		}

		public Criteria andSoldOutNotIn(List<Integer> values) {
			addCriterion("sold_out not in", values, "soldOut");
			return (Criteria) this;
		}

		public Criteria andSoldOutBetween(Integer value1, Integer value2) {
			addCriterion("sold_out between", value1, value2, "soldOut");
			return (Criteria) this;
		}

		public Criteria andSoldOutNotBetween(Integer value1, Integer value2) {
			addCriterion("sold_out not between", value1, value2, "soldOut");
			return (Criteria) this;
		}

		public Criteria andIsOnlineIsNull() {
			addCriterion("is_online is null");
			return (Criteria) this;
		}

		public Criteria andIsOnlineIsNotNull() {
			addCriterion("is_online is not null");
			return (Criteria) this;
		}

		public Criteria andIsOnlineEqualTo(Integer value) {
			addCriterion("is_online =", value, "isOnline");
			return (Criteria) this;
		}

		public Criteria andIsOnlineNotEqualTo(Integer value) {
			addCriterion("is_online <>", value, "isOnline");
			return (Criteria) this;
		}

		public Criteria andIsOnlineGreaterThan(Integer value) {
			addCriterion("is_online >", value, "isOnline");
			return (Criteria) this;
		}

		public Criteria andIsOnlineGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_online >=", value, "isOnline");
			return (Criteria) this;
		}

		public Criteria andIsOnlineLessThan(Integer value) {
			addCriterion("is_online <", value, "isOnline");
			return (Criteria) this;
		}

		public Criteria andIsOnlineLessThanOrEqualTo(Integer value) {
			addCriterion("is_online <=", value, "isOnline");
			return (Criteria) this;
		}

		public Criteria andIsOnlineIn(List<Integer> values) {
			addCriterion("is_online in", values, "isOnline");
			return (Criteria) this;
		}

		public Criteria andIsOnlineNotIn(List<Integer> values) {
			addCriterion("is_online not in", values, "isOnline");
			return (Criteria) this;
		}

		public Criteria andIsOnlineBetween(Integer value1, Integer value2) {
			addCriterion("is_online between", value1, value2, "isOnline");
			return (Criteria) this;
		}

		public Criteria andIsOnlineNotBetween(Integer value1, Integer value2) {
			addCriterion("is_online not between", value1, value2, "isOnline");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingIsNull() {
			addCriterion("reason_of_dismounting is null");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingIsNotNull() {
			addCriterion("reason_of_dismounting is not null");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingEqualTo(String value) {
			addCriterion("reason_of_dismounting =", value, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingNotEqualTo(String value) {
			addCriterion("reason_of_dismounting <>", value, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingGreaterThan(String value) {
			addCriterion("reason_of_dismounting >", value, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingGreaterThanOrEqualTo(String value) {
			addCriterion("reason_of_dismounting >=", value, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingLessThan(String value) {
			addCriterion("reason_of_dismounting <", value, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingLessThanOrEqualTo(String value) {
			addCriterion("reason_of_dismounting <=", value, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingLike(String value) {
			addCriterion("reason_of_dismounting like", value, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingNotLike(String value) {
			addCriterion("reason_of_dismounting not like", value, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingIn(List<String> values) {
			addCriterion("reason_of_dismounting in", values, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingNotIn(List<String> values) {
			addCriterion("reason_of_dismounting not in", values, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingBetween(String value1, String value2) {
			addCriterion("reason_of_dismounting between", value1, value2, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andReasonOfDismountingNotBetween(String value1, String value2) {
			addCriterion("reason_of_dismounting not between", value1, value2, "reasonOfDismounting");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyIsNull() {
			addCriterion("is_verfiy is null");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyIsNotNull() {
			addCriterion("is_verfiy is not null");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyEqualTo(Integer value) {
			addCriterion("is_verfiy =", value, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyNotEqualTo(Integer value) {
			addCriterion("is_verfiy <>", value, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyGreaterThan(Integer value) {
			addCriterion("is_verfiy >", value, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_verfiy >=", value, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyLessThan(Integer value) {
			addCriterion("is_verfiy <", value, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyLessThanOrEqualTo(Integer value) {
			addCriterion("is_verfiy <=", value, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyIn(List<Integer> values) {
			addCriterion("is_verfiy in", values, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyNotIn(List<Integer> values) {
			addCriterion("is_verfiy not in", values, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyBetween(Integer value1, Integer value2) {
			addCriterion("is_verfiy between", value1, value2, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andIsVerfiyNotBetween(Integer value1, Integer value2) {
			addCriterion("is_verfiy not between", value1, value2, "isVerfiy");
			return (Criteria) this;
		}

		public Criteria andImgsIsNull() {
			addCriterion("imgs is null");
			return (Criteria) this;
		}

		public Criteria andImgsIsNotNull() {
			addCriterion("imgs is not null");
			return (Criteria) this;
		}

		public Criteria andImgsEqualTo(String value) {
			addCriterion("imgs =", value, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsNotEqualTo(String value) {
			addCriterion("imgs <>", value, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsGreaterThan(String value) {
			addCriterion("imgs >", value, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsGreaterThanOrEqualTo(String value) {
			addCriterion("imgs >=", value, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsLessThan(String value) {
			addCriterion("imgs <", value, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsLessThanOrEqualTo(String value) {
			addCriterion("imgs <=", value, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsLike(String value) {
			addCriterion("imgs like", value, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsNotLike(String value) {
			addCriterion("imgs not like", value, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsIn(List<String> values) {
			addCriterion("imgs in", values, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsNotIn(List<String> values) {
			addCriterion("imgs not in", values, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsBetween(String value1, String value2) {
			addCriterion("imgs between", value1, value2, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgsNotBetween(String value1, String value2) {
			addCriterion("imgs not between", value1, value2, "imgs");
			return (Criteria) this;
		}

		public Criteria andImgIsNull() {
			addCriterion("img is null");
			return (Criteria) this;
		}

		public Criteria andImgIsNotNull() {
			addCriterion("img is not null");
			return (Criteria) this;
		}

		public Criteria andImgEqualTo(String value) {
			addCriterion("img =", value, "img");
			return (Criteria) this;
		}

		public Criteria andImgNotEqualTo(String value) {
			addCriterion("img <>", value, "img");
			return (Criteria) this;
		}

		public Criteria andImgGreaterThan(String value) {
			addCriterion("img >", value, "img");
			return (Criteria) this;
		}

		public Criteria andImgGreaterThanOrEqualTo(String value) {
			addCriterion("img >=", value, "img");
			return (Criteria) this;
		}

		public Criteria andImgLessThan(String value) {
			addCriterion("img <", value, "img");
			return (Criteria) this;
		}

		public Criteria andImgLessThanOrEqualTo(String value) {
			addCriterion("img <=", value, "img");
			return (Criteria) this;
		}

		public Criteria andImgLike(String value) {
			addCriterion("img like", value, "img");
			return (Criteria) this;
		}

		public Criteria andImgNotLike(String value) {
			addCriterion("img not like", value, "img");
			return (Criteria) this;
		}

		public Criteria andImgIn(List<String> values) {
			addCriterion("img in", values, "img");
			return (Criteria) this;
		}

		public Criteria andImgNotIn(List<String> values) {
			addCriterion("img not in", values, "img");
			return (Criteria) this;
		}

		public Criteria andImgBetween(String value1, String value2) {
			addCriterion("img between", value1, value2, "img");
			return (Criteria) this;
		}

		public Criteria andImgNotBetween(String value1, String value2) {
			addCriterion("img not between", value1, value2, "img");
			return (Criteria) this;
		}

		public Criteria andWidthIsNull() {
			addCriterion("width is null");
			return (Criteria) this;
		}

		public Criteria andWidthIsNotNull() {
			addCriterion("width is not null");
			return (Criteria) this;
		}

		public Criteria andWidthEqualTo(String value) {
			addCriterion("width =", value, "width");
			return (Criteria) this;
		}

		public Criteria andWidthNotEqualTo(String value) {
			addCriterion("width <>", value, "width");
			return (Criteria) this;
		}

		public Criteria andWidthGreaterThan(String value) {
			addCriterion("width >", value, "width");
			return (Criteria) this;
		}

		public Criteria andWidthGreaterThanOrEqualTo(String value) {
			addCriterion("width >=", value, "width");
			return (Criteria) this;
		}

		public Criteria andWidthLessThan(String value) {
			addCriterion("width <", value, "width");
			return (Criteria) this;
		}

		public Criteria andWidthLessThanOrEqualTo(String value) {
			addCriterion("width <=", value, "width");
			return (Criteria) this;
		}

		public Criteria andWidthLike(String value) {
			addCriterion("width like", value, "width");
			return (Criteria) this;
		}

		public Criteria andWidthNotLike(String value) {
			addCriterion("width not like", value, "width");
			return (Criteria) this;
		}

		public Criteria andWidthIn(List<String> values) {
			addCriterion("width in", values, "width");
			return (Criteria) this;
		}

		public Criteria andWidthNotIn(List<String> values) {
			addCriterion("width not in", values, "width");
			return (Criteria) this;
		}

		public Criteria andWidthBetween(String value1, String value2) {
			addCriterion("width between", value1, value2, "width");
			return (Criteria) this;
		}

		public Criteria andWidthNotBetween(String value1, String value2) {
			addCriterion("width not between", value1, value2, "width");
			return (Criteria) this;
		}

		public Criteria andHeightIsNull() {
			addCriterion("height is null");
			return (Criteria) this;
		}

		public Criteria andHeightIsNotNull() {
			addCriterion("height is not null");
			return (Criteria) this;
		}

		public Criteria andHeightEqualTo(String value) {
			addCriterion("height =", value, "height");
			return (Criteria) this;
		}

		public Criteria andHeightNotEqualTo(String value) {
			addCriterion("height <>", value, "height");
			return (Criteria) this;
		}

		public Criteria andHeightGreaterThan(String value) {
			addCriterion("height >", value, "height");
			return (Criteria) this;
		}

		public Criteria andHeightGreaterThanOrEqualTo(String value) {
			addCriterion("height >=", value, "height");
			return (Criteria) this;
		}

		public Criteria andHeightLessThan(String value) {
			addCriterion("height <", value, "height");
			return (Criteria) this;
		}

		public Criteria andHeightLessThanOrEqualTo(String value) {
			addCriterion("height <=", value, "height");
			return (Criteria) this;
		}

		public Criteria andHeightLike(String value) {
			addCriterion("height like", value, "height");
			return (Criteria) this;
		}

		public Criteria andHeightNotLike(String value) {
			addCriterion("height not like", value, "height");
			return (Criteria) this;
		}

		public Criteria andHeightIn(List<String> values) {
			addCriterion("height in", values, "height");
			return (Criteria) this;
		}

		public Criteria andHeightNotIn(List<String> values) {
			addCriterion("height not in", values, "height");
			return (Criteria) this;
		}

		public Criteria andHeightBetween(String value1, String value2) {
			addCriterion("height between", value1, value2, "height");
			return (Criteria) this;
		}

		public Criteria andHeightNotBetween(String value1, String value2) {
			addCriterion("height not between", value1, value2, "height");
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

		public Criteria andMaxAutionIdIsNull() {
			addCriterion("max_aution_id is null");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdIsNotNull() {
			addCriterion("max_aution_id is not null");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdEqualTo(Integer value) {
			addCriterion("max_aution_id =", value, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdNotEqualTo(Integer value) {
			addCriterion("max_aution_id <>", value, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdGreaterThan(Integer value) {
			addCriterion("max_aution_id >", value, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("max_aution_id >=", value, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdLessThan(Integer value) {
			addCriterion("max_aution_id <", value, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdLessThanOrEqualTo(Integer value) {
			addCriterion("max_aution_id <=", value, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdIn(List<Integer> values) {
			addCriterion("max_aution_id in", values, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdNotIn(List<Integer> values) {
			addCriterion("max_aution_id not in", values, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdBetween(Integer value1, Integer value2) {
			addCriterion("max_aution_id between", value1, value2, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAutionIdNotBetween(Integer value1, Integer value2) {
			addCriterion("max_aution_id not between", value1, value2, "maxAutionId");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionIsNull() {
			addCriterion("max_auction is null");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionIsNotNull() {
			addCriterion("max_auction is not null");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionEqualTo(java.math.BigDecimal value) {
			addCriterion("max_auction =", value, "maxAuction");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionNotEqualTo(java.math.BigDecimal value) {
			addCriterion("max_auction <>", value, "maxAuction");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionGreaterThan(java.math.BigDecimal value) {
			addCriterion("max_auction >", value, "maxAuction");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("max_auction >=", value, "maxAuction");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionLessThan(java.math.BigDecimal value) {
			addCriterion("max_auction <", value, "maxAuction");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("max_auction <=", value, "maxAuction");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionIn(List<java.math.BigDecimal> values) {
			addCriterion("max_auction in", values, "maxAuction");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionNotIn(List<java.math.BigDecimal> values) {
			addCriterion("max_auction not in", values, "maxAuction");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("max_auction between", value1, value2, "maxAuction");
			return (Criteria) this;
		}

		public Criteria andMaxAuctionNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("max_auction not between", value1, value2, "maxAuction");
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

		public Criteria andPlatformRateIsNull() {
			addCriterion("platform_rate is null");
			return (Criteria) this;
		}

		public Criteria andPlatformRateIsNotNull() {
			addCriterion("platform_rate is not null");
			return (Criteria) this;
		}

		public Criteria andPlatformRateEqualTo(java.math.BigDecimal value) {
			addCriterion("platform_rate =", value, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformRateNotEqualTo(java.math.BigDecimal value) {
			addCriterion("platform_rate <>", value, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformRateGreaterThan(java.math.BigDecimal value) {
			addCriterion("platform_rate >", value, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformRateGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("platform_rate >=", value, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformRateLessThan(java.math.BigDecimal value) {
			addCriterion("platform_rate <", value, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformRateLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("platform_rate <=", value, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformRateIn(List<java.math.BigDecimal> values) {
			addCriterion("platform_rate in", values, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformRateNotIn(List<java.math.BigDecimal> values) {
			addCriterion("platform_rate not in", values, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformRateBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("platform_rate between", value1, value2, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformRateNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("platform_rate not between", value1, value2, "platformRate");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyIsNull() {
			addCriterion("platform_money is null");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyIsNotNull() {
			addCriterion("platform_money is not null");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyEqualTo(java.math.BigDecimal value) {
			addCriterion("platform_money =", value, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyNotEqualTo(java.math.BigDecimal value) {
			addCriterion("platform_money <>", value, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyGreaterThan(java.math.BigDecimal value) {
			addCriterion("platform_money >", value, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyGreaterThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("platform_money >=", value, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyLessThan(java.math.BigDecimal value) {
			addCriterion("platform_money <", value, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyLessThanOrEqualTo(java.math.BigDecimal value) {
			addCriterion("platform_money <=", value, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyIn(List<java.math.BigDecimal> values) {
			addCriterion("platform_money in", values, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyNotIn(List<java.math.BigDecimal> values) {
			addCriterion("platform_money not in", values, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("platform_money between", value1, value2, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformMoneyNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
			addCriterion("platform_money not between", value1, value2, "platformMoney");
			return (Criteria) this;
		}

		public Criteria andPlatformStateIsNull() {
			addCriterion("platform_state is null");
			return (Criteria) this;
		}

		public Criteria andPlatformStateIsNotNull() {
			addCriterion("platform_state is not null");
			return (Criteria) this;
		}

		public Criteria andPlatformStateEqualTo(Integer value) {
			addCriterion("platform_state =", value, "platformState");
			return (Criteria) this;
		}

		public Criteria andPlatformStateNotEqualTo(Integer value) {
			addCriterion("platform_state <>", value, "platformState");
			return (Criteria) this;
		}

		public Criteria andPlatformStateGreaterThan(Integer value) {
			addCriterion("platform_state >", value, "platformState");
			return (Criteria) this;
		}

		public Criteria andPlatformStateGreaterThanOrEqualTo(Integer value) {
			addCriterion("platform_state >=", value, "platformState");
			return (Criteria) this;
		}

		public Criteria andPlatformStateLessThan(Integer value) {
			addCriterion("platform_state <", value, "platformState");
			return (Criteria) this;
		}

		public Criteria andPlatformStateLessThanOrEqualTo(Integer value) {
			addCriterion("platform_state <=", value, "platformState");
			return (Criteria) this;
		}

		public Criteria andPlatformStateIn(List<Integer> values) {
			addCriterion("platform_state in", values, "platformState");
			return (Criteria) this;
		}

		public Criteria andPlatformStateNotIn(List<Integer> values) {
			addCriterion("platform_state not in", values, "platformState");
			return (Criteria) this;
		}

		public Criteria andPlatformStateBetween(Integer value1, Integer value2) {
			addCriterion("platform_state between", value1, value2, "platformState");
			return (Criteria) this;
		}

		public Criteria andPlatformStateNotBetween(Integer value1, Integer value2) {
			addCriterion("platform_state not between", value1, value2, "platformState");
			return (Criteria) this;
		}

		public Criteria andSortOrderIsNull() {
			addCriterion("sort_order is null");
			return (Criteria) this;
		}

		public Criteria andSortOrderIsNotNull() {
			addCriterion("sort_order is not null");
			return (Criteria) this;
		}

		public Criteria andSortOrderEqualTo(Integer value) {
			addCriterion("sort_order =", value, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andSortOrderNotEqualTo(Integer value) {
			addCriterion("sort_order <>", value, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andSortOrderGreaterThan(Integer value) {
			addCriterion("sort_order >", value, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andSortOrderGreaterThanOrEqualTo(Integer value) {
			addCriterion("sort_order >=", value, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andSortOrderLessThan(Integer value) {
			addCriterion("sort_order <", value, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andSortOrderLessThanOrEqualTo(Integer value) {
			addCriterion("sort_order <=", value, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andSortOrderIn(List<Integer> values) {
			addCriterion("sort_order in", values, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andSortOrderNotIn(List<Integer> values) {
			addCriterion("sort_order not in", values, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andSortOrderBetween(Integer value1, Integer value2) {
			addCriterion("sort_order between", value1, value2, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andSortOrderNotBetween(Integer value1, Integer value2) {
			addCriterion("sort_order not between", value1, value2, "sortOrder");
			return (Criteria) this;
		}

		public Criteria andIsSuggestIsNull() {
			addCriterion("is_suggest is null");
			return (Criteria) this;
		}

		public Criteria andIsSuggestIsNotNull() {
			addCriterion("is_suggest is not null");
			return (Criteria) this;
		}

		public Criteria andIsSuggestEqualTo(Integer value) {
			addCriterion("is_suggest =", value, "isSuggest");
			return (Criteria) this;
		}

		public Criteria andIsSuggestNotEqualTo(Integer value) {
			addCriterion("is_suggest <>", value, "isSuggest");
			return (Criteria) this;
		}

		public Criteria andIsSuggestGreaterThan(Integer value) {
			addCriterion("is_suggest >", value, "isSuggest");
			return (Criteria) this;
		}

		public Criteria andIsSuggestGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_suggest >=", value, "isSuggest");
			return (Criteria) this;
		}

		public Criteria andIsSuggestLessThan(Integer value) {
			addCriterion("is_suggest <", value, "isSuggest");
			return (Criteria) this;
		}

		public Criteria andIsSuggestLessThanOrEqualTo(Integer value) {
			addCriterion("is_suggest <=", value, "isSuggest");
			return (Criteria) this;
		}

		public Criteria andIsSuggestIn(List<Integer> values) {
			addCriterion("is_suggest in", values, "isSuggest");
			return (Criteria) this;
		}

		public Criteria andIsSuggestNotIn(List<Integer> values) {
			addCriterion("is_suggest not in", values, "isSuggest");
			return (Criteria) this;
		}

		public Criteria andIsSuggestBetween(Integer value1, Integer value2) {
			addCriterion("is_suggest between", value1, value2, "isSuggest");
			return (Criteria) this;
		}

		public Criteria andIsSuggestNotBetween(Integer value1, Integer value2) {
			addCriterion("is_suggest not between", value1, value2, "isSuggest");
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

		public Criteria andSpecIsNull() {
			addCriterion("spec is null");
			return (Criteria) this;
		}

		public Criteria andSpecIsNotNull() {
			addCriterion("spec is not null");
			return (Criteria) this;
		}

		public Criteria andSpecEqualTo(String value) {
			addCriterion("spec =", value, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecNotEqualTo(String value) {
			addCriterion("spec <>", value, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecGreaterThan(String value) {
			addCriterion("spec >", value, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecGreaterThanOrEqualTo(String value) {
			addCriterion("spec >=", value, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecLessThan(String value) {
			addCriterion("spec <", value, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecLessThanOrEqualTo(String value) {
			addCriterion("spec <=", value, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecLike(String value) {
			addCriterion("spec like", value, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecNotLike(String value) {
			addCriterion("spec not like", value, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecIn(List<String> values) {
			addCriterion("spec in", values, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecNotIn(List<String> values) {
			addCriterion("spec not in", values, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecBetween(String value1, String value2) {
			addCriterion("spec between", value1, value2, "spec");
			return (Criteria) this;
		}

		public Criteria andSpecNotBetween(String value1, String value2) {
			addCriterion("spec not between", value1, value2, "spec");
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

		public Criteria andMaterialIsNull() {
			addCriterion("material is null");
			return (Criteria) this;
		}

		public Criteria andMaterialIsNotNull() {
			addCriterion("material is not null");
			return (Criteria) this;
		}

		public Criteria andMaterialEqualTo(String value) {
			addCriterion("material =", value, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialNotEqualTo(String value) {
			addCriterion("material <>", value, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialGreaterThan(String value) {
			addCriterion("material >", value, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialGreaterThanOrEqualTo(String value) {
			addCriterion("material >=", value, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialLessThan(String value) {
			addCriterion("material <", value, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialLessThanOrEqualTo(String value) {
			addCriterion("material <=", value, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialLike(String value) {
			addCriterion("material like", value, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialNotLike(String value) {
			addCriterion("material not like", value, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialIn(List<String> values) {
			addCriterion("material in", values, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialNotIn(List<String> values) {
			addCriterion("material not in", values, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialBetween(String value1, String value2) {
			addCriterion("material between", value1, value2, "material");
			return (Criteria) this;
		}

		public Criteria andMaterialNotBetween(String value1, String value2) {
			addCriterion("material not between", value1, value2, "material");
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

		public Criteria andCcLengthIsNull() {
			addCriterion("cc_length is null");
			return (Criteria) this;
		}

		public Criteria andCcLengthIsNotNull() {
			addCriterion("cc_length is not null");
			return (Criteria) this;
		}

		public Criteria andCcLengthEqualTo(String value) {
			addCriterion("cc_length =", value, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthNotEqualTo(String value) {
			addCriterion("cc_length <>", value, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthGreaterThan(String value) {
			addCriterion("cc_length >", value, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthGreaterThanOrEqualTo(String value) {
			addCriterion("cc_length >=", value, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthLessThan(String value) {
			addCriterion("cc_length <", value, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthLessThanOrEqualTo(String value) {
			addCriterion("cc_length <=", value, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthLike(String value) {
			addCriterion("cc_length like", value, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthNotLike(String value) {
			addCriterion("cc_length not like", value, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthIn(List<String> values) {
			addCriterion("cc_length in", values, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthNotIn(List<String> values) {
			addCriterion("cc_length not in", values, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthBetween(String value1, String value2) {
			addCriterion("cc_length between", value1, value2, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcLengthNotBetween(String value1, String value2) {
			addCriterion("cc_length not between", value1, value2, "ccLength");
			return (Criteria) this;
		}

		public Criteria andCcWidthIsNull() {
			addCriterion("cc_width is null");
			return (Criteria) this;
		}

		public Criteria andCcWidthIsNotNull() {
			addCriterion("cc_width is not null");
			return (Criteria) this;
		}

		public Criteria andCcWidthEqualTo(String value) {
			addCriterion("cc_width =", value, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthNotEqualTo(String value) {
			addCriterion("cc_width <>", value, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthGreaterThan(String value) {
			addCriterion("cc_width >", value, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthGreaterThanOrEqualTo(String value) {
			addCriterion("cc_width >=", value, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthLessThan(String value) {
			addCriterion("cc_width <", value, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthLessThanOrEqualTo(String value) {
			addCriterion("cc_width <=", value, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthLike(String value) {
			addCriterion("cc_width like", value, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthNotLike(String value) {
			addCriterion("cc_width not like", value, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthIn(List<String> values) {
			addCriterion("cc_width in", values, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthNotIn(List<String> values) {
			addCriterion("cc_width not in", values, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthBetween(String value1, String value2) {
			addCriterion("cc_width between", value1, value2, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcWidthNotBetween(String value1, String value2) {
			addCriterion("cc_width not between", value1, value2, "ccWidth");
			return (Criteria) this;
		}

		public Criteria andCcHeightIsNull() {
			addCriterion("cc_height is null");
			return (Criteria) this;
		}

		public Criteria andCcHeightIsNotNull() {
			addCriterion("cc_height is not null");
			return (Criteria) this;
		}

		public Criteria andCcHeightEqualTo(String value) {
			addCriterion("cc_height =", value, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightNotEqualTo(String value) {
			addCriterion("cc_height <>", value, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightGreaterThan(String value) {
			addCriterion("cc_height >", value, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightGreaterThanOrEqualTo(String value) {
			addCriterion("cc_height >=", value, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightLessThan(String value) {
			addCriterion("cc_height <", value, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightLessThanOrEqualTo(String value) {
			addCriterion("cc_height <=", value, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightLike(String value) {
			addCriterion("cc_height like", value, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightNotLike(String value) {
			addCriterion("cc_height not like", value, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightIn(List<String> values) {
			addCriterion("cc_height in", values, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightNotIn(List<String> values) {
			addCriterion("cc_height not in", values, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightBetween(String value1, String value2) {
			addCriterion("cc_height between", value1, value2, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcHeightNotBetween(String value1, String value2) {
			addCriterion("cc_height not between", value1, value2, "ccHeight");
			return (Criteria) this;
		}

		public Criteria andCcAllIsNull() {
			addCriterion("cc_all is null");
			return (Criteria) this;
		}

		public Criteria andCcAllIsNotNull() {
			addCriterion("cc_all is not null");
			return (Criteria) this;
		}

		public Criteria andCcAllEqualTo(String value) {
			addCriterion("cc_all =", value, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllNotEqualTo(String value) {
			addCriterion("cc_all <>", value, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllGreaterThan(String value) {
			addCriterion("cc_all >", value, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllGreaterThanOrEqualTo(String value) {
			addCriterion("cc_all >=", value, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllLessThan(String value) {
			addCriterion("cc_all <", value, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllLessThanOrEqualTo(String value) {
			addCriterion("cc_all <=", value, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllLike(String value) {
			addCriterion("cc_all like", value, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllNotLike(String value) {
			addCriterion("cc_all not like", value, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllIn(List<String> values) {
			addCriterion("cc_all in", values, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllNotIn(List<String> values) {
			addCriterion("cc_all not in", values, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllBetween(String value1, String value2) {
			addCriterion("cc_all between", value1, value2, "ccAll");
			return (Criteria) this;
		}

		public Criteria andCcAllNotBetween(String value1, String value2) {
			addCriterion("cc_all not between", value1, value2, "ccAll");
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

		public Criteria andBrandIsNull() {
			addCriterion("brand is null");
			return (Criteria) this;
		}

		public Criteria andBrandIsNotNull() {
			addCriterion("brand is not null");
			return (Criteria) this;
		}

		public Criteria andBrandEqualTo(String value) {
			addCriterion("brand =", value, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandNotEqualTo(String value) {
			addCriterion("brand <>", value, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandGreaterThan(String value) {
			addCriterion("brand >", value, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandGreaterThanOrEqualTo(String value) {
			addCriterion("brand >=", value, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandLessThan(String value) {
			addCriterion("brand <", value, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandLessThanOrEqualTo(String value) {
			addCriterion("brand <=", value, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandLike(String value) {
			addCriterion("brand like", value, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandNotLike(String value) {
			addCriterion("brand not like", value, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandIn(List<String> values) {
			addCriterion("brand in", values, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandNotIn(List<String> values) {
			addCriterion("brand not in", values, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandBetween(String value1, String value2) {
			addCriterion("brand between", value1, value2, "brand");
			return (Criteria) this;
		}

		public Criteria andBrandNotBetween(String value1, String value2) {
			addCriterion("brand not between", value1, value2, "brand");
			return (Criteria) this;
		}

		public Criteria andNewPercentIsNull() {
			addCriterion("new_percent is null");
			return (Criteria) this;
		}

		public Criteria andNewPercentIsNotNull() {
			addCriterion("new_percent is not null");
			return (Criteria) this;
		}

		public Criteria andNewPercentEqualTo(String value) {
			addCriterion("new_percent =", value, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentNotEqualTo(String value) {
			addCriterion("new_percent <>", value, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentGreaterThan(String value) {
			addCriterion("new_percent >", value, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentGreaterThanOrEqualTo(String value) {
			addCriterion("new_percent >=", value, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentLessThan(String value) {
			addCriterion("new_percent <", value, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentLessThanOrEqualTo(String value) {
			addCriterion("new_percent <=", value, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentLike(String value) {
			addCriterion("new_percent like", value, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentNotLike(String value) {
			addCriterion("new_percent not like", value, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentIn(List<String> values) {
			addCriterion("new_percent in", values, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentNotIn(List<String> values) {
			addCriterion("new_percent not in", values, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentBetween(String value1, String value2) {
			addCriterion("new_percent between", value1, value2, "newPercent");
			return (Criteria) this;
		}

		public Criteria andNewPercentNotBetween(String value1, String value2) {
			addCriterion("new_percent not between", value1, value2, "newPercent");
			return (Criteria) this;
		}

		public Criteria andThemeIsNull() {
			addCriterion("theme is null");
			return (Criteria) this;
		}

		public Criteria andThemeIsNotNull() {
			addCriterion("theme is not null");
			return (Criteria) this;
		}

		public Criteria andThemeEqualTo(String value) {
			addCriterion("theme =", value, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeNotEqualTo(String value) {
			addCriterion("theme <>", value, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeGreaterThan(String value) {
			addCriterion("theme >", value, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeGreaterThanOrEqualTo(String value) {
			addCriterion("theme >=", value, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeLessThan(String value) {
			addCriterion("theme <", value, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeLessThanOrEqualTo(String value) {
			addCriterion("theme <=", value, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeLike(String value) {
			addCriterion("theme like", value, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeNotLike(String value) {
			addCriterion("theme not like", value, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeIn(List<String> values) {
			addCriterion("theme in", values, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeNotIn(List<String> values) {
			addCriterion("theme not in", values, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeBetween(String value1, String value2) {
			addCriterion("theme between", value1, value2, "theme");
			return (Criteria) this;
		}

		public Criteria andThemeNotBetween(String value1, String value2) {
			addCriterion("theme not between", value1, value2, "theme");
			return (Criteria) this;
		}

		public Criteria andStyleIsNull() {
			addCriterion("style is null");
			return (Criteria) this;
		}

		public Criteria andStyleIsNotNull() {
			addCriterion("style is not null");
			return (Criteria) this;
		}

		public Criteria andStyleEqualTo(String value) {
			addCriterion("style =", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleNotEqualTo(String value) {
			addCriterion("style <>", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleGreaterThan(String value) {
			addCriterion("style >", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleGreaterThanOrEqualTo(String value) {
			addCriterion("style >=", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleLessThan(String value) {
			addCriterion("style <", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleLessThanOrEqualTo(String value) {
			addCriterion("style <=", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleLike(String value) {
			addCriterion("style like", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleNotLike(String value) {
			addCriterion("style not like", value, "style");
			return (Criteria) this;
		}

		public Criteria andStyleIn(List<String> values) {
			addCriterion("style in", values, "style");
			return (Criteria) this;
		}

		public Criteria andStyleNotIn(List<String> values) {
			addCriterion("style not in", values, "style");
			return (Criteria) this;
		}

		public Criteria andStyleBetween(String value1, String value2) {
			addCriterion("style between", value1, value2, "style");
			return (Criteria) this;
		}

		public Criteria andStyleNotBetween(String value1, String value2) {
			addCriterion("style not between", value1, value2, "style");
			return (Criteria) this;
		}

		public Criteria andMaterialNameIsNull() {
			addCriterion("material_name is null");
			return (Criteria) this;
		}

		public Criteria andMaterialNameIsNotNull() {
			addCriterion("material_name is not null");
			return (Criteria) this;
		}

		public Criteria andMaterialNameEqualTo(String value) {
			addCriterion("material_name =", value, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameNotEqualTo(String value) {
			addCriterion("material_name <>", value, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameGreaterThan(String value) {
			addCriterion("material_name >", value, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameGreaterThanOrEqualTo(String value) {
			addCriterion("material_name >=", value, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameLessThan(String value) {
			addCriterion("material_name <", value, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameLessThanOrEqualTo(String value) {
			addCriterion("material_name <=", value, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameLike(String value) {
			addCriterion("material_name like", value, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameNotLike(String value) {
			addCriterion("material_name not like", value, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameIn(List<String> values) {
			addCriterion("material_name in", values, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameNotIn(List<String> values) {
			addCriterion("material_name not in", values, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameBetween(String value1, String value2) {
			addCriterion("material_name between", value1, value2, "materialName");
			return (Criteria) this;
		}

		public Criteria andMaterialNameNotBetween(String value1, String value2) {
			addCriterion("material_name not between", value1, value2, "materialName");
			return (Criteria) this;
		}

		public Criteria andWordDescriptIsNull() {
			addCriterion("word_descript is null");
			return (Criteria) this;
		}

		public Criteria andWordDescriptIsNotNull() {
			addCriterion("word_descript is not null");
			return (Criteria) this;
		}

		public Criteria andWordDescriptEqualTo(String value) {
			addCriterion("word_descript =", value, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptNotEqualTo(String value) {
			addCriterion("word_descript <>", value, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptGreaterThan(String value) {
			addCriterion("word_descript >", value, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptGreaterThanOrEqualTo(String value) {
			addCriterion("word_descript >=", value, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptLessThan(String value) {
			addCriterion("word_descript <", value, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptLessThanOrEqualTo(String value) {
			addCriterion("word_descript <=", value, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptLike(String value) {
			addCriterion("word_descript like", value, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptNotLike(String value) {
			addCriterion("word_descript not like", value, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptIn(List<String> values) {
			addCriterion("word_descript in", values, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptNotIn(List<String> values) {
			addCriterion("word_descript not in", values, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptBetween(String value1, String value2) {
			addCriterion("word_descript between", value1, value2, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andWordDescriptNotBetween(String value1, String value2) {
			addCriterion("word_descript not between", value1, value2, "wordDescript");
			return (Criteria) this;
		}

		public Criteria andBannerVideoIsNull() {
			addCriterion("banner_video is null");
			return (Criteria) this;
		}

		public Criteria andBannerVideoIsNotNull() {
			addCriterion("banner_video is not null");
			return (Criteria) this;
		}

		public Criteria andBannerVideoEqualTo(String value) {
			addCriterion("banner_video =", value, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoNotEqualTo(String value) {
			addCriterion("banner_video <>", value, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoGreaterThan(String value) {
			addCriterion("banner_video >", value, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoGreaterThanOrEqualTo(String value) {
			addCriterion("banner_video >=", value, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoLessThan(String value) {
			addCriterion("banner_video <", value, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoLessThanOrEqualTo(String value) {
			addCriterion("banner_video <=", value, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoLike(String value) {
			addCriterion("banner_video like", value, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoNotLike(String value) {
			addCriterion("banner_video not like", value, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoIn(List<String> values) {
			addCriterion("banner_video in", values, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoNotIn(List<String> values) {
			addCriterion("banner_video not in", values, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoBetween(String value1, String value2) {
			addCriterion("banner_video between", value1, value2, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoNotBetween(String value1, String value2) {
			addCriterion("banner_video not between", value1, value2, "bannerVideo");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceIsNull() {
			addCriterion("banner_video_face is null");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceIsNotNull() {
			addCriterion("banner_video_face is not null");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceEqualTo(String value) {
			addCriterion("banner_video_face =", value, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceNotEqualTo(String value) {
			addCriterion("banner_video_face <>", value, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceGreaterThan(String value) {
			addCriterion("banner_video_face >", value, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceGreaterThanOrEqualTo(String value) {
			addCriterion("banner_video_face >=", value, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceLessThan(String value) {
			addCriterion("banner_video_face <", value, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceLessThanOrEqualTo(String value) {
			addCriterion("banner_video_face <=", value, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceLike(String value) {
			addCriterion("banner_video_face like", value, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceNotLike(String value) {
			addCriterion("banner_video_face not like", value, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceIn(List<String> values) {
			addCriterion("banner_video_face in", values, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceNotIn(List<String> values) {
			addCriterion("banner_video_face not in", values, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceBetween(String value1, String value2) {
			addCriterion("banner_video_face between", value1, value2, "bannerVideoFace");
			return (Criteria) this;
		}

		public Criteria andBannerVideoFaceNotBetween(String value1, String value2) {
			addCriterion("banner_video_face not between", value1, value2, "bannerVideoFace");
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