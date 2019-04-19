package com.seu.cose.seu_comp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seu.cose.seu_comp.Override.Base.BaseAppCompatActivity;
import com.seu.cose.seu_comp.Override.Base.NetCommunication;
import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.entity.Base.AccessResult;
import com.seu.cose.seu_comp.entity.Base.User;
import com.seu.cose.seu_comp.entity.Login.UserAccessResult;

/**
 * 登录窗口，输入在系统中已有的帐号密码，通过验证后进入系统
 */
public class LoginActivity_Client extends BaseAppCompatActivity {

    /**
     * 功能标注ID
     */
//    private static final int REQUEST_READ_CONTACTS = 0;     //联系人读取权限

    private static final String TAG = "LoginActivity_Client";

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
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUserNameView = (AutoCompleteTextView) findViewById(R.id.login_username);

        mPasswordView = (EditText) findViewById(R.id.login_password);
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

        Button mSignInButton = (Button) findViewById(R.id.login_sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button mSignUpButton = (Button) findViewById(R.id.login_sign_up_button);
        mSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // 跳转到注册窗口
                Intent intent;
//                boolean isRegActivityExist = false;
//                for (Activity act: app.activities){
//                    if (act.getClass().equals(RegisterActivity_client.class)){
//                        intent
//                        isRegActivityExist = true;
//                    }
//                }
//                if (!isRegActivityExist)
                    intent = new Intent(LoginActivity_Client.this, RegisterActivity_client.class);
                startActivity(intent);
            }
        });

        mLoginFormView = findViewById(R.id.login_input_form);
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
            mUserNameView.setError(getString(R.string.error_invalid_nickname));
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

//            Map<String, String> param = new HashMap<String, String>();
//            param.put("username", mUsername);
//            param.put("password", mPassword);
//
//            UserAccessResult result = NetCommunication.post(param, getString(R.string.server_host_url) + "/login");

            User u = new User();
            u.setCardid(mUsername);
            u.setPassword(mPassword);

            UserAccessResult result = NetCommunication.post(u, getString(R.string.server_host_url) + "/login", new UserAccessResult());

            if (result != null && result.getResult()) {
                app.setCurrentUser(result.getUser());
                return true;
            } else return false;
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
                Toast.makeText(getApplicationContext(), getString(R.string.toast_hint_login_success), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity_Client.this, MainActivity_Client.class);
                startActivity(intent);
                // finish();   //activity finish and terminate
            }else{
                mPasswordView.setError(getString(R.string.error_incorrect));
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
