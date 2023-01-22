package mqttlib.binary;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;

public class PublishMessage extends MqttMessage
{
   
    private int keepAlive;

    public PublishMessage() {
        super();
        
        this.keepAlive = 60;
        
        super.setMessageType(MessageType.PUBLISH);
        super.ensureHeaderToZeros();
        // super.setDup(true);
        super.setQos(0);

    }

    @Override
    public void writeTo(final WritableByteChannel channel) throws IOException
    {
        String topic = "openlabpro";
        String payload = "hello";
    
        final BufferByteChannel buffer = new BufferByteChannel();
        
        new MqttSendableStringList(Arrays.asList(topic)).writeTo(buffer);
        new MqttSendableStringList(Arrays.asList(payload)).writeTo(buffer);
      
        super.writeTo(channel, buffer.getLength());

        buffer.flushTo(channel);
    }
    

    public int getKeepAlive()
    {
        return this.keepAlive;
    }
    public void setKeepAlive(final int keepAlive)
    {
        this.keepAlive = keepAlive;
    }
    

    @Override
    public void initialise(final ReadableByteChannel byteChannel, final int remainingLength) throws IOException
    {
        throw new RuntimeException("not implemented"); // not needed for 6.2
    }

}
