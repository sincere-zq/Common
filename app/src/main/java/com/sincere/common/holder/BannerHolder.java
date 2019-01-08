package com.sincere.common.holder;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.sincere.common.R;
import com.sincere.common.image.ImageLoaderFactory;

public class BannerHolder extends Holder<String> {
    private ImageView imageView;

    public BannerHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView = itemView.findViewById(R.id.img_banner);
    }

    @Override
    public void updateUI(String data) {
        ImageLoaderFactory.getImageLoader().loadImage(data, imageView);
    }
}
