package hl7Integration.camel;

import ca.uhn.hl7v2.model.Message;
import org.springframework.stereotype.Component;

@Component
public class RespondACK {

    public Message process(Message in) throws Exception {
        System.out.println(in.toString());
        Message out =  in.generateACK();
        System.out.println(out.toString());
        return out;

    }
}

