package playarea.portcheck;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	public static final int SERVER_PORT = 7000;

	public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while(true)
           {
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              serverSocket.receive(receivePacket);
              String sentence = new String( receivePacket.getData());
              System.out.println("RECEIVED: " + sentence);
              InetAddress IPAddress = receivePacket.getAddress();
              int port = receivePacket.getPort();
              String capitalizedSentence = sentence.toUpperCase();
              sendData = capitalizedSentence.getBytes();
              DatagramPacket sendPacket =
              new DatagramPacket(sendData, sendData.length, IPAddress, port);
              serverSocket.send(sendPacket);
              System.out.println("FROM " + IPAddress);
           }
	}

}
