package views;

import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.yanwei.customviewdemo.R;

import utils.MeasureUtil;


/**
 * ShaderView
 * 
 * @author Aige
 * @since 2014/11/24
 */
public class ShaderView extends View {
	private static final int RECT_SIZE = 400;// ���γߴ��һ��

	private Paint mPaint;// ����

	private int left, top, right, bottom;// ����������������
	private int screenX, screenY;

	public ShaderView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ��ȡ��Ļ�ߴ�����
		int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

		// ��ȡ��Ļ�е�����
		screenX = screenSize[0] / 2;
		screenY = screenSize[1] / 2;

		// �������������������ֵ
		left = screenX - RECT_SIZE;
		top = screenY - RECT_SIZE;
		right = screenX + RECT_SIZE;
		bottom = screenY + RECT_SIZE;

		// ʵ��������
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		// ��ȡλͼ
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);

		// ʵ����һ��Shader
		BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

		// ʵ��һ���������
		Matrix matrix = new Matrix();

		// ���þ���任
//		matrix.postTranslate(50, 50);
//		matrix.setValues(new float[]{
//				1,0,screenX,
//				0,1,screenY,
//				0,0,1
//		});

		// ����Shader�ı任����
		bitmapShader.setLocalMatrix(matrix);
		
		float[] values = new float[9];
		matrix.getValues(values);
		System.out.println(Arrays.toString(values));

		// ������ɫ��
		mPaint.setShader(bitmapShader);
		// mPaint.setShader(new LinearGradient(left, top, right - RECT_SIZE, bottom - RECT_SIZE, Color.RED, Color.YELLOW, Shader.TileMode.MIRROR));
		// mPaint.setShader(new LinearGradient(left, top, right, bottom, new int[] { Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE }, null, Shader.TileMode.MIRROR));
		// mPaint.setShader(new SweepGradient(screenX, screenY, Color.RED, Color.YELLOW));
		// mPaint.setShader(new SweepGradient(screenX, screenY, new int[] { Color.GREEN, Color.WHITE, Color.GREEN }, null));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// ���ƾ���
//		canvas.drawRect(left, top, right, bottom, mPaint);
		 canvas.drawRect(0, 0, screenX * 2, screenY * 2, mPaint);
	}
}
