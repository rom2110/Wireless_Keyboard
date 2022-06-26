import java.awt.Robot;
import java.io.*;
import java.net.*;

public class Slave{
    final static int PORT = 12300;
    public static void main(String[] args) throws Exception {

        //create server socket
        ServerSocket servsock = new ServerSocket(PORT);
        Socket sock = servsock.accept();

        //create Object Input Stream
        ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());

        Robot robot = new Robot();

        while(!sock.isClosed()){
            boolean pressed = (boolean) objIn.readObject();
            if(pressed){
                    int keyRead = (int) objIn.readObject();
                    System.out.println("Ket Pressed: " + keyRead);
                    robot.keyPress(keyRead);
                } 
                
            else {
                    int keyRead = (int) objIn.readObject();
                    System.out.println("Ket Released: " + keyRead);
                    robot.keyRelease(keyRead);
                } 
        }

        //cleanup
        sock.close();
        servsock.close();

    }
}
