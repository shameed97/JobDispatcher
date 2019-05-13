package com.example.jobdispatcher;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobDispatcherService extends com.firebase.jobdispatcher.JobService {

    BackgroundTask backgroundTask;

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(@NonNull final com.firebase.jobdispatcher.JobParameters job) {

        backgroundTask = new BackgroundTask() {
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(), "Message From Background Task :" + s, Toast.LENGTH_SHORT).show();
                Log.d("sha", s);
                jobFinished(job, false);
            }
        };
        backgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(@NonNull com.firebase.jobdispatcher.JobParameters job) {


        return true;
    }

    public static class BackgroundTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            return "Hello From Background Task";
        }
    }
}
