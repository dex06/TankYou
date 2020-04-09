package fr.unice.miage.game.gui;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;

import java.awt.*;

public class HealthBar {

    private Panel healthBar = new Panel();
    private ProgressBar progressBar = new ProgressBar(1);
    private ProgressIndicator progressIndicator = new ProgressIndicator(1);
    private FlowPane root = new FlowPane();
    private int width = 50;
    private int height = 10;



    public HealthBar(){
        this.init();
    }

    public void init(){
        //healthBar.setBounds(this.x, this.y,this.width, this.height);
        //healthBar.setBackground(Color.BLUE);
        progressBar.setPrefSize(this.width,this.height);
        root.getChildren().addAll(this.progressBar, this.progressIndicator);
    }
    public FlowPane getHealthBarPanel(){
        return this.root;
    }

    public void bindProgressProperty(ObservableValue<Double> health){
        //this.progressBar.progressProperty().bind(health);
        //this.progressIndicator.progressProperty().bind(health);
        health.addListener((observableValue, aDouble, t1) -> progressBar.progressProperty().bind(health));
        this.progressBar.progressProperty().addListener((observableValue, aDouble, t1) -> progressBar.progressProperty().bind(health));
    }
}
