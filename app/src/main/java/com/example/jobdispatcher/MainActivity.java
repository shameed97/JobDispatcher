package com.example.jobdispatcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class MainActivity extends AppCompatActivity {
    private static final String Job_Tag = "my job tag";
    private FirebaseJobDispatcher firebaseJobDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseJobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
    }

    public void startJOb(View view) {

        Job job = firebaseJobDispatcher.newJobBuilder().
                setService(JobDispatcherService.class).
                setLifetime(Lifetime.FOREVER).
                setRecurring(true).
                setTag(Job_Tag).
                setTrigger(Trigger.executionWindow(10, 10)).
                setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL).
                setReplaceCurrent(false).
                setConstraints(Constraint.ON_ANY_NETWORK).build();
        firebaseJobDispatcher.mustSchedule(job);
        Toast.makeText(this, "Job Scheduled....", Toast.LENGTH_SHORT).show();
        Log.d("sha", "Job scheduled");
    }

    public void stopJOb(View view) {
        firebaseJobDispatcher.cancel(Job_Tag);
        Toast.makeText(this, "Job Cancelled....", Toast.LENGTH_SHORT).show();
        Log.d("sha", "job canceled");


    }
}
