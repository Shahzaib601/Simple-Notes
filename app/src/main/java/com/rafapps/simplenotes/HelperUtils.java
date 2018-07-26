package com.rafapps.simplenotes;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class HelperUtils {

    public static int darkenColor(int color, double fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        red = darken(red, fraction);
        green = darken(green, fraction);
        blue = darken(blue, fraction);
        int alpha = Color.alpha(color);
        return Color.argb(alpha, red, green, blue);
    }

    private static int darken(int color, double fraction) {
        return (int) Math.max(color - (color * fraction), 0);
    }

    public static void applyColours(Activity activity, int colourPrimary) {
        // Check API level
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //Get the activity window
            Window window = activity.getWindow();

            // Draw over the navigation bar
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(colourPrimary);

            // Colour the status bar
            window.setStatusBarColor(HelperUtils.darkenColor(colourPrimary, 0.2));

            // Set task description, colour and icon for the app switcher
            activity.setTaskDescription(new ActivityManager.TaskDescription("Simple Notes",
                    BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_note), colourPrimary));
        }
    }
}
