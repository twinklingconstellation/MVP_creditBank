package com.bank.deal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
    RestTemplate — это клиент для HTTP-запросов к внешним сервисам.
    Он позволяет отправлять запросы по различным методам (GET, POST, PUT, DELETE) и получать ответы.
    Он создаётся в конфигурации, а затем также внедряется в контроллер через @Autowired
 */

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
