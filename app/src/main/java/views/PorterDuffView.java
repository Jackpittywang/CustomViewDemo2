package views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import bo.PorterDuffBO;
import utils.MeasureUtil;


/**
 * ���Բ�ͬPorterDuffģʽ��View
 * 
 * @author Aige
 * @since 2014/11/19
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PorterDuffView extends View {
	/*
	 * PorterDuffģʽ����
	 * �����ڴ˸��Ĳ�ͬ��ģʽ����
	 */
	private static final PorterDuff.Mode MODE = PorterDuff.Mode.XOR;

	private static final int RECT_SIZE_SMALL = 400;// �����Ϸ�ʾ�����������εĳߴ��С
	private static final int RECT_SIZE_BIG = 800;// �м���Խ��������εĳߴ��С

	private Paint mPaint;// ����

	private PorterDuffBO porterDuffBO;// PorterDuffView���ҵ�����
	private PorterDuffXfermode porterDuffXfermode;// ͼ�λ��ģʽ

	private int screenW, screenH;// ��Ļ�ߴ�
	private int s_l, s_t;// ���Ϸ������ε�ԭ������
	private int d_l, d_t;// ���Ϸ������ε�ԭ������
	private int rectX, rectY;// �м������ε�ԭ������

	public PorterDuffView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ʵ�������ʲ����ÿ����
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		// ʵ����ҵ�����
		porterDuffBO = new PorterDuffBO();

		// ʵ�������ģʽ
		porterDuffXfermode = new PorterDuffXfermode(MODE);

		// ��������
		calu(context);
	}

	/**
	 * ��������
	 * 
	 * @param context
	 *            �����Ļ�������
	 */
	private void calu(Context context) {
		// ��ȡ������Ļ�ߴ������
		int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

		// ��ȡ��Ļ�ߴ�
		screenW = screenSize[0];
		screenH = screenSize[1];

		// �������Ϸ�������ԭ������
		s_l = 0;
		s_t = 0;

		// �������Ϸ�������ԭ������
		d_l = screenW - RECT_SIZE_SMALL;
		d_t = 0;

		// �����м䷽������ԭ������
		rectX = screenW / 2 - RECT_SIZE_BIG / 2;
		rectY = RECT_SIZE_SMALL + (screenH - RECT_SIZE_SMALL) / 2 - RECT_SIZE_BIG / 2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// ���û�����ɫΪ��ɫ�Ա����Ǹ��õع۲�
		canvas.drawColor(Color.BLACK);

		// ����ҵ�����ߴ�ֵ�������������Ϸ��Ľ��䷽��
		porterDuffBO.setSize(RECT_SIZE_SMALL);

		/*
		 * ���������Ϸ�����������
		 * ������ߵĵ�Ϊsrc�ұߵ�Ϊdis
		 */
		canvas.drawBitmap(porterDuffBO.initSrcBitmap(), s_l, s_t, mPaint);
		canvas.drawBitmap(porterDuffBO.initDisBitmap(), d_l, d_t, mPaint);

		/*
		 * �����Ʋ������浽�µ�ͼ�㣨���ٷ���˵��Ӧ�����������棩���ǽ���1/3��ѧϰ��Canvas��ȫ���÷��������follow me
		 */
		int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

		// ��������ҵ�����ߴ�ֵ���������м�Ľ��䷽��
		porterDuffBO.setSize(RECT_SIZE_BIG);

		// �Ȼ���disĿ��ͼ
		canvas.drawBitmap(porterDuffBO.initDisBitmap(), rectX, rectY, mPaint);

		// ���û��ģʽ
		mPaint.setXfermode(porterDuffXfermode);

		// �ٻ���srcԴͼ
		canvas.drawBitmap(porterDuffBO.initSrcBitmap(), rectX, rectY, mPaint);

		// ��ԭ���ģʽ
		mPaint.setXfermode(null);

		// ��ԭ����
		canvas.restoreToCount(sc);
	}
}
