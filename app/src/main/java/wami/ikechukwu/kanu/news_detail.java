package wami.ikechukwu.kanu;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class news_detail extends AppCompatActivity {

    //TODO: REMOVE THE UNUSED LINE IF THERE IS NO NEED FOR THEM IN THE APP
    //THESE VARIABLE ARE USED TO GET THE MATCHING RESPONSE FROM THE JSON FROM THE API
    // private final String KEY_AUTHOR = "author";
    private final String KEY_TITLE = "title";
    //private final String KEY_DESCRIPTION = "description";
    private final String KEY_URL = "url";
    private final String KEY_URL_TO_IMAGE = "urlToImage";
    private final String KEY_PUBLISHED_AT = "publishedAt";

    //THIS VARIABLE HOLD THE POSITION (NUMBER/INTEGER) OF THE ITEM CLICKED IN THE RECYCLERVIEW
    int itemPosition;

    //THIS STRING IS APPENDED TO THE URL OF THE API AND IS THE MAIN KEYWORD BEING SEARCHED FOR
    String urlLink = "buhari";

    //THIS STRING IS INTENDED TO HOLD THE URL FROM THE JSON -WHICH IS USED OPEN EACH INDIVIDUAL
    // NEWS PAGE

    String news_url;

    //INSTANCE OF THE XML VIEWS
    TextView newsDetail_Title, newDetail_Time_Posted, newsDetail_News;
    ImageView newsDetail_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        //GET THE POSITION (NUMBER) OF THE ITEM IN THE RECYCLERVIEW THAT WAS CLICKED IN THE MAIN
        // ACTIVITY
        itemPosition = getIntent().getIntExtra("POSITION", 0);
        news_url = getIntent().getStringExtra("URL");

        //GET THE INSTANCE OF THE VIEW
        newsDetail_Title = findViewById(R.id.newsDetail_Title);
        newDetail_Time_Posted = findViewById(R.id.newDetail_Time_Posted);
        newsDetail_News = findViewById(R.id.newsDetail_News);
        newsDetail_Image = findViewById(R.id.newsDetail_Image);

        //CALL THE METHOD THAT DOES ALL THE WORK IN THIS ACTIVITY
        newsRequest();
        new experiment().execute();
    }

    public void newsRequest() {

        //USING VOLLEY TO CREATE AN INTERNET CONNECTION AND PARSE THE JSON
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://newsapi.org/v2/everything?q=" + urlLink + "&language=en&sortBy=publishedAt&pageSize=100&apiKey=a5f976b34089493abc8f97f088e5df64", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                //I HAD TO SURROUND THIS IN A TRY AND CATCH STATEMENT TO AVOID THE APP CRASHING
                try {
                    //GETTING THR ARRAY IN THE JSON THAT HOLD OTHER OBJECT/ARRAY
                    JSONArray jsonArray = response.getJSONArray("articles");

                    //USING A FOR-LOOP TI GET THE OBJECT (DATA) IN THE JSON
                    JSONObject jsonObject = jsonArray.getJSONObject(itemPosition);

                    //SET THE TEXT IN THE XML TO THAT OF THE TITLE FROM THE JSON RESPONSE
                    newsDetail_Title.setText(jsonObject.getString(KEY_TITLE));

                    //news_url = jsonObject.getString(KEY_URL);

                    //newsDetail_News.setText(news_url);

                    Glide.with(getApplicationContext()).load(jsonObject.getString(KEY_URL_TO_IMAGE)).into(newsDetail_Image);

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

///////////////////////////////////////////////////////////////////////////////////////////////////

    }

    public class experiment extends AsyncTask<Void, Void, Void> {

        String title;
        String newUrl;

        @Override
        protected void onPreExecute() {

            if (!news_url.contains("http")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    newUrl = "https://" + news_url;
                } else {
                    newUrl = "http" + news_url;
                }
            } else {
                newUrl = news_url;
            }

            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document document =
                        Jsoup.connect(newUrl).followRedirects(true).timeout(600000).get();
                   /* Elements element = document.select("p");
                    for (Element paragraph : element) {
                        builder.append(paragraph.text());
                    }
                    */

                title = document.title();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            newsDetail_News.setText(title);

        }

        @Override
        protected void onProgressUpdate(Void... values) {

            Toast.makeText(getApplicationContext(), "IS IT WORKING", Toast.LENGTH_SHORT).show();
            super.onProgressUpdate(values);
        }

    }

}

