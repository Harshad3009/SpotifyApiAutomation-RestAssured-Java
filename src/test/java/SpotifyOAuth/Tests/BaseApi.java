package SpotifyOAuth.Tests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojos.GetPlayList.Playlist;

import java.util.HashMap;

import static SpotifyOAuth.Tests.Endpoints.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class BaseApi {


    static RequestSpecification requestSpecification = new RequestSpecBuilder().
            setBaseUri(BASEURI).
            setBasePath(BASEPATH).
            addFilter(new AllureRestAssured()).
            setContentType(ContentType.JSON).
            log(LogDetail.ALL).
            build();

    static ResponseSpecification responseSpecification = new ResponseSpecBuilder().
            log(LogDetail.ALL).
            build();

    public static Response post(Object requestBody, String endpoint, String token) {
        return given(requestSpecification).
                auth().oauth2(token).
                body(requestBody).
                when().
                post(endpoint).
                then().
                spec(responseSpecification).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String> formParams) {
        return given().
                baseUri(ACCOUNTS_BASEURI).
                basePath(ACCOUNTS_BASEPATH).
                contentType(ContentType.URLENC).
                formParams(formParams).
                filter(new AllureRestAssured()).
                when().
                post(REFRES_TOKEN).
                then().
                spec(responseSpecification).
                extract().
                response();
    }

    public static Response get(String endpoint, String token) {
        return given(requestSpecification).
                auth().oauth2(token).
                when().
                get(endpoint).
                then().
                spec(responseSpecification).
                extract().response();
    }

    public static Response update(Object requestBody, String endpoint, String token) {
        return given(requestSpecification).
                auth().oauth2(token).
                body(requestBody).
                when().
                put(endpoint).
                then().
                spec(responseSpecification).
                extract().
                response();
    }
}
