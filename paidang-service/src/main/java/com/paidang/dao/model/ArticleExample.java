package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class ArticleExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ArticleExample(){
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

		public Criteria andCoverIsNull() {
			addCriterion("cover is null");
			return (Criteria) this;
		}

		public Criteria andCoverIsNotNull() {
			addCriterion("cover is not null");
			return (Criteria) this;
		}

		public Criteria andCoverEqualTo(String value) {
			addCriterion("cover =", value, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverNotEqualTo(String value) {
			addCriterion("cover <>", value, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverGreaterThan(String value) {
			addCriterion("cover >", value, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverGreaterThanOrEqualTo(String value) {
			addCriterion("cover >=", value, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverLessThan(String value) {
			addCriterion("cover <", value, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverLessThanOrEqualTo(String value) {
			addCriterion("cover <=", value, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverLike(String value) {
			addCriterion("cover like", value, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverNotLike(String value) {
			addCriterion("cover not like", value, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverIn(List<String> values) {
			addCriterion("cover in", values, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverNotIn(List<String> values) {
			addCriterion("cover not in", values, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverBetween(String value1, String value2) {
			addCriterion("cover between", value1, value2, "cover");
			return (Criteria) this;
		}

		public Criteria andCoverNotBetween(String value1, String value2) {
			addCriterion("cover not between", value1, value2, "cover");
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

		public Criteria andIsShowIsNull() {
			addCriterion("is_show is null");
			return (Criteria) this;
		}

		public Criteria andIsShowIsNotNull() {
			addCriterion("is_show is not null");
			return (Criteria) this;
		}

		public Criteria andIsShowEqualTo(Integer value) {
			addCriterion("is_show =", value, "isShow");
			return (Criteria) this;
		}

		public Criteria andIsShowNotEqualTo(Integer value) {
			addCriterion("is_show <>", value, "isShow");
			return (Criteria) this;
		}

		public Criteria andIsShowGreaterThan(Integer value) {
			addCriterion("is_show >", value, "isShow");
			return (Criteria) this;
		}

		public Criteria andIsShowGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_show >=", value, "isShow");
			return (Criteria) this;
		}

		public Criteria andIsShowLessThan(Integer value) {
			addCriterion("is_show <", value, "isShow");
			return (Criteria) this;
		}

		public Criteria andIsShowLessThanOrEqualTo(Integer value) {
			addCriterion("is_show <=", value, "isShow");
			return (Criteria) this;
		}

		public Criteria andIsShowIn(List<Integer> values) {
			addCriterion("is_show in", values, "isShow");
			return (Criteria) this;
		}

		public Criteria andIsShowNotIn(List<Integer> values) {
			addCriterion("is_show not in", values, "isShow");
			return (Criteria) this;
		}

		public Criteria andIsShowBetween(Integer value1, Integer value2) {
			addCriterion("is_show between", value1, value2, "isShow");
			return (Criteria) this;
		}

		public Criteria andIsShowNotBetween(Integer value1, Integer value2) {
			addCriterion("is_show not between", value1, value2, "isShow");
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

		public Criteria andContentIsNull() {
			addCriterion("content is null");
			return (Criteria) this;
		}

		public Criteria andContentIsNotNull() {
			addCriterion("content is not null");
			return (Criteria) this;
		}

		public Criteria andContentEqualTo(String value) {
			addCriterion("content =", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotEqualTo(String value) {
			addCriterion("content <>", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentGreaterThan(String value) {
			addCriterion("content >", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentGreaterThanOrEqualTo(String value) {
			addCriterion("content >=", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLessThan(String value) {
			addCriterion("content <", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLessThanOrEqualTo(String value) {
			addCriterion("content <=", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLike(String value) {
			addCriterion("content like", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotLike(String value) {
			addCriterion("content not like", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentIn(List<String> values) {
			addCriterion("content in", values, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotIn(List<String> values) {
			addCriterion("content not in", values, "content");
			return (Criteria) this;
		}

		public Criteria andContentBetween(String value1, String value2) {
			addCriterion("content between", value1, value2, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotBetween(String value1, String value2) {
			addCriterion("content not between", value1, value2, "content");
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

		public Criteria andVideoIsNull() {
			addCriterion("video is null");
			return (Criteria) this;
		}

		public Criteria andVideoIsNotNull() {
			addCriterion("video is not null");
			return (Criteria) this;
		}

		public Criteria andVideoEqualTo(String value) {
			addCriterion("video =", value, "video");
			return (Criteria) this;
		}

		public Criteria andVideoNotEqualTo(String value) {
			addCriterion("video <>", value, "video");
			return (Criteria) this;
		}

		public Criteria andVideoGreaterThan(String value) {
			addCriterion("video >", value, "video");
			return (Criteria) this;
		}

		public Criteria andVideoGreaterThanOrEqualTo(String value) {
			addCriterion("video >=", value, "video");
			return (Criteria) this;
		}

		public Criteria andVideoLessThan(String value) {
			addCriterion("video <", value, "video");
			return (Criteria) this;
		}

		public Criteria andVideoLessThanOrEqualTo(String value) {
			addCriterion("video <=", value, "video");
			return (Criteria) this;
		}

		public Criteria andVideoLike(String value) {
			addCriterion("video like", value, "video");
			return (Criteria) this;
		}

		public Criteria andVideoNotLike(String value) {
			addCriterion("video not like", value, "video");
			return (Criteria) this;
		}

		public Criteria andVideoIn(List<String> values) {
			addCriterion("video in", values, "video");
			return (Criteria) this;
		}

		public Criteria andVideoNotIn(List<String> values) {
			addCriterion("video not in", values, "video");
			return (Criteria) this;
		}

		public Criteria andVideoBetween(String value1, String value2) {
			addCriterion("video between", value1, value2, "video");
			return (Criteria) this;
		}

		public Criteria andVideoNotBetween(String value1, String value2) {
			addCriterion("video not between", value1, value2, "video");
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

		public Criteria andCollectCountIsNull() {
			addCriterion("collect_count is null");
			return (Criteria) this;
		}

		public Criteria andCollectCountIsNotNull() {
			addCriterion("collect_count is not null");
			return (Criteria) this;
		}

		public Criteria andCollectCountEqualTo(Integer value) {
			addCriterion("collect_count =", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountNotEqualTo(Integer value) {
			addCriterion("collect_count <>", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountGreaterThan(Integer value) {
			addCriterion("collect_count >", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("collect_count >=", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountLessThan(Integer value) {
			addCriterion("collect_count <", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountLessThanOrEqualTo(Integer value) {
			addCriterion("collect_count <=", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountIn(List<Integer> values) {
			addCriterion("collect_count in", values, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountNotIn(List<Integer> values) {
			addCriterion("collect_count not in", values, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountBetween(Integer value1, Integer value2) {
			addCriterion("collect_count between", value1, value2, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountNotBetween(Integer value1, Integer value2) {
			addCriterion("collect_count not between", value1, value2, "collectCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountIsNull() {
			addCriterion("praise_count is null");
			return (Criteria) this;
		}

		public Criteria andPraiseCountIsNotNull() {
			addCriterion("praise_count is not null");
			return (Criteria) this;
		}

		public Criteria andPraiseCountEqualTo(Integer value) {
			addCriterion("praise_count =", value, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountNotEqualTo(Integer value) {
			addCriterion("praise_count <>", value, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountGreaterThan(Integer value) {
			addCriterion("praise_count >", value, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("praise_count >=", value, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountLessThan(Integer value) {
			addCriterion("praise_count <", value, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountLessThanOrEqualTo(Integer value) {
			addCriterion("praise_count <=", value, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountIn(List<Integer> values) {
			addCriterion("praise_count in", values, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountNotIn(List<Integer> values) {
			addCriterion("praise_count not in", values, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountBetween(Integer value1, Integer value2) {
			addCriterion("praise_count between", value1, value2, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andPraiseCountNotBetween(Integer value1, Integer value2) {
			addCriterion("praise_count not between", value1, value2, "praiseCount");
			return (Criteria) this;
		}

		public Criteria andClickCountIsNull() {
			addCriterion("click_count is null");
			return (Criteria) this;
		}

		public Criteria andClickCountIsNotNull() {
			addCriterion("click_count is not null");
			return (Criteria) this;
		}

		public Criteria andClickCountEqualTo(Integer value) {
			addCriterion("click_count =", value, "clickCount");
			return (Criteria) this;
		}

		public Criteria andClickCountNotEqualTo(Integer value) {
			addCriterion("click_count <>", value, "clickCount");
			return (Criteria) this;
		}

		public Criteria andClickCountGreaterThan(Integer value) {
			addCriterion("click_count >", value, "clickCount");
			return (Criteria) this;
		}

		public Criteria andClickCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("click_count >=", value, "clickCount");
			return (Criteria) this;
		}

		public Criteria andClickCountLessThan(Integer value) {
			addCriterion("click_count <", value, "clickCount");
			return (Criteria) this;
		}

		public Criteria andClickCountLessThanOrEqualTo(Integer value) {
			addCriterion("click_count <=", value, "clickCount");
			return (Criteria) this;
		}

		public Criteria andClickCountIn(List<Integer> values) {
			addCriterion("click_count in", values, "clickCount");
			return (Criteria) this;
		}

		public Criteria andClickCountNotIn(List<Integer> values) {
			addCriterion("click_count not in", values, "clickCount");
			return (Criteria) this;
		}

		public Criteria andClickCountBetween(Integer value1, Integer value2) {
			addCriterion("click_count between", value1, value2, "clickCount");
			return (Criteria) this;
		}

		public Criteria andClickCountNotBetween(Integer value1, Integer value2) {
			addCriterion("click_count not between", value1, value2, "clickCount");
			return (Criteria) this;
		}

		public Criteria andReportCountIsNull() {
			addCriterion("report_count is null");
			return (Criteria) this;
		}

		public Criteria andReportCountIsNotNull() {
			addCriterion("report_count is not null");
			return (Criteria) this;
		}

		public Criteria andReportCountEqualTo(Integer value) {
			addCriterion("report_count =", value, "reportCount");
			return (Criteria) this;
		}

		public Criteria andReportCountNotEqualTo(Integer value) {
			addCriterion("report_count <>", value, "reportCount");
			return (Criteria) this;
		}

		public Criteria andReportCountGreaterThan(Integer value) {
			addCriterion("report_count >", value, "reportCount");
			return (Criteria) this;
		}

		public Criteria andReportCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("report_count >=", value, "reportCount");
			return (Criteria) this;
		}

		public Criteria andReportCountLessThan(Integer value) {
			addCriterion("report_count <", value, "reportCount");
			return (Criteria) this;
		}

		public Criteria andReportCountLessThanOrEqualTo(Integer value) {
			addCriterion("report_count <=", value, "reportCount");
			return (Criteria) this;
		}

		public Criteria andReportCountIn(List<Integer> values) {
			addCriterion("report_count in", values, "reportCount");
			return (Criteria) this;
		}

		public Criteria andReportCountNotIn(List<Integer> values) {
			addCriterion("report_count not in", values, "reportCount");
			return (Criteria) this;
		}

		public Criteria andReportCountBetween(Integer value1, Integer value2) {
			addCriterion("report_count between", value1, value2, "reportCount");
			return (Criteria) this;
		}

		public Criteria andReportCountNotBetween(Integer value1, Integer value2) {
			addCriterion("report_count not between", value1, value2, "reportCount");
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

		public Criteria andReleaseTimeIsNull() {
			addCriterion("release_time is null");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeIsNotNull() {
			addCriterion("release_time is not null");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeEqualTo(java.util.Date value) {
			addCriterion("release_time =", value, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeNotEqualTo(java.util.Date value) {
			addCriterion("release_time <>", value, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeGreaterThan(java.util.Date value) {
			addCriterion("release_time >", value, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("release_time >=", value, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeLessThan(java.util.Date value) {
			addCriterion("release_time <", value, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("release_time <=", value, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeIn(List<java.util.Date> values) {
			addCriterion("release_time in", values, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeNotIn(List<java.util.Date> values) {
			addCriterion("release_time not in", values, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("release_time between", value1, value2, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andReleaseTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("release_time not between", value1, value2, "releaseTime");
			return (Criteria) this;
		}

		public Criteria andCreateNameIsNull() {
			addCriterion("create_name is null");
			return (Criteria) this;
		}

		public Criteria andCreateNameIsNotNull() {
			addCriterion("create_name is not null");
			return (Criteria) this;
		}

		public Criteria andCreateNameEqualTo(String value) {
			addCriterion("create_name =", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameNotEqualTo(String value) {
			addCriterion("create_name <>", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameGreaterThan(String value) {
			addCriterion("create_name >", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameGreaterThanOrEqualTo(String value) {
			addCriterion("create_name >=", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameLessThan(String value) {
			addCriterion("create_name <", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameLessThanOrEqualTo(String value) {
			addCriterion("create_name <=", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameLike(String value) {
			addCriterion("create_name like", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameNotLike(String value) {
			addCriterion("create_name not like", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameIn(List<String> values) {
			addCriterion("create_name in", values, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameNotIn(List<String> values) {
			addCriterion("create_name not in", values, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameBetween(String value1, String value2) {
			addCriterion("create_name between", value1, value2, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameNotBetween(String value1, String value2) {
			addCriterion("create_name not between", value1, value2, "createName");
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

		public Criteria andUpdateNameIsNull() {
			addCriterion("update_name is null");
			return (Criteria) this;
		}

		public Criteria andUpdateNameIsNotNull() {
			addCriterion("update_name is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateNameEqualTo(String value) {
			addCriterion("update_name =", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameNotEqualTo(String value) {
			addCriterion("update_name <>", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameGreaterThan(String value) {
			addCriterion("update_name >", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameGreaterThanOrEqualTo(String value) {
			addCriterion("update_name >=", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameLessThan(String value) {
			addCriterion("update_name <", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameLessThanOrEqualTo(String value) {
			addCriterion("update_name <=", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameLike(String value) {
			addCriterion("update_name like", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameNotLike(String value) {
			addCriterion("update_name not like", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameIn(List<String> values) {
			addCriterion("update_name in", values, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameNotIn(List<String> values) {
			addCriterion("update_name not in", values, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameBetween(String value1, String value2) {
			addCriterion("update_name between", value1, value2, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameNotBetween(String value1, String value2) {
			addCriterion("update_name not between", value1, value2, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNull() {
			addCriterion("update_time is null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNotNull() {
			addCriterion("update_time is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeEqualTo(java.util.Date value) {
			addCriterion("update_time =", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotEqualTo(java.util.Date value) {
			addCriterion("update_time <>", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThan(java.util.Date value) {
			addCriterion("update_time >", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThanOrEqualTo(java.util.Date value) {
			addCriterion("update_time >=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThan(java.util.Date value) {
			addCriterion("update_time <", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThanOrEqualTo(java.util.Date value) {
			addCriterion("update_time <=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIn(List<java.util.Date> values) {
			addCriterion("update_time in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotIn(List<java.util.Date> values) {
			addCriterion("update_time not in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("update_time between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotBetween(java.util.Date value1, java.util.Date value2) {
			addCriterion("update_time not between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andSortIsNull() {
			addCriterion("sort is null");
			return (Criteria) this;
		}

		public Criteria andSortIsNotNull() {
			addCriterion("sort is not null");
			return (Criteria) this;
		}

		public Criteria andSortEqualTo(Integer value) {
			addCriterion("sort =", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotEqualTo(Integer value) {
			addCriterion("sort <>", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThan(Integer value) {
			addCriterion("sort >", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThanOrEqualTo(Integer value) {
			addCriterion("sort >=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThan(Integer value) {
			addCriterion("sort <", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThanOrEqualTo(Integer value) {
			addCriterion("sort <=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortIn(List<Integer> values) {
			addCriterion("sort in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotIn(List<Integer> values) {
			addCriterion("sort not in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortBetween(Integer value1, Integer value2) {
			addCriterion("sort between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotBetween(Integer value1, Integer value2) {
			addCriterion("sort not between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("description not between", value1, value2, "description");
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