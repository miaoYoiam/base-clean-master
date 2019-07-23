package com.mine.data.net;

import android.util.Log;

import com.mine.data.net.converter.MyGsonConverterFactory;
import com.mine.data.net.interceptor.HeadInterceptor;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpService {
    private static final String TAG = HttpService.class.getSimpleName();
    private static final String BASE_API = "http://wapi.qa.douguo.com/";
    private Retrofit mRetrofit;


    private static volatile HttpService Instance = null;

    public static HttpService getInstance() {
        HttpService instance = Instance;
        if (instance == null) {
            synchronized (HttpService.class) {
                instance = Instance;
                if (instance == null) {
                    Instance = instance = new HttpService();
                }
            }
        }
        return instance;
    }

    public <T> T createApi(Class<T> clazz) {
        return this.mRetrofit.create(clazz);
    }

    public Retrofit getRetrofit() {
        return this.mRetrofit;
    }

    private HttpService() {
        this(true);
    }

    private HttpService(boolean useRxJava) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_API).addConverterFactory(MyGsonConverterFactory.create()).client(getClient());
        if (useRxJava) {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        this.mRetrofit = builder.build();
    }

    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        SSLSocketFactory sslSocketFactory = null;
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return new OkHttpClient.Builder().addInterceptor(new HeadInterceptor()).addInterceptor(logging).connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory).hostnameVerifier((hostname, session) -> {
            return true;
        }).cookieJar(new CookieJar() {
            private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap();

            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                this.cookieStore.put(HttpUrl.parse(url.host()), cookies);
            }

            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = (List) this.cookieStore.get(HttpUrl.parse(url.host()));
                return cookies != null ? cookies : new ArrayList();
            }
        }).build();
    }
}
