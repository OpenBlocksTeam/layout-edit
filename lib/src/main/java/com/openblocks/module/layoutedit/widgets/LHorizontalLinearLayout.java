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
    public void draw(Canvas canvas, int x, int y, int height, int width) {
        drawOutline(canvas, x, y, height, width);
    }
}
