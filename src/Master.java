import java.net.*;

import javax.swing.JFrame;

import java.io.*;

public class Master extends JFrame{
    final static String SERVER_IP = "192.168.3.105";
    final static int SERVER_PORT = 12300;

    Master(MyKeyListener keyListener){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(30,30);
        this.addKeyListener(keyListener);
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusTraversalKeysEnabled(false);
    }
    public static void main(String args[]) throws Exception{
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
        new Master(new MyKeyListener(objOut));
        while(!socket.isClosed());
        socket.close();
    }
}
