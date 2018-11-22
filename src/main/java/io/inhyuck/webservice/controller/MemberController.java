/**
 * Date: 18/11/2018
 * Author: inhyuck | https://github.com/inhyuck
 */

package io.inhyuck.webservice.controller;

import io.inhyuck.webservice.domain.member.LoginForm;
import io.inhyuck.webservice.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String login(LoginForm loginForm, Model model) {
        return "login";
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "home";
    }

}
