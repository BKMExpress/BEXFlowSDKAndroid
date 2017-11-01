package com.bkm.bexflowsample.network.core;

import android.content.Context;

import com.bkm.bexflowsample.R;
import com.bkm.bexflowsample.network.res.BaseResponse;
import com.bkm.bexflowsample.network.res.ErrorResponse;
import com.bkm.bexflowsample.utils.Constants;

import java.lang.annotation.Annotation;
import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


/**
 * Created by bsoykal on 29/01/16.
 */
public abstract class RetrofitCallback<T extends BaseResponse> implements Callback<T> {

    private Context context;

    public RetrofitCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T baseResponse = response == null ? null : response.body();

        if (baseResponse == null || !response.isSuccessful()) {
            parseError(response);
            return;
        }


        if (baseResponse.getResult().equals(Constants.SUCCESSFUL_STATUS_MESSAGE)){
            onSuccess(baseResponse);
            return;
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof ConnectException)
            onFailure(null, context.getString(R.string.no_connection));
        else if (t instanceof TimeoutException)
            onFailure(null, context.getString(R.string.timeout_exception));
        else
            onFailure(null, context.getString(R.string.general_network_exception));
    }

    public abstract void onSuccess(T successResponse);

    public abstract void onFailure(String code, String error);


    private void parseError( Response<T> response){
            Converter<ResponseBody, ErrorResponse> converter =
                    RestManagerApp.getRetrofit()
                            .responseBodyConverter(ErrorResponse.class, new Annotation[0]);

            ErrorResponse error = null;

            try {
                error = converter.convert(response.errorBody());
                onFailure(error.getCode(), error.getDescription());
            } catch (Exception e) {
                onFailure("XXX", context.getString(R.string.general_network_exception));
            }
    }
}
