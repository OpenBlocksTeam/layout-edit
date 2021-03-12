package com.openblocks.module.layoutedit;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * LWidget (LayoutEdit Widget) is an abstract class used to represent a widget that can be drawn
 * and interact
 */
public abstract class LWidget {
    public abstract int getHeight();
    public abstract int getWidth();

    public void draw(Canvas canvas, int x, int y, int height, int width) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFFFF0000);

        canvas.drawRect(x, y, x + height, y + width, paint);

        paint.setColor(0xFF000000);
        paint.setTextSize(24f);
        canvas.drawText("Hello LWidget!", x + (width >> 1), y + (height >> 1), paint);
    }
}
