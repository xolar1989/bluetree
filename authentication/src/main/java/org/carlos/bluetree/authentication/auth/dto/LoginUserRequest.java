package org.carlos.bluetree.authentication.auth.dto;

public record LoginUserRequest(
        String username,
        String password
) {
}
