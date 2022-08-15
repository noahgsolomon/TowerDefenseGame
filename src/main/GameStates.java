package main;

public enum GameStates {

    PLAYING,
    MENU,
    SETTINGS;
    public static GameStates gameStates = MENU;

    public static void setGameStates(GameStates states){
        gameStates = states;
    }

}
