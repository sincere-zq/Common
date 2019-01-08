package com.sincere.common.image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sincere.common.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * Glide下载图片
 */

public class ImageLoader implements IImageLoader {
    private static String IMAGE_BASE_URL = "http://222.85.133.48:40001";

    /**
     * 使用picasso进行图片加载
     *
     * @param imageUrl
     * @param imageView
     */
    @Override
    public void loadImage(String imageUrl, ImageView imageView) {

        loadImage(imageUrl, imageView, R.mipmap.ic_loading_large, R.mipmap.ic_loading_large);
    }

    @Override
    public void loadCircleImage(String imageUrl, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(IMAGE_BASE_URL +imageUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_loading_large)
                .crossFade()
                .error(R.mipmap.ic_loading_large)
                .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                .into(imageView);
    }

    @Override
    public void loadRoundImage(String imageUrl, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(IMAGE_BASE_URL +imageUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_loading_large)
                .crossFade()
                .error(R.mipmap.ic_loading_large)
                .bitmapTransform(new RoundedCornersTransformation(imageView.getContext(), 15, 0))
                .into(imageView);
    }

    @Override
    public void loadImage(String imageUrl, ImageView imageView, int defaultResId, int errorId) {
        //Picasso 加载图片
        Glide.with(imageView.getContext())
                .load(IMAGE_BASE_URL + imageUrl)
                .placeholder(defaultResId)
                .error(errorId)
                .into(imageView);
    }
}
