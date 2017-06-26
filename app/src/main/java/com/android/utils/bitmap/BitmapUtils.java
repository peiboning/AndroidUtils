package com.android.utils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by peiboning on 2017/6/26.
 */

public class BitmapUtils {
    public static int calculateInSampleSize(BitmapFactory.Options options, int targrtWidth, int targetheight){
        int sampleSize = 2;

        if(null != options && targetheight > 0 && targrtWidth > 0){
            int sourceHeight = options.outHeight;
            int sourceWidth = options.outWidth;

            if(sourceHeight > targetheight || sourceWidth > targrtWidth){

                while (sourceHeight / sampleSize > targetheight && sourceWidth / sampleSize > targrtWidth){
                    sampleSize = sampleSize << 1;
                }
            }
        }

        return sampleSize;
    }

    public static Bitmap getBitmapinSize(Context context, int id, int targetHeight, int targetWidth){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), id, options);
        int insampleSize = calculateInSampleSize(options, targetHeight, targetWidth);
        options.inJustDecodeBounds = false;
        options.inSampleSize = insampleSize;
        return BitmapFactory.decodeResource(context.getResources(), id, options);
    }
}
