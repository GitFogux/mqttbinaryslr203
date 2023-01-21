package mqttlib.binary;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class PublishMessage extends MqttMessage
{

    @Override
    public void writeTo(final WritableByteChannel channel) throws IOException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void initialise(final ReadableByteChannel byteChannel, final int remainingLength) throws IOException
    {
        throw new RuntimeException("not implemented"); // not needed for 6.2
    }

}
