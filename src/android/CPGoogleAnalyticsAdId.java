 

package com.appfeel.cordova.analytics.adid;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger.LogLevel;

public class CPGoogleAnalyticsAdId extends CordovaPlugin {
  public static final String ACTION_START_ADID_TRACKER = "startAdIdTrackerWithId";
  public static final String ACTION_GET_ADID = "getAdId";

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    PluginResult result = null;
     if (ACTION_GET_ADID.equals(action)) {
      result = execGetAdId(callbackContext);

    } else {
      return false;
    }

    if (result != null) {
      callbackContext.sendPluginResult(result);
    }

    return true;
  }

  
  private PluginResult execGetAdId(final CallbackContext callbackContext) {
    cordova.getThreadPool().execute(new Runnable() {
      @Override
      public void run() {
        AdvertisingIdClient.Info advId;
        try {
          advId = AdvertisingIdClient.getAdvertisingIdInfo(cordova.getActivity());
          callbackContext.success(advId.getId());

        } catch (Exception e) {
          callbackContext.error(e.getMessage());
        }
      }
    });

    return null;
  }

}