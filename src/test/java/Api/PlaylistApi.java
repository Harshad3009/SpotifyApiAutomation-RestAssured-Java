package Api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojos.GetPlayList.Playlist;

import static RestResources.Endpoints.CREATE_PLAYLIST;
import static RestResources.Endpoints.PLAYLIST;
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
