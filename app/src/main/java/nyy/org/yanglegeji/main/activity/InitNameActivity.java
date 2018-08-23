package nyy.org.yanglegeji.main.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import nyy.org.yanglegeji.R;
import nyy.org.yanglegeji.util.ActivityUtil;

/**
 * 初始化宠物和主人的名称
 * @author niuyy
 */
public class InitNameActivity extends BaseActivity implements View.OnClickListener{

    private Button nextStepButton;

    private TextInputLayout masterNameTitle;
    private TextInputEditText masterNameEditText;
    private TextInputLayout petNameTitle;
    private TextInputEditText petNameEditText;

    private TextInputLayout currentTextLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_name);

        init();
    }

    private void init() {
        initView();
        initListener();
    }

    private void initListener() {
        nextStepButton.setOnClickListener(this);
    }

    private void initView() {
        nextStepButton = findViewById(R.id.btn_next_step);
        masterNameTitle = findViewById(R.id.til_master_name);
        masterNameEditText = findViewById(R.id.et_master_name);
        petNameTitle = findViewById(R.id.til_pet_name);
        petNameEditText = findViewById(R.id.et_pet_name);

        currentTextLayout = petNameTitle;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next_step:
                if (currentTextLayout == petNameTitle) {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle(R.string.alert_dialog_title_tip)
                            .setMessage("确定使用「" + petNameEditText.getText() + "」作为您宠物的名字吗？确认后不可更改的哦~")
                            .setPositiveButton("就它了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO 保存宠物名称

                                    currentTextLayout.setVisibility(View.GONE);
                                    masterNameTitle.setVisibility(View.VISIBLE);

                                    currentTextLayout = masterNameTitle;

                                    nextStepButton.setText(R.string.complete);
                                }
                            })
                            .setNegativeButton("换一个", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    petNameEditText.setVisibility(View.FOCUSABLES_ALL);
                                }
                            })
                            .setCancelable(false)
                            .show();
                } else if (currentTextLayout == masterNameTitle) {
                    // TODO 保存名字
                    ActivityUtil.startActivity(MainActivity.class);
                    finish();
                }
        }
    }
}
