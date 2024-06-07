package com.example.projektsijad;

import com.example.projektsijad.Algorithms.Progress;
import com.example.projektsijad.Algorithms.Regress;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public void loadRules(ActionEvent actionEvent) {
        Stage stage = (Stage) rulesDirectory.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Otwórz plik");

        File file = fileChooser.showOpenDialog(stage);

        this.loadGoals(file);

        rulesDirectory.setText(file.getAbsolutePath());
    }

    public void loadFacts(ActionEvent actionEvent) {
        Stage stage = (Stage) factsDirectory.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Otwórz plik");

        File file = fileChooser.showOpenDialog(stage);

        factsDirectory.setText(file.getAbsolutePath());
    }

    private void loadGoals(File file) {
        try {
            Scanner scanner = new Scanner(file);

            List<String> goals = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String head = line.split("<-")[0];

                if (!goals.contains(head)) {
                    goals.add(head);
                }
            }

            goalsChoiceBox.setItems(FXCollections.observableArrayList(goals));

            scanner.close();
        } catch (FileNotFoundException e) {

        }
    }

    public void generateFacts(ActionEvent actionEvent) {
        String rulesDir = rulesDirectory.getText();
        String factsDir = factsDirectory.getText();

        if (rulesDir.isEmpty() || factsDir.isEmpty()) {
            return;
        }

        Progress progress = new Progress();

        progress.loadFactSet(factsDir);
        progress.loadKnowledgeBaseSet(rulesDir);

        List<String> list = progress.execute();

        list.forEach(element -> {
            resultArea.appendText(element + "\n");
        });
    }

    public void findGoal(ActionEvent actionEvent) {
        String rulesDir = rulesDirectory.getText();
        String factsDir = factsDirectory.getText();
        String goal = (String) goalsChoiceBox.getValue();

        if (rulesDir.isEmpty() || factsDir.isEmpty() || goal == null) {
            return;
        }

        Regress regress = new Regress();

        regress.loadFactSet(factsDir);
        regress.loadKnowledgeBaseSet(rulesDir);

        boolean result = regress.execute(goal);

        if (result) {
            resultArea.appendText(goal + " SPEŁNIONE :D");
        } else {
            resultArea.appendText(goal + " NIESPEŁNIONE D:");
        }
    }

    @FXML
    TextField rulesDirectory = new TextField();

    @FXML
    TextField factsDirectory = new TextField();

    @FXML
    ChoiceBox goalsChoiceBox = new ChoiceBox();
    
    @FXML
    TextArea resultArea = new TextArea();
}
