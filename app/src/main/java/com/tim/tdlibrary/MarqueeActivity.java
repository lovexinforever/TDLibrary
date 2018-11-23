package com.tim.tdlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tim.tdlibrary.marquee.MarqueeTextView;

public class MarqueeActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);

        MarqueeTextView marqueeTextView = findViewById(R.id.text);

        marqueeTextView.setText("测试测试测试发的卡积分考虑到撒酒疯林科大实际付款老司机发的可使肌肤都是框架范德萨开了房觉得上路");
    }
}
