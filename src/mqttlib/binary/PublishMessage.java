package mqttlib.binary;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;

public class PublishMessage extends MqttMessage
{


    private String topic;
    private String payload;



    public PublishMessage() {
        super();
        super.setMessageType(MessageType.PUBLISH);
    }
    public String getTopic()
    {
        return this.topic;
    }
    public void setTopic(final String topic)
    {
        this.topic = topic;
    }
    public String getPayload()
    {
        return this.payload;
    }
    public void setPayload(final String payload)
    {
        this.payload = payload;
    }

    @Override
    public String toString()
    {
        return "PublishMessage [topic=" + this.topic + ", payload=" + this.payload + " " + super.toString() +"]";
    }
    @Override
    public void writeTo(final WritableByteChannel channel) throws IOException
    {
        final BufferByteChannel buffer = new BufferByteChannel();

        new MqttSendableStringList(Arrays.asList(this.topic)).writeTo(buffer);
        new MqttSendableStringList(Arrays.asList(this.payload)).writeTo(buffer);

        super.writeTo(channel, buffer.getLength());

        buffer.flushTo(channel);
    }



    @Override
    public void initialise(final ReadableByteChannel byteChannel, final int remainingLength) throws IOException
    {
        throw new RuntimeException("not implemented"); // not needed for 6.2
    }

}
