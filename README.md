# Chatbot Project

A Spring Boot-based chatbot service that integrates with DialogFlow to provide joke search functionality. The service also includes product search and weather information capabilities through various APIs.

## Features

- Joke search integration with the Chuck Norris API
- Swagger UI documentation for API testing
- DialogFlow integration for natural language processing
- RESTful endpoints for different chatbot functionalities

## Public Access

The Swagger UI documentation is accessible at:
```
https://guy-chatbot.runmydocker-app.com/swagger-ui.html#/
```

## DialogFlow Integration

The chatbot is integrated with DialogFlow at:
```
https://dialogflow.cloud.google.com/#/agent/guy-chatbot-jg9i/fulfillment
```

### Using the Chatbot

1. To search for jokes:
   - Type "joke" in the chat
   - Specify what kind of joke you're looking for
   - The bot will search the Chuck Norris jokes database and return matching results

## API Endpoints

- `/bot` - Main endpoint for DialogFlow webhook integration
- `/bot/amazon` - Product search functionality
- `/bot/weather` - Weather information service

## Technical Stack

- Spring Boot 2.5.2
- Swagger 2.6.1 for API documentation
- OkHttp for HTTP requests
- DialogFlow for natural language processing

## Development

The project uses Maven for dependency management and includes configurations for:
- Swagger documentation
- REST controllers
- Service layer implementations
- DialogFlow webhook integration

For local development, clone the repository and run using Maven:
```bash
mvn spring-boot:run
```
