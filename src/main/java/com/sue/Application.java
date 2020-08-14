package com.sue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author sue
 * @date 2020/8/13 8:03
 */

@SpringBootApplication
@MapperScan("com.sue.mapper")
@ComponentScan(basePackages = {"com.sue","org.n3r.idworker"})
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}
