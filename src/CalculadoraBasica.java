import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculadoraBasica extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora Básica");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(grid, 300, 200);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        TextField textFieldX = new TextField();
        TextField textFieldY = new TextField();
        Label resultLabel = new Label("Resultado:");

        Button addButton = new Button("+");
        Button subButton = new Button("-");
        Button mulButton = new Button("x");
        Button divButton = new Button("/");

        addButton.getStyleClass().add("button");
        subButton.getStyleClass().add("button");
        mulButton.getStyleClass().add("button");
        divButton.getStyleClass().add("button");

        addButton.setOnAction(e -> performOperation("+", textFieldX, textFieldY, resultLabel));
        subButton.setOnAction(e -> performOperation("-", textFieldX, textFieldY, resultLabel));
        mulButton.setOnAction(e -> performOperation("*", textFieldX, textFieldY, resultLabel));
        divButton.setOnAction(e -> performOperation("/", textFieldX, textFieldY, resultLabel));

        VBox resultContainer = new VBox();
        resultContainer.getStyleClass().add("result-container");

        resultContainer.getChildren().add(resultLabel);

        grid.add(textFieldX, 0, 0);
        grid.add(textFieldY, 1, 0);
        grid.add(addButton, 0, 1);
        grid.add(subButton, 1, 1);
        grid.add(mulButton, 0, 2);
        grid.add(divButton, 1, 2);
        grid.add(resultContainer, 0, 4, 2, 1);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void performOperation(String operator, TextField textFieldX, TextField textFieldY, Label resultLabel) {
        String xStr = textFieldX.getText();
        String yStr = textFieldY.getText();
    
        if (xStr.isEmpty() || yStr.isEmpty()) {
            resultLabel.setText("Por favor, preencha ambos os campos.");
            return;
        }
    
        try {
            double x = Double.parseDouble(xStr);
            double y = Double.parseDouble(yStr);
    
            double result = 0;
            switch (operator) {
                case "+":
                    result = x + y;
                    break;
                case "-":
                    result = x - y;
                    break;
                case "*":
                    result = x * y;
                    break;
                case "/":
                    if (y != 0) {
                        result = x / y;
                    } else {
                        resultLabel.setText("Divisão por zero não é válido");
                        return;
                    }
                    break;
            }
    
            if (result == (int) result) {
                resultLabel.setText("Resultado: " + String.format("%.0f", result));
            } else {
                resultLabel.setText("Resultado: " + String.format("%.2f", result).replace(".", ","));
            }
    
            resultLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px");
        } catch (NumberFormatException e) {
            resultLabel.setText("Entradas inválidas. Certifique-se de inserir números válidos.");
        }
    }
    
}
