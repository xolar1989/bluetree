package org.carlos.bluetree.authentication.auth.client;


import org.carlos.bluetree.authentication.auth.dto.FacebookUserRequest;
import org.carlos.bluetree.authentication.config.RestTemplateResponseErrorHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;


class FacebookClientTest {

    static RestTemplate restTemplate;

    static FacebookClient facebookClient;

    @BeforeAll
    static void setup() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
        facebookClient = new FacebookClient(restTemplate);
    }

    @Test
    public void kkk(){
        String token = "EAAPcewftx7sBAAtlZBgBvh4dtfg7oKmTRVOQcVMpRuMZAm6KykoY0b1vsQCUARwkZBODHs68dzjORJgigum9ZBROBwVAAYsKJSCjGVvxWzQ8UBhi1ct31OMHi0pM0ud55sJaLxFmAvmerbmxuG5yFpGMBUjka3YR0zlSdZBsZBtCyuzSW1DqrhR9ZAYgwUyMNVlJFTLNEUwRZABDIanR13n1jIUhTJnsZAjqhaw0FUprjiQZDZD";
        FacebookUserRequest userRequest = facebookClient.getUser(token);


        var t = 4;

    }




}
