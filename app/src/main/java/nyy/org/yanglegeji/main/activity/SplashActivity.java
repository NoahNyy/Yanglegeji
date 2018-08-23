package nyy.org.yanglegeji.main.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import nyy.org.yanglegeji.R;
import nyy.org.yanglegeji.constant.TimeConstant;
import nyy.org.yanglegeji.util.ActivityUtil;
import nyy.org.yanglegeji.util.LogUtil;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    private final static String CLZ_NAME = SplashActivity.class.getSimpleName();

    public static final int OVERLAY_REQUEST_CODE = 1;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            //隐藏标题栏
            supportActionBar.hide();
        }
        setContentView(R.layout.activity_splash);

        initView();
        initPermission();

        //起线程跳转视图
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(TimeConstant.SPLASH_ACTIVITY_WAIT_TIME);
                    init();
                } catch (Exception e) {
                    LogUtil.e(CLZ_NAME, e);
                }
            }
        }.start();
    }

    private void init() {
        //判断信息初始化
        // TODO  信息
        if (true) {
            ActivityUtil.startActivity(InitNameActivity.class);
        } else {
            ActivityUtil.startActivity(MainActivity.class);
        }
        finish();
    }

    private void initPermission() {
        //悬浮窗权限
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(getApplicationContext())) {
                dialog.show();
            }
        }
    }

    private void initView() {
        dialog = new AlertDialog.Builder(this)
                .setMessage("需要打开「显示悬浮窗」权限！")
                .setPositiveButton("去打开", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        Uri uri = Uri.fromParts("package", SplashActivity.this.getPackageName(),
                                null);
                        intent.setData(uri);
                        startActivityForResult(intent, OVERLAY_REQUEST_CODE);
                    }
                })
                .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OVERLAY_REQUEST_CODE) {
            dialog.dismiss();
            init();
        }
    }
}
