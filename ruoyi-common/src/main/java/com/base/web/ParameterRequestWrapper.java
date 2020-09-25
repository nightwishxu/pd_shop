package com.base.web;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> params;

    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        this.params = new HashMap();
        this.params.putAll(request.getParameterMap());
    }

    public ParameterRequestWrapper(HttpServletRequest request, Map<String, Object> extendParams) {
        this(request);
        this.addAllParameters(extendParams);
    }

    public String getParameter(String name) {
        String[] values = (String[])this.params.get(name);
        return values != null && values.length != 0 ? values[0] : null;
    }

    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(this.params.keySet());
    }

    public Map<String, String[]> getParams() {
        return this.params;
    }

    public String[] getParameterValues(String name) {
        return (String[])this.params.get(name);
    }

    public void addAllParameters(Map<String, Object> otherParams) {
        Iterator var2 = otherParams.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, Object> entry = (Entry)var2.next();
            this.addParameter((String)entry.getKey(), entry.getValue());
        }

    }

    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                this.params.put(name, (String[])((String[])value));
            } else if (value instanceof String) {
                this.params.put(name, new String[]{(String)value});
            } else {
                this.params.put(name, new String[]{String.valueOf(value)});
            }
        }

    }
}
