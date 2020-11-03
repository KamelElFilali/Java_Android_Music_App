package com.example.tp_android_liberty_play_kamel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
public class InternetClient {
    Bitmap fetchUrlBitmapContent(String url_string) {
        try {
            URL url = new URL(url_string);
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return image;
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            return null;
        }
    }

    JSONObject fetchUrlJSONContent(String url_string) {
        String content = "";
        JSONObject json_object = null;
        try {
            URL url = new URL(url_string);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputline;
            while((inputline = in.readLine()) != null) {
                content += inputline+"\n";
            }
            in.close();
            json_object = new JSONObject(content);
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
        return json_object;
    }
}
