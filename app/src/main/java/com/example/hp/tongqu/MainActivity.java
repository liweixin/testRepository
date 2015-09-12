package com.example.hp.tongqu;

import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static String getAllInform(String Url) {
        //同去API
        StringBuffer strBuf = new StringBuffer();
        try {
            URL url = new URL(Url);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));//转码。
            String line = null;
            while ((line = reader.readLine()) != null)
                strBuf.append(line + " ");
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuf.toString();
    }
    //获取json
    static AllInf resolveActivityInf(String strPar){
        Gson gson = new Gson();
        AllInf allInf = gson.fromJson(strPar, AllInf.class);
        return allInf;
    }
    //json转换为类
    public ListView lv;
    public List<Act> dataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                .build());
        //允许主线程连接网络
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tmp=getAllInform("http://tongqu.me/index.php/api/act/type");
        AllInf allInf = resolveActivityInf(tmp);//json解析

        ImageLoader imageLoader = ImageLoader.getInstance();
        ImageView imageView;
        imageLoader.init(ImageLoaderConfiguration.createDefault(MainActivity.this));//网络图片加载初始化

        lv=(ListView)findViewById(R.id.lv);
        dataList = new ArrayList<Act>();
        dataList = Arrays.asList(allInf.getResult().getAct());

        lv.setAdapter(new UniversalAdapter<Act>(this, dataList, R.layout.cell) {
            @Override
            public void convert(UniversalViewHolder viewHolder, Act act, int position) {
                TextView name = viewHolder.getView(R.id.name);
                TextView time = viewHolder.getView(R.id.time);
                TextView location = viewHolder.getView(R.id.location);
                TextView source = viewHolder.getView(R.id.source);
                TextView menberCount = viewHolder.getView(R.id.member_count);
                TextView viewCount = viewHolder.getView(R.id.view_count);
                TextView leftTime = viewHolder.getView(R.id.left_time);
                name.setText(act.getName());
                time.setText(act.getTime());
                location.setText("地点:"+act.getLocation());
                source.setText("举办方:"+act.getSource());
                viewCount.setText("浏览:"+act.getView_count());
                menberCount.setText("报名人数:"+act.getMember_count());
                String st;
                SpannableStringBuilder style;
                if(act.getLeft_time()>=0)
                {
                    st=String.valueOf(act.getLeft_time()) + "天后结束";
                    style=new SpannableStringBuilder(st);
                    style.setSpan(new ForegroundColorSpan(Color.RED), 0, String.valueOf(act.getLeft_time()).length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                }
                else
                {
                    st="活动已结束";
                    style=new SpannableStringBuilder(st);
                    style.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                }
                leftTime.setText(style);
                viewHolder.setImageUrl(R.id.image_view, act.getPoster());
            }
        });//绘制界面
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
