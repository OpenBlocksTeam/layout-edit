package com.openblocks.module.layoutedit.widgets;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.Nullable;

import com.openblocks.module.layoutedit.LWidget;
import com.openblocks.module.layoutedit.LWidgetProperty;
import com.openblocks.module.layoutedit.LWidgetPropertyType;
import com.openblocks.module.layoutedit.SizeType;
import com.openblocks.module.layoutedit.Space;

import java.util.ArrayList;

// To avoid clashing with android's library, I've decided to add L for each widgets, as an
// abbreviation of "LayoutEdit"
public class LVerticalLinearLayout extends LWidget {

    public LVerticalLinearLayout(ArrayList<LWidget> childs, Space padding, Space margin, SizeType height_type, SizeType width_type) {
        super(childs, padding, margin, height_type, width_type);
    }

    public LVerticalLinearLayout(ArrayList<LWidget> childs, SizeType height_type, SizeType width_type) {
        super(childs, height_type, width_type);
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
        Paint outline = new Paint(Paint.ANTI_ALIAS_FLAG);
        outline.setStyle(Paint.Style.STROKE);
        outline.setColor(0xFF6A6A6A);
        outline.setStrokeWidth(1f);

        canvas.drawRect(
                x,
                y,

                x + width,
                y + height,

                outline
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
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(getBackgroundColor());

        canvas.drawRect(
                x,
                y,

                x + width,
                y + height,

                p
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
