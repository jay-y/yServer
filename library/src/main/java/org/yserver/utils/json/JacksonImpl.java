package org.yserver.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.yserver.utils.JsonUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class JacksonImpl extends ObjectMapper implements JsonUtil
{
    private static JsonUtil instance;

    private ObjectMapper objectMapper;

    public static JsonUtil getInstance()
    {
        if (instance == null)
        {
            synchronized (JacksonImpl.class)
            {
                if (instance == null)
                {
                    instance = new JacksonImpl();
                }
            }
        }
        return instance;
    }

    public String toJson(Object object)
    {
        String jsonString;
        try
        {
            jsonString = objectMapper.writeValueAsString(object);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
        return jsonString;
    }

    public String toPrettyJson(Object object)
    {
        String jsonString;
        try
        {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
        return jsonString;

    }

    public <T> T jsonToObject(String json, Class<T> c)
    {
        try
        {
            if (StringUtils.isBlank(json))
            {
                return c.newInstance();
            }
            else
            {
                return objectMapper.readValue(json, c);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public <T> List<T> jsonToList(String json, Class<T> cls)
    {
        return jsonToGenericObject(json, new TypeReference<List<T>>()
        {
        });
    }

    public List<Map<String, Object>> jsonToListMap(String json)
    {
        return jsonToGenericObject(json, new TypeReference<List<Map<String, Object>>>()
        {
        });
    }

    public Map<String, Object> jsonToMap(String json)
    {
        try
        {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            return map;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private <T> T jsonToGenericObject(String json, TypeReference<T> tr)
    {
        if (StringUtils.isBlank(json))
        {
            return null;
        }
        else
        {
            try
            {
                return objectMapper.readValue(json, tr);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    /**
     * 允许单引号
     * 允许不带引号的字段名称
     */
    private JacksonImpl enableSimple()
    {
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return this;
    }

    public JacksonImpl()
    {
        this(JsonInclude.Include.NON_EMPTY);
        objectMapper = new ObjectMapper();
        //解析器支持解析单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //解析器支持解析结束符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public JacksonImpl(JsonInclude.Include include)
    {
        // 设置输出时包含属性的风格
        if (include != null)
        {
            this.setSerializationInclusion(include);
        }
        // 允许单引号、允许不带引号的字段名称
        this.enableSimple();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()
        {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
            {
                jgen.writeString("");
            }
        });
        // 进行HTML解码。
        this.registerModule(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>()
        {
            @Override
            public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
            {
                jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
            }
        }));
        // 设置时区
        this.setTimeZone(TimeZone.getDefault());//getTimeZone("GMT+8:00")
    }
}
