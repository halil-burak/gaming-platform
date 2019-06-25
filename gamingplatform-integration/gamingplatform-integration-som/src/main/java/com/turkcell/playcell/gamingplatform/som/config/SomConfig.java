package com.turkcell.playcell.gamingplatform.som.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;

@EnableWs
@Configuration
public class SomConfig {

	@Bean(name = "createOrder")
    public Jaxb2Marshaller createOrderMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath( "com.turkcell.playcell.integration.som.wsdltypes.createorder");
        return marshaller;
    }

    @Bean(name = "isturkcellJaxb2Marshaller")
    public Jaxb2Marshaller isTurkcellMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath( "com.turkcell.playcell.integration.som.wsdltypes.isturkcell");
        return marshaller;
    }

    @Bean(name = "smsServiceMarshaller")
    public Jaxb2Marshaller smsServiceMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath( "com.turkcell.playcell.integration.som.wsdltypes.sms");
        return marshaller;
    }

	@Bean(name = "directDebitMarshaller")
	public Jaxb2Marshaller directDebitMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath( "com.accenture.assets.sdp.commondatamodel.asynch");
		return marshaller;
	}
	@Bean(name = "cmsMarshaller")
	public Jaxb2Marshaller cmsDebitMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath( "com.turkcell.playcell.integration.som.wsdltypes.cms");
		return marshaller;
	}
	
}
