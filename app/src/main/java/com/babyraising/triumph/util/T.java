package com.babyraising.triumph.util;

import android.content.Context;
import android.widget.Toast;

import com.babyraising.triumph.TriumphApplication;

public class T {

    private static TriumphApplication app;

    private T() {
    }

    public static void init(TriumphApplication app) {
        T.app = app;
    }

    public static void s(String msg) {
        if (app == null) return;
        s(app, msg);
    }

    public static void l(String msg) {
        if (app == null) return;
        l(app, msg);
    }

    public static void s(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    public static void l(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}