package com.api.util;

import java.util.HashMap;

public class MyMapUtils extends HashMap<String,Object> {

    public MyMapUtils put(String key, Object value){
        super.put(key,value);
        return this;
    }
}
