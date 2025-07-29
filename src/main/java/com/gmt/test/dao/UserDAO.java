package com.gmt.test.dao;

import com.gmt.test.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public List<User> loadUsers() {
        try {
            InputStream inputStream = UserDAO.class.getClassLoader().getResourceAsStream("users.json");

            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            Gson gson = new Gson();

            Type userListType = new TypeToken<List<User>>() {
            }.getType();

            JsonObject root = JsonParser.parseString(json).getAsJsonObject();
            List<User> users = gson.fromJson(root.get("users"), userListType);

            return users;
        } catch (Exception e) {
            logger.error("error trying to load users with error {}", e.getMessage());
            return null;
        }
    }

}