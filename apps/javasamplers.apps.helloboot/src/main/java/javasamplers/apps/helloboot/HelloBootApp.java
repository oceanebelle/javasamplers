package javasamplers.apps.helloboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Note: Standalone apps can be started as an ApplicationRunner or CommandLineRunner
 *       Use ApplicationRunner for more flexibility
 *
 */
@SpringBootApplication
public class HelloBootApp implements ApplicationRunner, ExitCodeGenerator
{
    private static final Logger LOG = LoggerFactory.getLogger(HelloBootApp.class);

    public static void main( String[] args )
    {
        System.exit(SpringApplication.exit(SpringApplication.run(HelloBootApp.class, args)));
    }

    public void run(ApplicationArguments applicationArguments) throws Exception {


        LOG.info("HELLO spring boot!");
    }

    public int getExitCode() {

        LOG.info("Shutdown hook invoked");
        return 1000;
    }
}
