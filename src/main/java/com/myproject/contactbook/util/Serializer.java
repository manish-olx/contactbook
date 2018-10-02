package com.myproject.contactbook.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class Serializer {

    /**
     * deserialize to Object from given file. We use the general Object so as that it can work for any Java Class.
     */
    public Object deserialize(String json, Class<?> t) {
        return new Gson().fromJson(json, t);
    }

    /**
     * serialize the given object and save it to given file
     */
    public String serialize(Object obj) {
        return new Gson().toJson(obj);
    }
}
