package wami.ikechukwu.kanu;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dmax.dialog.SpotsDialog;

public class news_detail extends AppCompatActivity {

    //THESE VARIABLE ARE USED TO GET THE MATCHING RESPONSE FROM THE API
    private final String KEY_TITLE = "title";
    private final String KEY_DESCRIPTION = "description";
    private final String KEY_URL = "url";
    private final String KEY_URL_TO_IMAGE = "urlToImage";
    private final String KEY_PUBLISHED_AT = "publishedAt";

    //THIS SERVES AS A GLOBAL VARIABLE THAT HOLD THE POSITION (NUMBER/INTEGER) OF THE ITEM
    // CLICKED IN THE RECYCLERVIEW
    int itemPosition;

    //THIS STRING APPENDS THE URL OF THE API AND IS THE MAIN KEYWORD BEING SEARCHED FOR USING THE
    // API
    String urlLink = "buhari";

    //THIS SERVES AS A GLOBAL VARIABLE HOLD THE URL OF THE ITEM CLICKED IN THE RECYCLERVIEW
    String mUrl;

    //THIS VARIABLES HOLD NEWS DATA THAT WILL BE SENT TO THE NEXT ACTIVITY
    String sendImage, sendTime, sendTitle;

    //THIS VARIABLE WILL HOLD A SEPARATE NEWS TEXT FOR SHARING OPTION
    String newsTextForSharing;

    //INSTANCE OF THE XML VIEWS
    TextView newsDetail_Title, newDetail_Time_Posted, newsDetail_News, share_text;
    ImageView newsDetail_Image;
    ImageView share_facebook, share_twitter, share_whatsapp, share_instagram, share;
    Button Button;

    View newsDetail_line;
    View newsDetail_line_below;

    //THIS METHOD FORMATE THE TIME STAMP FROM THE API INTO AN ORGANIZED TIME STAMP
    public String parseDate(String time) {

        String inputPattern = "yyyy-MM-dd HH:mm:ss"; //2019-09-09T15:01:44Z
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);


        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //HIDE THE ACTION BAR IN THE ACTIVITY SCREEN
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        //GET THE POSITION (NUMBER) OF THE ITEM IN THE RECYCLERVIEW THAT WAS CLICKED IN THE MAIN
        // ACTIVITY
        itemPosition = getIntent().getIntExtra("POSITION", 0);

        //GET THE INSTANCE OF THE VIEW
        newsDetail_Title = findViewById(R.id.newsDetail_Title);
        newDetail_Time_Posted = findViewById(R.id.newsDetail_Time_Posted);
        newsDetail_News = findViewById(R.id.newsDetail_News);
        newsDetail_Image = findViewById(R.id.newsDetail_Image);
        newsDetail_line = findViewById(R.id.newsDetail_line);
        newsDetail_line_below = findViewById(R.id.newsDetail_line_below);
        Button = findViewById(R.id.Button);
        share_text = findViewById(R.id.shareText);
        share_facebook = findViewById(R.id.facebook);
        share_instagram = findViewById(R.id.instagram);
        share_twitter = findViewById(R.id.twitter);
        share_whatsapp = findViewById(R.id.whatsapp);

