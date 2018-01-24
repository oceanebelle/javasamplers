package javasamplers.apps.wardemo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping
    public String status() {
        return "Alive and WELL and more";
    }
}
