package com.seu.cose.seu_comp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seu.cose.seu_comp.Override.Base.BaseAppCompatActivity;
import com.seu.cose.seu_comp.Override.Base.NetCommunication;
import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.entity.Base.User;
import com.seu.cose.seu_comp.entity.Login.UserAccessResult;

/**
 * 注册窗口，输入新的帐号密码，确认后完成注册
 */
public class RegisterActivity_client extends BaseAppCompatActivity {

    /**
     * 功能标注ID
     */
//    private static final int REQUEST_READ_CONTACTS = 0;     //联系人读取权限

    private static final String TAG = "RegisterActivity_Client";


    /**
     * 临时帐号密码
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "213150168:BallKing961006", "213162443:windy869"
    };

    /**
     * Keep track of the sign up task to ensure we can cancel it if requested.
     * 后台注册任务队列监控，需要时取消登录
     */
    private UserRegTask mAuthTask = null;

    // UI references.
    private EditText mCardIdView;
    private EditText mNickNameView;
    private EditText mPasswordView;
    private EditText mPasswordView_confirm;
    private Button mSignUpButton;

    private View mProgressView;
    private View mRegFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set up the register form.
        mCardIdView = (EditText) findViewById(R.id.signup_cardid);
        mNickNameView = (EditText) findViewById(R.id.signup_nickname);
        mPasswordView = (EditText) findViewById(R.id.signup_password);
        mPasswordView_confirm = (EditText) findViewById(R.id.signup_password_confirm);
        mPasswordView_confirm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                // 输入法点击注册按键
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptSignUp();
                    return true;
                }
                return false;
            }
        });

        mSignUpButton = (Button) findViewById(R.id.signup_sign_up_confirm_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });

        mRegFormView = findViewById(R.id.signup_input_form);
        mProgressView = findViewById(R.id.signup_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the register form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual register attempt is made.
     */
    private void attemptSignUp() {
        //后台正在请求注册
        if (mAuthTask != null) {
            return;
        }

        //重置错误信息
        mCardIdView.setError(null);
        mNickNameView.setError(null);
        mPasswordView.setError(null);
        mPasswordView_confirm.setError(null);

        //获取用户名、密码
        String cardId = mCardIdView.getText().toString();
        String nickName = mNickNameView.getText().toString();
        String password = mPasswordView.getText().toString();
        String password_confirm = mPasswordView_confirm.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 检查密码合法性
        if ((!TextUtils.isEmpty(password) && !isPasswordValid(password))) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if ((!TextUtils.isEmpty(password_confirm) && !isPasswordValid(password_confirm))) {
            mPasswordView_confirm.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // 检查一卡通号合法性
        if (TextUtils.isEmpty(nickName)) {
            mNickNameView.setError(getString(R.string.error_cardid_required));
            focusView = mNickNameView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_cardid));
            focusView = mPasswordView;
            cancel = true;
        }

        // 检查用户名合法性
        if (!isNickNameValid(nickName)) {
            mNickNameView.setError(getString(R.string.error_invalid_nickname));
            focusView = mNickNameView;
            cancel = true;
        }

        //检查两次密码是否一致
        if (!password.equals(password_confirm)) {
            mPasswordView_confirm.setError(getString(R.string.error_different_password));
            focusView = mPasswordView_confirm;
            cancel = true;
        }

        if (cancel) {
            // 定位到focusVie6
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user register attempt.
            showProgress(true);

            // 新建监听反射以更新UI
            mAuthTask = new UserRegTask(cardId, nickName, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isCardIdValid(String cardId) {
        boolean isValid = (cardId.length() == 9) ? true : false;
        for (char bit : cardId.toCharArray()){
            if (bit < 48 || bit > 57){
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private boolean isNickNameValid(String username) {
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

        mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mRegFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
     * Represents an asynchronous registration task used to authenticate
     * the user.
     * AsyncTask<Params, Progress, Result>
     * 1.
     */
    public class UserRegTask extends AsyncTask<Void, Void, Boolean> {

        private final String mCardId;
        private final String mNickname;
        private final String mPassword;

        UserRegTask(String cardid, String nickname, String password) {
            this.mCardId = cardid;
            this.mNickname = (nickname.isEmpty()) ? "" : nickname;
            this.mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            User user = new User();
            user.setCardid(mCardId);
            user.setNickname(mNickname);
            user.setPassword(mPassword);

            UserAccessResult result = NetCommunication.post(user, getString(R.string.server_host_url) + "/register", new UserAccessResult());

            if (result != null && result.getResult()) {
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
                // 结束注册窗口，返回登录界面
                Toast.makeText(getApplicationContext(), getString(R.string.toast_hint_signup_success), Toast.LENGTH_LONG).show();
                finish();
            }else{
                mPasswordView_confirm.setError(getString(R.string.error_signup_failed));
                mPasswordView_confirm.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
