package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.pl.firstclicker.IRequestCallback;

public class FeatureFlagService {

    public static final String REQUEST_URL = "http://szymongalazka.pythonanywhere.com/pierogiclicker/api/v1.0/features";
    public static final String FEATURE_SHOP = "FEATURE_SHOP";
    public static final String FEATURE_BLA = "FEATURE_BLA";

    private boolean shop = false;

    public void makeFeatureFlagRequest(final IRequestCallback requestCallback){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        HttpRequest httpRequest = requestBuilder.newRequest().method(HttpMethods.GET).url(REQUEST_URL).build();
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println("Result: ");
                System.out.println(httpResponse.getResultAsString());
                requestCallback.onSucceed();
            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t.getMessage());
                requestCallback.onError();
            }

            @Override
            public void cancelled() {
                requestCallback.onError();
            }
        });

    }

    public boolean hasShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }
}
