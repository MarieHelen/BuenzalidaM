package com.example.localad.buenzalidam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NextScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.next_screen);


        Button bTouch = (Button)  findViewById(R.id.bTouch);

        bTouch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(NextScreen.this,OnTouchListenerActivity.class);
                startActivity(i);
            }
        });
    }
}
