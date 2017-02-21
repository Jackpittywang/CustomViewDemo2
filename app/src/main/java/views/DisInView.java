package views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.example.yanwei.customviewdemo.R;

import utils.MeasureUtil;

/**
 * ����DisInģʽ��View
 * 
 * @author Aige
 * @since 2014/11/17
 */
public class DisInView extends View {
	private Paint mPaint;// ����
	private Bitmap bitmapDis, bitmapSrc;// λͼ
	private PorterDuffXfermode porterDuffXfermode;// ͼ�λ��ģʽ

	private int x, y;// λͼ����ʱ���Ͻǵ��������
	private int screenW, screenH;// ��Ļ�ߴ�

	public DisInView(Context context) {
		this(context, null);
	}

	public DisInView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ʵ�������ģʽ
		porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

		// ��ʼ������
		initPaint();

		// ��ʼ����Դ
		initRes(context);
	}

	/**
	 * ��ʼ������
	 */
	private void initPaint() {
		// ʵ��������
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	/**
	 * ��ʼ����Դ
	 */
	private void initRes(Context context) {
		// ��ȡλͼ
		bitmapDis = BitmapFactory.decodeResource(context.getResources(), R.drawable.a3);
		bitmapSrc = BitmapFactory.decodeResource(context.getResources(), R.drawable.a3_mask);

		// ��ȡ������Ļ�ߴ������
		int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

		// ��ȡ��Ļ�ߴ�
		screenW = screenSize[0];
		screenH = screenSize[1];

		/*
		 * ����λͼ����ʱ���Ͻǵ�����ʹ��λ����Ļ����
		 * ��Ļ����x������ƫ��λͼһ��Ŀ��
		 * ��Ļ����y������ƫ��λͼһ��ĸ߶�
		 */
		x = screenW / 2 - bitmapDis.getWidth() / 2;
		y = screenH / 2 - bitmapDis.getHeight() / 2;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);

		/*
		 * �����Ʋ������浽�µ�ͼ�㣨���ٷ���˵��Ӧ�����������棩���ǽ���1/3��ѧϰ��Canvas��ȫ���÷��������follow me
		 */
		int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

		// �Ȼ���disĿ��ͼ
		canvas.drawBitmap(bitmapDis, x, y, mPaint);

		// ���û��ģʽ
		mPaint.setXfermode(porterDuffXfermode);

		// �ٻ���srcԴͼ
		canvas.drawBitmap(bitmapSrc, x, y, mPaint);

		// ��ԭ���ģʽ
		mPaint.setXfermode(null);

		// ��ԭ����
		canvas.restoreToCount(sc);
	}
}
