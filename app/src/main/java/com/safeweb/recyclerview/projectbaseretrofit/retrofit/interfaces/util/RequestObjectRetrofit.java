package com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.util;

import retrofit2.Call;
import retrofit2.Response;

public interface RequestObjectRetrofit<T> {

    void requestRetrofit(Call<T> call, Response<T> response);
}
