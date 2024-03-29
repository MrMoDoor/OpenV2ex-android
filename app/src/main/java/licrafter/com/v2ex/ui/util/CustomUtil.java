package licrafter.com.v2ex.ui.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import licrafter.com.v2ex.R;
import licrafter.com.v2ex.model.Topic;
import licrafter.com.v2ex.ui.widget.CustomProgressbarDialog;

/**
 * Created by shell on 15-11-7.
 */
public class CustomUtil {

    public static String streamFormToString(InputStream inputStream) {
        try {
            int count = inputStream.available();
            byte[] bytes = new byte[count];
            inputStream.read(bytes);
            inputStream.close();
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static List<Topic> collectionToList(Collection<Topic> collections) {
        List<Topic> topics = new ArrayList<>();
        Iterator<Topic> iterator = collections.iterator();
        while (iterator.hasNext()) {
            topics.add(iterator.next());
        }
        return topics;
    }

    /**
     * 得到自定义dialog
     *
     * @param message
     * @return
     */
    public static CustomProgressbarDialog getCustomProgressDialog(String message) {
        CustomProgressbarDialog dialog = new CustomProgressbarDialog();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        dialog.setArguments(bundle);
        return dialog;
    }

    /**
     * 登陆报错解析
     *
     * @param response
     * @return
     */
    public static String getErrorMsg(String response) {
        Pattern errorPattern = Pattern.compile("<div class=\"problem\">(.*)</div>");
        Matcher errorMatcher = errorPattern.matcher(response);
        String errorContent;
        if (errorMatcher.find()) {
            errorContent = errorMatcher.group(1).replaceAll("<[^>]+>", "");
        } else {
            errorContent = "未知错误";
        }
        return errorContent;
    }

    /**
     * 在代码中修改status bar颜色
     * 也可以在values-v21里重写styles.yml
     * http://stackoverflow.com/questions/22192291/how-to-change-the-status-bar-color-in-android
     */

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarColor(Activity activity) {
        Window window = activity.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(activity.getResources().getColor(R.color.teal500));
    }
}
