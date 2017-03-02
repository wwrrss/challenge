package me.willermo.challenge.app;

import android.app.Application;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by william on 2/28/17.
 */

public class ChallengeApplication extends Application {
    public static final String SERVICE_BASE_URL ="https://itunes.apple.com/us/rss/";
    public static final String FEED_URL = "topfreeapplications/limit=20/json";

    public static ChallengeApplication instance;

    public Gson gson;
    public Retrofit retrofit;

    private OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("ChallengeApp")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }
                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                }).create();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .followRedirects(false).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

    }
}
