package views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import utils.MeasureUtil;


@SuppressLint("NewApi")
public class ShadowView extends View {
	private static final int RECT_SIZE = 800;// ���δ�С
	private Paint mPaint;// ����

	private int left, top, right, bottom;// ����ʱ����

	public ShadowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// setShadowLayer��֧��HW
		setLayerType(LAYER_TYPE_SOFTWARE, null);

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
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.FILL);
		mPaint.setShadowLayer(10, 3, 3, Color.DKGRAY);
	}

	/**
	 * ��ʼ����Դ
	 */
	private void initRes(Context context) {
		/*
		 * ����λͼ����ʱ���Ͻǵ�����ʹ��λ����Ļ����
		 */
		left = MeasureUtil.getScreenSize((Activity) context)[0] / 2 - RECT_SIZE / 2;
		top = MeasureUtil.getScreenSize((Activity) context)[1] / 2 - RECT_SIZE / 2;
		right = MeasureUtil.getScreenSize((Activity) context)[0] / 2 + RECT_SIZE / 2;
		bottom = MeasureUtil.getScreenSize((Activity) context)[1] / 2 + RECT_SIZE / 2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// �Ȼ���λͼ
		canvas.drawRect(left, top, right, bottom, mPaint);
	}
}
