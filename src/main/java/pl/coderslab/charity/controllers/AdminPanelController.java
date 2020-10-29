package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/adminPanel")
public class AdminPanelController {
    @GetMapping
    public String getAdminPanel(){
        return "admin/adminPanel";
    }
}
