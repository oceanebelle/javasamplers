package javasamplers.apps.webdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class WebController {

    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    @RequestMapping("/")
    @ResponseBody
    String hello() {
        return "Hello World!";
    }

    @RequestMapping(value ="/log/", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    String log(HttpServletRequest request) {
        // Extract all the parameters from the request
        LOG.info("{}: {}", request.getMethod(), LocalDateTime.now());
        for(Map.Entry<String, String[]> data : request.getParameterMap().entrySet()) {
            LOG.info("   {}={}", data.getKey(), data.getValue());
        }

        return "OK";
    }



    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebController.class, args);
    }
}
