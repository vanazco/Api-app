package com.example.retrofit.api;


import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviedbModule {
    static AnimedbAPI animedbAPI;

    public static AnimedbAPI getAPI(){
        if(animedbAPI == null){
            final OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                   // .addInterceptor(new ApiKeyInterceptor())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15,TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.jikan.moe/v3/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            animedbAPI = retrofit.create(AnimedbAPI.class);
        }
        return animedbAPI;
    }
}

/*class ApiKeyInterceptor implements Interceptor {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "030645b70ccbf9349f5eae2b0c41a15f")
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}*/

class LoggingInterceptor implements Interceptor {
    @Override public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.e("INTERCEPTOR", String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        okhttp3.Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.e("INTERCEPTOR---", String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}