        //CALL THE METHOD THAT DOES ALL THE WORK IN THIS ACTIVITY
        newsRequest();


    }

    public void newsRequest() {

        final AlertDialog progressDialog = new SpotsDialog(this, R.style.customProgressDialog);
        progressDialog.show();

        //USING VOLLEY TO CREATE AN INTERNET CONNECTION AND PARSE THE JSON
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v2/everything?q=" + urlLink + "&language=en&sortBy=publishedAt&pageSize=100&apiKey=a5f976b34089493abc8f97f088e5df64",
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                //I HAD TO SURROUND THIS IN A TRY AND CATCH STATEMENT TO AVOID THE APP CRASHING
                try {
                    //GETTING THR ARRAY IN THE JSON THAT HOLD OTHER OBJECT/ARRAY
                    JSONArray jsonArray = response.getJSONArray("articles");

                    //USING A FOR-LOOP TI GET THE OBJECT (DATA) IN THE JSON
                    JSONObject jsonObject = jsonArray.getJSONObject(itemPosition);

                    mUrl = jsonObject.getString(KEY_URL);
                    sendImage = jsonObject.getString(KEY_URL_TO_IMAGE);
                    sendTitle = jsonObject.getString(KEY_TITLE);

                    //USED GLIDE TO LOAD THE IMAGE FROM THE JSON INTO THE IMAGE XML THAT WAS CLICKED
                    Glide.with(getApplicationContext()).
                            load(jsonObject.getString(KEY_URL_TO_IMAGE)).
                            into(newsDetail_Image);

                    //SET THE TEXT IN THE XML TO THAT OF THE TITLE FROM THE JSON RESPONSE THAT
                    // WAS CLICKED
                    newsDetail_Title.setText(jsonObject.getString(KEY_TITLE));

                    //THIS VARIABLE HOLDS PART OF THE NEWS TEXT THAT WILL BE USED FOR SHARING OPTION
                    newsTextForSharing = jsonObject.getString(KEY_TITLE);

                    /**
                     * THE DATE STAMP FROM THE API, CONTAINS SOME INVALID CHARACTERS, SO I HAD TO
                     * GET THE VARIOUS TIME IN TWO STRING: DATE AND TIME */

                    //GET THE FULL TIME STRINGS AND PASS THEM INTO TWO SEPARATE STRING
                    String fullStringOne = jsonObject.getString(KEY_PUBLISHED_AT);
                    String fullStringTwo = jsonObject.getString(KEY_PUBLISHED_AT);

                    //START GETTING THE VALID PART FROM THE TIME STRING
                    String dateSubString = fullStringOne.substring(0, 10);
                    String timeSubString = fullStringTwo.substring(12, 19);

                    //MERGE BOTH VALID TIME STRING TOGETHER
                    String fullTime = dateSubString + " " + timeSubString;


                    String time = parseDate(fullTime);

                    newDetail_Time_Posted.setText("Posted by iyke on " + time);

                    sendTime = "Posted by Iyke on " + time;

                    //THIS GET THE URL FROM THE ITEM THAT WAS CLICKED
                    newsDetail_News.setText(jsonObject.getString(KEY_DESCRIPTION));


                } catch (JSONException e) {
                    e.printStackTrace();

                }
                progressDialog.dismiss();
                newsDetail_line.setVisibility(View.VISIBLE);
                newsDetail_line_below.setVisibility(View.VISIBLE);
                Button.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                share_facebook.setVisibility(View.INVISIBLE);
                share_instagram.setVisibility(View.INVISIBLE);
                share_instagram.setVisibility(View.INVISIBLE);
                share_whatsapp.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    public void readTheFullNews(View view) {

        Intent intent = new Intent(getApplicationContext(), newsReader.class);
        intent.putExtra("URL", mUrl);
        intent.putExtra("IMAGE", sendImage);
        intent.putExtra("TITLE", sendTitle);
        intent.putExtra("TIME", sendTime);
        startActivity(intent);
    }

    public void whatsapp(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        try {

            getPackageManager().getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            intent.setPackage("com.whatsapp");
            intent.putExtra(Intent.EXTRA_TEXT, " " + newsTextForSharing + " " + mUrl);
            startActivity(Intent.createChooser(intent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {

            Toast toast = Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_LONG);
            toast.show();

        }
    }

    public void facebook(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        try {

            getPackageManager().getPackageInfo("com.facebook.lite",
                    PackageManager.GET_META_DATA);
            intent.setPackage("com.facebook.lite");
            intent.putExtra(Intent.EXTRA_TEXT, " " + newsTextForSharing + " " + mUrl);
            startActivity(Intent.createChooser(intent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {

            Toast toast = Toast.makeText(this, "Facebook Lite not Installed", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void twitter(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        try {

            getPackageManager().getPackageInfo("com.twitter.android",
                    PackageManager.GET_META_DATA);
            intent.setPackage("com.twitter.android");
            intent.putExtra(Intent.EXTRA_TEXT, " " + newsTextForSharing + " " + mUrl);
            startActivity(Intent.createChooser(intent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {

            Toast toast = Toast.makeText(this, "Twitter not Installed", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    public void instagram(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        try {

            getPackageManager().getPackageInfo("com.instagram.android",
                    PackageManager.GET_META_DATA);
            intent.setPackage("com.instagram.android");
            intent.putExtra(Intent.EXTRA_TEXT, " " + newsTextForSharing + " " + mUrl);
            startActivity(Intent.createChooser(intent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {

            Toast toast = Toast.makeText(this, "Instagram not Installed", Toast.LENGTH_SHORT);
            toast.show();

        }

    }

    public void share(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, " " + newsTextForSharing + " " + mUrl);
        startActivity(Intent.createChooser(intent, "Share with "));
    }

}

