package step.UDP;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;

public class UDPClient {

    public static void main(String[] arg) throws Exception {
        try (DatagramSocket s = new DatagramSocket()) {

            InetAddress addr = InetAddress.getByName("localhost");
//            byte data[] = {1, 2, 3};
//            byte data[]="Test Setrer".getBytes("utf-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DatagramPacket p;
            byte data[];
            while (true) {
                System.out.print("\n>");
                String comand = br.readLine();
                if ("exit".equalsIgnoreCase(comand)) {
                    break;
                }

                data = comand.getBytes();
                p = new DatagramPacket(data, data.length, addr, 3456);
                s.send(p);
//                System.out.println("Datagram sent ->" + comand);
                int size = 256;
                if (comand.startsWith("get ")) {
                    String[] split = comand.split(" ");
                    size = Integer.parseInt(split[2]) + 5;
                    System.out.println("buffer size ->" + size);
                }
                data = new byte[size];
                p = new DatagramPacket(data, data.length);
                s.receive(p);
                byte[] reciveData = p.getData();
                String type = new String(reciveData,0,5);                
                
                if("file:".equalsIgnoreCase(type)){
                    saveFileFromByteArray("testServer.txt",reciveData);
                    System.out.println("File Saved");
                }else{
                    System.out.println(new String(reciveData));
                }
                
            }

        }
    }

    private static void saveFileFromByteArray(String fileName, byte[] reciveData) throws Exception {
        File file = new File("F:\\"+fileName);  
        byte[] fileBody = Arrays.copyOfRange(reciveData, 5, reciveData.length);
        try (BufferedOutputStream bos = new BufferedOutputStream(new  FileOutputStream(file))){ 
            bos.write(fileBody, 0, fileBody.length);
        }       
    }
}
