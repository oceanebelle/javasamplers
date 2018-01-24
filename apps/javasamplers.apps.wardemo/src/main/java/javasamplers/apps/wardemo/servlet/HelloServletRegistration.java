package javasamplers.apps.wardemo.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;

import javax.servlet.Servlet;


public class HelloServletRegistration extends ServletRegistrationBean {

    private static final String SERVLET = "HELLO-SERVLET";

    public HelloServletRegistration(Servlet servlet, String... urlMappings) {
        super(servlet, urlMappings);
    }

    @Override
    public String getServletName() {
        return SERVLET;
    }

}
