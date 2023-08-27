package com.niko09811.app.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.niko09811.app.utils.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class FlagController {
    @GetMapping("/flag")
    public String handleRequest(@CookieValue("token") String token) {
        DecodedJWT decoded = JwtUtil.verify(token);
        int userId = decoded.getClaim("userId").asInt();
        String username = decoded.getClaim("username").asString();
        String rnd = decoded.getClaim("rnd").asString();
        if (username != null && rnd != null && userId == 1 && username.equals("flag") && rnd.equals("Gw5bru998emWCgZK")) {
            return "flag{o.O}";
        } else {
            return "o.O";
        }

    }
}
