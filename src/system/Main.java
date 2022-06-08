package system;
import view.*;

import javax.swing.*;


public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });

    }

    private static void createGUI(){
        Dashboard dashboard = new Dashboard();
    }

}
