package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * �Զ���View
 * 
 * @author Aige
 * @since 2014/11/19
 */
public class StaticLayoutView extends View {
	private static final String TEXT = "This is used by widgets to control text layout. You should not need to use this class directly unless you are implementing your own widget or custom display object, or would be tempted to call Canvas.drawText() directly.";
	private TextPaint mTextPaint;// �ı��Ļ���
	private StaticLayout mStaticLayout;// �ı�����

	public StaticLayoutView(Context context) {
		this(context, null);
	}

	public StaticLayoutView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ��ʼ������
		initPaint();
	}

	/**
	 * ��ʼ������
	 */
	private void initPaint() {
		// ʵ��������
		mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextSize(50);
		mTextPaint.setColor(Color.BLACK);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mStaticLayout = new StaticLayout(TEXT, mTextPaint, canvas.getWidth(), Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
		mStaticLayout.draw(canvas);
		canvas.restore();
	}
}
