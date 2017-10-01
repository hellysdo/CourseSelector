package com.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	private ObjectMapper om;
    public JsonUtil() {
        om = new ObjectMapper();
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        om.setDateFormat(format);
    }
    public String format(Object obj) throws JsonGenerationException,
            JsonMappingException, IOException {
        if (obj == null) {
            return null;
        }
       
        return om.writeValueAsString(obj);
    }
    public <T> T format(String json, Class<T> cls) throws Exception{
        if (json == null) {
            return null;
        }
        try {
        	//System.out.println(json);
	return om.readValue(json, cls);
	} catch (JsonParseException e) {
	throw e;
	//e.printStackTrace();
	} catch (JsonMappingException e) {
	throw e;

	//e.printStackTrace();
	} catch (IOException e) {
	throw e;
	//e.printStackTrace();
	}

    }
}