package com.example.nguyenlinh.rotations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Rotations and App Restarts
 * Make an app that lets the user enter a short string.
 * Have an “Add Message” button, that,when pressed, adds the message to the bottom of the screen.
 * <p>
 * When you rotate the app, make sure that the TextView text is preserved.
 * When you rotate the app, use a different layout for the prompt, EditText, and Button.
 *
 * @author nguyenlinh
 * @version 1.0
 * @since 2017-6-10
 */
public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private Button mButton;
    private ArrayList mListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button);
        mListText = new ArrayList();

        mButton.setOnClickListener(new onclick());
    }

    private class onclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String someText = String.valueOf(mEditText.getText());
            LinearLayout mainlayouts = (LinearLayout) findViewById(R.id.activity_main);

            /*
            Note 1: to programmatically add a TextView to the bottom of a vertical LinearLayout,
            first get a reference to the main layout (e.g., findViewById(R.id_your_linear_layout)), then do this:
             */
            TextView textV = new TextView(MainActivity.this);
            textV.setText(someText);
            mListText.add(someText);
            mainlayouts.addView(textV);
        }
    }

    /**
     * save data
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("textdata", mListText); // chỉ lưu dc các kiểu nguyên thuỷ
    }

    /**
     * restore data
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList restoreData = savedInstanceState.getStringArrayList("textdata");

        for (int i = 0; i < restoreData.size(); i++) {
            String someText = (String) restoreData.get(i);
            LinearLayout mainlayouts = (LinearLayout) findViewById(R.id.activity_main);
            TextView textV = new TextView(MainActivity.this);
            textV.setText(someText);
            mListText.add(someText); // phải có nếu ko chỉ save 1 lần còn lần thứ 2 sẽ ko lưu dc
            mainlayouts.addView(textV);
        }
    }
}
