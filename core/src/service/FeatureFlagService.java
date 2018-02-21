package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.pl.firstclicker.IRequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FeatureFlagService {

    public static final String REQUEST_URL = "http://szymongalazka.pythonanywhere.com/pierogiclicker/api/v1.0/features";
    public static final String FEATURE_SHOP = "FEATURE_SHOP";
    public static final String FEATURE_BLA = "FEATURE_BLA";

    private Map<String, Boolean> featuresMap;

    public FeatureFlagService() {
        featuresMap = new HashMap<String, Boolean>();
    }

    public void makeFeatureFlagRequest(final IRequestCallback requestCallback){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        HttpRequest httpRequest = requestBuilder.newRequest().method(HttpMethods.GET).url(REQUEST_URL).build();
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                parseResponse(httpResponse.getResultAsString());
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

    private void parseResponse(String resultAsString) {
        try {
            JSONObject obj = new JSONObject(resultAsString);
            JSONArray jsonArray = obj.getJSONArray("features");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject innerObj = jsonArray.getJSONObject(i);
                featuresMap.put((String)innerObj.get("name"),(Boolean)innerObj.get("active"));
            }
            System.out.println("Parsed map: "+featuresMap);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public boolean hasFeature(String s){
        if(!featuresMap.containsKey(s)){
            return false;
        }else{
            return featuresMap.get(s);
        }
    }
}
