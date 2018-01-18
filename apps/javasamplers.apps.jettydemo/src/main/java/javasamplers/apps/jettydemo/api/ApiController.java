package javasamplers.apps.jettydemo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping
    public String healthCheck() {
        return "Alive";
    }
}
