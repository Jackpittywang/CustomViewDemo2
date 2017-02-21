package views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * PathEffect
 * 
 * @author Aige
 * @since 2014/11/23
 */
@SuppressLint({ "NewApi", "DrawAllocation" })
public class PathEffectView extends View {
	private float mPhase;// ƫ��ֵ
	private Paint mPaint;// ���ʶ���
	private Path mPath;// ·������
	private PathEffect[] mEffects;// ·��Ч������

	public PathEffectView(Context context, AttributeSet attrs) {
		super(context, attrs);

		/*
		 * ʵ�������ʲ���������
		 */
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(5);
		mPaint.setColor(Color.DKGRAY);

		// ʵ����·��
		mPath = new Path();

		// ����·�������
		mPath.moveTo(0, 0);

		// ����·���ĸ�����
		for (int i = 0; i <= 30; i++) {
			mPath.lineTo(i * 35, (float) (Math.random() * 100));
		}

		// ����·��Ч������
		mEffects = new PathEffect[7];
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		/*
		 * ʵ����������Ч
		 */
		mEffects[0] = null;
		mEffects[1] = new CornerPathEffect(10);
		mEffects[2] = new DiscretePathEffect(1.0F, 5.0F);
		mEffects[3] = new DashPathEffect(new float[] { 20, 10, 50, 5, 100, 30, 10, 5 }, mPhase);
		Path path = new Path();
		path.addCircle(0, 0, 3, Direction.CCW);
		mEffects[4] = new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE);
		mEffects[5] = new ComposePathEffect(mEffects[2], mEffects[4]);
		mEffects[6] = new SumPathEffect(mEffects[4], mEffects[3]);

		/*
		 * ����·��
		 */
		for (int i = 0; i < mEffects.length; i++) {
			mPaint.setPathEffect(mEffects[i]);
			canvas.drawPath(mPath, mPaint);

			// ÿ����һ������������ƽ��250������
			canvas.translate(0, 250);
		}

		// ˢ��ƫ��ֵ���ػ���ͼʵ�ֶ���Ч��
		mPhase += 1;
		invalidate();
	}
}
