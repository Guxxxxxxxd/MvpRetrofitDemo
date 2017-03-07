package com.mars.mvpretrofit.model;

import android.content.Context;
import android.util.Log;

import com.mars.mvpretrofit.api.ApiManager;
import com.mars.mvpretrofit.entities.Weather;
import com.mars.mvpretrofit.presenter.MainPresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
public class MainModelWeather implements MainModle{
    private static final String TAG = "MainModelWeather";
    /**
     * 实现MainModole获取天气信息
     * cityNum:城市代码
     * listener:网络连接是否成功接口
     */
    @Override
    public void getWeather(Context context, String cityNum, final MainPresenter listener) {
        ApiManager.getWeather(cityNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Weather>() {
            @Override
            public void call(Weather weather) {
                Log.e(TAG, "MainModelWeather:" + weather);
                listener.onSuccess(weather);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "MainModelWeather:" + throwable);
                listener.onError();
            }
        });
    }
}
