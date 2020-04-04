package fr.unice.miage.game;

import fr.unice.miage.game_objects.Player;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class GameEngine {
    GameMenu gameMenu;
    GameBoard gameBoard;
    Repository repository;

    int nbPlayers;
    List<Player> players;

    public GameEngine(File path) throws MalformedURLException {
        this.repository = new Repository(path);
    }
    public void init(){
        this.gameMenu = new GameMenu();


    }

    public void loadingPlayers(List<List<Object>> playersOptions) {
        this.nbPlayers = playersOptions.size();
        for(List<Object> playerOpts : playersOptions) {
            try {
                this.players.add(new Player(playerOpts, this.repository));
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public void loop(){

        //Repository repo = new Repository(new File());

        /* ClassLoader loader = new URLClassLoader( new URL[] { new File("/Users/haddad/.m2/repository/com/company/somejar-1.0.0-SNAPSHOT.jar").toURL() });
        Class<moveClass> c1 = (Class<moveClass>)loader.loadClass("chemin");
        Constructor<PlugInMovement> constructor = c1.getConstructor(String.class);
        PlugInMovement movePlugIn = constructor.newInstance("myString"); */
    }
}
