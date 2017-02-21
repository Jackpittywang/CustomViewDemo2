package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.yanwei.customviewdemo.R;


/**
 * BrickView
 * 
 * @author Aige
 * @since 2014/11/24
 */
public class BrickView extends View {
	private Paint mFillPaint, mStrokePaint;// ������ߵĻ���
	private BitmapShader mBitmapShader;// Bitmap��ɫ��

	private float posX, posY;// �������XY����

	public BrickView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ��ʼ������
		initPaint();
	}

	/**
	 * ��ʼ������
	 */
	private void initPaint() {
		/*
		 * ʵ������߻��ʲ����ò���
		 */
		mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		mStrokePaint.setColor(0xFF000000);
		mStrokePaint.setStyle(Paint.Style.STROKE);
		mStrokePaint.setStrokeWidth(5);

		// ʵ������仭��
		mFillPaint = new Paint();

		/*
		 * ����BitmapShader
		 */
		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
		mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
		mFillPaint.setShader(mBitmapShader);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/*
		 * ��ָ�ƶ�ʱ��ȡ���������겢ˢ����ͼ
		 */
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			posX = event.getX();
			posY = event.getY();

			invalidate();
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// ���û��ʱ���ɫ
		canvas.drawColor(Color.DKGRAY);

		/*
		 * ����Բ�����
		 */
		canvas.drawCircle(posX, posY, 300, mFillPaint);
		canvas.drawCircle(posX, posY, 300, mStrokePaint);
	}
}
