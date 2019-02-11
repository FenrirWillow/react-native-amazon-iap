
package com.dubit.amazoniap;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.amazon.iap.PurchasingService;

public class RNAmazonIapModule extends ReactContextBaseJavaModule {

  private static final IS_SANDBOX_MODE = "IS_SANDBOX_MODE";
  private static final SDK_VERSION = "SDK_VERSION";

  private final ReactApplicationContext reactContext;
  private final PurchasingService purchasingService;

  public RNAmazonIapModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    this.purchasingService = new PurchasingService();
  }

  @Override
  public String getName() {
    return "RNAmazonIap";
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(IS_SANDBOX_MODE, PurchasingService.IS_SANDBOX_MODE);
    constants.put(SDK_VERSION, PurchasingService.SDK_VERSION);
    return constants;
  }

}
