package hl7Integration.test;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
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

        assertNotNull(out);
        //Check that the output is an ACK message
        assertEquals("ACK", getMessageType(out));


    }

    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry jndi = super.createRegistry();
        jndi.bind("hl7codec", new HL7MLLPCodec());
        return jndi;
    }

    /**
     * We can use Hapi Terser to retrieve the type of Message
     * @param message HL7 Message
     * @return Type of the HL7 Message (e.g. ACK)
     */
    private String getMessageType(String message) throws HL7Exception{
        PipeParser pipeParser = new PipeParser();
        Message hl7Message = pipeParser.parse(message);
        Terser terser = new Terser(hl7Message);

        return terser.get("/MSH-9");

    }
}
