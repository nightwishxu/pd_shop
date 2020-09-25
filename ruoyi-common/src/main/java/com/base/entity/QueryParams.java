package com.base.entity;

import java.io.Serializable;
import java.util.HashMap;

public class QueryParams {
    private QueryParams() {
    }

    public static QueryParams.Builder newBuilder() {
        return new QueryParams.Builder();
    }

    public static class Builder extends HashMap<String, Object> {
        private static final long serialVersionUID = 1L;
        public static final String STR_KEY = "key";
        public static final String INT_KEY = "num";
        public static final String OBJ_KEY = "obj";
        public static final String ID_KEY = "id";
        public static final String USER_ID_KEY = "userId";
        public static final String LIKE = "%";

        public Builder() {
        }

        public void setKey(String key) {
            this.set("key", key);
        }

        public void setKeyLike(String key) {
            this.setLike("key", key);
        }

        public void setNum(Integer num) {
            this.set("num", num);
        }

        public void setObj(Object obj) {
            this.set("obj", obj);
        }

        public void setId(Serializable id) {
            this.set("id", id);
        }

        public void setUserId(Serializable id) {
            this.set("userId", id);
        }

        public void setLike(String key, String value) {
            this.put(key, "%" + value + "%");
        }

        public void set(String key, Object value) {
            this.put(key, value);
        }

        public void del(String key) {
            this.remove(key);
        }
    }
}
