Spring Boot application using Jetty 9 Hosting


1. Create the usual spring boot application
2. Remove the Tomcat dependency
3. Add the jetty spring boot dependency (default is jetty 7)
4. (Optionally) add properties to determine which version of jetty to use
   
    ```
    <properties>
        <java.version>1.8</java.version>
        <jetty.version>9.1.0.v20131115</jetty.version>
        <servlet-api.version>3.1.0</servlet-api.version>
    </properties>
    
    
Starting up the main method will show jetty server used.



Reference:
see https://spring.io/blog/2014/03/07/deploying-spring-boot-applications