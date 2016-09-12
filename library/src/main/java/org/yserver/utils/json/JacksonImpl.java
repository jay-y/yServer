package org.yserver.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.yserver.utils.JsonUtil;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class JacksonImpl implements JsonUtil {
    private static JsonUtil instance;

    private ObjectMapper objectMapper;

    public static JsonUtil getInstance() {
        if (instance == null) {
            synchronized (JacksonImpl.class) {
                if (instance == null) {
                    instance = new JacksonImpl();
                }
            }
        }
        return instance;
    }

    public String toJson(Object object) {
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return jsonString;
    }

    public String toPrettyJson(Object object) {
        String jsonString;
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return jsonString;

    }

    public <T> T jsonToObject(String json, Class<T> c) {
        try {
            if (StringUtils.isBlank(json)) {
                return c.newInstance();
            } else {
                return objectMapper.readValue(json, c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public <T> List<T> jsonToList(String json, Class<T> cls) {
        return jsonToGenericObject(json, new TypeReference<List<T>>() {
        });
    }

    public List<Map<String, Object>> jsonToListMap(String json) {
        return jsonToGenericObject(json, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    public Map<String, Object> jsonToMap(String json) {
        try {
            Map<String, Object> map = objectMapper.readValue(json,
                    Map.class);
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private <T> T jsonToGenericObject(String json,
                                      TypeReference<T> tr) {
        if (StringUtils.isBlank(json)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(json, tr);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    private JacksonImpl() {
        objectMapper = new ObjectMapper();
        //解析器支持解析单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //解析器支持解析结束符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
