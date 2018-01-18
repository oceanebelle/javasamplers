package javasamplers.apps.wardemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class WarDemoApp extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {


        return builder.sources(WarDemoApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WarDemoApp.class, args);
    }
}
