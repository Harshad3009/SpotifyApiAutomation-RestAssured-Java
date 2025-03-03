package SpotifyOAuth.Tests;

import Api.PlaylistApi;
import RestResources.StatusCode;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.GetPlayList.Playlist;
import pojos.generic.OuterError;
import utils.DataLoader;

import java.util.Random;

import static utils.FakerUtils.generateDescription;
import static utils.FakerUtils.generateName;

@Epic("Spotify OAuth 2.0 Framework")
@Feature("Spotify Playlists")
public class PlaylistTests extends BaseTest{

    @Test(description = "should be able to create a playlist")
    @Description("Given correct body and authentication, playlist creation should be successful")
    @TmsLink("1001")
    @Story("Playlist Creation")
    public void shouldBeAbleToCreateAPlaylist() {

        Playlist requestBody = playlistBuilder(generateName(), generateDescription(), new Random().nextBoolean());

        Response response = PlaylistApi.post(requestBody);

        assertStatusCode(response.getStatusCode(), StatusCode.CODE_201);
        assertPlaylist(response.as(Playlist.class), requestBody);
    }

    @Test(description = "should be able to get playlist details")
    @Description("Given correct playlist id and authentication, successfully gets the playlist details")
    @TmsLink("1002")
    @Story("Playlist Retrieval")
    public void shouldBeAbleToGetAPlaylist() {
        Playlist expectedPlaylist = playlistBuilder("New Playlist2 {Harshad}", "New playlist description", true);

        Response response = PlaylistApi.get(DataLoader.getInstance().getData("getPlayListId"));

        assertStatusCode(response.getStatusCode(), StatusCode.CODE_200);
        assertPlaylist(response.as(Playlist.class), expectedPlaylist);
    }

    @Test(description = "should be able to update a playlist")
    @Description("Given correct body and authentication, playlist is updated successfully")
    @TmsLink("1003")
    @Issue("BUG-001")
    @Story("Playlist Updation")
    public void shouldBeAbleToUpdateAPlaylist() {

        Playlist requestBody = playlistBuilder(generateName(), generateDescription(), new Random().nextBoolean());

        Response response = PlaylistApi.update(DataLoader.getInstance().getData("updatePlaylistId"), requestBody);
        assertStatusCode(response.getStatusCode(), StatusCode.CODE_200);

    }

    @Test(description = "should NOT be able to create a playlist without name field")
    @Description("Given in-correct body, playlist creation should be failed and proper error should be returned")
    @TmsLink("1004")
    @Story("Playlist Creation")
    public void shouldNOTBeAbleToCreateAPlaylistWithoutName() {

        Playlist requestBody = playlistBuilder(null, generateDescription(), new Random().nextBoolean());

        Response response = PlaylistApi.post(requestBody);

        assertStatusCode(response.getStatusCode(), StatusCode.CODE_400);
        assertError(response.as(OuterError.class), StatusCode.CODE_400);
    }

    @Test(description = "should get authentication error with wrong token")
    @Description("Given incorrect authentication, should throw authentication error")
    @TmsLink("1005")
    @Issue("BUG-002")
    @Story("Playlist Retrieval")
    public void shouldNOTBeAbleToGetAPlaylistWithExpiredToken() {

        Response response = PlaylistApi.getWithCustomToken(DataLoader.getInstance().getData("getPlayListId"), "12345");

        assertStatusCode(response.getStatusCode(), StatusCode.CODE_401);
        assertError(response.as(OuterError.class), StatusCode.CODE_401);
    }

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public) {
        return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }

    @Step
    public void assertPlaylist(Playlist responsePlaylist, Playlist requestPlaylist) {
        Assert.assertEquals(responsePlaylist.getName(), requestPlaylist.getName());
        Assert.assertEquals(responsePlaylist.getDescription(), requestPlaylist.getDescription());
        Assert.assertEquals(responsePlaylist.get_public(), requestPlaylist.get_public());
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode expectedStatusCode) {
        Assert.assertEquals(actualStatusCode, expectedStatusCode.code);
    }

    @Step
    public void assertError(OuterError error, StatusCode expectedStatusCode) {
        Assert.assertEquals(error.getError().getStatus(), expectedStatusCode.code);
        Assert.assertEquals(error.getError().getMessage(), expectedStatusCode.msg);
    }


}
