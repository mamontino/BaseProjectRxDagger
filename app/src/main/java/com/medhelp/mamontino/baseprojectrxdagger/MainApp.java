package com.medhelp.mamontino.baseprojectrxdagger;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.medhelp.mamontino.baseprojectrxdagger.di.component.AppComponent;
import com.medhelp.mamontino.baseprojectrxdagger.di.component.DaggerAppComponent;
import com.medhelp.mamontino.baseprojectrxdagger.di.module.AppModule;

import io.realm.Realm;

public class MainApp extends Application  implements Application.ActivityLifecycleCallbacks
{
    private AppComponent appComponent;
    private int activityCount = 0;
    public boolean isChatActivityStarted = false;
    public boolean isContactsActivityStarted = false;

    @Override
    public void onCreate()
    {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);

        Realm.init(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);

//        AndroidNetworking.initialize(getApplicationContext());
//        if (BuildConfig.DEBUG)
//        {
//            Timber.plant(new Timber.DebugTree());
//            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
//        }
    }

    public AppComponent getComponent()
    {
        return appComponent;
    }

    public void setComponent(AppComponent appComponent)
    {
        this.appComponent = appComponent;
    }

    public boolean isAppForeground() {
        return activityCount > 0;
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        activityCount++;
//        if (activity instanceof ChatActivity) {
//            isChatActivityStarted = true;
//        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        activityCount--;

//        if (activity instanceof ChatActivity) {
//            isChatActivityStarted = false;
//        }
//
//        if (activity instanceof ContactsActivity) {
//            isContactsActivityStarted = false;
//        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }
}
