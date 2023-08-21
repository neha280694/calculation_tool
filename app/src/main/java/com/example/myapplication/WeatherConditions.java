package com.example.myapplication;

import static com.example.myapplication.ProjectConstants.BASE_DEV_URL_ACCU_WEATHER;
import static com.example.myapplication.ProjectConstants.BASE_URL_ACCU_WEATHER;
import static com.example.myapplication.ProjectConstants.BASE_URL_OPEN_WEATHER;



import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherConditions {
    private static IWeatherApi mWeatherApi;

    /**
     * @param city
     * @param appId
     * @param listener
     */
    public static void getOpenWeatherData(String city, String appId, IWeatherCallbackListener listener) {

        mWeatherApi = ApiService.getRetrofitInstance(BASE_URL_OPEN_WEATHER).create(IWeatherApi.class);
        Call<OpenWeatherModel> resForgotPasswordCall = mWeatherApi.getOpenWeatherData(appId, city);
        resForgotPasswordCall.enqueue(new Callback<OpenWeatherModel>() {
            @Override
            public void onResponse(Call<OpenWeatherModel> call, Response<OpenWeatherModel> response) {
                if (response.body() != null) {
                    if (listener != null)
                        listener.getWeatherData(response.body(), true, "");
                }
            }

            @Override
            public void onFailure(Call<OpenWeatherModel> call, Throwable t) {
                if (listener != null)
                    listener.getWeatherData(null, false, t.getMessage());
            }
        });
    }


    public static void getOpenWeatherData5Days(String city, String appId, IWeatherCallbackListener listener) {

        mWeatherApi = ApiService.getRetrofitInstance(BASE_URL_OPEN_WEATHER).create(IWeatherApi.class);
        Call<OpenWeather5DayModel> call = mWeatherApi.getOpenWeatherData5days(appId, city);
        call.enqueue(new Callback<OpenWeather5DayModel>() {
            @Override
            public void onResponse(Call<OpenWeather5DayModel> call, Response<OpenWeather5DayModel> response) {
                if (response.body() != null) {
                    if (listener != null)
                        listener.getWeatherData(response.body(), true, "");
                }
            }

            @Override
            public void onFailure(Call<OpenWeather5DayModel> call, Throwable t) {
                if (listener != null)
                    listener.getWeatherData(null, false, t.getMessage());
            }
        });
    }

    public static void getAccuWeatherData(String city, String appId, IWeatherCallbackListener listener, Boolean isProductionUrl) {

        if (isProductionUrl)
            mWeatherApi = ApiService.getRetrofitInstance(BASE_URL_ACCU_WEATHER).create(IWeatherApi.class);
        else
            mWeatherApi = ApiService.getRetrofitInstance(BASE_DEV_URL_ACCU_WEATHER).create(IWeatherApi.class);
        Call<List<AccuWeatherModel>> call = mWeatherApi.getAccuWeatherData(city, appId);
        call.enqueue(new Callback<List<AccuWeatherModel>>() {
            @Override
            public void onResponse(Call<List<AccuWeatherModel>> call, Response<List<AccuWeatherModel>> response) {
                if (response.body() != null) {
                    if (listener != null)
                        listener.getWeatherData(response.body().get(0), true, "");
                }
            }

            @Override
            public void onFailure(Call<List<AccuWeatherModel>> call, Throwable t) {
                listener.getWeatherData(null, false, t.getMessage());
            }
        });
    }

    public static void getAccuWeatherData5Days(String city, String appId, IWeatherCallbackListener listener, Boolean isProductionUrl) {

        if (isProductionUrl)
            mWeatherApi = ApiService.getRetrofitInstance(BASE_URL_ACCU_WEATHER).create(IWeatherApi.class);
        else
            mWeatherApi = ApiService.getRetrofitInstance(BASE_DEV_URL_ACCU_WEATHER).create(IWeatherApi.class);
        Call<AccuWeather5DayModel> call = mWeatherApi.getAccuWeatherData5days(city, appId);
        call.enqueue(new Callback<AccuWeather5DayModel>() {
            @Override
            public void onResponse(Call<AccuWeather5DayModel> call, Response<AccuWeather5DayModel> response) {
                if (response.body() != null) {
                    if (listener != null)
                        listener.getWeatherData(response.body(), true, "");
                }
            }

            @Override
            public void onFailure(Call<AccuWeather5DayModel> call, Throwable t) {
                if (listener != null)
                    listener.getWeatherData(null, false, t.getMessage());
            }
        });
    }
}