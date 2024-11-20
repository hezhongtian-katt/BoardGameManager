package com.example.boardgamemanager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index";  // 这里返回的是 index.html
    }
}
