package network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;

public class P3T1_IP {

    public static void main(String[] args) throws Exception {
        URL u = new URL("http://www.baidu.com");
        InetAddress proxyIP = InetAddress.getByAddress(new byte[]{(byte)192,(byte)168,8,26});
        SocketAddress proxyAddress = new InetSocketAddress(proxyIP, 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
        try {
            URLConnection uc = u.openConnection(proxy);
            InputStream in = uc.getInputStream();

            BufferedReader utf8Reader = new BufferedReader(
                                        new InputStreamReader(in, Charset.defaultCharset()));
            utf8Reader.lines().forEach(System.out::println);
            System.out.println(in.available());
            System.out.println();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
