package com.inks.dropball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dropball.R;

public class Main2Activity extends AppCompatActivity {

    private Button button;
    private MediaPlayer mediaPlayer;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                intent = new Intent(Main2Activity.this, MyIntentService.class);
                String action = MyIntentService.ACTION_MUSIC;
                // 设置action
                intent.setAction(action);
                startService(intent);
            }
        });

    }
}
