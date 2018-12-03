package network;

import java.net.*;

public class P3T2_socket {

    public static void main(String[] args) throws Exception {
        InetAddress proxyIP = InetAddress.getByAddress(new byte[]{(byte)192, (byte)168, 8, 26});
        SocketAddress proxyAddress = new InetSocketAddress(proxyIP, 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);

        Socket s = new Socket(proxy);
        System.out.println(s.getInputStream().available());
    }
}
