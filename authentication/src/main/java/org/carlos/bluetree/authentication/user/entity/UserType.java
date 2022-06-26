package org.carlos.bluetree.authentication.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {
    LOCAL("Local"),
    FACEBOOK("Facebook"),
    GOOGLE("Google")
    ;

    private final String type;
}
