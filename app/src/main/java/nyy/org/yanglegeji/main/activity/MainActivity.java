package nyy.org.yanglegeji.main.activity;

import android.content.Intent;
import android.os.Bundle;

import nyy.org.yanglegeji.main.service.FloatWindowService;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        startFloatWindow();
    }

    private void initView() {
    }

    private void startFloatWindow() {
        Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
        startService(intent);
//        finish();
    }

}
