package com.paidang.dao.model;

import java.util.ArrayList;
import java.util.List;

public class ArticleCommentReplyExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ArticleCommentReplyExample(){
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

		public Criteria andCommentIdIsNull() {
			addCriterion("comment_id is null");
			return (Criteria) this;
		}

		public Criteria andCommentIdIsNotNull() {
			addCriterion("comment_id is not null");
			return (Criteria) this;
		}

		public Criteria andCommentIdEqualTo(Integer value) {
			addCriterion("comment_id =", value, "commentId");
			return (Criteria) this;
		}

		public Criteria andCommentIdNotEqualTo(Integer value) {
			addCriterion("comment_id <>", value, "commentId");
			return (Criteria) this;
		}

		public Criteria andCommentIdGreaterThan(Integer value) {
			addCriterion("comment_id >", value, "commentId");
			return (Criteria) this;
		}

		public Criteria andCommentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("comment_id >=", value, "commentId");
			return (Criteria) this;
		}

		public Criteria andCommentIdLessThan(Integer value) {
			addCriterion("comment_id <", value, "commentId");
			return (Criteria) this;
		}

		public Criteria andCommentIdLessThanOrEqualTo(Integer value) {
			addCriterion("comment_id <=", value, "commentId");
			return (Criteria) this;
		}

		public Criteria andCommentIdIn(List<Integer> values) {
			addCriterion("comment_id in", values, "commentId");
			return (Criteria) this;
		}

		public Criteria andCommentIdNotIn(List<Integer> values) {
			addCriterion("comment_id not in", values, "commentId");
			return (Criteria) this;
		}

		public Criteria andCommentIdBetween(Integer value1, Integer value2) {
			addCriterion("comment_id between", value1, value2, "commentId");
			return (Criteria) this;
		}

		public Criteria andCommentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("comment_id not between", value1, value2, "commentId");
			return (Criteria) this;
		}

		public Criteria andReplyTypeIsNull() {
			addCriterion("reply_type is null");
			return (Criteria) this;
		}

		public Criteria andReplyTypeIsNotNull() {
			addCriterion("reply_type is not null");
			return (Criteria) this;
		}

		public Criteria andReplyTypeEqualTo(Integer value) {
			addCriterion("reply_type =", value, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyTypeNotEqualTo(Integer value) {
			addCriterion("reply_type <>", value, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyTypeGreaterThan(Integer value) {
			addCriterion("reply_type >", value, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("reply_type >=", value, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyTypeLessThan(Integer value) {
			addCriterion("reply_type <", value, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyTypeLessThanOrEqualTo(Integer value) {
			addCriterion("reply_type <=", value, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyTypeIn(List<Integer> values) {
			addCriterion("reply_type in", values, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyTypeNotIn(List<Integer> values) {
			addCriterion("reply_type not in", values, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyTypeBetween(Integer value1, Integer value2) {
			addCriterion("reply_type between", value1, value2, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("reply_type not between", value1, value2, "replyType");
			return (Criteria) this;
		}

		public Criteria andReplyIdIsNull() {
			addCriterion("reply_id is null");
			return (Criteria) this;
		}

		public Criteria andReplyIdIsNotNull() {
			addCriterion("reply_id is not null");
			return (Criteria) this;
		}

		public Criteria andReplyIdEqualTo(Integer value) {
			addCriterion("reply_id =", value, "replyId");
			return (Criteria) this;
		}

		public Criteria andReplyIdNotEqualTo(Integer value) {
			addCriterion("reply_id <>", value, "replyId");
			return (Criteria) this;
		}

		public Criteria andReplyIdGreaterThan(Integer value) {
			addCriterion("reply_id >", value, "replyId");
			return (Criteria) this;
		}

		public Criteria andReplyIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("reply_id >=", value, "replyId");
			return (Criteria) this;
		}

		public Criteria andReplyIdLessThan(Integer value) {
			addCriterion("reply_id <", value, "replyId");
			return (Criteria) this;
		}

		public Criteria andReplyIdLessThanOrEqualTo(Integer value) {
			addCriterion("reply_id <=", value, "replyId");
			return (Criteria) this;
		}

		public Criteria andReplyIdIn(List<Integer> values) {
			addCriterion("reply_id in", values, "replyId");
			return (Criteria) this;
		}

		public Criteria andReplyIdNotIn(List<Integer> values) {
			addCriterion("reply_id not in", values, "replyId");
			return (Criteria) this;
		}

		public Criteria andReplyIdBetween(Integer value1, Integer value2) {
			addCriterion("reply_id between", value1, value2, "replyId");
			return (Criteria) this;
		}

		public Criteria andReplyIdNotBetween(Integer value1, Integer value2) {
			addCriterion("reply_id not between", value1, value2, "replyId");
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

		public Criteria andToUidIsNull() {
			addCriterion("to_uid is null");
			return (Criteria) this;
		}

		public Criteria andToUidIsNotNull() {
			addCriterion("to_uid is not null");
			return (Criteria) this;
		}

		public Criteria andToUidEqualTo(Integer value) {
			addCriterion("to_uid =", value, "toUid");
			return (Criteria) this;
		}

		public Criteria andToUidNotEqualTo(Integer value) {
			addCriterion("to_uid <>", value, "toUid");
			return (Criteria) this;
		}

		public Criteria andToUidGreaterThan(Integer value) {
			addCriterion("to_uid >", value, "toUid");
			return (Criteria) this;
		}

		public Criteria andToUidGreaterThanOrEqualTo(Integer value) {
			addCriterion("to_uid >=", value, "toUid");
			return (Criteria) this;
		}

		public Criteria andToUidLessThan(Integer value) {
			addCriterion("to_uid <", value, "toUid");
			return (Criteria) this;
		}

		public Criteria andToUidLessThanOrEqualTo(Integer value) {
			addCriterion("to_uid <=", value, "toUid");
			return (Criteria) this;
		}

		public Criteria andToUidIn(List<Integer> values) {
			addCriterion("to_uid in", values, "toUid");
			return (Criteria) this;
		}

		public Criteria andToUidNotIn(List<Integer> values) {
			addCriterion("to_uid not in", values, "toUid");
			return (Criteria) this;
		}

		public Criteria andToUidBetween(Integer value1, Integer value2) {
			addCriterion("to_uid between", value1, value2, "toUid");
			return (Criteria) this;
		}

		public Criteria andToUidNotBetween(Integer value1, Integer value2) {
			addCriterion("to_uid not between", value1, value2, "toUid");
			return (Criteria) this;
		}

		public Criteria andFromUidIsNull() {
			addCriterion("from_uid is null");
			return (Criteria) this;
		}

		public Criteria andFromUidIsNotNull() {
			addCriterion("from_uid is not null");
			return (Criteria) this;
		}

		public Criteria andFromUidEqualTo(Integer value) {
			addCriterion("from_uid =", value, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromUidNotEqualTo(Integer value) {
			addCriterion("from_uid <>", value, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromUidGreaterThan(Integer value) {
			addCriterion("from_uid >", value, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromUidGreaterThanOrEqualTo(Integer value) {
			addCriterion("from_uid >=", value, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromUidLessThan(Integer value) {
			addCriterion("from_uid <", value, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromUidLessThanOrEqualTo(Integer value) {
			addCriterion("from_uid <=", value, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromUidIn(List<Integer> values) {
			addCriterion("from_uid in", values, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromUidNotIn(List<Integer> values) {
			addCriterion("from_uid not in", values, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromUidBetween(Integer value1, Integer value2) {
			addCriterion("from_uid between", value1, value2, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromUidNotBetween(Integer value1, Integer value2) {
			addCriterion("from_uid not between", value1, value2, "fromUid");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgIsNull() {
			addCriterion("from_thumb_img is null");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgIsNotNull() {
			addCriterion("from_thumb_img is not null");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgEqualTo(String value) {
			addCriterion("from_thumb_img =", value, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgNotEqualTo(String value) {
			addCriterion("from_thumb_img <>", value, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgGreaterThan(String value) {
			addCriterion("from_thumb_img >", value, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgGreaterThanOrEqualTo(String value) {
			addCriterion("from_thumb_img >=", value, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgLessThan(String value) {
			addCriterion("from_thumb_img <", value, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgLessThanOrEqualTo(String value) {
			addCriterion("from_thumb_img <=", value, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgLike(String value) {
			addCriterion("from_thumb_img like", value, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgNotLike(String value) {
			addCriterion("from_thumb_img not like", value, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgIn(List<String> values) {
			addCriterion("from_thumb_img in", values, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgNotIn(List<String> values) {
			addCriterion("from_thumb_img not in", values, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgBetween(String value1, String value2) {
			addCriterion("from_thumb_img between", value1, value2, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromThumbImgNotBetween(String value1, String value2) {
			addCriterion("from_thumb_img not between", value1, value2, "fromThumbImg");
			return (Criteria) this;
		}

		public Criteria andFromNicknameIsNull() {
			addCriterion("from_nickname is null");
			return (Criteria) this;
		}

		public Criteria andFromNicknameIsNotNull() {
			addCriterion("from_nickname is not null");
			return (Criteria) this;
		}

		public Criteria andFromNicknameEqualTo(String value) {
			addCriterion("from_nickname =", value, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameNotEqualTo(String value) {
			addCriterion("from_nickname <>", value, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameGreaterThan(String value) {
			addCriterion("from_nickname >", value, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameGreaterThanOrEqualTo(String value) {
			addCriterion("from_nickname >=", value, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameLessThan(String value) {
			addCriterion("from_nickname <", value, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameLessThanOrEqualTo(String value) {
			addCriterion("from_nickname <=", value, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameLike(String value) {
			addCriterion("from_nickname like", value, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameNotLike(String value) {
			addCriterion("from_nickname not like", value, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameIn(List<String> values) {
			addCriterion("from_nickname in", values, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameNotIn(List<String> values) {
			addCriterion("from_nickname not in", values, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameBetween(String value1, String value2) {
			addCriterion("from_nickname between", value1, value2, "fromNickname");
			return (Criteria) this;
		}

		public Criteria andFromNicknameNotBetween(String value1, String value2) {
			addCriterion("from_nickname not between", value1, value2, "fromNickname");
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

		public Criteria andToNicknameIsNull() {
			addCriterion("to_nickname is null");
			return (Criteria) this;
		}

		public Criteria andToNicknameIsNotNull() {
			addCriterion("to_nickname is not null");
			return (Criteria) this;
		}

		public Criteria andToNicknameEqualTo(String value) {
			addCriterion("to_nickname =", value, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameNotEqualTo(String value) {
			addCriterion("to_nickname <>", value, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameGreaterThan(String value) {
			addCriterion("to_nickname >", value, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameGreaterThanOrEqualTo(String value) {
			addCriterion("to_nickname >=", value, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameLessThan(String value) {
			addCriterion("to_nickname <", value, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameLessThanOrEqualTo(String value) {
			addCriterion("to_nickname <=", value, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameLike(String value) {
			addCriterion("to_nickname like", value, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameNotLike(String value) {
			addCriterion("to_nickname not like", value, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameIn(List<String> values) {
			addCriterion("to_nickname in", values, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameNotIn(List<String> values) {
			addCriterion("to_nickname not in", values, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameBetween(String value1, String value2) {
			addCriterion("to_nickname between", value1, value2, "toNickname");
			return (Criteria) this;
		}

		public Criteria andToNicknameNotBetween(String value1, String value2) {
			addCriterion("to_nickname not between", value1, value2, "toNickname");
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