package bean.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.leslie.daylrulistview.Person;
import com.qf.leslie.daylrulistview.R;

import java.util.List;

import Utils.DownloadUtils;
import Utils.LruUtils;
import Utils.OptionBitmap;

/**
 * Created by Administrator on 16-8-26.
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    List<Person> list;
    LruUtils lruUtils;

    Handler handler=new Handler();

    public MyAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;

        lruUtils=new LruUtils();
        lruUtils.initLru();//初始化Lru缓存
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;

        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_listview,null);
            viewHolder.name= (TextView) convertView.findViewById(R.id.nameId);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imageId);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getName());
        final String imageUrl=list.get(position).getImageUrl();

        final ViewHolder finalViewHolder = viewHolder;
        //判断是否存在要加载的图片 如果有直接设置缓存   没有就下载
        if (lruUtils.getimageBitmap(imageUrl)!=null){

            Log.d("leslie","进入缓存---"+lruUtils.getimageBitmap(imageUrl));

            viewHolder.imageView.setImageBitmap(lruUtils.getimageBitmap(imageUrl));
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte[] imagebyte = DownloadUtils.getImageByte(imageUrl);
                   // final Bitmap bitmap = BitmapFactory.decodeByteArray(imagebyte, 0, imagebyte.length);

                    //使用二次采样得到压缩后的bitmap
                    final Bitmap bitmap = OptionBitmap.getOptionBitmap(imagebyte);
                    Log.d("lelsie","position---"+position);
                    Log.d("lelsie", "bitmap---" + bitmap);
                    if (bitmap != null) {
                        lruUtils.saveImageBitmap(imageUrl, bitmap);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            finalViewHolder.imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }).start();
        }

        return convertView;
    }

    class ViewHolder{
        TextView name;
        ImageView imageView;
    }
}
