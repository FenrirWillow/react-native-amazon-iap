package com.dubit.amazoniap;

import android.util.Log;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserDataResponse;
import com.facebook.react.bridge.Promise;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;

public class RNAmazonIapPurchasingListener implements PurchasingListener {

    private final Map<RequestId, Promise> responseMap;

    RNAmazonIapPurchasingListener() {
        this.responseMap = new HashMap<>();
    }

    void addPromiseForRequest(RequestId requestId, Promise promise) {
        this.responseMap.put(requestId, promise);
    }

    @Override
    public void onUserDataResponse(UserDataResponse userDataResponse) {
        final RequestId requestId = userDataResponse.getRequestId();
        final Promise promise = this.responseMap.get(requestId);

        if (promise == null) {
            Log.e(RNAmazonIapPackage.LOGGER_TAG, String.format("No promise for response: %s", requestId));
        } else {
            try {
                promise.resolve(userDataResponse.toJSON().toString());
            } catch (JSONException exception) {
                promise.reject(exception);
            }
        }
    }

    @Override
    public void onProductDataResponse(ProductDataResponse productDataResponse) {
        final RequestId requestId = productDataResponse.getRequestId();
        final Promise promise = this.responseMap.get(requestId);

        if (promise == null) {
            Log.e(RNAmazonIapPackage.LOGGER_TAG, String.format("No promise for response: %s", requestId));
        } else {
            try {
                promise.resolve(productDataResponse.toJSON().toString());
            } catch (JSONException exception) {
                promise.reject(exception);
            }
        }
    }

    @Override
    public void onPurchaseResponse(PurchaseResponse purchaseResponse) {
        final RequestId requestId = purchaseResponse.getRequestId();
        final Promise promise = this.responseMap.get(requestId);

        if (promise == null) {
            Log.e(RNAmazonIapPackage.LOGGER_TAG, String.format("No promise for response: %s", requestId));
        } else {
            try {
                promise.resolve(purchaseResponse.getReceipt().toJSON().toString());
            } catch (Exception exception) {
                promise.reject(exception);
            }
        }
    }

    @Override
    public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse purchaseUpdatesResponse) {
        final RequestId requestId = purchaseUpdatesResponse.getRequestId();
        final Promise promise = this.responseMap.get(requestId);

        if (promise == null) {
            Log.e(RNAmazonIapPackage.LOGGER_TAG, String.format("No promise for response: %s", requestId));
        } else {
            try {
                promise.resolve(purchaseUpdatesResponse.toJSON().toString());
            } catch (JSONException exception) {
                promise.reject(exception);
            }
        }
    }
}
