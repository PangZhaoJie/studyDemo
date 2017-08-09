package com.baobaotao;

import com.baobaotao.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by pangzhaojie on 17-8-8.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.getEnvironment().setActiveProfiles("prod");
        configApplicationContext.register(BeanConfig.class);
        configApplicationContext.refresh();
        User user = configApplicationContext.getBean(User.class);
        configApplicationContext.getEnvironment().setActiveProfiles("dev");
        configApplicationContext.register(BeanConfig.class);
        user = configApplicationContext.getBean(User.class);

    }
}
