package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class ArticleCommentExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ArticleCommentExample(){
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

		public Criteria andArticleIdIsNull() {
			addCriterion("article_id is null");
			return (Criteria) this;
		}

		public Criteria andArticleIdIsNotNull() {
			addCriterion("article_id is not null");
			return (Criteria) this;
		}

		public Criteria andArticleIdEqualTo(Integer value) {
			addCriterion("article_id =", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdNotEqualTo(Integer value) {
			addCriterion("article_id <>", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdGreaterThan(Integer value) {
			addCriterion("article_id >", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("article_id >=", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdLessThan(Integer value) {
			addCriterion("article_id <", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
			addCriterion("article_id <=", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdIn(List<Integer> values) {
			addCriterion("article_id in", values, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdNotIn(List<Integer> values) {
			addCriterion("article_id not in", values, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdBetween(Integer value1, Integer value2) {
			addCriterion("article_id between", value1, value2, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
			addCriterion("article_id not between", value1, value2, "articleId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdIsNull() {
			addCriterion("author_id is null");
			return (Criteria) this;
		}

		public Criteria andAuthorIdIsNotNull() {
			addCriterion("author_id is not null");
			return (Criteria) this;
		}

		public Criteria andAuthorIdEqualTo(Integer value) {
			addCriterion("author_id =", value, "authorId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdNotEqualTo(Integer value) {
			addCriterion("author_id <>", value, "authorId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdGreaterThan(Integer value) {
			addCriterion("author_id >", value, "authorId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("author_id >=", value, "authorId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdLessThan(Integer value) {
			addCriterion("author_id <", value, "authorId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdLessThanOrEqualTo(Integer value) {
			addCriterion("author_id <=", value, "authorId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdIn(List<Integer> values) {
			addCriterion("author_id in", values, "authorId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdNotIn(List<Integer> values) {
			addCriterion("author_id not in", values, "authorId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdBetween(Integer value1, Integer value2) {
			addCriterion("author_id between", value1, value2, "authorId");
			return (Criteria) this;
		}

		public Criteria andAuthorIdNotBetween(Integer value1, Integer value2) {
			addCriterion("author_id not between", value1, value2, "authorId");
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

		public Criteria andNicknameIsNull() {
			addCriterion("nickname is null");
			return (Criteria) this;
		}

		public Criteria andNicknameIsNotNull() {
			addCriterion("nickname is not null");
			return (Criteria) this;
		}

		public Criteria andNicknameEqualTo(String value) {
			addCriterion("nickname =", value, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameNotEqualTo(String value) {
			addCriterion("nickname <>", value, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameGreaterThan(String value) {
			addCriterion("nickname >", value, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameGreaterThanOrEqualTo(String value) {
			addCriterion("nickname >=", value, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameLessThan(String value) {
			addCriterion("nickname <", value, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameLessThanOrEqualTo(String value) {
			addCriterion("nickname <=", value, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameLike(String value) {
			addCriterion("nickname like", value, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameNotLike(String value) {
			addCriterion("nickname not like", value, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameIn(List<String> values) {
			addCriterion("nickname in", values, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameNotIn(List<String> values) {
			addCriterion("nickname not in", values, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameBetween(String value1, String value2) {
			addCriterion("nickname between", value1, value2, "nickname");
			return (Criteria) this;
		}

		public Criteria andNicknameNotBetween(String value1, String value2) {
			addCriterion("nickname not between", value1, value2, "nickname");
			return (Criteria) this;
		}

		public Criteria andIconIsNull() {
			addCriterion("icon is null");
			return (Criteria) this;
		}

		public Criteria andIconIsNotNull() {
			addCriterion("icon is not null");
			return (Criteria) this;
		}

		public Criteria andIconEqualTo(String value) {
			addCriterion("icon =", value, "icon");
			return (Criteria) this;
		}

		public Criteria andIconNotEqualTo(String value) {
			addCriterion("icon <>", value, "icon");
			return (Criteria) this;
		}

		public Criteria andIconGreaterThan(String value) {
			addCriterion("icon >", value, "icon");
			return (Criteria) this;
		}

		public Criteria andIconGreaterThanOrEqualTo(String value) {
			addCriterion("icon >=", value, "icon");
			return (Criteria) this;
		}

		public Criteria andIconLessThan(String value) {
			addCriterion("icon <", value, "icon");
			return (Criteria) this;
		}

		public Criteria andIconLessThanOrEqualTo(String value) {
			addCriterion("icon <=", value, "icon");
			return (Criteria) this;
		}

		public Criteria andIconLike(String value) {
			addCriterion("icon like", value, "icon");
			return (Criteria) this;
		}

		public Criteria andIconNotLike(String value) {
			addCriterion("icon not like", value, "icon");
			return (Criteria) this;
		}

		public Criteria andIconIn(List<String> values) {
			addCriterion("icon in", values, "icon");
			return (Criteria) this;
		}

		public Criteria andIconNotIn(List<String> values) {
			addCriterion("icon not in", values, "icon");
			return (Criteria) this;
		}

		public Criteria andIconBetween(String value1, String value2) {
			addCriterion("icon between", value1, value2, "icon");
			return (Criteria) this;
		}

		public Criteria andIconNotBetween(String value1, String value2) {
			addCriterion("icon not between", value1, value2, "icon");
			return (Criteria) this;
		}

		public Criteria andIsTopIsNull() {
			addCriterion("is_top is null");
			return (Criteria) this;
		}

		public Criteria andIsTopIsNotNull() {
			addCriterion("is_top is not null");
			return (Criteria) this;
		}

		public Criteria andIsTopEqualTo(Integer value) {
			addCriterion("is_top =", value, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsTopNotEqualTo(Integer value) {
			addCriterion("is_top <>", value, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsTopGreaterThan(Integer value) {
			addCriterion("is_top >", value, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsTopGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_top >=", value, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsTopLessThan(Integer value) {
			addCriterion("is_top <", value, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsTopLessThanOrEqualTo(Integer value) {
			addCriterion("is_top <=", value, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsTopIn(List<Integer> values) {
			addCriterion("is_top in", values, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsTopNotIn(List<Integer> values) {
			addCriterion("is_top not in", values, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsTopBetween(Integer value1, Integer value2) {
			addCriterion("is_top between", value1, value2, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsTopNotBetween(Integer value1, Integer value2) {
			addCriterion("is_top not between", value1, value2, "isTop");
			return (Criteria) this;
		}

		public Criteria andIsHotIsNull() {
			addCriterion("is_hot is null");
			return (Criteria) this;
		}

		public Criteria andIsHotIsNotNull() {
			addCriterion("is_hot is not null");
			return (Criteria) this;
		}

		public Criteria andIsHotEqualTo(Integer value) {
			addCriterion("is_hot =", value, "isHot");
			return (Criteria) this;
		}

		public Criteria andIsHotNotEqualTo(Integer value) {
			addCriterion("is_hot <>", value, "isHot");
			return (Criteria) this;
		}

		public Criteria andIsHotGreaterThan(Integer value) {
			addCriterion("is_hot >", value, "isHot");
			return (Criteria) this;
		}

		public Criteria andIsHotGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_hot >=", value, "isHot");
			return (Criteria) this;
		}

		public Criteria andIsHotLessThan(Integer value) {
			addCriterion("is_hot <", value, "isHot");
			return (Criteria) this;
		}

		public Criteria andIsHotLessThanOrEqualTo(Integer value) {
			addCriterion("is_hot <=", value, "isHot");
			return (Criteria) this;
		}

		public Criteria andIsHotIn(List<Integer> values) {
			addCriterion("is_hot in", values, "isHot");
			return (Criteria) this;
		}

		public Criteria andIsHotNotIn(List<Integer> values) {
			addCriterion("is_hot not in", values, "isHot");
			return (Criteria) this;
		}

		public Criteria andIsHotBetween(Integer value1, Integer value2) {
			addCriterion("is_hot between", value1, value2, "isHot");
			return (Criteria) this;
		}

		public Criteria andIsHotNotBetween(Integer value1, Integer value2) {
			addCriterion("is_hot not between", value1, value2, "isHot");
			return (Criteria) this;
		}

		public Criteria andLikeNumIsNull() {
			addCriterion("like_num is null");
			return (Criteria) this;
		}

		public Criteria andLikeNumIsNotNull() {
			addCriterion("like_num is not null");
			return (Criteria) this;
		}

		public Criteria andLikeNumEqualTo(Integer value) {
			addCriterion("like_num =", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumNotEqualTo(Integer value) {
			addCriterion("like_num <>", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumGreaterThan(Integer value) {
			addCriterion("like_num >", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("like_num >=", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumLessThan(Integer value) {
			addCriterion("like_num <", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumLessThanOrEqualTo(Integer value) {
			addCriterion("like_num <=", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumIn(List<Integer> values) {
			addCriterion("like_num in", values, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumNotIn(List<Integer> values) {
			addCriterion("like_num not in", values, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumBetween(Integer value1, Integer value2) {
			addCriterion("like_num between", value1, value2, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumNotBetween(Integer value1, Integer value2) {
			addCriterion("like_num not between", value1, value2, "likeNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumIsNull() {
			addCriterion("reply_num is null");
			return (Criteria) this;
		}

		public Criteria andReplyNumIsNotNull() {
			addCriterion("reply_num is not null");
			return (Criteria) this;
		}

		public Criteria andReplyNumEqualTo(Integer value) {
			addCriterion("reply_num =", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumNotEqualTo(Integer value) {
			addCriterion("reply_num <>", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumGreaterThan(Integer value) {
			addCriterion("reply_num >", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("reply_num >=", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumLessThan(Integer value) {
			addCriterion("reply_num <", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumLessThanOrEqualTo(Integer value) {
			addCriterion("reply_num <=", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumIn(List<Integer> values) {
			addCriterion("reply_num in", values, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumNotIn(List<Integer> values) {
			addCriterion("reply_num not in", values, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumBetween(Integer value1, Integer value2) {
			addCriterion("reply_num between", value1, value2, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumNotBetween(Integer value1, Integer value2) {
			addCriterion("reply_num not between", value1, value2, "replyNum");
			return (Criteria) this;
		}

		public Criteria andIsReplyIsNull() {
			addCriterion("is_reply is null");
			return (Criteria) this;
		}

		public Criteria andIsReplyIsNotNull() {
			addCriterion("is_reply is not null");
			return (Criteria) this;
		}

		public Criteria andIsReplyEqualTo(Integer value) {
			addCriterion("is_reply =", value, "isReply");
			return (Criteria) this;
		}

		public Criteria andIsReplyNotEqualTo(Integer value) {
			addCriterion("is_reply <>", value, "isReply");
			return (Criteria) this;
		}

		public Criteria andIsReplyGreaterThan(Integer value) {
			addCriterion("is_reply >", value, "isReply");
			return (Criteria) this;
		}

		public Criteria andIsReplyGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_reply >=", value, "isReply");
			return (Criteria) this;
		}

		public Criteria andIsReplyLessThan(Integer value) {
			addCriterion("is_reply <", value, "isReply");
			return (Criteria) this;
		}

		public Criteria andIsReplyLessThanOrEqualTo(Integer value) {
			addCriterion("is_reply <=", value, "isReply");
			return (Criteria) this;
		}

		public Criteria andIsReplyIn(List<Integer> values) {
			addCriterion("is_reply in", values, "isReply");
			return (Criteria) this;
		}

		public Criteria andIsReplyNotIn(List<Integer> values) {
			addCriterion("is_reply not in", values, "isReply");
			return (Criteria) this;
		}

		public Criteria andIsReplyBetween(Integer value1, Integer value2) {
			addCriterion("is_reply between", value1, value2, "isReply");
			return (Criteria) this;
		}

		public Criteria andIsReplyNotBetween(Integer value1, Integer value2) {
			addCriterion("is_reply not between", value1, value2, "isReply");
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

		public Criteria andIsAuthorIsNull() {
			addCriterion("is_author is null");
			return (Criteria) this;
		}

		public Criteria andIsAuthorIsNotNull() {
			addCriterion("is_author is not null");
			return (Criteria) this;
		}

		public Criteria andIsAuthorEqualTo(Integer value) {
			addCriterion("is_author =", value, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsAuthorNotEqualTo(Integer value) {
			addCriterion("is_author <>", value, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsAuthorGreaterThan(Integer value) {
			addCriterion("is_author >", value, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsAuthorGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_author >=", value, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsAuthorLessThan(Integer value) {
			addCriterion("is_author <", value, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsAuthorLessThanOrEqualTo(Integer value) {
			addCriterion("is_author <=", value, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsAuthorIn(List<Integer> values) {
			addCriterion("is_author in", values, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsAuthorNotIn(List<Integer> values) {
			addCriterion("is_author not in", values, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsAuthorBetween(Integer value1, Integer value2) {
			addCriterion("is_author between", value1, value2, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsAuthorNotBetween(Integer value1, Integer value2) {
			addCriterion("is_author not between", value1, value2, "isAuthor");
			return (Criteria) this;
		}

		public Criteria andIsReportIsNull() {
			addCriterion("is_report is null");
			return (Criteria) this;
		}

		public Criteria andIsReportIsNotNull() {
			addCriterion("is_report is not null");
			return (Criteria) this;
		}

		public Criteria andIsReportEqualTo(Integer value) {
			addCriterion("is_report =", value, "isReport");
			return (Criteria) this;
		}

		public Criteria andIsReportNotEqualTo(Integer value) {
			addCriterion("is_report <>", value, "isReport");
			return (Criteria) this;
		}

		public Criteria andIsReportGreaterThan(Integer value) {
			addCriterion("is_report >", value, "isReport");
			return (Criteria) this;
		}

		public Criteria andIsReportGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_report >=", value, "isReport");
			return (Criteria) this;
		}

		public Criteria andIsReportLessThan(Integer value) {
			addCriterion("is_report <", value, "isReport");
			return (Criteria) this;
		}

		public Criteria andIsReportLessThanOrEqualTo(Integer value) {
			addCriterion("is_report <=", value, "isReport");
			return (Criteria) this;
		}

		public Criteria andIsReportIn(List<Integer> values) {
			addCriterion("is_report in", values, "isReport");
			return (Criteria) this;
		}

		public Criteria andIsReportNotIn(List<Integer> values) {
			addCriterion("is_report not in", values, "isReport");
			return (Criteria) this;
		}

		public Criteria andIsReportBetween(Integer value1, Integer value2) {
			addCriterion("is_report between", value1, value2, "isReport");
			return (Criteria) this;
		}

		public Criteria andIsReportNotBetween(Integer value1, Integer value2) {
			addCriterion("is_report not between", value1, value2, "isReport");
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