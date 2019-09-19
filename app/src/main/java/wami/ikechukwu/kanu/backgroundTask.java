package wami.ikechukwu.kanu;

import android.os.AsyncTask;

//TODO: LATER CHANGE FROM ASYNCTASK TO ASYNCTASKLOADER
public class backgroundTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        MainActivity mainActivity = new MainActivity();

        return null;
    }

}
