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

    String getUrl;
    String getImage;
    String getTime;
    String getTitle;

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
        getSupportActionBar().hide();
        setContentView(R.layout.activity_news_reader);

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

        AdRequest adRequest1 = new AdRequest.Builder().build();
        newsReader_Ad1.loadAd(adRequest1);

        AdRequest adRequest2 = new AdRequest.Builder().build();
        newsReader_Ad2.loadAd(adRequest2);

        new experiment().execute();
    }


    //THIS PART OF THIS CODE WAS COMMENTED BECAUSE IT WOULD BE USED LATER

    public class experiment extends AsyncTask<Void, Void, String> {

        String title;
        String newUrl;
        Snackbar snackbar;

        @Override
        protected void onPreExecute() {

            if (!getUrl.contains("http")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    newUrl = "https://" + getUrl;
                } else {
                    newUrl = "http" + getUrl;
                }
            } else {
                newUrl = getUrl;
            }

            snackbar = Snackbar.make(findViewById(R.id.newsReaderLayout),
                    "Please wait, news coming up shortly...", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                Document document =
                        Jsoup.connect(newUrl).followRedirects(true).timeout(600000).get();
                Elements element = document.select("p").nextAll();


                title = element.text();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return title;
        }

        @Override
        protected void onPostExecute(String title) {

            super.onPostExecute(title);
            news_text.setText(title);
            news_time.setText(getTime);
            news_title.setText(getTitle);
            Glide.with(getApplicationContext()).load(getImage).into(news_image);
            view_line.setVisibility(View.VISIBLE);
            view_line_below.setVisibility(View.VISIBLE);
            snackbar.dismiss();
        }

    }


}
