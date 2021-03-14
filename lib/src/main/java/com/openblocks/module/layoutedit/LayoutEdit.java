package com.openblocks.module.layoutedit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.openblocks.module.layoutedit.widgets.LVerticalLinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class LayoutEdit extends View {

    // TODO: 3/12/21 Add customizable variables

    LWidget root_widget;
    
    public LayoutEdit(Context context) {
        super(context);
        
        initialize(context, null);
    }

    public LayoutEdit(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initialize(context, attrs);
    }

    public LayoutEdit(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize(context, attrs);
    }

    public LayoutEdit(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initialize(context, attrs);
    }

    
    private void initialize(Context context, AttributeSet attributeSet) {
        ArrayList<LWidget> widgets = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            LWidget widget = new LVerticalLinearLayout(
                    new ArrayList<LWidget>(),
                    new Space(16, 16, 16, 16),
                    new Space(8, 0, 0, 0),
                    SizeType.WRAP_CONTENT,
                    SizeType.WRAP_CONTENT
            );

            Random rnd = new Random();

            int color = Color.argb(255, 0, 0, rnd.nextInt(256));
            widget.setBackgroundColor(color);

            widgets.add(widget);
        }

        root_widget = new LVerticalLinearLayout(
                widgets,
                /* Padding */ new Space(8, 16, 16, 16),
                /* Margin  */ new Space(16, 16, 16, 16),
                SizeType.MATCH_PARENT,
                SizeType.MATCH_PARENT
        );

        root_widget.setBackgroundColor(0xFFF31010);

        if (attributeSet != null) {
            // TODO: 3/12/21 Create custom styleable
        }
    }

    // TODO: 3/12/21 Implement simple drag and drop


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Well, just draw the root widget I guess
        root_widget.draw(canvas, 0, 0, getHeight(), getWidth());
    }
}
