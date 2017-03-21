package com.test.example.web.docs;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Profile("docs")
@Controller
public class ApiDocsController {

    @RequestMapping("/docs")
    public String getSwaggerApiDocsPage() {
        return "redirect:swagger-ui.html";
    }
}
