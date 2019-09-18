package wami.ikechukwu.kanu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class news_detail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        newsRequest();
    }

    public void newsRequest() {

    }

}
