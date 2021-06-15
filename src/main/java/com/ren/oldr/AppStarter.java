package com.ren.oldr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@ComponentScan("com.ren")
@MapperScan("com.ren.oldr.dao")
public class AppStarter {

    private static ApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(AppStarter.class, args);
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }
}
