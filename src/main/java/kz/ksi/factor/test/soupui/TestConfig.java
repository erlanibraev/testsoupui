package kz.ksi.factor.test.soupui;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

/**
 * Created by Erlan.Ibraev on 09.07.2016.
 */
@EnableWs
@Configuration
public class TestConfig {

    public static final String NAMESPACE_URI = "http://www.factor.kz/resources/test";
    public static final String NAMESPACE_URI_1 = "http://spring.io/guides/gs-producing-web-service";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet,"/ws/*");
    }

    @Bean(name="test")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema, XsdSchemaCollection schemaCollection) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("TestPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
        wsdl11Definition.setSchema(schema);
        wsdl11Definition.setSchemaCollection(schemaCollection);
        return wsdl11Definition;
    }

    @Bean(name="SchemaCollection")
    public XsdSchemaCollection getSchemaCollection() {
        Resource[] schema = {
                new ClassPathResource("simpletype.xsd"),
                new ClassPathResource("test.xsd")
        };
        CommonsXsdSchemaCollection collection = new CommonsXsdSchemaCollection();
        collection.setXsds(schema);
        collection.setInline(true);
        return collection;
    }


    @Bean
    public XsdSchema getSchema() {
        return new SimpleXsdSchema(new ClassPathResource("test.xsd"));
        //return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }

}
