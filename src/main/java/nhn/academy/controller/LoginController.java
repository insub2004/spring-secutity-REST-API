package nhn.academy.controller;

import jakarta.servlet.http.HttpSession;
import nhn.academy.model.Member;
import nhn.academy.model.login.LoginMemberRequest;
import nhn.academy.service.login.LoginMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller
//@RequestMapping("/login")
public class LoginController {

    public static final String LOGIN_USER = "LOGIN_USER";

    private final LoginMemberService loginMemberService;

    public LoginController(LoginMemberService loginMemberService) {
        this.loginMemberService = loginMemberService;
    }

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public ModelAndView processLogin(@ModelAttribute LoginMemberRequest loginRequest, HttpSession session)  {
        Member memberResponse = loginMemberService.login(loginRequest);
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("loginName", memberResponse.getName());
        HttpSession newSession = session;   // 실전은 request.getSession(true) 새 세션 발급이 적절
        newSession.setAttribute(LOGIN_USER, memberResponse);
        return mav;
    }
}
