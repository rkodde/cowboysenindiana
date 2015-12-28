package nl.cowboysenindiana.app.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import nl.cowboysenindiana.app.networkTest.RequestPackage;

/**
 * Created by Rene on 22-11-2015.
 */
public class HttpManager {

    public static String getData(RequestPackage rp) {

        BufferedReader reader = null;
        String uri = rp.getUri();
        if (rp.getMethod().equals("GET")) {
            uri += "?" + rp.getEncodedParams();
        }

        try {
            URL url = new URL (uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(rp.getMethod());

            if (rp.getMethod().equals("POST")) {
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(rp.getEncodedParams());
                writer.flush();
            }
//            return rp.getEncodedParams();
            StringBuilder sb = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            return sb.toString();

        } catch(Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
