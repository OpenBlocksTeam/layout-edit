package com.openblocks.module.layoutedit.widgets;

import android.graphics.Canvas;

import com.openblocks.module.layoutedit.LWidget;
import com.openblocks.module.layoutedit.SizeType;
import com.openblocks.module.layoutedit.Space;

import java.util.ArrayList;

public class LHorizontalLinearLayout extends LVerticalLinearLayout {

    public LHorizontalLinearLayout(ArrayList<LWidget> childs, Space padding, Space margin, SizeType height_type, SizeType width_type, int gravity) {
        super(childs, padding, margin, height_type, width_type, gravity);
    }

    public LHorizontalLinearLayout(ArrayList<LWidget> childs, Space padding, Space margin, SizeType height_type, SizeType width_type) {
        super(childs, padding, margin, height_type, width_type);
    }

    public LHorizontalLinearLayout(ArrayList<LWidget> childs, SizeType height_type, SizeType width_type) {
        super(childs, height_type, width_type);
    }


    @Override
    public int getMinHeight(int parent_height) {
        int height_total = padding.top;

        for (LWidget child : childs) {
            height_total =
                    Math.max(
                            padding.top +

                            child.margin.top +
                            child.getHeight(parent_height) +
                            child.margin.bottom
                            ,
                            height_total
                    );
        }

        height_total += padding.bottom;

        return height_total;
    }

    @Override
    public int getMinWidth(int parent_width) {
        int width_total = padding.left;

        for (LWidget child : childs) {
            width_total +=
                    child.margin.left +
                    child.getWidth(parent_width) +
                    child.margin.right;
        }

        width_total += padding.right;

        return width_total;
    }

    void drawChilds(Canvas canvas, int x, int y, int height, int width) {
        int x_child_offset = x;

        for (LWidget child : childs) {
            x_child_offset += child.margin.left;

            child.draw(canvas, x_child_offset, y + child.margin.top, child.getHeight(height), child.getWidth(width));

            x_child_offset += child.getHeight(height) + child.margin.right;
        }
    }
}
