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
import android.util.Log;
import android.view.View;

import com.example.yanwei.customviewdemo.R;

import utils.MeasureUtil;


/**
 * ShaderView
 * 
 * @author Aige
 * @since 2014/11/24
 */
public class MatrixView extends View {
	private static final int RECT_SIZE = 400;// ���γߴ��һ��

	private Paint mPaint;// ����

	private int left, top, right, bottom;// ����������������
	private int screenX, screenY;

	public MatrixView(Context context, AttributeSet attrs) {
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
		BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

		// ʵ��һ���������
		Matrix matrix = new Matrix();

		/*
		 * �½�һ��9����λ���ȵĸ�������
		 * ��Ϊ���ǵ�Matrix������9����λ���Ķ԰�
		 */
		float[] fs = new float[9];

		// ����matrix�л�ȡ���ĸ�������װ�ؽ����ǵ�fs��
		matrix.getValues(fs);
		Log.d("Aige", Arrays.toString(fs));// ��������£�

		// ���þ���任
		matrix.setSkew(0.1F, 0F);

		matrix.getValues(fs);
		Log.d("Aige", Arrays.toString(fs));// ��������£�

		matrix.preRotate(5);

		matrix.getValues(fs);
		Log.d("Aige", Arrays.toString(fs));// ��������£�

		matrix.postTranslate(100, 200);

		matrix.getValues(fs);
		Log.d("Aige", Arrays.toString(fs));// ��������£�

		// ����Shader�ı任����
		bitmapShader.setLocalMatrix(matrix);

		// ������ɫ��
		mPaint.setShader(bitmapShader);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// ���ƾ���
		// canvas.drawRect(left, top, right, bottom, mPaint);
		canvas.drawRect(0, 0, screenX * 2, screenY * 2, mPaint);
	}
}
