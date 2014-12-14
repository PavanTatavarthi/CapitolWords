package capitalwords.example.com.capitolwords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Base64;
import android.util.Log;

/**
 * Created by pavan on 12/14/14.
 *
 * To make Http call
 */
public class HttpManager {

        public static String getData(String uri) {

            BufferedReader reader = null;

            try {
                URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                Log.d("Pavan" , sb.toString());
                return sb.toString();

            } catch (Exception e) {
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

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
        }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
        public static String getData(String uri, String userName, String password) {

            BufferedReader reader = null;
            HttpURLConnection con = null;

            byte[] loginBytes = (userName + ":" + password).getBytes();
            StringBuilder loginBuilder = new StringBuilder()
                    .append("Basic ")
                    .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));

            try {
                URL url = new URL(uri);
                con = (HttpURLConnection) url.openConnection();

                con.addRequestProperty("Authorization", loginBuilder.toString());

                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                return sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    int status = con.getResponseCode();
                    Log.d("HttpManager", "HTTP response code: " + status);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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

