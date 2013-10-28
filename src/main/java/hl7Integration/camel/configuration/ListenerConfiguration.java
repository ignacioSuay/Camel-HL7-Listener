package hl7Integration.camel.configuration;

import hl7Integration.camel.RespondACK;
import hl7Integration.camel.routes.in.InboundRouteBuilder;
import org.apache.camel.component.hl7.HL7MLLPCodec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ListenerConfiguration {

    @Bean
    public HL7MLLPCodec hl7codec() {

        HL7MLLPCodec theBean = new HL7MLLPCodec();
        theBean.setCharset("ISO-8859-1");
        theBean.setValidate(false);

        return theBean;
    }

    @Bean
    public RespondACK RespondACK() {
        return new RespondACK();
    }

    @Bean
    public InboundRouteBuilder InboundRouteBuilder() {
        return new InboundRouteBuilder();
    }

}
