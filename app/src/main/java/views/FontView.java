package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * �Զ���View
 * 
 * @author Aige
 * @since 2014/11/19
 */
public class FontView extends View {
	private static final String TEXT = "ap����Φ�\n�§ҧ��Ψ�";
	private Paint textPaint, linePaint;// �ı��Ļ��ʺ������ߵĻ���

	private int baseX, baseY;// Baseline���Ƶ�XY����

	public FontView(Context context) {
		this(context, null);
	}

	public FontView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ��ʼ������
		initPaint(context);
	}

	/**
	 * ��ʼ������
	 */
	private void initPaint(Context context) {
		// ʵ��������
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setTextSize(70);
		textPaint.setColor(Color.BLACK);

		linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		linePaint.setStyle(Paint.Style.STROKE);
		linePaint.setStrokeWidth(1);
		linePaint.setColor(Color.RED);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// ����Baseline���Ƶ����X������
		baseX = (int) (canvas.getWidth() / 2 - textPaint.measureText(TEXT) / 2);

		// ����Baseline���Ƶ�Y����
		baseY = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));

		canvas.drawText(TEXT, baseX, baseY, textPaint);

		// Ϊ�˱�����������ڻ������Ĵ�����һ������
		canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, linePaint);
	}
}
