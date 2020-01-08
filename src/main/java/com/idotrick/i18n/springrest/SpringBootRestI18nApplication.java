package com.idotrick.i18n.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.idotrick.i18n.springrest.Translator.translate;


@RestController
@RequestMapping(value = "/api")
@SpringBootApplication
public class SpringBootRestI18nApplication {

    @GetMapping("message")
    public String getMessage(){
        return translate("message.greeting");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestI18nApplication.class, args);
    }

}
