package com.example.chentian.chatrobot.tuling;


import android.os.AsyncTask;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author chentian
 */
public class TulingClient extends AsyncTask<String, Void, TulingApiResult> {

  private interface Tuling {
    @GET("/api")
    TulingApiResult fetch(
            @Query("key") String key,
            @Query("info") String info
    );
  }

  public interface Listener {
    void onDataFetched(TulingApiResult result);
  }

  private static final String API_KEY = "8c97651c3025326edfc1964d0a6d141b";

  private static final String API_URL = "http://www.tuling123.com/openapi";

  private Listener listener;

  public TulingClient(Listener listener) {
    this.listener = listener;
  }

  @Override
  protected TulingApiResult doInBackground(String... params) {
    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(API_URL).build();

    Tuling tuling = restAdapter.create(Tuling.class);
    return tuling.fetch(API_KEY, params[0]);
  }

  @Override
  protected void onPostExecute(TulingApiResult tulingApiResult) {
    if (listener != null) {
      listener.onDataFetched(tulingApiResult);
    }
  }
}
