package com.seu.cose.seu_comp.Override.Base;

import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.seu.cose.seu_comp.entity.Base.AccessResult;
import com.seu.cose.seu_comp.entity.Login.UserAccessResult;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetCommunication {

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T extends AccessResult<? extends Object>> T post(Object params, String url, T ret){
        //TODO: 使用OkHttp框架进行网络通信

        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //添加Post请求体内容
        String postRequestBody = JSON.toJSONString(params);

        /*
         * 另一种设置方式：直接添加参数
         * 代码可能很长，且一般是将Bean对象直接转换成Map，不建议使用
         * Example:
         * RequestBody requestBody = new FormBody.Builder()
                .add("username", mUsername)
                .add("password", mPassword)
                .build();
         */

        //创建请求对象，添加请求地址和post参数
        Request request = new Request.Builder()
                .url(url)
//                .addHeader("content-type", "application/json;charset:utf-8")
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postRequestBody))
                .build();

        // 初始化返回对象
        try {
            //创建调用对象，重写回调函数
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()){
                // success
                String json = response.body().string();
                ret = (T) JSON.parseObject(json, ret.getClass());
                response.body().close();
            }
            else {
                response.body().close();
                throw new IOException("Unexpected code " + response);
            }
        } catch (NullPointerException | IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return ret;
        }
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T extends AccessResult<? extends Object>> T post_NewThread
            (Object params, String url, Callback callback, T ret) {
        //TODO: 使用OkHttp框架进行网络通信

        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //添加Post请求体内容
        String postRequestBody = JSON.toJSONString(params);

        //创建请求对象，添加请求地址和post参数
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postRequestBody))
                .build();

        try {
            //创建调用对象，重写回调函数
            okHttpClient.newCall(request).enqueue(callback);

        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return ret;
        }
    }

}
