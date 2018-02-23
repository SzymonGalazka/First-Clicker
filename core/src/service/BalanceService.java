package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.pl.firstclicker.IRequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.badlogic.gdx.Net.HttpMethods.GET;

public class BalanceService {

    public static final String REQUEST_URL ="http://szymongalazka.pythonanywhere.com/pierogiclicker/api/v1.0/balance";
    public static final String BALANCE_MONEY_CLICK = "BALANCE_PIEROGI_CLICK";

    private int moneyClickValue;

    public void makeBalanceServiceRequest(final IRequestCallback request){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(GET).url(REQUEST_URL).build();
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                parseResponse(httpResponse.getResultAsString());
                request.onSucceed();
            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t.getMessage());
                request.onError();
            }

            @Override
            public void cancelled() {
                request.onError();
            }
        });
    }

    private void parseResponse(String resultAsString) {
        try {
            JSONObject obj = new JSONObject(resultAsString);
            JSONArray jsonArray = obj.getJSONArray("balance");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject innerObj = jsonArray.getJSONObject(i);
                if(innerObj.get("name").equals(BALANCE_MONEY_CLICK)) {
                    moneyClickValue = (Integer) innerObj.get("value");
                    System.out.println("MoneyClickValue: "+ moneyClickValue);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    public int getMoneyClickValue() {
        return moneyClickValue;
    }
}
