package Bai3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
public class LoginController {
    @RequestMapping("/login/{action}")
    public String login(Model model, @PathVariable("action") String action) {
        switch (action) {
            case "form" -> model.addAttribute("message", "Đăng nhập");
            case "success" -> model.addAttribute("message", "Dăng nhập thành công");
            case "failure" -> model.addAttribute("message", "Sai thông tin đăng nhập");
            case "exit" -> model.addAttribute("message", "Đăng xuất thành công");
        }
        return "login";
    }
}