package org.carlos.bluetree.authentication.auth.dto;

public record CreateLocalUserRequest(
        String username,
        String email,
        String password
) {


}
