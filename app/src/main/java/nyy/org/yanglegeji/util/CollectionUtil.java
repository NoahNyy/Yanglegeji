package nyy.org.yanglegeji.util;

import java.util.Collection;
import java.util.List;

/**
 * Collection 工具类
 * @author niuyy
 * @date 2018/1/28
 */
public class CollectionUtil {

    /**
     * 判断空
     * @param collection 要验证的 list
     * @return true if 为 null 或者 size 为 0
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
