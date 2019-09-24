package wami.ikechukwu.kanu;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class newsReader extends AppCompatActivity {

    //GLOBAL VARIABLES TO STORE THE DATA GOTTEN FROM THE NEWS_DETAIL ACTIVITY
    String getUrl;
    String getImage;
    String getTime;
    String getTitle;

    //INSTANCES OF THE VIEWS IN THE ACTIVITY
    TextView news_text;
    TextView news_title;
    TextView news_time;
    ImageView news_image;
    View view_line;
    View view_line_below;
    AdView newsReader_Ad1;
    AdView newsReader_Ad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //THIS STATEMENT HIDES THE APP ACTION BAR VIEW FROM THE PHONE SCREEN
        getSupportActionBar().hide();

        setContentView(R.layout.activity_news_reader);

        //THIS STORES THE DATA GOTTEN FROM THE NEWS_DETAIL ACTIVITY
        getUrl = getIntent().getStringExtra("URL");
        getImage = getIntent().getStringExtra("IMAGE");
        getTime = getIntent().getStringExtra("TIME");
        getTitle = getIntent().getStringExtra("TITLE");


        //GET THE INSTANCE OF THE VIEW ID
        news_text = findViewById(R.id.news_text);
        news_title = findViewById(R.id.news_title);
        news_time = findViewById(R.id.news_time);
        news_image = findViewById(R.id.news_image);
        view_line = findViewById(R.id.news_line);
        view_line_below = findViewById(R.id.news_line_below);
        newsReader_Ad1 = findViewById(R.id.news_reader_AD1);
        newsReader_Ad2 = findViewById(R.id.news_reader_AD2);

        //LOADS THE ADs INTO THE ADVIEW
        AdRequest adRequest1 = new AdRequest.Builder().build();
        newsReader_Ad1.loadAd(adRequest1);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        newsReader_Ad2.loadAd(adRequest2);

        //CALLS THE INSTANCE OF THE CLASS AND EXECUTE THE ASYNCTASK
        new experiment().execute();
    }

    //THIS CLASS EXTENDS THE ASYNC TASK WHEN LOADS THE NEWS TEXT FROM IN THE BACKGROUND
    public class experiment extends AsyncTask<Void, Void, String> {

        //VARIABLES FOR STORING THE DATA NEEDED IN THIS ASYNC TASK
        String title;
        String newUrl;
        Snackbar snackbar;

        @Override
        protected void onPreExecute() {

            //CHECK IF THE URL PARSE FROM THE API CONTAINS HTTPS OR HTTP
            //IF IT DOESN'T AND THE ANDROID VERSION NEEDS HTTPS, THEN ADD IT TO THE URL
            //ELSE USE THE DEFAULT HTTP
            if (!getUrl.contains("http")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    newUrl = "https://" + getUrl;
                } else {
                    newUrl = "http" + getUrl;
                }
            } else {
                newUrl = getUrl;
            }

            //WHILE THE DATA IS BEING PARSED IN THE BACKGROUND, SHOW THIS SNACK BAR
            snackbar = Snackbar.make(findViewById(R.id.newsReaderLayout),
                    "Please wait, full details of the news coming up shortly...",
                    Snackbar.LENGTH_INDEFINITE);
            snackbar.show();

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... voids) {

            //USING JSOUP TO ESTABLISH AN INTERNET CONNECTION AND GET THE CONTENT WITH <P> TAG IN
            // THE WEBSITE
            try {
                Document document =
                        Jsoup.connect(newUrl).followRedirects(true).timeout(600000).get();
                Elements element = document.select("p").nextAll();

                //GET THE TEXT
                title = element.text();

            } catch (IOException e) {
                e.printStackTrace();
            }
            //RETURN THE WEBSITE TEXT
            return title;
        }

        @Override
        protected void onPostExecute(String title) {

            //SET THE TEXTVIEW TO THAT OF THE DATA FROM FROM WEBSITE
            news_text.setText(title);

            //SET ALL CORRESPONDING TEXTVIEW TO THE DATA GOTTEN FROM NEWS_DETAIL ACTIVITY
            news_time.setText(getTime);
            news_title.setText(getTitle);

            //USE GLIDE TO DOWNLOAD THE IMAGE FROM THE WEBSITE AND SET IT IN THE IMAGE VIEW
            Glide.with(getApplicationContext()).load(getImage).into(news_image);

            //WEN ALL DATA ARE READY, MAKE THE REMAINING VIEWS VISIBLE
            view_line.setVisibility(View.VISIBLE);
            view_line_below.setVisibility(View.VISIBLE);

            //THEN DISMISS THE SNACKBAR THAT WAS DISPLAYING WHEN, THE ASYNC TASK WAS LOADING
            snackbar.dismiss();

            super.onPostExecute(title);

        }

    }

}
