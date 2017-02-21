package views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.yanwei.customviewdemo.R;

import utils.MeasureUtil;


/**
 * �Զ���View
 * 
 * @author Aige
 * @since 2014/11/19
 */
@SuppressWarnings("deprecation")
public class CustomView extends View {
	private Paint mPaint;// ����
	private Context mContext;// �����Ļ�������
	private Bitmap bitmap;// λͼ
//	private AvoidXfermode avoidXfermode;// AVģʽ

	private int x, y, w, h;// λͼ����ʱ���Ͻǵ��������

	public CustomView(Context context) {
		this(context, null);
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;

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

		/*
		 * ���������и�0XFFFFFFFFɫ��һ���ĵط�ʱ��š�Ⱦ��ɫ
		 */
//		avoidXfermode = new AvoidXfermode(0XFFFFFFFF, 255, AvoidXfermode.Mode.TARGET);
	}

	/**
	 * ��ʼ����Դ
	 */
	private void initRes(Context context) {
		// ��ȡλͼ
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);


		x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - bitmap.getWidth() / 2;
		y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - bitmap.getHeight() / 2;
		w = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 + bitmap.getWidth() / 2;
		h = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 + bitmap.getHeight() / 2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// �Ȼ���λͼ
		canvas.drawBitmap(bitmap, x, y, mPaint);

		// ��Ⱦ��ʲôɫ���������Լ�������
		mPaint.setARGB(255, 211, 53, 243);

		// ����AVģʽ
//		mPaint.setXfermode(avoidXfermode);

		// ��һ��λͼ��Сһ���ľ���
		canvas.drawRect(x, y, w, h, mPaint);
	}
}
