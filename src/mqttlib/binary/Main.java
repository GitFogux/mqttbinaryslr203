package mqttlib.binary;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
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
                Main.doQuestion2(new DebugWriteChannel());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
    }

    public static void doWithConnection() {
        try
        {
            final InetAddress adress =InetAddress.getByName("localhost");
            final Socket socket = new Socket(adress, 1883);
            final WritableByteChannel channel = Channels.newChannel(socket.getOutputStream());
            Main.doQuestion2(channel);
            socket.close();
        } catch (final IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void doQuestion1(final WritableByteChannel channel) throws IOException {
        final ConnectMessage message = new ConnectMessage("python1");
        message.setKeepAlive(60);
        message.writeTo(channel);
    }

    public static void doQuestion2(final WritableByteChannel channel) throws IOException {
        final PublishMessage message = new PublishMessage();
        message.setKeepAlive(60);
        message.writeTo(channel);
    }
}
