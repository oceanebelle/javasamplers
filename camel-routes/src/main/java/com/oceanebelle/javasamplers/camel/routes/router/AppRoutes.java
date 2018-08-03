package com.oceanebelle.javasamplers.camel.routes.router;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.oceanebelle.javasamplers.camel.routes.dto.Person;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jacksonxml.JacksonXMLDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.apache.camel.component.stax.StAXBuilder.stax;

@Component
public class AppRoutes extends RouteBuilder {
    private static final Logger LOG  = LoggerFactory.getLogger(AppRoutes.class);

    public static final String DATA_LOAD_INPUT_ID = "DATA_LOAD_INPUT_ID";

    private static final String LOAD_LARGE_FILE_ENDPOINT = "seda:loadLargeFile?waitForTaskToComplete=Always";
    private static final String HTTP_ENDPOINT = "http://%s/api/large";
    private static final String SYSTEM_OUT_ENDPOINT = "stream:out";
    private static final String SPLIT_USING_XPATH_ENDPOINT = "direct:splitWithXpath";
    private static final String SPLIT_USING_STAX_ENDPOINT = "direct:splitWithStax";
    private static final String SPLIT_USING_TOKENIZE_XML_ENDPOINT = "direct:splitWithTokenize";

    private static final String PROCESS_INPUT_ROUTE_ID = "PROCESS_INPUT_ROUTE_ID";
    public static final String SPLIT_USING_XPATH_ENDPOINT_ID = "SPLIT_USING_XPATH_ENDPOINT_ID";
    public static final String SPLIT_USING_TOKENIZE_XML_ENDPOINT_ID = "SPLIT_USING_TOKENIZE_XML_ENDPOINT_ID";
    public static final String SPLIT_USING_STAX_ENDPOINT_ID = "SPLIT_USING_STAX_ENDPOINT_ID";


    private final int serverPort;
    private final boolean autoStart;
    private final String initRoute;

    @Autowired
    public AppRoutes(@Value("${server.port:8080}") int serverPort,
                     @Value("${app.route.autostart}") boolean autoStart,
                     @Value("${app.route.init}") String initRoute) {
        this.serverPort = serverPort;
        this.autoStart = autoStart;
        this.initRoute = initRoute;
    }

    @Override
    public void configure() throws Exception {
        from(initRoute)
                .autoStartup(autoStart)
                .log(LoggingLevel.INFO, LOG, "LOADING")
                .to(LOAD_LARGE_FILE_ENDPOINT);

        // Loads a large file from an HTTP endpoint
        from(LOAD_LARGE_FILE_ENDPOINT)
                .autoStartup(autoStart)
                .routeId(DATA_LOAD_INPUT_ID)
                .to(String.format(HTTP_ENDPOINT, "localhost:" + serverPort))
//                .streamCaching() // caches the inputstream to a file to allow multiple read to the InputStream. remove if not needed
                .log("${in.headers}")
//                .multicast()
//                    .to(SPLIT_USING_XPATH_ENDPOINT)
                    .to(SPLIT_USING_TOKENIZE_XML_ENDPOINT)
//                    .to(SPLIT_USING_STAX_ENDPOINT)
//                .end()
                .setBody(constant(null)) // clear the body
                .log("DONE");

        // Reads the XML using XPATH and converts the content into XML
        from(SPLIT_USING_XPATH_ENDPOINT)
                .autoStartup(autoStart)
                .routeId(SPLIT_USING_XPATH_ENDPOINT_ID)
                .log("Loading using XPATH")
                .split(xpath("/top/root/person")).streaming().parallelProcessing()
                    .unmarshal(jacksonXml(Person.class))
                    .log("${body}")
                .end()
                .log("${messageHistory(true)}");

        // Less memory intensive than xpath version
        from(SPLIT_USING_TOKENIZE_XML_ENDPOINT)
                .autoStartup(autoStart)
                .routeId(SPLIT_USING_TOKENIZE_XML_ENDPOINT_ID)
                .log("Loading using TOKENIZE XML")
                .split().tokenizeXML("person")
                    .streaming().parallelProcessing()
                    .unmarshal(jacksonXml(Person.class))
                    .log("${body}")
                .end()
                .log("${messageHistory(true)}");

        // Reads the XML using Stax
        from(SPLIT_USING_STAX_ENDPOINT)
                .autoStartup(autoStart)
                .routeId(SPLIT_USING_STAX_ENDPOINT_ID)
                .log("Loading using STAX")
                .split(stax(Person.class)).streaming().parallelProcessing()
                    .log("${body}")
                .end()
                .log("${messageHistory(true)}");
    }

    /**
     * defines how an xml is deserialized
     */
    private JacksonXMLDataFormat jacksonXml(Class<?> type) {
        return jacksonXml(type, false);
    }

    /**
     * @param type
     * @param useWrapper - Jackson does not expect a wrapper to lists
     * @return
     */
    private JacksonXMLDataFormat jacksonXml(Class<?> type, boolean useWrapper) {
        JacksonXMLDataFormat dataFormat = new JacksonXMLDataFormat();
        XmlMapper xmlMapper = new XmlMapper();
        // By default jackson expects wrapper=true. disable
        xmlMapper.setDefaultUseWrapper(useWrapper);
        dataFormat.setXmlMapper(xmlMapper);
        dataFormat.setUnmarshalType(type);
        return dataFormat;
    }


}
