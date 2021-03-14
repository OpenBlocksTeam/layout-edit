package com.openblocks.module.layoutedit.widgets;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.openblocks.module.layoutedit.LWidget;
import com.openblocks.module.layoutedit.SizeType;
import com.openblocks.module.layoutedit.Space;

import java.util.ArrayList;

// To avoid clashing with android's library, I've decided to add L for each widgets, as an
// abbreviation of "LayoutEdit"
public class LVerticalLinearLayout extends LWidget {

    Paint background_paint;
    Paint outline_paint;

    public LVerticalLinearLayout(ArrayList<LWidget> childs, Space padding, Space margin, SizeType height_type, SizeType width_type) {
        super(childs, padding, margin, height_type, width_type);

        initialize();
    }

    public LVerticalLinearLayout(ArrayList<LWidget> childs, SizeType height_type, SizeType width_type) {
        super(childs, height_type, width_type);

        initialize();
    }

    private void initialize() {
        background_paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        outline_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outline_paint.setStyle(Paint.Style.STROKE);
        outline_paint.setColor(0xFF6A6A6A);
        outline_paint.setStrokeWidth(1f);
    }

    @Override
    public int getMinHeight(int parent_height) {
        int height_total = padding.top;

        for (LWidget child : childs) {
            height_total +=
                    child.margin.top +
                    child.getHeight(parent_height) +
                    child.margin.bottom;
        }

        height_total += padding.bottom;

        return height_total;
    }

    @Override
    public int getMinWidth(int parent_width) {
        int width_total = padding.left;

        for (LWidget child : childs) {
            width_total =
                    Math.max(
                            padding.left +

                            child.margin.left +
                            child.getWidth(parent_width) +
                            child.margin.right
                            ,
                            width_total
                    );
        }

        width_total += padding.right;

        return width_total;
    }

    void drawOutline(Canvas canvas, int x, int y, int height, int width) {
        // Draw the outline
        canvas.drawRect(
                x,
                y,

                x + width,
                y + height,

                outline_paint
        );
    }

    void drawChilds(Canvas canvas, int x, int y, int height, int width) {
        int y_child_offset = y;

        for (LWidget child : childs) {
            y_child_offset += child.margin.top;

            child.draw(canvas, x + child.margin.left, y_child_offset, child.getHeight(height), child.getWidth(width));

            y_child_offset += child.getHeight(height) + child.margin.bottom;
        }
    }

    // Note: Padding offset should be handled by us, not the "renderer"

    @Override
    public void draw(Canvas canvas, int x, int y, int height, int width) {
        // Draw the background color
        background_paint.setColor(getBackgroundColor());

        canvas.drawRect(
                x,
                y,

                x + width,
                y + height,

                background_paint
        );

        drawOutline(
                canvas,
                x,
                y,
                height,
                width
        );

        drawChilds(
                canvas,
                x + padding.left,
                y + padding.top,
                height - padding.bottom,
                width - padding.right
        );
    }
}
