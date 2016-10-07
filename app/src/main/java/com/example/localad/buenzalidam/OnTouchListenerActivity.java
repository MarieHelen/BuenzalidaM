package com.example.localad.buenzalidam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

public class OnTouchListenerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_listener);

        final ImageView image = (ImageView) findViewById(R.id.image);
        final TextView x1 = (TextView) findViewById(R.id.x1);
        final TextView x2 = (TextView) findViewById(R.id.x2);
        final TextView y1 = (TextView) findViewById(R.id.y1);
        final TextView y2 = (TextView) findViewById(R.id.y2);
        final TextView diff = (TextView) findViewById(R.id.diff);
        final TextView motion = (TextView) findViewById(R.id.motion);
        final TextView quad = (TextView) findViewById(R.id.quad);

        image.setOnTouchListener(new View.OnTouchListener(){
            float newX, oldX, newY, oldY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // float x1 = 0, x2 = 0, y1 = 0, y2 =0, dx, dy, oldx = 0, oldy = 0;
                // String direction;
                // int action = motionEvent.getAction();

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        newX = motionEvent.getX();
                        newY = motionEvent.getY();

                        x1.setText("X1" + String.valueOf(newX));
                        x2.setText("X2" + String.valueOf(oldX));
                        y1.setText("Y1" + String.valueOf(newY));
                        y2.setText("Y2" + String.valueOf(oldY));
                        //Toast.makeText(getApplicationContext(), ""+String.format("ACTION_DOWN>>>X:%.2f,Y:%.2f",newX, newY), Toast.LENGTH_SHORT).show();
                        //return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        oldX = motionEvent.getX();
                        oldY = motionEvent.getY();

                        float q;
                        q = newX - oldX;
                        diff.setText(String.valueOf(q));

                        float w;
                        w = newY - oldY;
                        diff.setText(String.valueOf(w));

                        if (newX < oldX) {
                            if (newY < oldY) {
                                motion.setText("Left to Right, Downward");
                                quad.setText("Q4");
                            }
                        }
                        if (newX > oldX) {
                            if (newY < oldY) {
                                motion.setText("Right to Left, Downward");
                                quad.setText("Q3");
                            }
                        }
                        if (newX < oldX) {
                            if (newY > oldY) {
                                motion.setText("Left to Right, Upward");
                                quad.setText("Q1");
                            }
                        }
                        if (newX > oldX) {
                            if (newY > oldY) {
                                motion.setText("Right to Left, Upward");
                                quad.setText("Q2");
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
        });


    }}