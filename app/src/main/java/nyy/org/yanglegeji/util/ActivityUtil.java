package nyy.org.yanglegeji.util;

import android.app.Activity;
import android.content.Intent;

import nyy.org.yanglegeji.main.activity.BaseActivity;

/**
 * @author niuyy
 */
public class ActivityUtil {

    /**
     * 启动一个视图
     * @param desActivityClz 目标视图字节码
     */
    public static void startActivity(Class<? extends Activity> desActivityClz) {
        Intent intent = new Intent(BaseActivity.mContext, desActivityClz);
        BaseActivity.mContext.startActivity(intent);
    }
}
