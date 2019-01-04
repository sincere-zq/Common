package com.sincere.common.image;

/**
 * 图片加载工厂
 * todo  获取图片加载实现类的对象
 */

public class ImageLoaderFactory {

    private static IImageLoader imageLoader;


    private ImageLoaderFactory() {
    }


    /**
     * 单例提供 ImageLoader对象
     *
     * @return
     */
    public static IImageLoader getImageLoader() {
        if (imageLoader == null) {
            synchronized (ImageLoaderFactory.class) {
                if (imageLoader == null) {
                    //使用 UniversalImageLoader
//                    imageLoader = new UniversalImageLoader();
//                    imageLoader = new CustomImageLoader();
//                    imageLoader = new VolleyImageLoader();

                    imageLoader = new ImageLoader();
                }
            }
        }
        return imageLoader;
    }


}
