module fr.unice.miage.common {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    //requires org.junit.jupiter.engine;

    exports fr.unice.miage.common.plugins;
    exports fr.unice.miage.common.game_objects;
    exports fr.unice.miage.common.utils;
    exports fr.unice.miage.common.sprite;
    exports fr.unice.miage.common.geom;
    exports fr.unice.miage.common.input;
    exports fr.unice.miage.common;

}