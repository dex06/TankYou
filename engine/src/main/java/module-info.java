module fr.unice.miage.engine{
    requires transitive fr.unice.miage.common;
    //requires javafx.controls;
    opens fr.unice.miage.engine.gui to javafx.graphics;
    //requires junit;

    exports fr.unice.miage.engine;
}