package SpotifyOAuth.Tests;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import pojos.GetPlayList.Playlist;

import static SpotifyOAuth.Tests.Endpoints.CREATE_PLAYLIST;
import static SpotifyOAuth.Tests.Endpoints.PLAYLIST;
import static io.restassured.RestAssured.given;
import static utils.TokenManager.getToken;

public class PlaylistApi {

    @Step
    public static Response post(Playlist requestBody) {
        return BaseApi.post(requestBody, CREATE_PLAYLIST, getToken());
    }

    @Step
    public static Response get(String playlistId) {
        return BaseApi.get(PLAYLIST + playlistId, getToken());
    }

    @Step
    public static Response getWithCustomToken(String playlistId, String token) {
        return BaseApi.get(PLAYLIST + playlistId, token);
    }

    @Step
    public static Response update(String playlistId, Playlist requestBody) {
        return BaseApi.update(requestBody, PLAYLIST + playlistId, getToken());
    }
}
