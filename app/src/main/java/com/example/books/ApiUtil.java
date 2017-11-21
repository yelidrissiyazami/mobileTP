package com.example.books;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by root on 11/18/17.
 */

public class ApiUtil {
    //class will never be instantiated
    private ApiUtil(){}

    public static final String Base_API_URI=
            "https://www.googleapis.com/books/v1/volumes";
    public static URL buildUrl(String title){

        String fullUrl= Base_API_URI + "?q=" + title;
        URL url=null;
        try {
            url=new URL(fullUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
                return url;
    }

    //connect to url
    public static String getJson(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

   try {
       //read data
       InputStream stream=connection.getInputStream();
       //convert we use scanner bcs it buffer data and encode it in UTF-16 andro format

       Scanner scanner=new Scanner(stream);
       //large streams into smaller ones but here we read all
       scanner.useDelimiter("\\A");
       boolean hasData=scanner.hasNext();
       if (hasData){
           return scanner.next();
       }else {
           return null;
       }
   }catch (Exception e){
       Log.d("ERROR",e.toString());
       return null;
   }finally {
       connection.disconnect();
   }

    }


}
