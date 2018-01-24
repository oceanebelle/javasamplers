package javasamplers.apps.wardemo;

import javasamplers.apps.wardemo.servlet.HelloServlet;
import javasamplers.apps.wardemo.servlet.HelloServletRegistration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

@Configuration
public class WarConfiguration {

    @Bean
    public ServletRegistrationBean helloServlet() {
        Servlet servlet = new HelloServlet();
        ServletRegistrationBean helloRegistration =  new HelloServletRegistration(servlet, "/hello");
        helloRegistration.setLoadOnStartup(1);
        helloRegistration.setEnabled(true);
        return helloRegistration;
    }
}
