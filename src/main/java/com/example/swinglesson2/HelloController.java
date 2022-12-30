package com.example.swinglesson2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    public void initialize(){
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }
    @FXML
    private TextField nameField;
    @FXML
    private Label ourLabel;

    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;


    public void onButtonClicked(ActionEvent e){
        if(e.getSource().equals(helloButton)){
            System.out.println("Hello, " + nameField.getText());
        } else if(e.getSource().equals(byeButton)){
            System.out.println("Bye, " + nameField.getText());
        }
        if(ourCheckBox.isSelected()){
            nameField.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                    System.out.println("I'm going to sleep on the " + s);
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                                public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                            System.out.println("I'm updating the label on the " + s);
                            ourLabel.setText("We did something");
                        }

                    });

                } catch (InterruptedException event) {
                    //we dont care
                }

            }
        };
        new Thread(task).start();

    }
    @FXML
    public void handleKeyReleased(){
        String text = nameField.getText();
        boolean disabledButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disabledButtons);
        byeButton.setDisable(disabledButtons);
    }
    public void handleChange(){
        System.out.println("The checkbox is " +(ourCheckBox.isSelected() ? "checked":"not checked"));

    }
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}