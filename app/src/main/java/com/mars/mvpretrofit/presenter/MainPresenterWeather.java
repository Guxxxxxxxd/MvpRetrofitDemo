package com.mars.mvpretrofit.presenter;

import android.content.Context;

import com.mars.mvpretrofit.entities.Weather;
import com.mars.mvpretrofit.model.MainModelWeather;
import com.mars.mvpretrofit.model.MainModle;
import com.mars.mvpretrofit.view.MainView;

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
public class MainPresenterWeather implements MainPresenter{
    private MainView mainView;
    private MainModle mainModle;

    public MainPresenterWeather(MainView mainView){
        this.mainView = mainView;
        mainModle = new MainModelWeather();
    }

    @Override
    public void onSuccess(Weather weather) {
        mainView.showSuccess(weather);
    }

    @Override
    public void onError() {
        mainView.showError();
    }

    @Override
    public void showNoNet() {
        mainView.showNoNet();
    }

    @Override
    public void initView() {
        mainView.initView();
    }

    @Override
    public void sendRequest(Context context, String cityNum) {
        mainView.showLoadding();
        mainModle.getWeather(context,cityNum,this);
    }
}
