package com.turkcell.playcell.gamingplatform.som.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

	@Autowired
    private SomApplicationProperties somApplicationProperties;

	@Bean
    public ServletRegistrationBean<?> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(false);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name ="generic-provisioning")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema genericProvisioningSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GenericProvisioningPort");
        wsdl11Definition.setLocationUri(somApplicationProperties.getServerLocation() + "/ws");
        wsdl11Definition.setRequestSuffix("_DATA");
        wsdl11Definition.setResponseSuffix("result");
        wsdl11Definition.setTargetNamespace("http://www.accenture.com/assets/sdp/commonDataModel/asynch");
        wsdl11Definition.setSchema(genericProvisioningSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema genericProvisioningSchema() {
        return new SimpleXsdSchema(new ClassPathResource("generic-provisioning.xsd"));
    }

}
