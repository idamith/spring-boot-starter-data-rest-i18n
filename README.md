# Demo Project for i18n with Spring Boot Rest

This demo project is to demonstrate how to implement internationalization (i18n) in Spring Boot based REST API.

## Implement translatable REST API
- Create a simple REST API using Spring Boot
- Create message resource bundles for default language and application supported languages
[](src/main/resources/images/img-01.jpeg)

- Create [CustomLocaleResolver.java](src/main/java/com/idotrick/i18n/springrest/CustomLocaleResolver.java)
```java
@Configuration
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerLang = request.getHeader("Accept-Language");
        return null == headerLang || headerLang.isEmpty()
                ? Locale.getDefault()
                : Locale.forLanguageTag(headerLang);
    }
}
```
- Implement [Translator.java](src/main/java/com/idotrick/i18n/springrest/Translator.java)
```java
@Component
public class Translator {
    private static ResourceBundleMessageSource messageSource;

    @Autowired
    Translator(ResourceBundleMessageSource resourceBundleMessageSource){
        this.messageSource = resourceBundleMessageSource;
    }

    public static String translate(String messageCode){
        return messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
    }
}
```
- Expose at least one service to provide a greeting message as in
[SpringBootRestI18nApplication.java](src/main/java/com/idotrick/i18n/springrest/SpringBootRestI18nApplication.java)

```java
@RestController
@RequestMapping(value = "/api")
@SpringBootApplication
public class SpringBootRestI18nApplication {

    @GetMapping("message")
    public String getMessage(){
        return Translator.translate("message.greeting");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestI18nApplication.class, args);
    }

}
```

##Consume REST API Message service
- git clone https://github.com/idotrick/spring-boot-starter-data-rest-i18n.git
- Import project to your favourite IDE and run Spring Boot application
- Run on Terminal
```
user$ curl -X GET http://localhost:8080/api/message
user$ Welcome to Spring Boot i18n!

user$ curl -X GET -H "Accept-Language: si-LK" http://localhost:8080/api/message
user$ Spring Boot i18n වෙත සාදරයෙන් පිළිගනිමු!

user$ curl -X GET -H "Accept-Language: fr" http://localhost:8080/api/message 
user$ Bienvenue sur Spring Boot i18n!
```