package com.example.books;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        //TextView tvResult=(TextView) findViewById(R.id.tvResponse);
        //functionnert nicht pas de net on the main thread you know it ;)
        try {
            URL bookUrl=ApiUtil.buildUrl("cooking");
           new BookQueryTask().execute(bookUrl);
            // String jsonResult=ApiUtil.getJson(bookUrl);
           // tvResult.setText(jsonResult);

        }catch (Exception e){
            Log.d("ERROR",e.toString());
        }

    }

public class BookQueryTask extends AsyncTask<URL,Void,String>{


    @Override
    protected String doInBackground(URL... urls) {
        URL searchUrl=urls[0];
        String result=null;
        try {
            result=ApiUtil.getJson(searchUrl);
        }catch (IOException e){
            Log.d("ERROR",e.toString());
        }
            return result;
    }

    @Override
    protected void onPostExecute(String result) {
        TextView tvResult=(TextView) findViewById(R.id.tvResponse);
        tvResult.setText(result);

    }
}

}
