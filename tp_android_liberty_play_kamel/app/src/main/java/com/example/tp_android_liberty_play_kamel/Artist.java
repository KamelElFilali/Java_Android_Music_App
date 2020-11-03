package com.example.tp_android_liberty_play_kamel;
import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
public class Artist {

    private String artist_name_encoded;
    private String search_url;

    private JSONObject search_json;
    private JSONObject artist_json;

    private String artist_url = null;
    private String artist_name = null;
    private String artist_profile = null;
    private Bitmap artist_picture = null;
    private String artist_web_url = null;

    protected String getArtist_web_url() {
        return artist_web_url;
    }

    protected Bitmap getArtist_picture() {
        return artist_picture;
    }

    protected String getArtist_url() {
        return artist_url;
    }

    protected String getArtist_name() {
        return artist_name;
    }

    protected String getArtist_profile() {
        return artist_profile;
    }

    private InternetClient internet_client = new InternetClient();


    private String createArtistSearchURL(String artist_name) {
        try {
            artist_name_encoded = URLEncoder.encode(artist_name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        String url = "http://api.discogs.com/database/search?type=artist&q="+artist_name_encoded;
        return url;
    }


    private String fetchArtistURL(JSONObject json_object) throws JSONException {
        String result;
        result = json_object.getJSONArray("results").getJSONObject(0).getString("resource_url");
        return result;
    }


    private String fetchArtistName(JSONObject json_object) {
        if(json_object != null)
            return json_object.optString("name");
        else
            return null;
    }


    private String fetchArtistProfile(JSONObject json_object) {
        if(json_object != null)
            return json_object.optString("profile");
        else
            return null;
    }


    private String fetchArtistWebURL(JSONObject json_object) {
        if(json_object != null)
            return json_object.optString("uri");
        else
            return null;
    }

    private Bitmap fetchArtistPicture(JSONObject json_object) {
        if(json_object != null) {
            String picture_url;
            try {
                picture_url = json_object.getJSONArray("images").getJSONObject(0).getString("resource_url");
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return internet_client.fetchUrlBitmapContent(picture_url);
        } else
            return null;
    }

    public Artist(String search_string) {
        search_url = createArtistSearchURL(search_string);
        search_json = internet_client.fetchUrlJSONContent(search_url);

        try {
            artist_url = fetchArtistURL(search_json);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        artist_json = internet_client.fetchUrlJSONContent(artist_url);

        artist_name = fetchArtistName(artist_json);
        artist_profile = fetchArtistProfile(artist_json);
        artist_picture = fetchArtistPicture(artist_json);
        artist_web_url = fetchArtistWebURL(artist_json);
        return;
    }




}
