module fr.unice.miage.engine{
    requires transitive fr.unice.miage.common;
    opens fr.unice.miage.engine.gui to javafx.graphics;
    exports fr.unice.miage.engine;
}