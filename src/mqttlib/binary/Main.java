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
        try
        {
            Main.doQuestion1(new DebugWriteChannel());
        } catch (final IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void doWithConnection() {
        try
        {
            final InetAddress adress =InetAddress.getByName("localhost");
            final Socket socket = new Socket(adress, 1883);
            final WritableByteChannel channel = Channels.newChannel(socket.getOutputStream());
            Main.doQuestion1(channel);
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
}