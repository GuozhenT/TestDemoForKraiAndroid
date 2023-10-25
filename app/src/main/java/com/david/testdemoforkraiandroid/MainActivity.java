package com.david.testdemoforkraiandroid;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView draggableTextView;
    private float initialX, initialY;
    private View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the parent view
        parentView = findViewById(R.id.parentView);

        draggableTextView = findViewById(R.id.draggableTextView);

        draggableTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = event.getX() - initialX;
                        float dy = event.getY() - initialY;

                        // Update the TextView's position
                        draggableTextView.setX(draggableTextView.getX() + dx);
                        draggableTextView.setY(draggableTextView.getY() + dy);

                        // Calculate the maximum X and Y coordinates
                        float maxX = parentView.getWidth() - draggableTextView.getWidth();
                        float maxY = parentView.getHeight() - draggableTextView.getHeight();

                        // Check if the view is going out of bounds and adjust its position
                        if (draggableTextView.getX() < 0) {
                            draggableTextView.setX(0);
                        } else if (draggableTextView.getX() > maxX) {
                            draggableTextView.setX(maxX);
                        }

                        if (draggableTextView.getY() < 0) {
                            draggableTextView.setY(0);
                        } else if (draggableTextView.getY() > maxY) {
                            draggableTextView.setY(maxY);
                        }

                        // Update the initial touch coordinates
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                }
                return true;
            }
        });
    }
}
