package com.mightted.blogsns;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mightted.blogsns.Utils.LogUtil;
import com.mightted.blogsns.Utils.MyApplication;
import com.mightted.blogsns.Utils.UserManager;
import com.mightted.blogsns.domain.entity.User;
import com.mightted.blogsns.domain.service.UserService;
import com.mightted.blogsns.http.retrofit.RetrofitClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private final int LOGIN_FAILED = 0;
    private final int LOGIN_WRONG = 1;
    private final int LOGIN_SUCCESS = 2;
    private int loginStatus = LOGIN_FAILED;
    Disposable tempD;

    private EditText userNameText,pwdText;
    private TextView hintText,forgetPwdText;
    private Button loginButton,registerButton;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(toolbar != null) {
            getSupportActionBar().setTitle("登录");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        initUi();
        initListener();

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.register_btn:
                    onRegister();
                    break;
                case R.id.login_btn:
                    onLoginTest();
                    break;
                default:
                    break;
            }
        }
    };

    private void initUi() {

        userNameText = (EditText)findViewById(R.id.username_text);
        pwdText = (EditText) findViewById(R.id.password_text);
        hintText = (TextView) findViewById(R.id.hint_text);
        forgetPwdText = (TextView) findViewById(R.id.forget_pwd_text);
        loginButton = (Button) findViewById(R.id.login_btn);
        registerButton = (Button) findViewById(R.id.register_btn);

        final String forgetText ="<a href=\"https://www.baidu.com\"><u>忘记密码</u></a>";
        if(Build.VERSION.SDK_INT >= 24) {
            forgetPwdText.setText(Html.fromHtml(forgetText,Html.FROM_HTML_MODE_LEGACY));
        } else {
            forgetPwdText.setText(Html.fromHtml(forgetText));
        }
        forgetPwdText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initListener() {
        loginButton.setOnClickListener(clickListener);
        registerButton.setOnClickListener(clickListener);
    }

    /**
     * 注册Button执行动作
     */
    private void onRegister() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://github.com/Mightted"));
        startActivity(intent);
    }


    /**
     * 登录Button执行动作
     */
    private void onLoginTest() {

        String username = userNameText.getText().toString();
        String pwd = pwdText.getText().toString();
//        String email = "邮箱未设置";
//        String icon = "https://avatars3.githubusercontent.com/u/1476232?v=3&s=460";

        onLogin(username,pwd);

        //邮箱获取方式未确定
//        int status = checkPwdStatus(username,pwd);
////        int status = onLogin(username,pwd);
//        if(status == LOGIN_SUCCESS) {
//            SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
//            editor.putString("userName",username);
//            editor.putString("email",email);
//            editor.putString("icon",icon);
//            editor.putBoolean("loginStatus",true);
//            editor.apply();
//            UserManager.getInstance().setLoginStatus(true);
//            Intent intent = new Intent();
//            setResult(RESULT_OK,intent);
//        }
//        finish();

    }


    private void onLogin(String username, final String password) {
        loginStatus = LOGIN_FAILED;
        UserService loginService = RetrofitClient.getInstance().create(UserService.class);
        loginService.getLogin(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        Toast.makeText(LoginActivity.this,"收到回复",Toast.LENGTH_SHORT).show();
                        LogUtil.i("LoginActivity",user.status);
                        if(!user.status.equals("1")) {
                            loginStatus = LOGIN_WRONG;
                            Toast.makeText(LoginActivity.this,"登录失败，请检查账号或密码是否正确",Toast.LENGTH_SHORT).show();
                        } else {
                            loginStatus = LOGIN_SUCCESS;
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).observeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        tempD = d;
                    }

                    @Override
                    public void onNext(User value) {

                        if(loginStatus == LOGIN_SUCCESS) {
                            SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
                            editor.putString("userName",value.userName);
                            editor.putString("email",value.email);
                            editor.putString("password",password);
                            editor.putString("icon",value.headImgPath);
                            editor.putBoolean("loginStatus",true);
                            editor.apply();
                            UserManager.getInstance().setLoginStatus(true);
                            Intent intent = new Intent();
                            setResult(RESULT_OK,intent);
                            finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        loginStatus = LOGIN_FAILED;
                        Toast.makeText(LoginActivity.this,"连接失败，请检查网络是否正常",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        //如果登录成功的话，解除
                        if(loginStatus == LOGIN_SUCCESS) {
                            tempD.dispose();
                            finish();
                        }

                    }
                });
    }

    private int checkPwdStatus(String username,String pwd) {

        return LOGIN_SUCCESS;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if(tempD !=null && !tempD.isDisposed()) {
            tempD.dispose();
        }

        super.onDestroy();
    }
}
