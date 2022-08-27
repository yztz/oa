package top.yztz.oa.utils;

import org.apache.commons.lang3.StringUtils;
import top.yztz.oa.bean.Category;
import top.yztz.oa.bean.News;
import top.yztz.oa.bean.User;

public class ValidateUtils {
    public static boolean isLegalUser(User user) {
        return !StringUtils.isEmpty(user.getUserName()) &&
                !StringUtils.isEmpty(user.getPassword()) &&
                !StringUtils.isEmpty(user.getPhone());
    }

    public static boolean isLegalCategory(Category category) {
        return !StringUtils.isEmpty(category.getName());
    }

    public static boolean isLegalNews(News news) {
        return !StringUtils.isEmpty(news.getName()) &&
                news.getCategoryID() != null;
    }
}
