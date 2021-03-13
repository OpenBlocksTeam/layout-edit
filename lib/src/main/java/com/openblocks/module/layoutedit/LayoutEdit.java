package com.openblocks.module.layoutedit;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.openblocks.module.layoutedit.widgets.LVerticalLinearLayout;

import java.util.ArrayList;

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
        root_widget = new LVerticalLinearLayout(
                new ArrayList<LWidget>(),
                /* Padding */ new Space(0, 0, 0, 0),
                /* Margin  */ new Space(16, 16, 16, 16)
        );

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
