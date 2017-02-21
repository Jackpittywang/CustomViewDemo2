package views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.yanwei.customviewdemo.R;

import utils.MeasureUtil;


/**
 * BlurMaskFilter
 * 
 * @author Aige
 * @since 2014/11/23
 */
@SuppressLint("NewApi")
public class BlurMaskFilterView extends View {
	private Paint shadowPaint;//
	private Context mContext;//
	private Bitmap srcBitmap, shadowBitmap;// λͼ����Ӱλͼ

	private int x, y;// λͼ����ʱ���Ͻǵ��������

	public BlurMaskFilterView(Context context) {
		this(context, null);
	}

	public BlurMaskFilterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		// �ǵ�����ģʽΪSOFTWARE
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
		shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		shadowPaint.setColor(Color.DKGRAY);
		shadowPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
	}

	/**
	 * ��ʼ����Դ
	 */
	private void initRes(Context context) {
		// ��ȡλͼ
		srcBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);

		// ��ȡλͼ��Alphaͨ��ͼ
		shadowBitmap = srcBitmap.extractAlpha();

		/*
		 * ����λͼ����ʱ���Ͻǵ�����ʹ��λ����Ļ����
		 */
		x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - srcBitmap.getWidth() / 2;
		y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - srcBitmap.getHeight() / 2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(shadowBitmap, x, y, shadowPaint);

		canvas.drawBitmap(srcBitmap, x, y, null);
	}
}
