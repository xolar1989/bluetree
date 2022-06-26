package org.carlos.bluetree.authentication.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FacebookUserRequest(
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        String id,
        String email
) {
}
