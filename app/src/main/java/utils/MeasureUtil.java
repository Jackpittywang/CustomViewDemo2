package utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.DisplayMetrics;

/**
 * ��湤����
 * 
 * @author Aige
 * @since 2014/11/19
 */
public final class MeasureUtil {
	/**
	 * ��ȡ��Ļ�ߴ�
	 * 
	 * @param activity
	 *            Activity
	 * @return ��Ļ�ߴ�����ֵ���±�Ϊ0��ֵΪ���±�Ϊ1��ֵΪ��
	 */
	public static int[] getScreenSize(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return new int[] { metrics.widthPixels, metrics.heightPixels };
	}

	public static Bitmap changeColor(Bitmap src, int keyColor, int replColor, int tolerance) {
		Bitmap copy = src.copy(Bitmap.Config.ARGB_8888, true);
		int width = copy.getWidth();
		int height = copy.getHeight();
		int[] pixels = new int[width * height];
		src.getPixels(pixels, 0, width, 0, 0, width, height);
		int sR = Color.red(keyColor);
		int sG = Color.green(keyColor);
		int sB = Color.blue(keyColor);
		int tR = Color.red(replColor);
		int tG = Color.green(replColor);
		int tB = Color.blue(replColor);
		float[] hsv = new float[3];
		Color.RGBToHSV(tR, tG, tB, hsv);
		float targetHue = hsv[0];
		float targetSat = hsv[1];
		float targetVal = hsv[2];

		for (int i = 0; i < pixels.length; ++i) {
			int pixel = pixels[i];

			if (pixel == keyColor) {
				pixels[i] = replColor;
			} else {
				int pR = Color.red(pixel);
				int pG = Color.green(pixel);
				int pB = Color.blue(pixel);

				int deltaR = Math.abs(pR - sR);
				int deltaG = Math.abs(pG - sG);
				int deltaB = Math.abs(pB - sB);

				if (deltaR <= tolerance && deltaG <= tolerance && deltaB <= tolerance) {
					Color.RGBToHSV(pR, pG, pB, hsv);
					hsv[0] = targetHue;
					hsv[1] = targetSat;
					hsv[2] *= targetVal;

					int mixTrgColor = Color.HSVToColor(Color.alpha(pixel), hsv);
					pixels[i] = mixTrgColor;
				}
			}
		}

		copy.setPixels(pixels, 0, width, 0, 0, width, height);

		return copy;
	}
}
