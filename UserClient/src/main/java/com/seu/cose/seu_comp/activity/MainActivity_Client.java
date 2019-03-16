package com.seu.cose.seu_comp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.seu.cose.seu_comp.R;
import com.seu.cose.seu_comp.fragment.main.HomeFragment;

public class MainActivity_Client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_client);
        if (savedInstanceState == null) {
            // 默认定位到
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow();
        }
    }
}
