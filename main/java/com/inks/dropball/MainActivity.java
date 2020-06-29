package com.inks.dropball;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dropball.R;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Button left,right;
    private Intent intent;
    private MyGameView mygameView;
    private MediaPlayer mediaPlayer;
    MyIntentService myIntentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, MyIntentService.class);
        String action = MyIntentService.ACTION_MUSIC;
        // 设置action
        intent.setAction(action);
        startService(intent);

        initEvent();
        mygameView = (MyGameView) findViewById(R.id.nnn);
        mygameView.setGameover(gameover);

        left=findViewById(R.id.left);
        right=findViewById(R.id.right);

        left.setOnClickListener(cccc);
        right.setOnClickListener(cccc);


        //获取窗口的一个管理器
        WindowManager windowManager= getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        DisplayMetrics metrics= new DisplayMetrics();
        display.getMetrics(metrics);
        //获取屏幕的宽和高
        mygameView.zmkuang = metrics.widthPixels;
        mygameView.zmGao = metrics.heightPixels;

        mygameView.play();
        mygameView.restart();
    }

    public void initEvent(){
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                mygameView.handler.sendEmptyMessage(0x246);
            }
        });

    }
    protected void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setTitle("提示");
        builder.setMessage("是否继续游戏或者直接退出");
        builder.setNegativeButton("继续游戏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("退出游戏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mygameView.jieshu = true;
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                stopService(intent);
                mediaPlayer.stop();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    MyGameView.GameOver gameover = new MyGameView.GameOver() {
        @Override
        public void Over() {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("游戏结束");
            builder.setMessage("好可惜，再接再厉哦\n本次得分："+mygameView.num+"");
            builder.setNegativeButton("重新开始", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mygameView.restart();
                }
            });
            builder.setPositiveButton("退出游戏", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mygameView.jieshu = true;
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                    stopService(intent);
                    mediaPlayer.stop();
                }
            });
            builder.show();
        }
    };
     View.OnClickListener cccc = new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             switch (v.getId()) {
                 case R.id.left:
                     mygameView.moveLeft();
                     break;
                 case R.id.right:
                     mygameView.moveRight();
                     break;
             }
         }
     };

}
