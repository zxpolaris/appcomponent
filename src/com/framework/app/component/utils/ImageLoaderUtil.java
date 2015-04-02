package com.framework.app.component.utils;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * ImageLoader配置
 * 
 * @ClassName: ImageLoaderOptions.java
 * 
 * @author Xun.Zhang
 * 
 * @date 2014-12-8 下午12:59:10
 */
public class ImageLoaderUtil {

	/**
	 * ImageLoader配置参数
	 */
	private static final int MAX_IMAGE_WIDTH = 1280; // 1280px
	private static final int MAX_IMAGE_HEIGHT = 1800; // 1800px
	private static final int MAX_IMAGE_MEMORY_CACHE_SIZE = 2 * 1024 * 1024; // 2MB
	private static final int MAX_IMAGE_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
	private static final int MAX_IMAGE_DISK_FILE_COUNT = 200;
	/**
	 * 图片圆角像素
	 */
	private static final int IMAGE_CORNER_RADIUS_PIXELS = 150;

	/**
	 * 初始化ImageLoader(只在Application的OnCreate中调用一次就行，其他时候不调)
	 * 
	 * @param applicationContext
	 */
	public static void initImageLoader(Context applicationContext) {

		// ImageLoader内存缓存最大为系统可用内存数的1/8，默认为2MB
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int maxImageMemoryCacheSize = (maxMemory == 0) ? ImageLoaderUtil.MAX_IMAGE_MEMORY_CACHE_SIZE : (maxMemory / 8);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(applicationContext).memoryCache(new LruMemoryCache(maxImageMemoryCacheSize))
				.memoryCacheExtraOptions(ImageLoaderUtil.MAX_IMAGE_WIDTH, ImageLoaderUtil.MAX_IMAGE_HEIGHT).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory().diskCacheSize(ImageLoaderUtil.MAX_IMAGE_DISK_CACHE_SIZE)
				.diskCacheFileCount(ImageLoaderUtil.MAX_IMAGE_DISK_FILE_COUNT).diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();

		ImageLoader.getInstance().init(config);
	}

	/**
	 * 清楚ImageLoader的缓存
	 */
	public static void clearCache() {
		ImageLoader.getInstance().clearMemoryCache();
		ImageLoader.getInstance().clearDiskCache();
	}

	/**
	 * 显示常规的图片
	 * 
	 * @param imageResId
	 *            默认图片资源ID
	 * @return DisplayImageOptions
	 */
	public static DisplayImageOptions getDisplayImageOptions(int imageResId) {
		return getDisplayImageOptions(imageResId, false, true);
	}

	/**
	 * 显示圆形的图片
	 * 
	 * @param imageResId
	 *            默认图片资源ID
	 * @return DisplayImageOptions
	 */
	public static DisplayImageOptions getRoundDisplayImageOptions(int imageResId) {
		return getDisplayImageOptions(imageResId, true, true);
	}

	/**
	 * 不显示加载中的默认图片
	 * 
	 * @return DisplayImageOptions
	 */
	public static DisplayImageOptions getNotShowDisplayImageOptions() {
		return getDisplayImageOptions(0, false, true);
	}

	/**
	 * @param imageResId
	 *            图片资源ID
	 * @param cornerRadiusPixels
	 *            显示图片是否为圆形
	 * @param resetViewBeforeLoading
	 *            是否需要加载前重置视图
	 * @return DisplayImageOptions
	 */
	private static DisplayImageOptions getDisplayImageOptions(int imageResId, boolean cornerRadiusPixels, boolean resetViewBeforeLoading) {
		DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
		if (0 != imageResId) {
			builder.showImageOnLoading(imageResId);
			builder.showImageForEmptyUri(imageResId);
			builder.showImageForEmptyUri(imageResId);
		}
		builder.cacheInMemory(true);
		builder.cacheOnDisk(true);
		builder.bitmapConfig(Bitmap.Config.RGB_565);
		builder.imageScaleType(ImageScaleType.EXACTLY);
		if (cornerRadiusPixels) {
			builder.displayer(new RoundedBitmapDisplayer(IMAGE_CORNER_RADIUS_PIXELS));
		}
		builder.resetViewBeforeLoading(resetViewBeforeLoading);
		return builder.build();
	}

	public static String getUriFromLocalFile(File file) {
		return Uri.decode(Uri.fromFile(file).toString());
	}
}
