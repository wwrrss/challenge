package me.willermo.challenge.retrofit_interfaces;

import me.willermo.challenge.app.ChallengeApplication;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by william on 2/28/17.
 */

public interface AppsService {
    @GET(ChallengeApplication.FEED_URL)
    Call<ResponseBody> listApps();

}
