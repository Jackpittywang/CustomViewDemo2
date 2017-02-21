package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * ����ȦȦȦ
 * 
 * @author Aige
 * @since 2014/11/25
 */
public class MultiCricleView extends View {
	private static final float STROKE_WIDTH = 1F / 256F, // ��߿��ռ��
			SPACE = 1F / 64F,// ��ԲСԲ�߶����˼��ռ��
			LINE_LENGTH = 3F / 32F, // �߶γ���ռ��
			CRICLE_LARGER_RADIU = 3F / 32F,// ��Բ�뾶
			CRICLE_SMALL_RADIU = 5F / 64F,// СԲ�뾶
			ARC_RADIU = 1F / 8F,// ���뾶
			ARC_TEXT_RADIU = 5F / 32F;// ��Χ�����ְ뾶

	private Paint strokePaint, textPaint, arcPaint;// ��߻��ʺ����ֻ���

	private int size;// �ؼ��߳�

	private float strokeWidth;// ��߿��
	private float ccX, ccY;// ����ԲԲ������
	private float largeCricleRadiu, smallCricleRadiu;// ��Բ�뾶��СԲ�뾶
	private float lineLength;// �߶γ���
	private float space;// ��ԲСԲ�߶����˼��
	private float textOffsetY;// �ı���Y��ƫ��ֵ

