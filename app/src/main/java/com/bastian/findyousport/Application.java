package com.bastian.findyousport;

import com.bugfender.sdk.Bugfender;

/**
 * Created by Bastian on 27-01-2017.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bugfender.init(this, "hBUsTmfW9pVS6jUioqBYPETM5SIekkoZ", BuildConfig.DEBUG);
        Bugfender.enableLogcatLogging();
    }
}
