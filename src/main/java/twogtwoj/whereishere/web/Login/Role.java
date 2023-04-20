package twogtwoj.whereishere.web.Login;

import lombok.Getter;

@Getter
public enum Role { // 사용자 권한 부여 클래스

    ROLE_ADMIN("ADMIN"),
    ROLE_COMPANY("COMPANY"),
    ROLE_MEMBER("MEMBER");

    private String description;

    Role(String description) {
        this.description = description;
    }

}
