package org.illegaller.ratabb.hishoot2i

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.microsoft.appcenter.AppCenter
import dagger.hilt.android.HiltAndroidApp
import entity.mode
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.illegaller.ratabb.hishoot2i.data.pref.SettingPref
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class HiShootApp : MultiDexApplication() {
    @Inject
    lateinit var settingPref: SettingPref

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(settingPref.dayNightMode.mode)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            enableStrictMode()
        }
        RxJavaPlugins.setErrorHandler { Timber.e(it) }
        AppCenter.configure(this, BuildConfig.APP_CENTER_SECRET)
    }
}
