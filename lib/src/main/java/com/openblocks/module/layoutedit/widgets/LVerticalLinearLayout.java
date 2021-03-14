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

        int x_with_margin = x + margin.left;
        int y_with_margin = y + margin.top;

        canvas.drawRect(
                x_with_margin,
                y_with_margin,

                x_with_margin + width,
                y_with_margin + height,

                outline
        );
    }

    void drawChilds(Canvas canvas, int x, int y, int height, int width) {
        int y_child_offset = y + padding.top;

        for (LWidget child : childs) {
            y_child_offset += child.margin.top;

            child.draw(canvas, x + padding.left, y_child_offset, child.getHeight(height - padding.bottom), child.getWidth(width - padding.right));

            y_child_offset += child.getHeight(height) + child.margin.bottom;
        }
    }

    // Note: Margin and padding offset should be handled by us, not the "renderer"

    @Override
    public void draw(Canvas canvas, int x, int y, int height, int width) {
        // Draw the background color
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(getBackgroundColor());

        int x_with_margin = x + margin.left;
        int y_with_margin = y + margin.top;

        canvas.drawRect(
                x_with_margin,
                y_with_margin,

                x_with_margin + width,
                y_with_margin + height,

                p
        );

        drawOutline(
                canvas,
                x,
                y,
                height,
                width
        );

        // Make sure to apply the margin
        drawChilds(
                canvas,
                x + margin.left,
                y + margin.top,
                height - margin.bottom,
                width - margin.right
        );
    }
}
