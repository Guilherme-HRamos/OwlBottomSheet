package br.vince.owlsheetexample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.vince.owlbottomsheet.OwlBottomSheet;

public class MainActivity extends AppCompatActivity {

    private OwlBottomSheet mBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomSheet = findViewById(R.id.owl_bottom_sheet);

        setupView();
    }

    private void setupView() {
        //used to calculate some animations
        mBottomSheet.setActivityView(this);
        //icon to show in collapsed sheet
        mBottomSheet.setIcon(R.drawable.ic_keyboard_arrow_down_black_24dp);

        mBottomSheet.setBottomSheetColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        //view will show in bottom sheet
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
