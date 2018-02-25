package jenauto;


import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.jibx.schema.org.opentravel._2013B.ping.PingRQ;

import org.jibx.schema.org.opentravel._2013B.base.OTAPayloadStdAttributes;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.joda.time.DateTime;

/**
 * Hello world!
 * Sample Jibx OTA OSGi program.
 */
public class App 
{
    public final static String STRING_ENCODING = "UTF8";
    public final static String URL_ENCODING = "UTF-8";

    /**
     * Standard constructor.
     */
    public App()
    {
    	super();
    }
    /**
     * Main
     * @param args
     */
    public static void main( String[] args )
    {
        App app = new App();
        app.run();
    }
    /**
     * Run the test.
     */
    public void run()
    {
    	String system = "ping";
    	Object message = createMessage();
    	String xml = marshalObject(message, system);
	    System.out.println(xml);
	    
	    message = unmarshalMessage(xml, system);
	    this.printMessage(message);
    }
    /**
     * Create a default ping message.
     * @return
     */
    public Object createMessage()
    {
    	PingRQ ping = new PingRQ();
    	ping.setEchoData("Hello World!");
        ping.setPayloadStdAttributes(createStandardPayload());
        return ping;
    }
    /**
     * Print the payload of this ping message.
     * @param message
     */
    public void printMessage(Object message)
    {
    	PingRQ ping = (PingRQ)message;
    	System.out.println(ping.getEchoData());
    }
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final SimpleDateFormat gDateFormat = new SimpleDateFormat(DATE_FORMAT);
    /**
     * Convert this date to the standard string date format.
     * which is yyyy-MM-dd.
     * @param date
     * @return
     */
    public String dateToStringDateFormat(Date date)
    {
        if (date == null)
            return null;
        return gDateFormat.format(date);
    }
    /**
     * Shared method to set up a default payload.
     * @return The payload
     */
    public OTAPayloadStdAttributes createStandardPayload()
    {
        OTAPayloadStdAttributes payloadStdAttributes = new OTAPayloadStdAttributes();
        DateTime timeStamp = new DateTime();
        payloadStdAttributes.setTimeStamp(timeStamp);
        Float version = new Float(1.00);
        payloadStdAttributes.setVersion(version);
        payloadStdAttributes.setTarget(OTAPayloadStdAttributes.Target.PRODUCTION);
        return payloadStdAttributes;
    }
    /**
     * Marshal this message to xml .
     * @param message
     * @param system
     * @return
     */
    public String marshalObject(Object message, String system)
    {
        String packageName = "org.jibx.schema.org.opentravel._2013B." + system;
        String bindingName = "binding";

		try {
			IBindingFactory jc = BindingDirectory.getFactory(bindingName, packageName);
			IMarshallingContext marshaller = jc.createMarshallingContext();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshalDocument(message, URL_ENCODING, null, out);
			String xml = out.toString(STRING_ENCODING);
			return xml;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		return null;
    }
    /**
     * Unmarshal this xml Message to an object.
     * @param xml
     * @param system
     * @return
     */
    public Object unmarshalMessage(String xml, String system)
    {
        String packageName = "org.jibx.schema.org.opentravel._2013B." + system;
        String bindingName = "binding";
        
		try {
			IBindingFactory jc = BindingDirectory.getFactory(bindingName, packageName);
			IUnmarshallingContext unmarshaller = jc.createUnmarshallingContext();
	        Reader inStream = new StringReader(xml);
			Object message = unmarshaller.unmarshalDocument( inStream, bindingName);
			return message;
		} catch (JiBXException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
