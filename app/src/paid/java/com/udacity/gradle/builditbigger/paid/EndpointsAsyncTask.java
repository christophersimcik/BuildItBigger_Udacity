package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.test.mock.MockContext;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jokeandroidlib.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context, mContext;
    ProgressBar progressBar;
    public EndpointsAsyncTask(Context context, ProgressBar progBar){
this.mContext = context;
this.progressBar = progBar;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        context = params[0].first;
            Log.i("CONTEXTNAME", context.getPackageName());
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            ApplicationInfo appInfo = null;
            try {
                appInfo = packageManager.getApplicationInfo("com.udacity.gradle.builditbigger.paid", 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            String applicationName = (String) packageManager.getApplicationLabel(appInfo);
            Log.i("background test", " do in background");
            Log.i("package_name = ", applicationName);
            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        //.setRootUrl("http://192.168.0.19:8080/_ah/api/")
                        .setApplicationName(applicationName)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;

        try {
            return myApiService.retrieveJoke().execute().getMyJoke();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i("Post testing", "On Post Execeute");
        Log.i("post execute"," occured");
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context,JokeActivity.class);
        intent.putExtra("joke",result);
        context.startActivity(intent);

    }
    @Override
    protected void onPreExecute(){
        if(progressBar != null) {
            progressBar.setVisibility(View.VISIBLE
            );
        }
    }
}