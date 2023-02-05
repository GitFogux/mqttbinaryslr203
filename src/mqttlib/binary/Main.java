package mqttlib.binary;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;


public class Main
{

    public static void main(final String[] args)
    {
        Main.doDebug();
    }

    public static void doDebug() {

        try {
            Main.doWithConnection();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void doWithConnection() throws IOException {
        final DebugWriteChannel consoleWriter = new DebugWriteChannel();
        final InetAddress adress =InetAddress.getByName("localhost");
        final Socket socket = new Socket(adress, 1883);
        final WritableByteChannel channel = Channels.newChannel(socket.getOutputStream());
        final ConnectMessage connectMsg = Main.doQuestion1();
        connectMsg.writeTo(channel);

        System.out.println("Connect message sent : ");
        System.out.println(connectMsg);
        connectMsg.writeTo(consoleWriter);
        try
        {
            Thread.sleep(200);
        } catch (final InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        byte[] returnVal = new byte[5];
        int v = socket.getInputStream().read(returnVal);
        System.out.println("Returned message to connect: ");

        consoleWriter.write(ByteBuffer.wrap(returnVal,0,v));
        final MqttMessage respMsg = MqttMessage.readMessage(Channels.newChannel(new ByteArrayInputStream(ByteBuffer.wrap(returnVal,0,v).array())));
        System.out.println(respMsg);
        final PublishMessage publishMsg = Main.doQuestion2();
        publishMsg.writeTo(channel);
        System.out.println("Publish message sent : ");
        System.out.println(publishMsg);
        publishMsg.writeTo(consoleWriter);
        try
        {
            Thread.sleep(200);
        } catch (final InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        returnVal = new byte[25];
        v = socket.getInputStream().read(returnVal);
        System.out.println("Returned message to publish : ");
        consoleWriter.write(ByteBuffer.wrap(returnVal,0,v));
        socket.close();

        consoleWriter.close();
    }



    public static ConnectMessage doQuestion1() throws IOException {
        final ConnectMessage message = new ConnectMessage("python1");
        message.setKeepAlive(60);
        return message;
    }

    public static PublishMessage doQuestion2() throws IOException {
        final PublishMessage message = new PublishMessage();
        message.setTopic("ccc");
        message.setPayload("hello34");
        message.setQos(1);
        return message;
    }
}
