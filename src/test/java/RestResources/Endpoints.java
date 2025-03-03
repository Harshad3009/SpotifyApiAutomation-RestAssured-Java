package RestResources;

import utils.DataLoader;

public class Endpoints {

    public static final String BASEURI = System.getProperty("BASE_URI"); //"https://api.spotify.com"
    public static final String BASEPATH = "/v1";
    public static final String CREATE_PLAYLIST = "/users/" + DataLoader.getInstance().getData("userId") + "/playlists";
    public static final String PLAYLIST = "/playlists/";
    public static final String ACCOUNTS_BASEURI = System.getProperty("ACCOUNTS_BASE_URI"); //"https://accounts.spotify.com"
    public static final String ACCOUNTS_BASEPATH = "/api";
    public static final String REFRES_TOKEN = "/token";

}
