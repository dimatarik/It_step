
package step.UDP;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    public static void main(String[] arg) throws Exception {
        try {
   DatagramSocket s = 
      new DatagramSocket(3456);
   byte data[]=new byte[256];
   DatagramPacket p = 
      new DatagramPacket(data, 3);
   System.out.println("Waiting...");
   s.receive(p);
//   System.out.println("Datagram received: "+
//      data[0]+", "+data[1]+", "+data[2]);
   System.out.println(new String(data,"utf-8"));
   s.close();
} catch (SocketException e) {
   e.printStackTrace();
} catch (IOException e) {
   e.printStackTrace();
}
    }
}
