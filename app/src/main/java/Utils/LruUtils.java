package Utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Administrator on 16-8-26.
 */
public class LruUtils {

    LruCache<String,Bitmap> lruCache;

    //初始化lrucashe
    public LruCache initLru(){
        int maxSize=4*1024*1024; //设置最大缓存控件为4M
       return lruCache=new LruCache<String, Bitmap>(maxSize);
    }

    //从缓存里取图片
    public Bitmap getimageBitmap(String url) {
        if (lruCache != null) {
            return lruCache.get(url);
        }
            return null;
    }

    //将图片存入缓存
    public void saveImageBitmap(String url,Bitmap bitmap){
        if (getimageBitmap(url)==null){
            lruCache.put(url,bitmap);
        }
    }

    //从缓存中移除图片
    public void deleteImageBitmap(String url){
        if (getimageBitmap(url)!=null){
            lruCache.remove(url);
        }
    }
}
