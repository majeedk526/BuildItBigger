package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.majeed.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Majeed on 10-08-2016.
 */
class EndpointAsyncTask extends AsyncTask<EndpointAsyncTask.JokeReceiver, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    JokeReceiver jokeReceiver;

    @Override
    protected String doInBackground(JokeReceiver... params) {
        jokeReceiver = params[0];

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8085/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        //context = params[0].first;
        //String name = params[0].second;

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        jokeReceiver.retrivedJoke(result);
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }


    public interface JokeReceiver{

        public void retrivedJoke(String s);
    }
}
