package com.galvanize.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("math/pi")
    public double helloWorld() {
        return Math.PI;
    }

    @PostMapping("/thispost")
    public String respond() {
        return "You just posted!";
    }

}
