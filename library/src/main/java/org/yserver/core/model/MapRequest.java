package org.yserver.core.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MapRequest {
    private Map params;

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Object get(Object key) {
        return params.get(key);
    }

    public Object put(Object key, Object value) {
        return params.put(key, value);
    }

    public Object remove(Object key) {
        return params.remove(key);
    }

    public void clear() {
        params.clear();
    }

    public boolean containsKey(Object key) {
        return params.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return params.containsValue(value);
    }

    public Set entrySet() {
        return params.entrySet();
    }

    public boolean isEmpty() {
        return params.isEmpty();
    }

    public Set keySet() {
        return params.keySet();
    }

    public void putAll(Map t) {
        params.putAll(t);
    }

    public int size() {
        return params.size();
    }

    public Collection values() {
        return params.values();
    }

    public String getString(Object key) {
        return (String) get(key);
    }

    public double getDouble(Object key) {
        return Double.parseDouble(get(key).toString());
    }

    public int getInt(Object key) {
        return Integer.parseInt(get(key).toString());
    }

    public long getLong(Object key) {
        return Long.parseLong(get(key).toString());
    }

    public BigDecimal getBigDecimal(Object key) {
        return (BigDecimal) get(key);
    }
}
