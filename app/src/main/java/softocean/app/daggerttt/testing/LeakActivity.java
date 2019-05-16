package softocean.app.daggerttt.testing;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import softocean.app.daggerttt.R;

public class LeakActivity extends Activity {

    private TextView mMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad);
        mMessageView = (TextView) findViewById(R.id.text);

        new LongRunningTask().execute();
    }

    private class LongRunningTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Am finally done!";
        }

        @Override
        protected void onPostExecute(String result) {
            mMessageView.setText(result);
        }
    }
}