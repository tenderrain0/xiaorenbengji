package com.inks.dropball;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.dropball.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("AppCompatCustomView")
public class MyGameView extends View {
    Paint paint = new Paint();
    Canvas canvas;
    int zmkuang;
    int zmGao;
    //小球的变量
    float ball_size = 10;
    int ball_sudu;
    int ballX;
    int ballY;
    //柱子的属性
    int zhu_chang = 260;
    int zhu_gao = 10;
    int zhu_gao_2 = 10;
    float zhuX;
    int zhuY;
    float zhuX_2;
    int zhuY_2;
    float zhuX_3;
    int zhuY_3;
    float zhuX_4;
    int zhuY_4;
    float zhuX_5;
    int zhuY_5;
    int zhu_sudu;
    float num = 0;
    int ch = 0;
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.xiaoren);
    boolean jieshu = false;
    boolean zanting = false;
    public ImageView imageView;
    private MainActivity mainActivity;
    private MyGameView mygameView;
    public interface GameOver{
        void Over();
    }
    public GameOver mgameover;
    public void setGameover(GameOver mgameover){
        this.mgameover = mgameover;
    }

    public MyGameView(Context context) {
        super(context);
        init("View(Context context)");
    }
    public MyGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init("View(Context context, AttributeSet attrs)");
    }
    public MyGameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init("View(Context context, AttributeSet attrs, int defStyle)");
    }
    private void init(String structName) {
    }
    public void replay(){
        jieshu = false;
        play();
    }

    public void play(){
        jieshu = false;
        zanting = false;
        //设置柱子的初始位置
        zhuX = zmkuang/2-30;
        zhuY = zmGao/5;
        zhuX_2 = zmkuang-zmkuang;
        zhuY_2 = zmGao*2/5;
        zhuX_3 = zmkuang/2-30;
        zhuY_3 = zmGao*3/5;
        zhuX_4 = zmkuang/2+20;
        zhuY_4 = zmGao*4/5;
        num = 0;
        zhu_sudu =3;
        ball_sudu =4;
        ballX = zmkuang/2;
        ballY = 230;
        final int min = 0;
        final int max = 800;
        //设立随机事件
        final Random random = new Random();

        handler.sendEmptyMessage(0x123);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!jieshu) {
                    //设置小球和柱子的坐标变化
                    ballY = ballY + ball_sudu;
                    zhuY = zhuY - zhu_sudu;
                    zhuY_2 = zhuY_2 - zhu_sudu;
                    zhuY_3 = zhuY_3 - zhu_sudu;
                    zhuY_4 = zhuY_4 - zhu_sudu;
                    zhuY_5 = zhuY_5 - zhu_sudu;
                    //如果柱子碰到屏幕边缘事件
                    if (zhuY <= 130) {
                        zhuY = zmGao - zhu_gao_2;
                        ch = ch + 1;
                        zhuX = random.nextInt(max) % (max - min + 1) + min;
                        num = num + 1;
                    }
                    if (zhuY_2 <= 130) {
                        zhuY_2 = zmGao - zhu_gao_2;
                        zhuX_2 = random.nextInt(max) % (max - min + 1) + min;
                        num = num + 1;
                    }
                    if (zhuY_3 <= 130) {
                        zhuY_3 = zmGao - zhu_gao_2;
                        zhuX_3 = random.nextInt(max) % (max - min + 1) + min;
                        num = num + 1;
                    }
                    if (zhuY_4 <= 130) {
                        zhuY_4 = zmGao - zhu_gao_2;
                        zhuX_4 = random.nextInt(max) % (max - min + 1) + min;
                        num = num + 1;
                    }

                    if (zhuY_5 <= 130) {
                        zhuY_5 = zmGao - zhu_gao_2;
                        zhuX_5 = random.nextInt(max) % (max - min + 1) + min;
                        num = num + 1;
                    }
                    //设置游戏加速
                    if (ch >= 3) {
                        zhu_sudu = 4;
                        ball_sudu = 4;
                    }
                    if (ch >= 6) {
                        zhu_sudu = 5;
                        ball_sudu = 5;
                    }
                    if (ch >= 10) {
                        zhu_sudu = 6;
                        ball_sudu = 6;
                    }
                    if (ch >= 13) {
                        zhu_sudu = 7;
                        ball_sudu = 7;
                    }
                    if (ch >= 16) {
                        zhu_sudu = 8;
                        ball_sudu = 8;
                    }
                    if (ch >= 19) {
                        zhu_sudu = 9;
                        ball_sudu = 9;
                    }
                    //判断小球是否碰到屏幕边缘
                    if (ballY >= zmGao || ballY <= 135) {
                        jieshu = true;
                    }
                    //判断柱子是否接住小球
                    if (zhuY + zhu_gao >= ballY+50 && ballY+50 >= zhuY) {
                        if (zhuX <= ballX+33 && ballX <= zhuX + zhu_chang-8) {
                            ballY = zhuY - zhu_sudu-50;
                        } else {
                            ballY = ballY + ball_sudu;
                            ball_sudu = 4;
                        }
                    }
                    if (zhuY_2 + zhu_gao >= ballY+50 && ballY+50 >= zhuY_2) {
                        if (zhuX_2 <= ballX+33 && ballX <= zhuX_2 + zhu_chang-8) {
                            ballY = zhuY_2 - zhu_sudu-50;

                        } else {
                            ballY = ballY + ball_sudu;
                            ball_sudu = 4;
                        }
                    }
                    if (zhuY_3 + zhu_gao >= ballY+50 && ballY+50 >= zhuY_3) {
                        if (zhuX_3 <= ballX+33 && ballX <= zhuX_3 + zhu_chang-8) {
                            ballY = zhuY_3 - zhu_sudu-50;
                        } else {
                            ballY = ballY + ball_sudu;
                            ball_sudu = 4;
                        }
                    }
                    if (zhuY_4 + zhu_gao >= ballY+50 && ballY+50 >= zhuY_4) {
                        if (zhuX_4 <= ballX+33 && ballX <= zhuX_4 + zhu_chang-8) {
                            ballY = zhuY_4 - zhu_sudu-50;
                        } else {
                            ballY = ballY + ball_sudu;
                            ball_sudu = 4;
                        }
                    }
                    //红色陷阱柱子
                    if (ch >= 10) {
                        if (zhuY_5 + zhu_gao >= ballY+50 && ballY+50 >= zhuY_5) {
                            if (zhuX_5 <= ballX+33 && ballX <= zhuX_5 + zhu_chang-8) {
                                jieshu = true;
                            }
                        }
                    }
                    handler.sendEmptyMessage(0x123);
                }
            }
        },0,15);

    };
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what ==0x123){
                invalidate();
            }
        }
    };

    public void restart()
    {
        jieshu = false;
        zhuX = zmkuang/2-30;
        zhuY = zmGao/5;
        zhuX_2 = zmkuang-zmkuang;
        zhuY_2 = zmGao*2/5;
        zhuX_3 = zmkuang/2-30;
        zhuY_3 = zmGao*3/5;
        zhuX_4 = zmkuang/2+20;
        zhuY_4 = zmGao*4/5;
        num = 0;
        zhu_sudu =3;
        ball_sudu =4;
        ballX = zmkuang/2;
        ballY = 230;
        ch = 0;
    }
    public void gameover() {
        mgameover.Over();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);//抗锯齿
        if (jieshu){
            //结束游戏
            gameover();
        }else {
            paint.setColor(Color.RED);
            paint.setTextSize(60);
            canvas.drawText(num+"",zmkuang/2 -10,50,paint);

            Rect rect = new Rect(ballX,ballY,ballX+45,ballY+55);
            canvas.drawBitmap(bitmap,null,rect,paint);

            //绘制柱子1
            paint.setColor(Color.rgb(214,152,61));
            canvas.drawRect(zhuX,zhuY,zhuX+zhu_chang,zhuY+zhu_gao,paint);
            canvas.drawRect(zhuX_2,zhuY_2,zhuX_2+zhu_chang,zhuY_2+zhu_gao_2,paint);
            canvas.drawRect(zhuX_3,zhuY_3,zhuX_3+zhu_chang,zhuY_3+zhu_gao,paint);
            canvas.drawRect(zhuX_4,zhuY_4,zhuX_4+zhu_chang,zhuY_4+zhu_gao,paint);
            if(ch>=10){
                paint.setColor(Color.rgb(244,7,7));
                canvas.drawRect(zhuX_5,zhuY_5,zhuX_5+zhu_chang,zhuY_5+zhu_gao,paint);
            }
        }
    }
    public void moveLeft()
    {
        ballX = ballX-13;

        if (ch >=6){
            ballX = ballX-15;
        }
        if (ch >=13){
            ballX = ballX-17;
        }
    }
    public void moveRight()
    {
        ballX = ballX+13;
        if (ch >=6){
            ballX = ballX+15;
        }
        if (ch >=13){
            ballX = ballX+17;
        }
    }
}
