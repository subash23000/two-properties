package threads;

import data.Robot;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendData implements Runnable {
 // sends robot data to REST
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);

                URL url = new URL("http://10.22.16.206:8080/rest/lego/setvalues/"
                        + Robot.getRun() + "/"
                        + Robot.getSpeed() + "/"
                        + Robot.getTurn());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.getInputStream();
                conn.disconnect();

                System.out.println("Sent data to server");

            } catch (Exception e) {
                System.out.println("Cannot send data");
            }
        }
    }
}