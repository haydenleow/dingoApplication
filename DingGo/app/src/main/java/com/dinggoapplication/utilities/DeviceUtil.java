package com.dinggoapplication.utilities;

import android.content.Context;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * @author Lee Quee Leong & Seah Siu Ngee
 * @version x.x
 *          Created by Leon on 25/8/2015.
 */
public class DeviceUtil {
    private static final String TAG = "Device Utility";

    public static ParseInstallation getDeviceInstallation() {
        return ParseInstallation.getCurrentInstallation();
    }

    public static void installDevice(Context context) {
        if (AccountUtils.isLogin()) {
            final ParseInstallation installation = getDeviceInstallation();
            /*
            if (installation.get("deviceToken") == null) {
                String deviceId = Settings.System.getString(context.getContentResolver(),
                        Settings.System.ANDROID_ID);
                installation.put("deviceToken", deviceId);
            }
            */
            installation.put("user", AccountUtils.getUser());

            installation.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        LogUtils.LOGD(TAG, "Installation success:" + installation.getString("deviceToken"));
                        ParsePush.subscribeInBackground("");
                    } else {
                        LogUtils.LOGE(TAG, "Installation failed:" + e.getMessage());
                    }
                }
            });
        }
    }

    public static void addChannel(final String channelName) {
        ParsePush.subscribeInBackground(channelName, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    LogUtils.LOGD(TAG, String.format("%s has subscribed %s as a channel!", AccountUtils.getUser().get("name"), channelName));
                } else {
                    LogUtils.LOGE(TAG, "Unable to subscribe for " + channelName + ":" + e.getMessage());
                }
            }
        });
    }
    public static void removeDevice() {
        ParseInstallation installation = getDeviceInstallation();
        getDeviceInstallation().remove("user");

        installation.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    LogUtils.LOGD(TAG, "Uninstall success:");
                    ParsePush.subscribeInBackground("");
                } else {
                    LogUtils.LOGE(TAG, "Uninstall failed:" + e.getMessage());
                }
            }
        });

    }
}
