package com.mars.mvpretrofit.api;

import com.mars.mvpretrofit.entities.Weather;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by gu on 2016/07/13
 * ━━━━━━女神出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    女神保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p/>
 * ━━━━━━感觉萌萌哒━━━━━━
 */
public class ApiManager {
    private static final String BASE_URL = "http://apis.baidu.com";

    private static final Retrofit getServiceList = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

    private static final ApiService apiManager = getServiceList.create(ApiService.class);

    public static Observable<Weather> getWeather(String pinyin){
        return apiManager.getWeather("c50757d2e4d31f64290d503c74fe6054",pinyin);
    }
}
