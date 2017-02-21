package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * �ĵ�ͼView
 * 
 * @author Aige
 * @since 2014/11/23
 */
public class ECGView extends View {
	private Paint mPaint;// ����
	private Path mPath;// ·������

	private int screenW, screenH;// ��Ļ���
	private float x, y;// ·����ʼ����
	private float initScreenW;// ��Ļ��ʼ���
	private float initX;// ��ʼX������
	private float transX, moveX;// �����ƶ��ľ���

	private boolean isCanvasMove;// �����Ƿ���Ҫƽ��

	public ECGView(Context context, AttributeSet set) {
		super(context, set);

		/*
		 * ʵ�������ʲ���������
		 */
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		mPaint.setColor(Color.GREEN);
		mPaint.setStrokeWidth(5);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setShadowLayer(7, 0, 0, Color.GREEN);

		mPath = new Path();
		transX = 0;
		isCanvasMove = false;
	}

	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		/*
		 * ��ȡ��Ļ���
		 */
		screenW = w;
		screenH = h;

		/*
		 * �����������
		 */
		x = 0;
		y = (screenH / 2) + (screenH / 4) + (screenH / 10);

		// ��Ļ��ʼ���
		initScreenW = screenW;

		// ��ʼX������
		initX = ((screenW / 2) + (screenW / 4));

		moveX = (screenW / 24);

		mPath.moveTo(x, y);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);

		mPath.lineTo(x, y);

		// ����ƽ�ƻ���
		canvas.translate(-transX, 0);

		// ��������
		calCoors();

		// ����·��
		canvas.drawPath(mPath, mPaint);
		invalidate();
	}

	/**
	 * ��������
	 */
	private void calCoors() {
		if (isCanvasMove == true) {
			transX += 4;
		}

		if (x < initX) {
			x += 8;
		} else {
			if (x < initX + moveX) {
				x += 2;
				y -= 8;
			} else {
				if (x < initX + (moveX * 2)) {
					x += 2;
					y += 14;
				} else {
					if (x < initX + (moveX * 3)) {
						x += 2;
						y -= 12;
					} else {
						if (x < initX + (moveX * 4)) {
							x += 2;
							y += 6;
						} else {
							if (x < initScreenW) {
								x += 8;
							} else {
								isCanvasMove = true;
								initX = initX + initScreenW;
							}
						}
					}
				}
			}

		}
	}
}
