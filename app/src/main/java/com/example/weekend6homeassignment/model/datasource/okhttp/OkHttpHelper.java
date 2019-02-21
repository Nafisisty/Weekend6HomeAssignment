package com.example.weekend6homeassignment.model.datasource.okhttp;

import android.util.Log;

import com.example.weekend6homeassignment.model.books.Book;
import com.example.weekend6homeassignment.model.events.BookEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.weekend6homeassignment.model.Constants.BASE_URL;

public class OkHttpHelper {

    public static void asyncOkHttpApiCall(final String url) {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResponse;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResponse = response.body().string();
                Log.d("TAG", "onResponse: " + jsonResponse);

                Gson gson = new Gson();

                    Book[] bookArray = gson.fromJson(jsonResponse, Book[].class);
                    System.out.println("Getting Book Info");
                    EventBus.getDefault().post(new BookEvent(bookArray));
            }
        });
    }

}
