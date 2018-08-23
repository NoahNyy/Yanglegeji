package nyy.org.yanglegeji.main.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import nyy.org.yanglegeji.R;

/**
 * 浮窗 service
 *
 * @author niuyy
 */
public class FloatWindowService extends Service implements View.OnClickListener, View.OnTouchListener {

    private ConstraintLayout floatWindowLayout;
    private WindowManager.LayoutParams params;
    private WindowManager windowManager;

    private ImageButton floatWindowBtn;

    // 状态栏高度
    int statusBarHeight = -1;

    /**
     * 默认构造器
     */
    public FloatWindowService() {
    }

    @Override
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initFloatWindow();
    }

    private void initFloatWindow() {
        params = new WindowManager.LayoutParams();
        windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);

        //设置type.系统提示型窗口，一般都在应用程序窗口之上.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){//6.0
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            params.type =  WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        //设置效果为背景透明.
        params.format = PixelFormat.RGBA_8888;
        //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置窗口初始停靠位置.
        params.gravity = Gravity.START | Gravity.TOP;
        params.x = 0;
        params.y = 0;

        //设置悬浮窗口长宽数据.
        //注意，这里的width和height均使用px而非dp
        //如果想完全对应布局设置，需要先获取到机器的dpi
        //px与dp的换算为px = dp * (dpi / 160).
        params.width = 300;
        params.height = 300;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局.
        floatWindowLayout = (ConstraintLayout) inflater.inflate(R.layout.float_window, null);
        //添加toucherlayout
        windowManager.addView(floatWindowLayout, params);

        //主动计算出当前View的宽高信息.
        floatWindowLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        //用于检测状态栏高度.
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        //浮动窗口按钮.
        floatWindowBtn = floatWindowLayout.findViewById(R.id.float_window_btn);

        floatWindowBtn.setOnClickListener(new View.OnClickListener() {
            long[] hints = new long[2];

            @Override
            public void onClick(View v) {
                System.arraycopy(hints, 1, hints, 0, hints.length - 1);
                hints[hints.length - 1] = SystemClock.uptimeMillis();
                if (SystemClock.uptimeMillis() - hints[0] >= 700) {
                    Toast.makeText(FloatWindowService.this, "连续点击两次以退出", Toast.LENGTH_SHORT).show();
                } else {
                    stopSelf();
                }
            }
        });

        floatWindowBtn.setOnTouchListener(this);

    }

    @Override
    public void onDestroy() {
        if (floatWindowBtn != null) {
            windowManager.removeView(floatWindowLayout);
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.float_window_btn:

                break;
            default:
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.float_window_btn:
                //ImageButton我放在了布局中心，布局一共300dp
                params.x = (int) event.getRawX() - 150;
                //这就是状态栏偏移量用的地方
                params.y = (int) event.getRawY() - 150 - statusBarHeight;
                windowManager.updateViewLayout(floatWindowLayout, params);
                break;
            default:
        }
        return false;
    }
}
