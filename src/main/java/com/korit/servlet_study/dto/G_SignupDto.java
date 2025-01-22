package com.korit.servlet_study.dto;

import com.korit.servlet_study.entity.G_User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class G_SignupDto {
    private String username;
    private String password;
    private String name;
    private String email;

    public G_User toUser() {
        return G_User.builder()
                .username(username)
                //BCrypt로 암호화 할 수 있다. 값은 암호화의 복잡성을 나타내고, 10이 표준이다.
                .password(BCrypt.hashpw(password, BCrypt.gensalt(10)))
                .name(name)
                .email(email)
                .build();
    }
}
