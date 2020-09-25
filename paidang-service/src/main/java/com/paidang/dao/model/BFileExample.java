package com.paidang.dao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BFileExample() {
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

        public Criteria andFileIdIsNull() {
            addCriterion("file_id is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("file_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(String value) {
            addCriterion("file_id =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(String value) {
            addCriterion("file_id <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(String value) {
            addCriterion("file_id >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(String value) {
            addCriterion("file_id >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(String value) {
            addCriterion("file_id <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(String value) {
            addCriterion("file_id <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLike(String value) {
            addCriterion("file_id like", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotLike(String value) {
            addCriterion("file_id not like", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<String> values) {
            addCriterion("file_id in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<String> values) {
            addCriterion("file_id not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(String value1, String value2) {
            addCriterion("file_id between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(String value1, String value2) {
            addCriterion("file_id not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileBelongIsNull() {
            addCriterion("file_belong is null");
            return (Criteria) this;
        }

        public Criteria andFileBelongIsNotNull() {
            addCriterion("file_belong is not null");
            return (Criteria) this;
        }

        public Criteria andFileBelongEqualTo(String value) {
            addCriterion("file_belong =", value, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongNotEqualTo(String value) {
            addCriterion("file_belong <>", value, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongGreaterThan(String value) {
            addCriterion("file_belong >", value, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongGreaterThanOrEqualTo(String value) {
            addCriterion("file_belong >=", value, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongLessThan(String value) {
            addCriterion("file_belong <", value, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongLessThanOrEqualTo(String value) {
            addCriterion("file_belong <=", value, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongLike(String value) {
            addCriterion("file_belong like", value, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongNotLike(String value) {
            addCriterion("file_belong not like", value, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongIn(List<String> values) {
            addCriterion("file_belong in", values, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongNotIn(List<String> values) {
            addCriterion("file_belong not in", values, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongBetween(String value1, String value2) {
            addCriterion("file_belong between", value1, value2, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFileBelongNotBetween(String value1, String value2) {
            addCriterion("file_belong not between", value1, value2, "fileBelong");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeIsNull() {
            addCriterion("file_minitype is null");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeIsNotNull() {
            addCriterion("file_minitype is not null");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeEqualTo(String value) {
            addCriterion("file_minitype =", value, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeNotEqualTo(String value) {
            addCriterion("file_minitype <>", value, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeGreaterThan(String value) {
            addCriterion("file_minitype >", value, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeGreaterThanOrEqualTo(String value) {
            addCriterion("file_minitype >=", value, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeLessThan(String value) {
            addCriterion("file_minitype <", value, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeLessThanOrEqualTo(String value) {
            addCriterion("file_minitype <=", value, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeLike(String value) {
            addCriterion("file_minitype like", value, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeNotLike(String value) {
            addCriterion("file_minitype not like", value, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeIn(List<String> values) {
            addCriterion("file_minitype in", values, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeNotIn(List<String> values) {
            addCriterion("file_minitype not in", values, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeBetween(String value1, String value2) {
            addCriterion("file_minitype between", value1, value2, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileMinitypeNotBetween(String value1, String value2) {
            addCriterion("file_minitype not between", value1, value2, "fileMinitype");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileStateIsNull() {
            addCriterion("file_state is null");
            return (Criteria) this;
        }

        public Criteria andFileStateIsNotNull() {
            addCriterion("file_state is not null");
            return (Criteria) this;
        }

        public Criteria andFileStateEqualTo(Integer value) {
            addCriterion("file_state =", value, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileStateNotEqualTo(Integer value) {
            addCriterion("file_state <>", value, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileStateGreaterThan(Integer value) {
            addCriterion("file_state >", value, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_state >=", value, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileStateLessThan(Integer value) {
            addCriterion("file_state <", value, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileStateLessThanOrEqualTo(Integer value) {
            addCriterion("file_state <=", value, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileStateIn(List<Integer> values) {
            addCriterion("file_state in", values, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileStateNotIn(List<Integer> values) {
            addCriterion("file_state not in", values, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileStateBetween(Integer value1, Integer value2) {
            addCriterion("file_state between", value1, value2, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileStateNotBetween(Integer value1, Integer value2) {
            addCriterion("file_state not between", value1, value2, "fileState");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("file_size is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("file_size is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(BigDecimal value) {
            addCriterion("file_size =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(BigDecimal value) {
            addCriterion("file_size <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(BigDecimal value) {
            addCriterion("file_size >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("file_size >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(BigDecimal value) {
            addCriterion("file_size <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("file_size <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<BigDecimal> values) {
            addCriterion("file_size in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<BigDecimal> values) {
            addCriterion("file_size not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("file_size between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("file_size not between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileMd5IsNull() {
            addCriterion("file_md5 is null");
            return (Criteria) this;
        }

        public Criteria andFileMd5IsNotNull() {
            addCriterion("file_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andFileMd5EqualTo(String value) {
            addCriterion("file_md5 =", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotEqualTo(String value) {
            addCriterion("file_md5 <>", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5GreaterThan(String value) {
            addCriterion("file_md5 >", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5GreaterThanOrEqualTo(String value) {
            addCriterion("file_md5 >=", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5LessThan(String value) {
            addCriterion("file_md5 <", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5LessThanOrEqualTo(String value) {
            addCriterion("file_md5 <=", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5Like(String value) {
            addCriterion("file_md5 like", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotLike(String value) {
            addCriterion("file_md5 not like", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5In(List<String> values) {
            addCriterion("file_md5 in", values, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotIn(List<String> values) {
            addCriterion("file_md5 not in", values, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5Between(String value1, String value2) {
            addCriterion("file_md5 between", value1, value2, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotBetween(String value1, String value2) {
            addCriterion("file_md5 not between", value1, value2, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileCreaterIsNull() {
            addCriterion("file_creater is null");
            return (Criteria) this;
        }

        public Criteria andFileCreaterIsNotNull() {
            addCriterion("file_creater is not null");
            return (Criteria) this;
        }

        public Criteria andFileCreaterEqualTo(String value) {
            addCriterion("file_creater =", value, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterNotEqualTo(String value) {
            addCriterion("file_creater <>", value, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterGreaterThan(String value) {
            addCriterion("file_creater >", value, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("file_creater >=", value, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterLessThan(String value) {
            addCriterion("file_creater <", value, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterLessThanOrEqualTo(String value) {
            addCriterion("file_creater <=", value, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterLike(String value) {
            addCriterion("file_creater like", value, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterNotLike(String value) {
            addCriterion("file_creater not like", value, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterIn(List<String> values) {
            addCriterion("file_creater in", values, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterNotIn(List<String> values) {
            addCriterion("file_creater not in", values, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterBetween(String value1, String value2) {
            addCriterion("file_creater between", value1, value2, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreaterNotBetween(String value1, String value2) {
            addCriterion("file_creater not between", value1, value2, "fileCreater");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeIsNull() {
            addCriterion("file_createtime is null");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeIsNotNull() {
            addCriterion("file_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeEqualTo(Date value) {
            addCriterion("file_createtime =", value, "fileCreatetime");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeNotEqualTo(Date value) {
            addCriterion("file_createtime <>", value, "fileCreatetime");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeGreaterThan(Date value) {
            addCriterion("file_createtime >", value, "fileCreatetime");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("file_createtime >=", value, "fileCreatetime");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeLessThan(Date value) {
            addCriterion("file_createtime <", value, "fileCreatetime");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("file_createtime <=", value, "fileCreatetime");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeIn(List<Date> values) {
            addCriterion("file_createtime in", values, "fileCreatetime");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeNotIn(List<Date> values) {
            addCriterion("file_createtime not in", values, "fileCreatetime");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeBetween(Date value1, Date value2) {
            addCriterion("file_createtime between", value1, value2, "fileCreatetime");
            return (Criteria) this;
        }

        public Criteria andFileCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("file_createtime not between", value1, value2, "fileCreatetime");
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