package com.seu.cose.seu_comp.layout.Menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seu.cose.seu_comp.R;

import java.net.URL;

public class MenuWindowIconView extends LinearLayout {

    private ImageView mWindowIcon;
    private TextView mWindowName;

    public MenuWindowIconView(Context context){
        this(context, null);
    }

    public MenuWindowIconView (Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.menu_windowicon, this, true);
        mWindowIcon = (ImageView) findViewById(R.id.window_icon_menu);
        mWindowName = (TextView) findViewById(R.id.window_name_menu);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MenuWindowIconView);
        if (attributes != null) {
            // 设置图标
            int iconRes = attributes.getResourceId(R.styleable.MenuWindowIconView_windowIcon, -1);
            if (iconRes != -1) {
                mWindowIcon.setImageResource(iconRes);
            }

            // 设置窗口名
            String windowNameText = attributes.getString(R.styleable.MenuWindowIconView_name);
            if (!TextUtils.isEmpty(windowNameText)) {
                mWindowName.setText(windowNameText);
                // 设置名字颜色
                int name_textColor = attributes.getColor(R.styleable.MenuWindowIconView_name_textColor, Color.BLACK);
                mWindowName.setTextColor(name_textColor);
            }
        }
    }

    public MenuWindowIconView(Context context, AttributeSet attrs, String imageUrl, String text) {
        this(context, attrs);
        setWindowIcon(imageUrl);
        setWindowName(text);
    }

    public void setClickListener(OnClickListener listener) {
        if (listener != null) {
            mWindowIcon.setOnClickListener(listener);
            mWindowName.setOnClickListener(listener);
        }
    }

    public void setWindowIcon(String imageUrl) {
        try {
            mWindowIcon.setImageURI(Uri.parse(imageUrl));
            Bitmap pngBM = BitmapFactory.decodeStream(new URL(imageUrl).openStream());
            mWindowIcon.setImageBitmap(pngBM);
        } catch (Exception e) {
            e.printStackTrace();
            mWindowIcon.setImageResource(R.drawable.ic_blankhead);
        }

    }

    public void setWindowName(String name) {
        mWindowName.setText(name);
    }

    public ImageView getWindowIcon() {
        return mWindowIcon;
    }

    public TextView getWindowName() {
        return mWindowName;
    }

}
