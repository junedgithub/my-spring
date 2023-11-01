package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Login {
    private String email;
    private String password;

}
