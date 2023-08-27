package com.niko09811.app.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.niko09811.app.utils.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/")
    public String handleRequest(@CookieValue(value = "token", required = false) String token, HttpServletResponse response, Model model) {
        String username = "anonymous";
        if (token == null || token.equals("")) {
            String jwtToken = JwtUtil.getToken(403, username, "o.O");
            Cookie cookie = new Cookie("token", jwtToken);

            cookie.setMaxAge(3600);
            cookie.setPath("/");

            response.addCookie(cookie);
        } else {
            DecodedJWT decoded = JwtUtil.verify(token);
            username = decoded.getClaim("username").asString();
        }

        model.addAttribute("username", username);
        return "index";
    }
}
