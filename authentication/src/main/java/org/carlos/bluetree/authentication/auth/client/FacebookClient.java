package org.carlos.bluetree.authentication.auth.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.carlos.bluetree.authentication.auth.dto.FacebookUserRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@AllArgsConstructor
@Slf4j
@Service
public class FacebookClient {

    private final RestTemplate restTemplate;

    private final String FACEBOOK_GRAPH_API_BASE = "https://graph.facebook.com";

    public FacebookUserRequest getUser(String accessToken) {
        String path = "/me?fields={fields}&redirect={redirect}&access_token={access_token}";
        String fields = "email,first_name,last_name,id";
        var variables = new HashMap<String,String>();
        variables.put("fields",fields);
        variables.put("redirect","false");
        variables.put("access_token",accessToken);
        return restTemplate
                .getForObject(FACEBOOK_GRAPH_API_BASE + path, FacebookUserRequest.class, variables);
    }

}
