package pl.edu.pwr.cookbook.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.WeakHashMap;


/**
 * Created by johniak8 on 5/3/2014.
 */
public class InnerShadowButton extends Button {

    public InnerShadowButton(Context context) {
        super(context);
        init();
    }

    public InnerShadowButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InnerShadowButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        float[] direction = new float[]{0f, -1.0f, 0.5f};
        MaskFilter filter = new EmbossMaskFilter(direction, 0.8f, 15f, 2f);
        getPaint().setMaskFilter(filter);
    }
}
