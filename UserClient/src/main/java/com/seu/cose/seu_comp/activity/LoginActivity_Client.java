package com.seu.cose.seu_comp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.seu.cose.seu_comp.R;

//import static android.Manifest.permission.READ_CONTACTS;

/**
 * 登录窗口，输入在系统中已有的帐号密码，通过验证后进入系统
 */
public class LoginActivity_Client extends AppCompatActivity {

    /**
     * 功能标注ID
     */
//    private static final int REQUEST_READ_CONTACTS = 0;     //联系人读取权限

    private static final String TAG = "LoginActivity_Client";


    /**
     * 临时帐号密码
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "213150168:BallKing961006", "213162443:windy869"
    };

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     * 后台登录任务队列监控，需要时取消登录
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mUserNameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);
        // Set up the login form.
        mUserNameView = (AutoCompleteTextView) findViewById(R.id.username);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                // 输入法点击登录按键
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        //后台正在登录
        if (mAuthTask != null) {
            return;
        }

        //重置错误信息
        mUserNameView.setError(null);
        mPasswordView.setError(null);

        //获取用户名、密码
        String userName = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //检查密码合法性
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // 检查用户名合法性
        if (TextUtils.isEmpty(userName)) {
            mUserNameView.setError(getString(R.string.error_name_required));
            focusView = mUserNameView;
            cancel = true;
        } else if (!isUserNameValid(userName)) {
            mUserNameView.setError(getString(R.string.error_invalid_username));
            focusView = mUserNameView;
            cancel = true;
        }

        if (cancel) {
            // 定位到focusView
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            // 新建监听反射以更新UI
            mAuthTask = new UserLoginTask(userName, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isUserNameValid(String username) {
        // 用户名长度不得超过9位
        return username.length() <= 9;
    }

    private boolean isPasswordValid(String password) {
        // 密码长度必须为6-16位
        return password.length() >= 6 && password.length() <= 16;
    }

    /**
     * 显示进度条，隐藏登录框
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     * AsyncTask<Params, Progress, Result>
     * 1.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;

        UserLoginTask(String username, String password) {
            this.mUsername = username;
            this.mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            // 向服务器请求通信
            try {
                Thread.sleep(2000);

//                //使用OkHttp框架进行网络通信
//
//                //创建OkHttpClient对象
//                OkHttpClient okHttpClient = new OkHttpClient();
//                //添加Post请求体内容
//                RequestBody requestBody = new FormBody.Builder()
//                        .add("username", mUsername)
//                        .add("password", mPassword)
//                        .build();
//                //创建请求对象，添加请求地址和post参数
//                Request request = new Request.Builder()
//                        .url("http://127.0.0.1/login")
//                        .post(requestBody)
//                        .build();
//
//                //创建调用对象，重写回调函数
//                Response response = okHttpClient.newCall(request).execute();
//                if (response != null){
//                    // success
//                    Log.i(TAG, response.message());
//                    response.body().close();
//                    return true;
//                }

                // Simulations
                for (String credential : DUMMY_CREDENTIALS) {
                    String[] pieces = credential.split(":");
                    if (pieces[0].equals(mUsername)) {
                        // Account exists, return true if the password matches.
                        return pieces[1].equals(mPassword);
                    }
                }

            } catch (InterruptedException e) {
                return false;
//            } catch (IOException e){
//                Log.e(TAG, e.toString());
//                e.printStackTrace();
//                return false;
            }

            // (response == null) or network interrupted
            return false;
        }

        /*
         * onPostExecute方法可以在主线程中执行
         * 可以执行UI的更新操作
         */
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            if (success){
                // 跳转到主界面
                Intent intent = new Intent(LoginActivity_Client.this, MainActivity_Client.class);
                startActivity(intent);
                // finish();   //activity finish and terminate
            }else{
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

