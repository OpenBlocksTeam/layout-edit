package com.openblocks.module.layoutedit.widgets;

import android.graphics.Canvas;

import com.openblocks.module.layoutedit.LWidget;
import com.openblocks.module.layoutedit.Space;

import java.util.ArrayList;

public class LHorizontalLinearLayout extends LVerticalLinearLayout {

    public LHorizontalLinearLayout(ArrayList<LWidget> childs, Space padding, Space margin) {
        super(childs, padding, margin);
    }

    @Override
    public int getMinWidth() {
        int width_total = margin.left + padding.left;

        for (LWidget child : childs) {
            width_total +=
                    child.margin.left + child.padding.left +
                    child.getWidth() +
                    child.margin.right + child.padding.right;
        }

        width_total += margin.right + padding.right;

        return width_total;
    }

    @Override
    public int getMinHeight() {
        int height_total =
                margin.top + padding.top
                 + margin.bottom + padding.bottom;

        for (LWidget child : childs) {
            height_total =
                    Math.max(
                            child.margin.top + child.padding.top +
                            child.getHeight() +
                            child.margin.bottom + child.padding.bottom
                            ,
                            height_total
                    );
        }

        height_total += margin.bottom + padding.bottom;

        return height_total;
    }

    @Override
    void drawChilds(Canvas canvas, int x, int y, int height, int width) {
        int x_child_offset = x + margin.left + padding.left;

        for (LWidget child : childs) {
            x_child_offset += child.margin.left + child.padding.left;

            child.draw(canvas, x_child_offset, y, height, width);

            x_child_offset += child.getWidth() + child.margin.right + child.margin.right;
        }
    }
}
