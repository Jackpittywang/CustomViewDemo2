package views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import utils.MeasureUtil;


/**
 * EmbossMaskFilter
 * 
 * @author Aige
 * @since 2014/11/23
 */
@SuppressLint("NewApi")
public class EmbossMaskFilterView extends View {
	private static final int H_COUNT = 2, V_COUNT = 4;// ˮƽ�ʹ�ֱ�и���
	private Paint mPaint;// ����
	private PointF[] mPointFs;// �洢�����ɿ�����������ĵ�

	private int width, height;// �����ɿ������
	private float coorY;// �����ɿ�������Y������ֵ

	public EmbossMaskFilterView(Context context) {
		this(context, null);
	}

	public EmbossMaskFilterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// ��ʹ��Ӳ������
		setLayerType(LAYER_TYPE_SOFTWARE, null);

		// ��ʼ������
		initPaint();

		// �������
		cal(context);
	}

	/**
	 * ��ʼ������
	 */
	private void initPaint() {
		// ʵ��������
		mPaint = new Paint();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(0xFF603811);

		// ���û��������˾�
		mPaint.setMaskFilter(new EmbossMaskFilter(new float[] { 1, 1, 1F }, 0.1F, 10F, 20F));
	}

	/**
	 * �������
	 */
	private void cal(Context context) {
		int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

		width = screenSize[0] / H_COUNT;
		height = screenSize[1] / V_COUNT;

		int count = V_COUNT * H_COUNT;

		mPointFs = new PointF[count];
		for (int i = 0; i < count; i++) {
			if (i % 2 == 0) {
				coorY = i * height / 2F;
				mPointFs[i] = new PointF(0, coorY);
			} else {
				mPointFs[i] = new PointF(width, coorY);
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.GRAY);

		// ������
		for (int i = 0; i < V_COUNT * H_COUNT; i++) {
			canvas.drawRect(mPointFs[i].x, mPointFs[i].y, mPointFs[i].x + width, mPointFs[i].y + height, mPaint);
		}
	}
}
