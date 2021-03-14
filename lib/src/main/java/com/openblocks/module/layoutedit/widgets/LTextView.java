package com.openblocks.module.layoutedit.widgets;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.openblocks.module.layoutedit.LWidget;
import com.openblocks.module.layoutedit.SizeType;
import com.openblocks.module.layoutedit.Space;

import java.util.ArrayList;

public class LTextView extends LWidget {

    String text;
    Paint text_paint;

    public LTextView(String text, Space padding, Space margin, SizeType height_type, SizeType width_type) {
        super(new ArrayList<LWidget>(), padding, margin, height_type, width_type);

        text_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        text_paint.setColor(0xFFFFFFFF);
        text_paint.setTextSize(12f);

        this.text = text;
    }

    public void setTextSize(int size) {
        text_paint.setTextSize(size);
    }

    public void setTextColor(int color) {
        text_paint.setColor(color);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getMinHeight(int parent_height) {
        Rect bounds = new Rect();
        text_paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.height();
    }

    @Override
    public int getMinWidth(int parent_width) {
        return padding.left + (int) text_paint.measureText(text) + padding.right;
    }

    @Override
    public void draw(Canvas canvas, int x, int y, int height, int width) {
        canvas.drawText(text, x + padding.left, y + padding.top, text_paint);
    }
}
