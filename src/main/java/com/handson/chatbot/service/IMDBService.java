package com.handson.chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class IMDBService {

    public String searchMovies(String keyword) {
        return "Searched for:" + keyword;
    }
}