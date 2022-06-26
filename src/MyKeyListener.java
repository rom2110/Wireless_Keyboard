import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectOutputStream;

public class MyKeyListener implements KeyListener{
    ObjectOutputStream objOut;
    int[] curKeyPressed = {-1,-1,-1};
    boolean capslock;

    MyKeyListener(ObjectOutputStream objOut){
        this.objOut = objOut;
        capslock = false;
    }


    //sends false to signal that it is a key being pressed
    @Override
    public void keyPressed(KeyEvent arg0){
        try {
            int keyCode = arg0.getKeyCode();
            boolean notPressed = true;

            //check if the key has already been pressed
            for(int i : curKeyPressed){
                if(i == keyCode){
                    notPressed = false;
                }
            }
            
            if(notPressed){

                //record the key pressed
                for(int i = 0; i < 3; i ++){
                    if(curKeyPressed[i] < 0){
                        curKeyPressed[i] = keyCode;
                        break;
                    }
                }

                //send if any regular key or if caps lock is not toggled
                if(keyCode != 20 || keyCode == 20 && !capslock){
                    writeKey(true, keyCode);
                    System.out.println("Key Pressed: " + keyCode);
                }

                //togle capslock if recorded
                if(keyCode == 20){
                    capslock = !capslock;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        try {
            int keyCode = arg0.getKeyCode();
            //if its a regular key or capslock is untoggled, unpress it
            if(keyCode != 20 || keyCode == 20 && !capslock){
                writeKey(false, keyCode);
                System.out.println("Key Released: " + keyCode);

                
            }
            //remove key if it has been unpressed
            for(int i = 0; i < 3; i ++ ){   
                if(curKeyPressed[i] == keyCode){
                    curKeyPressed[i] = -1;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        System.out.println("hello");
    }

    private void writeKey(boolean pressed, int keyCode) throws Exception{
        //true if pressed, false if released
        objOut.writeObject(pressed);
        objOut.writeObject(keyCode);
    }
    
}
