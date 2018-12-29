package org.aminism.spider.huobi.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by ygxie on 2018/2/9.
 */
@SpringBootApplication(scanBasePackages = {"org.aminism.spider.huobi"}
        , exclude = {DataSourceAutoConfiguration.class})
public class SpringBootTestInitializer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        //System.setProperty("env", "fat");
        SpringApplication.run(SpringBootTestInitializer.class, args);
    }

    /**
     * Configure your application when it’s launched by the servlet container
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootTestInitializer.class);
    }

}
