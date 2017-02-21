package views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.yanwei.customviewdemo.R;

import utils.MeasureUtil;


/**
 * �λ�Ч��
 * 
 * @author Aige
 * @since 2014/11/25
 */
public class DreamEffectView extends View {
	private Paint mBitmapPaint, mShaderPaint;// λͼ���ʺ�Shaderͼ�εĻ���
	private Bitmap mBitmap, darkCornerBitmap;// Դͼ��Bitmap�������Լ����İ���Bitmap
	private PorterDuffXfermode mXfermode;// ͼ�λ��ģʽ
	private int x, y;// λͼ�������
	private int screenW, screenH;// ��Ļ���

	public DreamEffectView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ��ʼ����Դ
		initRes(context);

		// ��ʼ������
		initPaint();
	}

	/**
	 * ��ʼ����Դ
	 * 
	 * @param context
	 *            ������ĸ
	 */
	private void initRes(Context context) {
		// ��ȡλͼ
		mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.gril);

		// ʵ�������ģʽ
		mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

		screenW = MeasureUtil.getScreenSize((Activity) context)[0];
		screenH = MeasureUtil.getScreenSize((Activity) context)[1];

		x = screenW / 2 - mBitmap.getWidth() / 2;
		y = screenH / 2 - mBitmap.getHeight() / 2;
	}

	/**
	 * ��ʼ������
	 */
	private void initPaint() {
		// ʵ��������
		mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		// ȥ���͡�������ɫ�����
		mBitmapPaint.setColorFilter(new ColorMatrixColorFilter(new float[] { 0.8587F, 0.2940F, -0.0927F, 0, 6.79F, 0.0821F, 0.9145F, 0.0634F, 0, 6.79F, 0.2019F, 0.1097F, 0.7483F, 0, 6.79F, 0, 0, 0, 1, 0 }));

		// ʵ����Shaderͼ�εĻ���
		mShaderPaint = new Paint();

		// ��������Դͼ�Ĵ�С���ɰ���Bitmap
		darkCornerBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);

		// ���ð���Bitmapע��Canvas
		Canvas canvas = new Canvas(darkCornerBitmap);

		// ���㾶�򽥱�뾶
		float radiu = canvas.getHeight() * (2F / 3F);

		// ʵ�������򽥱�
		RadialGradient radialGradient = new RadialGradient(canvas.getWidth() / 2F, canvas.getHeight() / 2F, radiu, new int[] { 0, 0, 0xAA000000 }, new float[] { 0F, 0.7F, 1.0F }, Shader.TileMode.CLAMP);

		// ʵ����һ������
		Matrix matrix = new Matrix();

		// ���þ��������
		matrix.setScale(canvas.getWidth() / (radiu * 2F), 1.0F);

		// ���þ����Ԥƽ��
		matrix.preTranslate(((radiu * 2F) - canvas.getWidth()) / 2F, 0);

		// ���þ���ע�뾶�򽥱�
		radialGradient.setLocalMatrix(matrix);

		// ���û���Shader
		mShaderPaint.setShader(radialGradient);

		// ���ƾ���
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mShaderPaint);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);

		// �½�ͼ��
		int sc = canvas.saveLayer(x, y, x + mBitmap.getWidth(), y + mBitmap.getHeight(), null, Canvas.ALL_SAVE_FLAG);

		// ���ƻ����ɫ
		canvas.drawColor(0xcc1c093e);

		// ���û��ģʽ
		mBitmapPaint.setXfermode(mXfermode);

		// ����λͼ
		canvas.drawBitmap(mBitmap, x, y, mBitmapPaint);

		// ��ԭ���ģʽ
		mBitmapPaint.setXfermode(null);

		// ��ԭ����
		canvas.restoreToCount(sc);

		// �������ǻ��õľ��򽥱�ͼ
		canvas.drawBitmap(darkCornerBitmap, x, y, null);
	}
}
