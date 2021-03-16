package com.openblocks.module.layoutedit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class LLinearLayoutV extends LinearLayout {

    View shadow;

    Paint outline_paint;

    public LLinearLayoutV(Context context) {
        super(context);

        init(context);
    }

    public LLinearLayoutV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public LLinearLayoutV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public LLinearLayoutV(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);

        shadow = LayoutInflater.from(context).inflate(R.layout.shadow, null);

        outline_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outline_paint.setStyle(Paint.Style.STROKE);
        outline_paint.setColor(0xFF000000);
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_ENTERED:
                addView(shadow);
                break;

            case DragEvent.ACTION_DROP:
                removeView(shadow);

                LLinearLayoutV lLinearLayoutV = new LLinearLayoutV(getContext());
                lLinearLayoutV.setPadding(8, 8, 8, 8);
                lLinearLayoutV.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                addView(lLinearLayoutV);

                break;

            case DragEvent.ACTION_DRAG_EXITED:
                removeView(shadow);
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), outline_paint);
    }
}
