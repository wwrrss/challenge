package me.willermo.challenge.services;

import android.app.IntentService;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.realm.Realm;
import me.willermo.challenge.app.ChallengeApplication;
import me.willermo.challenge.retrofit_interfaces.AppsService;
import me.willermo.challenge.models.App;
import me.willermo.challenge.models.Category;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by william on 2/28/17.
 */

public class DataFetchService extends IntentService {
    public DataFetchService(){
        super("DataFetchService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Retrofit retrofit = ChallengeApplication.instance.retrofit;
        AppsService appsService = retrofit.create(AppsService.class);
        Call<ResponseBody> call = appsService.listApps();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    try {
                        realm.deleteAll();
                        JSONObject rootObject = new JSONObject(response.body().string());
                        if(rootObject.has("feed")){
                            JSONObject jsonObjectFeed = rootObject.getJSONObject("feed");
                            JSONArray entries = jsonObjectFeed.getJSONArray("entry");
                            JSONObject entry;
                            App app;
                            Category category;
                            for(int i=0;i<entries.length();i++){

                                entry = entries.getJSONObject(i);
                                Integer appId = entry.getJSONObject("id").getJSONObject("attributes").getInt("im:id");
                                String name=entry.getJSONObject("im:name").getString("label");
                                String summary = entry.getJSONObject("summary").getString("label");
                                JSONArray jsonArrayImages = entry.getJSONArray("im:image");
                                String image = jsonArrayImages.getJSONObject(2).getString("label");
                                app = new App();
                                app.setId(appId);
                                app.setTitle(name);
                                app.setSummary(summary);
                                app.setImage(image);



                                Integer categoryId = entry.getJSONObject("category").getJSONObject("attributes").getInt("im:id");
                                String categoryLabel = entry.getJSONObject("category").getJSONObject("attributes").getString("label");
                                category = new Category();
                                category.setId(categoryId);
                                category.setTerm(categoryLabel);
                                app.setCategory(category);

                                realm.copyToRealm(app);
                            }
                            realm.commitTransaction();

                        }
                    } catch (IOException |JSONException e) {
                        e.printStackTrace();
                    }finally {
                        if(realm!=null){
                            if(realm.isInTransaction()){
                                realm.cancelTransaction();
                            }
                            realm.close();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
