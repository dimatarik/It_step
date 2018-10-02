package step.UDP;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;

public class UDPServer {

    public static void main(String[] arg) throws Exception {
        try (DatagramSocket s = new DatagramSocket(3456);) {

            byte[] sentData;
            DatagramPacket p;
            while (true) {
                sentData = new byte[256];
                p = new DatagramPacket(sentData, sentData.length);
                System.out.println("Waiting...");
                s.receive(p);
                byte[] reciveData = p.getData();
                String command = new String(reciveData).trim();
//                System.out.println("command<-" + command + "!!!");
//                System.out.println("command length-"+command.length());
                InetAddress address = p.getAddress();
                int port = p.getPort();

                if ("help".equalsIgnoreCase(command)) {
                    sentData = "server RUNED".getBytes();
                } 
                else if ("stop".equalsIgnoreCase(command)) {
                    sentData = "server STOPED".getBytes();
                } 
                else if ("list".equalsIgnoreCase(command)) {
                    sentData = getFileList();
                } 
                else if (command.startsWith("get ")) {
                    sentData = getFile(command);
                } 
                else {
                    sentData = " Isn't command".getBytes();
                }

                p = new DatagramPacket(sentData, sentData.length, address, port);
//                System.out.println("Response -> "+new String(sentData));

                s.send(p);
                if ("stop".equalsIgnoreCase(command)) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getFile(String command) {
        String[] split = command.split(" ");        
        File file = new File("F:/"+split[1]);
        try {
            String prefix = "file:";
            int prefixLength = prefix.length();
            int buffer = (int) file.length() + prefixLength;
            byte[] resp = new byte[buffer];
            addPrefix(prefix,resp);
            try (BufferedInputStream bis = 
                    new BufferedInputStream(
                            new FileInputStream(file))) {
                bis.read(resp, prefixLength, buffer - prefixLength);
            }
            return resp;
        } catch (FileNotFoundException ex) {
            return "file not exist".getBytes();
        } catch (IOException ex) {
            return ("read error - " + ex.getMessage()).getBytes();
        }
    }

    private static byte[] getFileList() {
        File list[] = new File("F:\\").listFiles();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            if (list[i].isFile()) {
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                sb.append(list[i].getName()+" "+list[i].length());
//                System.out.println(list[i].getName());
            }
        }
        return sb.toString().getBytes();
    }

    private static void addPrefix(String pref, byte[] resp) {
        byte[]commandArray = pref.getBytes();
        for(int i=0; i<pref.length();i++){
            resp[i] = commandArray[i];
        }
    }
}
