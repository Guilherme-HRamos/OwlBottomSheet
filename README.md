# OwlBottomSheet

[![](https://jitpack.io/v/Guilherme-HRamos/OwlBottomSheet.svg)](https://jitpack.io/#Guilherme-HRamos/OwlBottomSheet)

<img src="https://github.com/Guilherme-HRamos/OwlBottomSheet/blob/master/ezgif.com-crop.gif"/>

## Adding the project

Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency:
```
dependencies {
	        implementation 'com.github.Guilherme-HRamos:OwlBottomSheet:1.01'
	}
```

## Usage

In your Activity layout:

```xlm

<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!-- content -->
    
    <!-- ... -->

    <!-- bottom sheet -->
    <br.vince.owlbottomsheet.OwlBottomSheet
        android:id="@+id/owl_bottom_sheet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</android.support.design.widget.CoordinatorLayout>

```

In your Activity:

```java
public class MainActivity extends AppCompatActivity {

    // the bottom sheet
    private OwlBottomSheet mBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mBottomSheet = findViewById(R.id.owl_bottom_sheet);

        setupView();
    }

    // basic usage
    private void setupView() {
    
        //used to calculate some animations. it's required
        mBottomSheet.setActivityView(this);
        
        //icon to show in collapsed sheet
        mBottomSheet.setIcon(R.drawable.ic_keyboard_arrow_down_black_24dp);

        //bottom sheet color
        mBottomSheet.setBottomSheetColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        //view shown in bottom sheet
        mBottomSheet.attachContentView(R.layout.layout_bottom_sheet_example);

        //getting close button from view shown
        mBottomSheet.getContentView().findViewById(R.id.comments_sheet_close_button)
                .setOnClickListener(v -> mBottomSheet.collapse());
    }


    // collapse bottom sheet when back button pressed
    @Override
    public void onBackPressed() {
        if (!mBottomSheet.isExpanded())
            super.onBackPressed();
        else
            mBottomSheet.collapse();
    }
}

```

## Methods:

```java

    /**
     * Set icon to show in collapsed sheet
     * @param drawable ex R.drawable.collapse_icon
     */
    public void setIcon(@DrawableRes int drawable);

    /**
     * Color of bottom sheet
     * @param color ex: ContextCompat.getColor(getContext(), R.color.colorAccent);
     */
    public void setBottomSheetColor(@ColorInt int color);

    /**
     * This will make OwlBottomSheet calculate animation from that Activity (required)
     */
    public void setActivityView(AppCompatActivity activity);

    /**
     * Click listener interceptor from collapse and expand click
     */
    public void setOnClickInterceptor(OnClickInterceptor interceptor);

    /**
     * Get animation duration
     */
    public int getDuration();

    /**
     * Set animation duration. For better results, it's recommended 250~350
     */
    public void setDuration(int duration);

    /**
     * Get on click interceptor listener
     */
    public OnClickInterceptor getOnClickInterceptor();

    /**
     * Collapse bottom sheet
     */
    public void collapse();

    /**
     * Expand bottom sheet
     */
    public void expand();

    /**
     * Get bottom sheet state
     * @return
     */
    public boolean isExpanded();

    /**
     * Get view shown
     * @return
     */
    public View getContentView();

    /**
     * Attach view to show in bottom sheet
     * @param view to be added to bottom sheet
     */
    public void attachContentView(View view);

    /**
     * Attach view to show in bottom sheet
     * @param view layout to be inflated on bottom sheet
     */
    public void attachContentView(@LayoutRes int view);

```

## Finally

This is a very simple library for usage. There is not a lot customizations yet. I did this because I needed for personal usage, and I decided to share that.
Feel free to contribute ;)

## License

```
MIT License

Copyright (c) 2018 Guilherme Ramos

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
