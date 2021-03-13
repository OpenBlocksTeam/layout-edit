package com.openblocks.module.layoutedit;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * LWidget (LayoutEdit Widget) is an abstract class used to represent a widget that can be drawn
 * and interact
 */
public abstract class LWidget {

    public Space padding;
    public Space margin;

    public int fixed_width;
    public int fixed_height;

    public SizeType height_type;
    public SizeType width_type;

    public ArrayList<LWidget> childs;
    public ArrayList<LWidgetProperty> properties = new ArrayList<>();

    public LWidget(ArrayList<LWidget> childs, Space padding, Space margin) {
        this.childs = childs;
        this.padding = padding;
        this.margin = margin;
    }

    public LWidget(ArrayList<LWidget> childs) {
        this.childs = childs;
        this.padding = new Space(8, 8, 8, 8);
        this.margin = new Space();
    }

    public SizeType getHeightSizeType() {
        return height_type;
    }

    public SizeType getWidthSizeType() {
        return width_type;
    }

    public abstract int getMinHeight(int parent_height);
    public abstract int getMinWidth(int parent_width);

    public int getHeight(int parent_height) {
        switch (height_type) {
            case WRAP_CONTENT:
                return getMinHeight(parent_height);

            case FIXED:
                return fixed_height;

            case MATCH_PARENT:
            default:
                return parent_height;
        }
    }

    public int getWidth(int parent_width) {
        switch (width_type) {
            case WRAP_CONTENT:
                return getMinWidth(parent_width);

            case FIXED:
                return fixed_width;

            case MATCH_PARENT:
            default:
                return parent_width;
        }
    }

    /**
     * This function is used to draw the widget when It's currently selected
     * @param canvas The canvas
     * @param x The x position
     * @param y The y position
     * @param height The height
     * @param width The width
     */
    public void drawSelected(Canvas canvas, int x, int y, int height, int width) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0x557AFDFF);

        canvas.drawRect(x, y, x + height, y + width, p);

        draw(canvas, x, y, height, width);
    }

    /**
     * This function is used to casually draw the widget
     * @param canvas The canvas
     * @param x The x position
     * @param y The y position
     * @param height The height
     * @param width The width
     */
    public void draw(Canvas canvas, int x, int y, int height, int width) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFFFF0000);

        canvas.drawRect(x, y, x + height, y + width, paint);

        paint.setColor(0xFF000000);
        paint.setTextSize(24f);
        canvas.drawText("Hello " + getClass().getSimpleName() + "!", x + (width >> 1), y + (height >> 1), paint);
    }
}
