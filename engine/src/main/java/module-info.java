module fr.unice.miage.engine{
    requires transitive javafx.controls;
    opens fr.unice.miage.engine.gui to javafx.graphics;
    //requires junit;
    requires fr.unice.miage.common;
    exports fr.unice.miage.engine;
}