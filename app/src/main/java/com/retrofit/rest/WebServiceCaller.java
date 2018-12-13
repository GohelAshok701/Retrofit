package com.retrofit.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.retrofit.model.Example;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class WebServiceCaller {

    private static ApiInterface apiInterface;

    public static ApiInterface getClient() {
        if (apiInterface == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okclient = new OkHttpClient();
            Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(WebUtility.BASE_URL)
                    .client(okclient.newBuilder()
                            .connectTimeout(100, TimeUnit.SECONDS)
                            .readTimeout(100, TimeUnit.SECONDS)
                            .writeTimeout(100, TimeUnit.SECONDS)
                            .addInterceptor(logging).build())
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

            apiInterface = client.create(ApiInterface.class);
        }
        return apiInterface;
    }

    public interface ApiInterface {
        @FormUrlEncoded
        @POST(WebUtility.GET_FILE)
        Call<Example> getUrls(@Field(ApiConstant.CATEGORY) String category);

    }
}
