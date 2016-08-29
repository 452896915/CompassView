package com.magic.wangdongliang.compassactivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangdongliang on 16/8/26.
 */
public class CompassView extends View {
    private Paint mPaint;

    private double mRadius;
    private double mDash_short;
    private double mDash_long;
    private double mTextSize;

    private double mSeta = -Math.PI / 2;

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CompassViewStyle);
        mRadius = typedArray.getDimension(R.styleable.CompassViewStyle_radius, 0);
        mDash_short = typedArray.getDimension(R.styleable.CompassViewStyle_short_dash, 20);
        mDash_long = typedArray.getDimension(R.styleable.CompassViewStyle_long_dash, 30);

        mTextSize = typedArray.getDimension(R.styleable.CompassViewStyle_text_size, 10);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(getResources().getColor(R.color.text_bg_green_stroke));

        double width = getWidth();
        double height = getHeight();

        // choose the shortest in width, height, radius as actual radius
        double radius = (mRadius < width && mRadius < height) ? mRadius : Math.min(width, height);

        // draw the outer circle
        for (int seta = -180; seta < 180; seta++) {
            float x = (float) (width / 2 + radius * Math.cos(seta * Math.PI/180));
            float y = (float) (height / 2 + radius * Math.sin(seta * Math.PI / 180));

            if ((seta - (-180)) % 20 == 0) {
                float x2 = (float) (width / 2 + (radius + mDash_long) * Math.cos(seta * Math.PI/180));
                float y2 = (float) (height / 2 + (radius + mDash_long) * Math.sin(seta * Math.PI / 180));

                canvas.drawLine(x, y, x2, y2, mPaint);
            } else {
                float x2 = (float) (width / 2 + (radius + mDash_short) * Math.cos(seta * Math.PI/180));
                float y2 = (float) (height / 2 + (radius + mDash_short) * Math.sin(seta * Math.PI / 180));

                canvas.drawLine(x, y, x2, y2, mPaint);
            }

        }

        // draw 东西南北
        double spacing = 10;
        mPaint.setTextSize((float)mTextSize);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

        canvas.drawText("东", (float)(width / 2 + radius - spacing - mTextSize), (float)(height / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.descent - fontMetrics.leading), mPaint);
        canvas.drawText("西", (float)(width / 2 - radius + spacing), (float)(height / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.descent - fontMetrics.leading), mPaint);
        canvas.drawText("南", (float)(width /2 - mTextSize /2), (float)(height / 2 + radius - spacing - fontMetrics.descent), mPaint);
        canvas.drawText("北", (float)(width /2 - mTextSize /2), (float)(height / 2 - radius + spacing + fontMetrics.leading - fontMetrics.ascent), mPaint);

        // draw 8 triangles
        double triangle_half_bottom = 40;
        double triangle_vertical_line = 200;
        double triangle_vertical_line_2 = 300;

        Point p0 = new Point((int)(width / 2), (int)(height / 2 - triangle_vertical_line_2));
        Point p00 = new Point((int)(width / 2 + triangle_half_bottom), (int)(height / 2));
        Point p000 = new Point((int)(width / 2 - triangle_half_bottom), (int)(height / 2));

        Point p1 = new Point((int)(width / 2 + triangle_vertical_line / Math.sqrt(2)), (int)(height / 2 - triangle_vertical_line / Math.sqrt(2)));
        Point p11 = new Point((int)(width / 2 + triangle_half_bottom / Math.sqrt(2)), (int)(height / 2 + triangle_half_bottom / Math.sqrt(2)));
        Point p111 = new Point((int)(width / 2 - triangle_half_bottom / Math.sqrt(2)), (int)(height / 2 - triangle_half_bottom / Math.sqrt(2)));

        Point p2 = new Point((int)(width / 2 + triangle_vertical_line_2), (int)(height / 2));
        Point p22 = new Point((int)(width / 2), (int)(height / 2 + triangle_half_bottom));
        Point p222 = new Point((int)(width / 2), (int)(height / 2 - triangle_half_bottom));

        Point p3 = new Point((int)(width / 2 + triangle_vertical_line / Math.sqrt(2)), (int)(height / 2 + triangle_vertical_line / Math.sqrt(2)));
        Point p33 = new Point((int)(width / 2 - triangle_half_bottom / Math.sqrt(2)), (int)(height / 2 + triangle_half_bottom / Math.sqrt(2)));
        Point p333 = new Point((int)(width / 2 + triangle_half_bottom / Math.sqrt(2)), (int)(height / 2 - triangle_half_bottom / Math.sqrt(2)));

        Point p4 = new Point((int)(width / 2), (int)(height / 2 + triangle_vertical_line_2));
        Point p44 = new Point((int)(width / 2 - triangle_half_bottom), (int)(height / 2));
        Point p444 = new Point((int)(width / 2 + triangle_half_bottom), (int)(height / 2));

        Point p5 = new Point((int)(width / 2 - triangle_vertical_line / Math.sqrt(2)), (int)(height / 2 + triangle_vertical_line / Math.sqrt(2)));
        Point p55 = new Point((int)(width / 2 - triangle_half_bottom / Math.sqrt(2)), (int)(height / 2 - triangle_half_bottom / Math.sqrt(2)));
        Point p555 = new Point((int)(width / 2 + triangle_half_bottom / Math.sqrt(2)), (int)(height / 2 + triangle_half_bottom / Math.sqrt(2)));

        Point p6 = new Point((int)(width / 2 - triangle_vertical_line_2), (int)(height / 2));
        Point p66 = new Point((int)(width / 2 ), (int)(height / 2 - triangle_half_bottom));
        Point p666 = new Point((int)(width / 2), (int)(height / 2 + triangle_half_bottom));

        Point p7 = new Point((int)(width / 2 - triangle_vertical_line / Math.sqrt(2)), (int)(height / 2 - triangle_vertical_line / Math.sqrt(2)));
        Point p77 = new Point((int)(width / 2 + triangle_half_bottom / Math.sqrt(2)), (int)(height / 2 - triangle_half_bottom / Math.sqrt(2)));
        Point p777 = new Point((int)(width / 2 - triangle_half_bottom / Math.sqrt(2)), (int)(height / 2 + triangle_half_bottom / Math.sqrt(2)));

        Path path = new Path();

        path.moveTo(p0.x, p0.y);
        path.lineTo(p00.x, p00.y);
        path.lineTo(p000.x, p000.y);
        path.close();

        path.moveTo(p1.x, p1.y);
        path.lineTo(p11.x, p11.y);
        path.lineTo(p111.x, p111.y);
        path.close();

        path.moveTo(p2.x, p2.y);
        path.lineTo(p22.x, p22.y);
        path.lineTo(p222.x, p222.y);
        path.close();

        path.moveTo(p3.x, p3.y);
        path.lineTo(p33.x, p33.y);
        path.lineTo(p333.x, p333.y);
        path.close();

        path.moveTo(p4.x, p4.y);
        path.lineTo(p44.x, p44.y);
        path.lineTo(p444.x, p444.y);
        path.close();

        path.moveTo(p5.x, p5.y);
        path.lineTo(p55.x, p55.y);
        path.lineTo(p555.x, p555.y);
        path.close();

        path.moveTo(p6.x, p6.y);
        path.lineTo(p66.x, p66.y);
        path.lineTo(p666.x, p666.y);
        path.close();

        path.moveTo(p7.x, p7.y);
        path.lineTo(p77.x, p77.y);
        path.lineTo(p777.x, p777.y);
        path.close();

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, mPaint);

        // 计算指南针上的四个点
        double spacing3 = 50;

        Point b = new Point((int)(width / 2 + radius * Math.cos(mSeta)),
                (int)(height / 2 - radius * Math.sin(mSeta)));
        Point bb = new Point((int)(width / 2 + spacing3 * Math.cos(mSeta - Math.PI / 2)),
                (int)(height / 2 - spacing3 * Math.sin(mSeta - Math.PI / 2)));
        Point bbb = new Point((int)(width / 2 + spacing3 * Math.cos(Math.PI / 2 + mSeta)),
                (int)(height / 2 - spacing3 * Math.sin(Math.PI / 2 + mSeta)));

        Point b2 = new Point((int)(width / 2 - radius * Math.cos(mSeta)),
                (int)(height / 2 + radius * Math.sin(mSeta)));

        // 画南向指针
        Path path2 = new Path();
        path2.moveTo(b.x, b.y);
        path2.lineTo(bb.x, bb.y);
        path2.lineTo(bbb.x, bbb.y);
        path2.close();

        mPaint.setColor(getResources().getColor(R.color.red_a11));
        canvas.drawPath(path2, mPaint);

        // 北向指针
        Path path3 = new Path();
        path3.moveTo(b2.x, b2.y);
        path3.lineTo(bbb.x, bbb.y);
        path3.lineTo(bb.x, bb.y);
        path3.close();

        mPaint.setColor(getResources().getColor(R.color.blue_a1));
        canvas.drawPath(path3, mPaint);
    }

    // 设置南向指针和x轴的夹角，弧度表示
    public void setSouth(double seta) {
        mSeta = seta;
    }
}
