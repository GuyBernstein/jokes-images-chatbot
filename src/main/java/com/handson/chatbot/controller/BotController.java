package com.handson.chatbot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.handson.chatbot.service.IMDBService;
import com.handson.chatbot.service.JokesService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    IMDBService imdbService;

    @Autowired
    JokesService jokesService;

    @RequestMapping(value = "/imdb", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@RequestParam String keyword) throws UnirestException {
        return new ResponseEntity<>(imdbService.searchMovies(keyword), HttpStatus.OK);
    }

    @RequestMapping(value = "/joke", method = RequestMethod.GET)
    public ResponseEntity<?> getJoke(@RequestParam String keyword) throws UnirestException, JsonProcessingException {
        return new ResponseEntity<>(jokesService.getJokeId(keyword), HttpStatus.OK);
    }
}
