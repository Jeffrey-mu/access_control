package com.access;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableCaching
@EnableScheduling
@ServletComponentScan
@EnableAsync
@ComponentScan(nameGenerator = Main.SpringBeanNameGenerator.class)
//@MapperScan("com.**.mapper")
public class Main {
    public static class SpringBeanNameGenerator extends AnnotationBeanNameGenerator {
        @Override
        protected String buildDefaultBeanName(BeanDefinition definition) {
            return definition.getBeanClassName();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}