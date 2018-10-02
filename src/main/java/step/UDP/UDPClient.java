
package step.UDP;

import java.io.IOException;
import java.net.*;



public class UDPClient {
    public static void main(String[] arg) throws Exception {
        try {
            DatagramSocket s = new DatagramSocket();
            byte data[] = {1, 2, 3};
//            byte data[]="Test Setrer".getBytes("utf-8");
            InetAddress addr
                    = InetAddress.getByName("localhost");
            DatagramPacket p
                    = new DatagramPacket(data, 3, addr, 3456);
            s.send(p);
            System.out.println("Datagram sent");
            s.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
