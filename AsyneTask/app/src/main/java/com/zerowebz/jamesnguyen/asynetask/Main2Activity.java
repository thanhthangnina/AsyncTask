package com.zerowebz.jamesnguyen.asynetask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Button btnStart;
    AsyncTaskRunner myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAsyncTask = new AsyncTaskRunner(Main2Activity.this);
                myAsyncTask.execute();
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<Void, Integer, Void> {
        Activity contextParent;
        private String resp;

        public AsyncTaskRunner(Activity contextParent) {
            this.contextParent = contextParent;
        }

        @Override
        protected Void doInBackground(Void... params){
            for (int i = 0; i <= 100; i++) {
                SystemClock.sleep(100);
                publishProgress(i);
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(contextParent, "Okie, Finished", Toast.LENGTH_SHORT).show();
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(contextParent, "Start", Toast.LENGTH_SHORT).show();
        }


        @Override
        protected void onProgressUpdate(Integer... text) {
            super.onProgressUpdate(text);
            ProgressBar progressBar = (ProgressBar) contextParent.findViewById(R.id.prbDemo);
            int number = text[0];
            progressBar.setProgress(number);
            TextView textView = (TextView) contextParent.findViewById(R.id.txtStatus);
            textView.setText(number + "%");
        }
    }
}
