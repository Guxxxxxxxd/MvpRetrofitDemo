package com.mars.mvpretrofit.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mars.mvpretrofit.R;
import com.mars.mvpretrofit.entities.Weather;
import com.mars.mvpretrofit.loaddingview.LoadingState;
import com.mars.mvpretrofit.loaddingview.LoadingView;
import com.mars.mvpretrofit.loaddingview.OnRetryListener;
import com.mars.mvpretrofit.presenter.MainPresenterWeather;

import butterknife.ButterKnife;

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
//========================================================================================================
//MVP设计模式与Retrofit框架在Android中的应用
//========================================================================================================
public class MainActivity extends AppCompatActivity implements MainView{

    private EditText editNum;
    private Button btnGo;
    private LoadingView loadding;
    private TextView showText;

    private MainPresenterWeather presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (null == presenter) {
            presenter = new MainPresenterWeather(this);
        }
        presenter.initView();
    }

    @Override
    public void initView() {
        editNum = (EditText) findViewById(R.id.edit_num);
        btnGo = (Button) findViewById(R.id.btn_go);
        loadding = (LoadingView) findViewById(R.id.loadding);
        showText = (TextView) findViewById(R.id.show_text);

        loadding.withLoadedEmptyText("连根毛都没有 !")
                .withEmptyIco(R.mipmap.disk_file_no_data)
                .withBtnEmptyEnnable(false)
                .withErrorIco(R.mipmap.ic_chat_empty)
                .withLoadedErrorText("程序猿跑路了 !")
                .withbtnErrorText("去找回她!!!")
                .withLoadedNoNetText("你挡着信号啦")
                .withNoNetIco(R.mipmap.ic_chat_empty)
                .withbtnNoNetText("网弄好了，重试")
                .withLoadingIco(R.drawable.loading_animation)
                .withLoadingText("加载中...")
                .withOnRetryListener(new OnRetryListener() {
            @Override
            public void onRetry() {
                //传送给P层次
                presenter.sendRequest(MainActivity.this, editNum.getText().toString().trim());
            }
        }).build();

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传送给P层次
                presenter.sendRequest(MainActivity.this, editNum.getText().toString().trim());
            }
        });
        loadding.setVisibility(View.GONE);
        showText.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess(Weather weather) {
        showText.setText(weather.toString());
        loadding.setVisibility(View.GONE);
        showText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoNet() {
        loadding.setState(LoadingState.STATE_NO_NET);
        loadding.setVisibility(View.VISIBLE);
        showText.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        loadding.setState(LoadingState.STATE_ERROR);
        loadding.setVisibility(View.VISIBLE);
        showText.setVisibility(View.GONE);
    }

    @Override
    public void showLoadding() {
        loadding.setState(LoadingState.STATE_LOADING);
        loadding.setVisibility(View.VISIBLE);
        showText.setVisibility(View.GONE);
    }
}
