package utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static Api.BaseApi.postAccount;

public class TokenManager {

    private static String accessToken;
    private static Instant expiryTime;

    public synchronized static String getToken() {
        try {
            if(accessToken == null || Instant.now().isAfter(expiryTime)) {
                System.out.println("Renewing token....");
                Response response = renewToken();
                accessToken = response.path("access_token");
                int expiryTimeInSeconds =  response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryTimeInSeconds - 300);
            }
            else{
                System.out.println("token is good to go!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ABORT!!! Access token is not renewed");
        }

        return accessToken;
    }

    @Step
    private static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<>();

        formParams.put("grant_type", ConfigLoader.getInstance().getConfig("grant_type"));
        formParams.put("refresh_token", ConfigLoader.getInstance().getConfig("refresh_token"));
        formParams.put("client_id", ConfigLoader.getInstance().getConfig("client_id"));
        formParams.put("client_secret", ConfigLoader.getInstance().getConfig("client_secret"));
        Response response = postAccount(formParams);

        if(response.statusCode() != 200){
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }

        return response;
    }
}
