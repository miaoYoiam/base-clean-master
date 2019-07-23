package com.mine.data.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mine.data.exception.NetworkConnectionException;
import com.mine.data.exception.ResponseException;
import com.mine.data.net.HttpResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


public class RepositoryUtils {
    private static final String TAG = "RepositoryUtils";
    private static Gson mGson = new GsonBuilder().create();


    public static <T> Observable<T> extractData(Observable<HttpResponse> observable, Class<T> clazz) {


        return observable.flatMap((Function<HttpResponse, ObservableSource<T>>) httpResponse -> {
            if (httpResponse == null) {
                return Observable.error(new NetworkConnectionException(""));
            }
            if ("success".equals(httpResponse.getStatusMessage())) {
                return Observable.just(mGson.fromJson(mGson.toJson(httpResponse.result), clazz));
            }

            return Observable.error(new ResponseException(new HttpResponse()));
        });
    }

    /**
     * @param observable
     * @param <T>
     * @return 如果不为空 则返回先前传入的observable
     */
    public static <T> Observable<T> extractDataCompate(Observable<T> observable) {
        return observable.flatMap((Function<T, ObservableSource<T>>) t -> {
            if (t == null) {
                return Observable.error(new NetworkConnectionException(""));
            }
            return Observable.just(t);
        });
    }

}
