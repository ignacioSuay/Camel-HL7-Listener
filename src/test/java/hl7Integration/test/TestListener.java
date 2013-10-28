package hl7Integration.test;

import org.apache.camel.component.hl7.HL7MLLPCodec;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: suay
 * Date: 10/28/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestListener extends CamelTestSupport {


    /**
     * This method sends an HL7 message to the Listener and
     * receives and ACK confirmation message
     * @throws Exception
     */
    @Test
    public void testHl7Codec() throws Exception {

        String inMessage = "MSH|^~\\&|hl7Integration|hl7Integration|||||ADT^A01|||2.5|\r" +
                "EVN|A01|20130617154644\r" +
                "PID|1|465 306 5961||407623|Wood^Patrick^^^MR||19700101|1|||High Street^^Oxford^^Ox1 4DP~George St^^Oxford^^Ox1 5AP|||||||";

        String out = (String) template.requestBody("mina:tcp://localhost:4444?sync=true&codec=#hl7codec", inMessage);

        System.out.println(out);

    }

    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry jndi = super.createRegistry();
        jndi.bind("hl7codec", new HL7MLLPCodec());
        return jndi;
    }
}
