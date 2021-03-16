package com.openblocks.module.layoutedit;

import android.content.Context;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class LLinearLayoutV extends LinearLayout {

    View shadow;

    public LLinearLayoutV(Context context) {
        super(context);
        setOrientation(VERTICAL);

        shadow = LayoutInflater.from(context).inflate(R.layout.shadow, null);
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
                addView(lLinearLayoutV);

                break;

            case DragEvent.ACTION_DRAG_EXITED:
                removeView(shadow);
                break;
        }

        return true;
    }
}
