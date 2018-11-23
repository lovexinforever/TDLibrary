<img src="screenshots/TD.png" width="200">

# TDLibrary
[ ![Download](https://api.bintray.com/packages/timding/TDLibrary/tdlibrary/images/download.svg) ](https://bintray.com/timding/TDLibrary/tdlibrary/_latestVersion)
[ ![QQ](https://img.shields.io/badge/QQ-514387454-orange.svg) ](https://timding.top)[![](https://jitpack.io/v/lovexinforever/TDLibrary.svg)](https://jitpack.io/#lovexinforever/TDLibrary)


TDLibrary 是一个 android 上面经常用到的控件库和一些工具类.

## License

TDLibrary is licensed under the Apache 2.0 License

Copyright 2018 TIMDING


## Installation

### JCenter 引用

Add TDLibrary as a dependency to your `build.gradle`
```groovy
dependencies {
    implementation 'com.tim:tdlibrary:1.0.2'
}
```

### jitpack 引用

Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

 Add the dependency
 ```
 dependencies {
	        implementation 'com.github.lovexinforever:TDLibrary:v1.0.2'
	}
 ```

## Usage

### 可控输入个数的验证码输入框

#### 效果
![验证码](https://raw.githubusercontent.com/lovexinforever/blog_img/master/ezgif.com-video-to-gif.gif)
#### 使用

在 xml 中引入控件
```
<com.tim.tdlibrary.ActivationCodeView
        android:id="@+id/icv"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_centerHorizontal="true"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:layout_marginTop="26dp"
        app:icv_et_bg_focus="@drawable/shape_icv_et_bg_focus"
        app:icv_et_bg_normal="@drawable/shape_icv_et_bg_normal"
        app:icv_et_divider_drawable="@drawable/shape_divider_identifying"
        app:icv_et_number="5"
        app:icv_text_bg_alpha="100"
        app:icv_et_text_color="#fff"
        app:icv_text_count_num="4"
        app:icv_et_width="60dp"/>
```
java 中监听输入框内容变化
```
 mActivationCodeView.setInputCompleteListener(new ActivationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                updateViewAfterInput();
            }

            @Override
            public void deleteContent() {
                updateViewAfterInput();
            }
        });
```