package com.openblocks.module.layoutedit;

public class Space {
    public int top;
    public int bottom;
    public int right;
    public int left;

    public Space() {
        top = 0;
        bottom = 0;
        right = 0;
        left = 0;
    }

    public Space(int top, int bottom, int right, int left) {
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
    }
}
