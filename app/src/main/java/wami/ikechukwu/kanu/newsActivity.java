package wami.ikechukwu.kanu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class newsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        MainActivity mainActivity = new MainActivity();
        mainActivity.list.get(0);
    }

}
