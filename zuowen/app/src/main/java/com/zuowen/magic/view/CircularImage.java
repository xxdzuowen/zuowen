package com.zuowen.magic.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.zuowen.magic.R;

public class CircularImage extends MaskedImage {

	public CircularImage(Context paramContext) {  
        super(paramContext);  
    }  
  
    public CircularImage(Context paramContext, AttributeSet paramAttributeSet) {  
        super(paramContext, paramAttributeSet);  
    }  
  
    public CircularImage(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {  
        super(paramContext, paramAttributeSet, paramInt);  
    }  
    //转换成bitmap
    public Bitmap createMask() {  
        int i = getWidth();  
        int j = getHeight();  
        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;  
        Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);  
        Canvas localCanvas = new Canvas(localBitmap);  
        Paint localPaint = new Paint(1);
        localPaint.setColor(Color.WHITE);
        float f1 = getWidth();  
        float f2 = getHeight();  
        RectF localRectF = new RectF(0.0F, 0.0F, f1, f2);  
        localCanvas.drawOval(localRectF, localPaint);  
        return localBitmap;  
    }  
    
}
