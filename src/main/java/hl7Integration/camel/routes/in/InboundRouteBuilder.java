package hl7Integration.camel.routes.in;

import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InboundRouteBuilder extends SpringRouteBuilder
{
    private static final Logger log = LoggerFactory.getLogger(InboundRouteBuilder.class);

    @Override
    public void configure() throws Exception {

        from("hl7listener")
          .routeId("route_hl7listener")
            .startupOrder(997)
              .unmarshal()
               .hl7(false)
                .to("bean:respondACK?method=process")
                .end();


    }
}