	public MultiCricleView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ��ʼ������
		initPaint(context);
	}

	/**
	 * ��ʼ������
	 * 
	 * @param context
	 *            Fuck
	 */
	private void initPaint(Context context) {
		/*
		 * ��ʼ����߻���
		 */
		strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		strokePaint.setStyle(Paint.Style.STROKE);
		strokePaint.setColor(Color.WHITE);
		strokePaint.setStrokeCap(Paint.Cap.ROUND);

		/*
		 * ��ʼ�����ֻ���
		 */
		textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
		textPaint.setColor(Color.WHITE);
		textPaint.setTextSize(30);
		textPaint.setTextAlign(Paint.Align.CENTER);

		textOffsetY = (textPaint.descent() + textPaint.ascent()) / 2;

		arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		arcPaint.setStrokeCap(Paint.Cap.ROUND);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// ǿ�Ƴ���һ��
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// ��ȡ�ؼ��߳�
		size = w;

		// ��������
		calculation();
	}

	/*
	 * ��������
	 */
	private void calculation() {
		// ������߿��
		strokeWidth = STROKE_WIDTH * size;

		// �����Բ�뾶
		largeCricleRadiu = size * CRICLE_LARGER_RADIU;

		// ����СԲ�뾶
		smallCricleRadiu = size * CRICLE_SMALL_RADIU;

		// �����߶γ���
		lineLength = size * LINE_LENGTH;

		// �����ԲСԲ�߶����˼��
		space = size * SPACE;

		// ��������ԲԲ������
		ccX = size / 2;
		ccY = size / 2 + size * CRICLE_LARGER_RADIU;

		// ���ò���
		setPara();
	}

	/**
	 * ���ò���
	 */
	private void setPara() {
		// ������߿��
		strokePaint.setStrokeWidth(strokeWidth);
		arcPaint.setStrokeWidth(strokeWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// ���Ʊ���
		canvas.drawColor(0xFFF29B76);

		// ��������Բ
		canvas.drawCircle(ccX, ccY, largeCricleRadiu, strokePaint);
		canvas.drawText("AigeStudio", ccX, ccY - textOffsetY, textPaint);

		// �������Ϸ�ͼ��
		drawTopLeft(canvas);

		// �������Ϸ�ͼ��
		drawTopRight(canvas);

		// �������·�ͼ��
		drawBottomLeft(canvas);

		// �����·�ͼ��
		drawBottom(canvas);

		// �������·�ͼ��
		drawBottomRight(canvas);
	}

	/**
	 * �������Ϸ�ͼ��
	 * 
	 * @param canvas
	 */
	private void drawTopLeft(Canvas canvas) {
		// ��������
		canvas.save();

		// ƽ�ƺ���ת����
		canvas.translate(ccX, ccY);
		canvas.rotate(-30);

		// ���λ�����-Ȧ-��-Ȧ
		canvas.drawLine(0, -largeCricleRadiu, 0, -lineLength * 2, strokePaint);
		canvas.drawCircle(0, -lineLength * 3, largeCricleRadiu, strokePaint);
		canvas.drawText("Apple", 0, -lineLength * 3 - textOffsetY, textPaint);

		canvas.drawLine(0, -largeCricleRadiu * 4, 0, -lineLength * 5, strokePaint);
		canvas.drawCircle(0, -lineLength * 6, largeCricleRadiu, strokePaint);
		canvas.drawText("Orange", 0, -lineLength * 6 - textOffsetY, textPaint);

		// �ͷŻ���
		canvas.restore();
	}

	/**
	 * �������Ϸ�ͼ��
	 * 
	 * @param canvas
	 */
	private void drawTopRight(Canvas canvas) {
		float cricleY = -lineLength * 3;

		// ��������
		canvas.save();

		// ƽ�ƺ���ת����
		canvas.translate(ccX, ccY);
		canvas.rotate(30);

		// ���λ�����-Ȧ
		canvas.drawLine(0, -largeCricleRadiu, 0, -lineLength * 2, strokePaint);
		canvas.drawCircle(0, cricleY, largeCricleRadiu, strokePaint);
		canvas.drawText("Tropical", 0, cricleY - textOffsetY, textPaint);

		// ������
		drawTopRightArc(canvas, cricleY);

		// �ͷŻ���
		canvas.restore();
	}

	/**
	 * �������Ͻǻ�����
	 * 
	 * @param canvas
	 * @param cricleY
	 */
	private void drawTopRightArc(Canvas canvas, float cricleY) {
		canvas.save();

		canvas.translate(0, cricleY);
		canvas.rotate(-30);

		float arcRadiu = size * ARC_RADIU;
		RectF oval = new RectF(-arcRadiu, -arcRadiu, arcRadiu, arcRadiu);
		arcPaint.setStyle(Paint.Style.FILL);
		arcPaint.setColor(0x55EC6941);
		canvas.drawArc(oval, -22.5F, -135, true, arcPaint);
		arcPaint.setStyle(Paint.Style.STROKE);
		arcPaint.setColor(Color.WHITE);
		canvas.drawArc(oval, -22.5F, -135, false, arcPaint);

		float arcTextRadiu = size * ARC_TEXT_RADIU;

		canvas.save();
		// �ѻ�����ת��������˵ķ���
		canvas.rotate(-135F / 2F);

		/*
		 * ÿ��33.75�Ƚǻ�һ���ı�
		 */
		for (float i = 0; i < 5 * 33.75F; i += 33.75F) {
			canvas.save();
			canvas.rotate(i);

			canvas.drawText("Aige", 0, -arcTextRadiu, textPaint);

			canvas.restore();
		}

		canvas.restore();

		canvas.restore();
	}

	/**
	 * �������·�ͼ��
	 * 
	 * @param canvas
	 */
	private void drawBottomLeft(Canvas canvas) {
		float lineYS = -largeCricleRadiu - space, lineYE = -lineLength * 2 - space, cricleY = -lineLength * 2 - smallCricleRadiu - space * 2;

		// ��������
		canvas.save();

		// ƽ�ƺ���ת����
		canvas.translate(ccX, ccY);
		canvas.rotate(-100);

		// ���λ���(���)��(���)-Ȧ
		canvas.drawLine(0, lineYS, 0, lineYE, strokePaint);
		canvas.drawCircle(0, cricleY, smallCricleRadiu, strokePaint);
		canvas.drawText("Banana", 0, cricleY - textOffsetY, textPaint);

		// �ͷŻ���
		canvas.restore();
	}

	/**
	 * �����·�ͼ��
	 * 
	 * @param canvas
	 */
	private void drawBottom(Canvas canvas) {
		float lineYS = -largeCricleRadiu - space, lineYE = -lineLength * 2 - space, cricleY = -lineLength * 2 - smallCricleRadiu - space * 2;

		// ��������
		canvas.save();

		// ƽ�ƺ���ת����
		canvas.translate(ccX, ccY);
		canvas.translate(ccX, ccY);
		canvas.rotate(180);

		// ���λ���(���)��(���)-Ȧ
		canvas.drawLine(0, lineYS, 0, lineYE, strokePaint);
		canvas.drawCircle(0, cricleY, smallCricleRadiu, strokePaint);
		canvas.drawText("Cucumber", 0, 0, textPaint);

		// �ͷŻ���
		canvas.restore();
	}

	/**
	 * �������·�ͼ��
	 * 
	 * @param canvas
	 */
	private void drawBottomRight(Canvas canvas) {
		float lineYS = -largeCricleRadiu - space, lineYE = -lineLength * 2 - space, cricleY = -lineLength * 2 - smallCricleRadiu - space * 2;

		// ��������
		canvas.save();

		// ƽ�ƺ���ת����
		canvas.translate(ccX, ccY);
		canvas.rotate(100);

		// ���λ���(���)��(���)-Ȧ
		canvas.drawLine(0, lineYS, 0, lineYE, strokePaint);
		canvas.drawCircle(0, cricleY, smallCricleRadiu, strokePaint);
		canvas.drawText("Vibrators", 0, cricleY - textOffsetY, textPaint);

		// �ͷŻ���
		canvas.restore();
	}
}
