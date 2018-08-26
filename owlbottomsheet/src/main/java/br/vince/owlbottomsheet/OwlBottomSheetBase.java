package br.vince.owlbottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * Classe criada por Guilherme Ramos em Agosto 2018
 * Contato: +55 (11) 974049050
 */
class OwlBottomSheetBase extends FrameLayout {

    MaterialCardView bottomSheet;
    AppCompatImageView icon;
    ViewGroup contentView;
    View scrimView;
    private View mActivityView;
    OnClickInterceptor interceptor;

    int ANIM_DURATION = 350;
    static boolean isExpanded = false;
    private AnimationBuilder animationBuilder;

    public OwlBottomSheetBase(@NonNull final Context context) {
        super(context);
        init();
    }

    public OwlBottomSheetBase(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OwlBottomSheetBase(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.owl_bottom_sheet, this, true);

        scrimView = getChildAt(0);

        bottomSheet = (MaterialCardView) getChildAt(1);

        icon = (AppCompatImageView) ((ViewGroup) getChildAt(1)).getChildAt(0);

        contentView = (ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(1);

        setupViews();
    }

    private void setupViews() {
        bottomSheet.setOnClickListener(v -> expand());
        contentView.setVisibility(View.GONE);
        scrimView.setVisibility(View.GONE);
    }

    void collapse() {
        if (!isExpanded)
            return;

        initAnimBuilder();

        final ValueAnimator widthMotion = ValueAnimator.ofFloat(0, 1);
        widthMotion.setDuration(5 * ANIM_DURATION / 6); // milliseconds
        widthMotion.setStartDelay((ANIM_DURATION / 3));
        widthMotion.setInterpolator(new AccelerateDecelerateInterpolator());

        final ValueAnimator heightMotion = ValueAnimator.ofFloat(0, 1);
        heightMotion.setDuration(ANIM_DURATION); // milliseconds
        heightMotion.setInterpolator(new FastOutSlowInInterpolator());

        widthMotion.addUpdateListener(this::expandXAnimation);


        heightMotion.addUpdateListener(this::expandYAnimation);

        widthMotion.start();
        heightMotion.start();

        contentView.setAlpha(1);
        contentView.setVisibility(View.VISIBLE);

        scrimView.setAlpha(1);
        scrimView.setVisibility(View.VISIBLE);

        icon.setAlpha(0f);
        icon.setVisibility(View.VISIBLE);

        contentView.animate().alpha(0).setDuration(ANIM_DURATION / 2)
                .setStartDelay(0)
                .withEndAction(() -> contentView.setVisibility(View.GONE));

        scrimView.animate().alpha(0).setDuration(ANIM_DURATION / 2)
                .setStartDelay(ANIM_DURATION / 2)
                .withEndAction(() -> {
                    scrimView.setVisibility(View.GONE);
                    if (interceptor != null)
                        interceptor.onCollapseBottomSheet();
                });

        icon.animate().alpha(1).setStartDelay(ANIM_DURATION / 2)
                .setDuration(ANIM_DURATION / 2).start();

        bottomSheet.animate().translationY(0)
                .setStartDelay(0).setDuration(ANIM_DURATION).start();

        bottomSheet.animate().translationX(0)
                .setStartDelay(ANIM_DURATION / 2)
                .setDuration(ANIM_DURATION / 2).start();

        isExpanded = false;
    }

    void expand() {
        if (isExpanded)
            return;

        initAnimBuilder();

        final ValueAnimator widthMotion = ValueAnimator.ofFloat(1, 0);
        widthMotion.setDuration(ANIM_DURATION / 3); // milliseconds
        widthMotion.setInterpolator(new DecelerateInterpolator());

        final ValueAnimator heightMotion = ValueAnimator.ofFloat(1, 0);
        heightMotion.setDuration(ANIM_DURATION); // milliseconds
        heightMotion.setInterpolator(new FastOutSlowInInterpolator());

        widthMotion.addUpdateListener(this::expandXAnimation);

        heightMotion.addUpdateListener(this::expandYAnimation);

        widthMotion.start();
        heightMotion.start();

        contentView.setAlpha(0);
        contentView.setVisibility(View.VISIBLE);

        scrimView.setAlpha(0);
        scrimView.setVisibility(View.VISIBLE);

        icon.setAlpha(1f);

        contentView.animate().alpha(1).setStartDelay(ANIM_DURATION / 2).setDuration(ANIM_DURATION / 2).start();
        scrimView.animate().setStartDelay(0).alpha(1).setDuration(ANIM_DURATION / 3).start();

        icon.animate().alpha(0).setDuration(ANIM_DURATION / 3)
                .setStartDelay(0)
                .withEndAction(() -> {
                    icon.setVisibility(View.GONE);
                    if (interceptor != null)
                        interceptor.onExpandBottomSheet();
                });

        bottomSheet.animate().translationY(-dp2px(48))
                .setStartDelay(0).setDuration(ANIM_DURATION).start();
        bottomSheet.animate().translationX(-dp2px(42))
                .setStartDelay(0)
                .setDuration(ANIM_DURATION / 3).start();

        isExpanded = true;
    }

    private void expandXAnimation(final ValueAnimator animation) {

        float fraction = (float) animation.getAnimatedValue();

        animationBuilder.getCardView()
                .setRadius(interpolate(animationBuilder.getFromRadius(),
                        animationBuilder.getToRadius(), fraction));

        animationBuilder.getCardView().getLayoutParams().width =
                (int) ((animationBuilder.getToWidth() -
                        animationBuilder.getFromWidth()) * (1 - fraction) +
                        animationBuilder.getFromWidth());

        animationBuilder.getCardView().requestLayout();
    }

    private void expandYAnimation(final ValueAnimator animation) {

        float fraction = (float) animation.getAnimatedValue();

        animationBuilder.getCardView().getLayoutParams().height = (int) ((animationBuilder.getToHeight() -
                animationBuilder.getFromHeight()) * (1 - fraction) +
                animationBuilder.getFromHeight());

        animationBuilder.getCardView().requestLayout();
    }

    private float interpolate(float from, float to, float fraction) {
        return ((from - to) * fraction) + to;
    }

    private void initAnimBuilder() {
        if (animationBuilder != null)
            return;

        animationBuilder = new AnimationBuilder();

        animationBuilder.setCardView(bottomSheet);
        animationBuilder.setFromHeight(bottomSheet.getHeight());
        animationBuilder.setFromRadius(bottomSheet.getRadius());
        animationBuilder.setFromWidth(bottomSheet.getWidth());
        animationBuilder.setFromX(bottomSheet.getX());
        animationBuilder.setFromY(bottomSheet.getY());
        animationBuilder.setToHeight(getContentView().getHeight());
        animationBuilder.setToWidth(getContentView().getWidth());
        animationBuilder.setToRadius(0);
        animationBuilder.setToX(0);
        animationBuilder.setToY(0);
    }

    void setActivityView(AppCompatActivity activity) {
        mActivityView = activity.getWindow().getDecorView().findViewById(android.R.id.content) != null ?
                activity.getWindow().getDecorView().findViewById(android.R.id.content) :
                findViewById(android.R.id.content).getRootView();
    }

    private int dp2px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private View getContentView() {
        return mActivityView;
    }
}
