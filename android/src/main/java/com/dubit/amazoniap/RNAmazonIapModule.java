
package com.dubit.amazoniap;

import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.PurchasingService;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RNAmazonIapModule extends ReactContextBaseJavaModule {

  private static final String IS_SANDBOX_MODE = "IS_SANDBOX_MODE";
  private static final String SDK_VERSION = "SDK_VERSION";

  private final RNAmazonIapPurchasingListener purchasingListener;

  RNAmazonIapModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.purchasingListener = new RNAmazonIapPurchasingListener();
    PurchasingService.registerListener(reactContext, this.purchasingListener);
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

  @ReactMethod
  public void getUserData(Promise promise) {
    final RequestId requestId = PurchasingService.getUserData();
    this.purchasingListener.addPromiseForRequest(requestId, promise);
  }

  @ReactMethod
  public void getPurchaseUpdates(boolean reset, Promise promise) {
    final RequestId requestId = PurchasingService.getPurchaseUpdates(reset);
    this.purchasingListener.addPromiseForRequest(requestId, promise);
  }

  @ReactMethod
  public void getProductData(ReadableArray SKUs, Promise promise) {
    final Set<String> SKUSet = new HashSet<>();
    for (int i = 0; i < SKUs.size(); i++) {
      SKUSet.add(SKUs.getString(i));
    }
    final RequestId requestId = PurchasingService.getProductData(SKUSet);
    this.purchasingListener.addPromiseForRequest(requestId, promise);
  }

  @ReactMethod
  public void purchase(String sku, Promise promise) {
    final RequestId requestId = PurchasingService.purchase(sku);
    this.purchasingListener.addPromiseForRequest(requestId, promise);
  }

  // TODO: (Stefan) Implement fulfillment notification from the upper layer of the API.
}
