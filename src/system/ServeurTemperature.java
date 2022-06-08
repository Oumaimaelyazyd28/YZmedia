package system;

import java.io.*;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ServeurTemperature {
    public static void main(String args[]){
        String hote = "127.0.0.1";
        int port = 1000;
        Socket soc = null;
        FileReader input = null;
        File file = new File("src/view/files/bruit.txt");
        try {

            soc = new Socket(hote,port);

            OutputStream flux = soc.getOutputStream();
            OutputStreamWriter sortie = new OutputStreamWriter(flux);

            input = new FileReader(file);
            char c;
            while ((c = (char) input.read()) != -1){
                sortie.write(c);
            }

            sortie.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
