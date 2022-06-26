import java.awt.Robot;
import java.io.*;
import java.net.*;

public class Slave{
    final static int PORT = 12300;
    public static void main(String[] args) throws Exception {

        ServerSocket servsock = new ServerSocket(PORT);
        //create server socket
        while(true){
            
            Socket sock = servsock.accept();

            //create Object Input Stream
            ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());

            Robot robot = new Robot();

            while(!sock.isClosed()){
                try{
                    boolean pressed = (boolean) objIn.readObject();
                    if(pressed){
                        int keyRead = (int) objIn.readObject();
                        System.out.println("Key Pressed: " + keyRead);
                        robot.keyPress(keyRead);
                    } 
                    
                    else {
                        int keyRead = (int) objIn.readObject();
                        System.out.println("Key Released: " + keyRead);
                        robot.keyRelease(keyRead);
                    } 
                } catch(Exception e){
                    //cleanup
                    objIn.close();
                    sock.close();
                }
                
            }
        }
    }
}
