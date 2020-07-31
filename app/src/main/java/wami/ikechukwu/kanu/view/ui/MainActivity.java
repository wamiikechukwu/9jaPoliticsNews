package wami.ikechukwu.kanu.view.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import wami.ikechukwu.kanu.R;
import wami.ikechukwu.kanu.model.OnboardingModel;
import wami.ikechukwu.kanu.viewmodel.newsAdapter;
import wami.ikechukwu.kanu.viewmodel.news_detail;

public class MainActivity extends AppCompatActivity implements newsAdapter.onclicklistener {

    //THESE VARIABLE ARE USED TO GET THE MATCHING RESPONSE FROM THE JSON FROM THE API
    private final String KEY_TITLE = "title";
    private final String KEY_DESCRIPTION = "description";
    private final String KEY_URL = "url";
    private final String KEY_URL_TO_IMAGE = "urlToImage";
    private final String KEY_PUBLISHED_AT = "publishedAt";

    //THIS STRING IS USED TO APPEND THE SEARCH WORD TO THE QUERY
    String urlLink = "buhari";

    //THIS SERVES AS A GLOBAL VARIABLE TO HOLD THE POSITION (NUMBER/INTEGER) OF THE ITEM CLICKED IN
    // THE RECYCLERVIEW
    int mPosition;

    //THIS ARRAY LIST CONTAINS THE DATA FROM THE JSON, THAT WILL MIND TO THE RECYCLERVIEW
    ArrayList<OnboardingModel> list;

    //
    private RecyclerView recyclerView;
    private newsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new newsAdapter(this, list, this);
        mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setAdapter(mAdapter);

        if (isConnected()) {
            jsonParser();
        } else {
            Snackbar snackbar = Snackbar.make(recyclerView, "Oops No Internet",
                    Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    jsonParser();
                }
            });
            snackbar.show();
        }

    }

    private boolean isConnected() {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        boolean isconnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        return isconnected;
    }

    private void jsonParser() {

        while (!isConnected()) {
            Snackbar snackbar = Snackbar.make(recyclerView, "Oops No Internet",
                    Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    jsonParser();
                }
            });
            snackbar.show();
            break;
        }

        final AlertDialog progressDialog = new SpotsDialog(this, R.style.customProgressDialog);
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://newsapi.org/v2/everything?q=" + urlLink + "&language=en&sortBy=publishedAt&pageSize=100&apiKey=a5f976b34089493abc8f97f088e5df64", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    //Using a for loop to get the object (data) in the JSON
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject JO = jsonArray.getJSONObject(mPosition);

//                        OnboardingModel OnboardingModel = new OnboardingModel();
//                        OnboardingModel.setTitle(jsonObject.getString(KEY_TITLE));
//                        OnboardingModel.setImage(jsonObject.getString(KEY_URL_TO_IMAGE));
//                        OnboardingModel.setDescrip(jsonObject.getString(KEY_DESCRIPTION));

//                        list.add(OnboardingModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
                mAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position) {

        mPosition = position;
        Intent intent = new Intent(this, news_detail.class);
        intent.putExtra("POSITION", position);
        startActivity(intent);

    }

}

