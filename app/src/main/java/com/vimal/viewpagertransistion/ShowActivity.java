package com.vimal.viewpagertransistion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import miaoyongjun.pagetransformer.MagicTransformer;
import miaoyongjun.pagetransformer.TransitionEffect;

public class ShowActivity extends AppCompatActivity {

    public static TransitionEffect transitionEffect;

    String[] imageUrls = new String[]{
            "https://camo.githubusercontent.com/174cbd40e3b75da7ead763f3001f503ca38e53559abb56a53498030727194f19/68747470733a2f2f706c61792d6c682e676f6f676c6575736572636f6e74656e742e636f6d2f774a5675735f4e68585858613543767074636b715a74416a476c38584976676277676547746d4565526e6f4263516c7a6d46446652694641436470774735717544513d773732302d683331302d7277",
            "https://camo.githubusercontent.com/1dde28edd654526b84e770b292909456661eb5756d033c4414a1e45880423583/68747470733a2f2f706c61792d6c682e676f6f676c6575736572636f6e74656e742e636f6d2f5a58787471507678316951693468703673763856634778505f775f4f366b6c4c7945435f69596732315f5f31424b6e47596d4a514c61377369305a65534d78506434513d773732302d683331302d7277",
            "https://camo.githubusercontent.com/61c26dc8142619bfa7c2873b02d68bc771eccb1fd8b47bf8bb3d2ddd85bc50b0/68747470733a2f2f706c61792d6c682e676f6f676c6575736572636f6e74656e742e636f6d2f796d6661767367307973674253434e7430334c5a784d4263524c4463567a6d743031564648697a34594f66426e336f5234706e7243466e504d4b7a397263516f43773d773732302d683331302d7277",
            "https://camo.githubusercontent.com/6dafe3d6c9673cbac7c54e2d26b4db8790390d749cc3e65f2a5f9c54c0eb2c6a/68747470733a2f2f706c61792d6c682e676f6f676c6575736572636f6e74656e742e636f6d2f31654e6d456431346b58376243666158634d5348455f774168737132316d5a476d6c317771477937746c6d654c48355f625755555738544a384e6b683842665a6b4a49433d773732302d683331302d7277"};

    List<View> mData = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show);
        ViewPager viewPager = findViewById(R.id.viewPager);
        getLayoutData(mData);
        viewPager.setAdapter(new MyAdapter(mData));
        viewPager.setPageTransformer(true, MagicTransformer.getPageTransformer(transitionEffect));
        viewPager.setOffscreenPageLimit(imageUrls.length);
    }

    private void getLayoutData(List<View> data) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_item, null);
        for (String id : imageUrls) {
            data.add(view);
        }
    }

    public class MyAdapter extends PagerAdapter {

        List<View> mList = null;

        MyAdapter(List<View> list) {
            mList = list;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(ShowActivity.this).inflate(R.layout.layout_item, null);
            ImageView imageView = view.findViewById(R.id.imageView);
            Picasso.with(ShowActivity.this).load(imageUrls[position]).into(imageView);
            container.addView(view);
            return view;
        }

    }
}
