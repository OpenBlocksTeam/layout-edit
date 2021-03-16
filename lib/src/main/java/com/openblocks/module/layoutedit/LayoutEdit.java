package com.openblocks.module.layoutedit;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

public class LayoutEdit extends FrameLayout {
    public LayoutEdit(Context context) {
        super(context);

        init(context);
    }

    public LayoutEdit(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public LayoutEdit(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public LayoutEdit(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.editor, this);

        ViewGroup dock = findViewById(R.id.dock);
        final ViewGroup editor = findViewById(R.id.editor);

        for (int i = 0; i < dock.getChildCount(); i++) {
            View view = dock.getChildAt(i);

            view.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                    ClipData dragData = new ClipData(
                            (CharSequence) v.getTag(),
                            new String[] {
                                    ClipDescription.MIMETYPE_TEXT_PLAIN
                            },
                            item
                    );

                    DragShadowBuilder shadow = new View.DragShadowBuilder(v);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        v.startDragAndDrop(dragData, shadow, null, 0);
                    } else {
                        v.startDrag(dragData, shadow, null, 0);
                    }

                    return true;
                }
            });
        }

        // TODO: 3/16/21 this 
        
        editor.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        
                    case DragEvent.ACTION_DRAG_LOCATION:

                    case DragEvent.ACTION_DROP:

                    case DragEvent.ACTION_DRAG_EXITED:

                }

                return true;
            }
        });
    }
}
