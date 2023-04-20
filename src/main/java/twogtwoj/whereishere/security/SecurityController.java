package twogtwoj.whereishere.security;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import twogtwoj.whereishere.domain.Member;

import java.security.Principal;

@Controller
public class SecurityController {

    @GetMapping("/userId")
    @ResponseBody
    public String currentUserId(@AuthenticationPrincipal User user) {
        String p = user.getAuthorities().toString();
        String n = user.getUsername();
        return p + n;
    }
}
