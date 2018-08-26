package br.vince.owlbottomsheet;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * Classe criada por Guilherme Ramos em Agosto 2018
 * Contato: +55 (11) 974049050
 */
public class OwlBottomSheet extends OwlBottomSheetBase {

    public OwlBottomSheet(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OwlBottomSheet(@NonNull final Context context) {
        super(context);
    }

    public OwlBottomSheet(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Set icon to show in collapsed sheet
     * @param drawable ex R.drawable.collapse_icon
     */
    public void setIcon(@DrawableRes int drawable){
        icon.setImageResource(drawable);
    }

    /**
     * Color of bottom sheet
     * @param color ex: ContextCompat.getColor(getContext(), R.color.colorAccent);
     */
    public void setBottomSheetColor(@ColorInt int color){
        bottomSheet.setCardBackgroundColor(color);
    }

    /**
     * This will make OwlBottomSheet calculate animation from that Activity
     * @param activity
     */
    public void setActivityView(AppCompatActivity activity) {
        super.setActivityView(activity);
    }

    /**
     * Click listener interceptor from collapse and expand click
     * @param interceptor
     */
    public void setOnClickInterceptor(OnClickInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    /**
     * Get animation duration
     * @return
     */
    public int getDuration() {
        return ANIM_DURATION;
    }

    /**
     * Set animation duration. For better results, it's recommended 250~350
     */
    public void setDuration(int duration) {
        ANIM_DURATION = duration;
    }

    /**
     * Get on click interceptor listener
     * @return
     */
    public OnClickInterceptor getOnClickInterceptor() {
        return interceptor;
    }

    /**
     * Collapse bottom sheet
     */
    public void collapse() {
        super.collapse();
    }

    /**
     * Expand bottom sheet
     */
    public void expand() {
        super.expand();
    }

    /**
     * Get bottom sheet state
     * @return
     */
    public boolean isExpanded() {
        return isExpanded;
    }

    /**
     * Get view shown
     * @return
     */
    public View getContentView() {
        return contentView;
    }

    /**
     * Attach view to show in bottom sheet
     * @param view to be added to bottom sheet
     */
    public void attachContentView(View view) {
        contentView.addView(view);
    }

    /**
     * Attach view to show in bottom sheet
     * @param view layout to be inflated on bottom sheet
     */
    public void attachContentView(@LayoutRes int view) {
        View.inflate(getContext(), view, contentView);
    }

}
