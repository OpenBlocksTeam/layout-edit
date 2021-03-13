package com.openblocks.module.layoutedit.widgets;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.openblocks.module.layoutedit.LWidget;
import com.openblocks.module.layoutedit.LWidgetProperty;
import com.openblocks.module.layoutedit.LWidgetPropertyType;
import com.openblocks.module.layoutedit.Space;

import java.util.ArrayList;

// To avoid clashing with android's library, I've decided to add L for each widgets, as an
// abbreviation of "LayoutEdit"
public class LVerticalLinearLayout extends LWidget {

    public LVerticalLinearLayout(ArrayList<LWidget> childs, Space padding, Space margin) {
        super(childs, padding, margin);
    }

    @Override
    public int getMinHeight(int parent_height) {
        int height_total = margin.top + padding.top;

        for (LWidget child : childs) {
            height_total +=
                    child.margin.top + child.padding.top +
                    child.getHeight(parent_height) +
                    child.margin.bottom + child.padding.bottom;
        }

        height_total += margin.bottom + padding.bottom;

        return height_total;
    }

    @Override
    public int getMinWidth(int parent_width) {
        int width_total =
                margin.left + padding.left
                 + margin.right + padding.right;

        for (LWidget child : childs) {
            width_total =
                    Math.max(
                            margin.left + padding.left +

                            child.margin.left + child.padding.left +
                            child.getWidth(parent_width) +
                            child.margin.right + child.padding.right
                            ,
                            width_total
                    );
        }

        width_total += margin.right + padding.right;

        return width_total;
    }

    void drawOutline(Canvas canvas, int x, int y, int height, int width) {
        // Draw the outline
        Paint outline = new Paint(Paint.ANTI_ALIAS_FLAG);
        outline.setStyle(Paint.Style.STROKE);
        outline.setColor(0xFF6A6A6A);
        outline.setStrokeWidth(1f);

        canvas.drawRect(x, y, x + width, y + height, outline);
    }

    void drawChilds(Canvas canvas, int x, int y, int height, int width) {
        int y_child_offset =
                y +
                margin.top + padding.top;

        for (LWidget child : childs) {
            y_child_offset += child.margin.top + child.padding.top;

            child.draw(canvas, x, y_child_offset, height, width);

            y_child_offset += child.getHeight(height) + child.margin.bottom + child.padding.bottom;
        }
    }

    // !CANCELLED! Note: Margin offset should be handled by the renderer, not ourselves

    @Override
    public void draw(Canvas canvas, int x, int y, int height, int width) {
        drawOutline(canvas, x, y, height, width);
        drawChilds(canvas, x, y, height, width);
    }
}
