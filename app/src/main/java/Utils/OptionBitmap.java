package Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 16-8-26.
 */
public class OptionBitmap {
    public static  Bitmap getOptionBitmap(byte[] bytes){
        //首先得到图片的宽高
        int width=0;
        int hegiht=0;
        //定义用于压缩的options类对象
        BitmapFactory.Options options=new BitmapFactory.Options();

        //第一次只拿到图片的边缘，得到图片的大小
        options.inJustDecodeBounds=true;

        //一次采样
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        width=options.outWidth;
        hegiht=options.outHeight;

        //使用宽高来设置压缩比例
        options.inSampleSize=Math.max(width,hegiht)/300;

        //二次采样
        options.inJustDecodeBounds=false;

        //二次采样得到bitmap对象  压缩后的bitmap
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
    }
}
