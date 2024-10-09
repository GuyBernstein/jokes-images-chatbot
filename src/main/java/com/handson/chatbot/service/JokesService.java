package com.handson.chatbot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokesService {

    @Autowired
    ObjectMapper objectMapper;

    public String getJokeId(String keyword) throws UnirestException, JsonProcessingException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://api.chucknorris.io/jokes/search?query=" + keyword)
                .asString();
        JokesResponse res = objectMapper.readValue(response.getBody(), JokesResponse.class);
        if (res.getResult().isEmpty()) {
            return "No jokes found for the given keyword.";
        }
        return res.getResult().get(0).getValue();

    }

//    private String getJokeForId(Integer locationId) throws IOException {
//    }

    static class JokesResponse {
        private Integer total;
        private List<JokesResponseObject> result;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<JokesResponseObject> getResult() {
            return result;
        }

        public void setResult(List<JokesResponseObject> result) {
            this.result = result;
        }
    }

    static class JokesResponseObject {
        private List<String> categories;
        private String created_at;
        private String icon_url;
        private String id;
        private String updated_at;
        private String url;
        private String value;

        // Getters and setters for all fields
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
