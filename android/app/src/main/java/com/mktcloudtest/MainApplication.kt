package com.mktcloudtest

import android.app.Application
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.load
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost
import com.facebook.react.defaults.DefaultReactNativeHost
import com.facebook.react.flipper.ReactNativeFlipper
import com.facebook.soloader.SoLoader

class MainApplication : Application(), ReactApplication {

  override val reactNativeHost: ReactNativeHost =
      object : DefaultReactNativeHost(this) {
        override fun getPackages(): List<ReactPackage> =
            PackageList(this).packages.apply {
              // Packages that cannot be autolinked yet can be added manually here, for example:
              // add(MyReactNativePackage())
            }

        override fun getJSMainModuleName(): String = "index"

        override fun getUseDeveloperSupport(): Boolean = BuildConfig.DEBUG

        override val isNewArchEnabled: Boolean = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
        override val isHermesEnabled: Boolean = BuildConfig.IS_HERMES_ENABLED
      }

  override val reactHost: ReactHost
    get() = getDefaultReactHost(this.applicationContext, reactNativeHost)

  override fun onCreate() {
    super.onCreate()
    SoLoader.init(this, false)

      SFMCSdk.configure(this as Context, SFMCSdkModuleConfig.build { builder ->
          builder.setPushModuleConfig(MarketingCloudConfig.builder()
                  .setApplicationId("{MC_APP_ID}")
                  .setAccessToken("{MC_ACCESS_TOKEN}")
                  .setSenderId("{FCM_SENDER_ID_FOR_MC_APP}")
                  .setMarketingCloudServerUrl("{MC_APP_SERVER_URL}")
                  .setNotificationCustomizationOptions(NotificationCustomizationOptions.create(R.drawable.ic_notification))
                  .setAnalyticsEnabled(true)
                  .build(this))
          null
      }) { initializationStatus ->
          Log.e("TAG", "STATUS $initializationStatus")
          if (initializationStatus.getStatus() === 1) {
              Log.e("TAG", "STATUS SUCCESS")
          }
          null
      }

      if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      // If you opted-in for the New Architecture, we load the native entry point for this app.
      load()
    }
    ReactNativeFlipper.initializeFlipper(this, reactNativeHost.reactInstanceManager)
  }
}
