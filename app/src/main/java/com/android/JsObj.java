package com.android;

import android.content.ContentValues;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by peiboning on 2017/9/28.
 */

public class JsObj {
    private Context context;

    public JsObj(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void tt(){
        Toast.makeText(context, "hahahahah", Toast.LENGTH_LONG).show();
    }
}
