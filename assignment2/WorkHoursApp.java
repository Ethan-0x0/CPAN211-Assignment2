package assignment2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WorkHoursApp extends Application {

    // GUI components are declared as fields
    // so the event handler class (Member 2) can use them
    private TextField tfName = new TextField();
    private TextField tfRole = new TextField();
    private TextField tfHours = new TextField();
    private Button btSubmit = new Button("Submit");
    private TextArea taOutput = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        // title label at the top
        Label lblTitle = new Label("Work Hours Management System");
        lblTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));

        // GridPane for the input form (labels in column 0, text fields in column 1)
        GridPane formPane = new GridPane();
        formPane.setAlignment(Pos.CENTER);
        formPane.setHgap(10);
        formPane.setVgap(10);

        formPane.add(new Label("Name:"), 0, 0);
        formPane.add(tfName, 1, 0);
        formPane.add(new Label("Role (Professor, TA, Student):"), 0, 1);
        formPane.add(tfRole, 1, 1);
        formPane.add(new Label("Hours Worked:"), 0, 2);
        formPane.add(tfHours, 1, 2);
        formPane.add(btSubmit, 1, 3);

        // put the Submit button on the right side of its cell
        GridPane.setHalignment(btSubmit, HPos.RIGHT);

        // register the event handler for the Submit button
        btSubmit.setOnAction(new SubmitHandler());

        // VBox for the whole layout: title, form, then the output area
        VBox root = new VBox(15);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setStyle("-fx-alignment: center;");
        root.getChildren().addAll(lblTitle, formPane, new Label("Entries:"), taOutput);

        // TODO (Member 3): load the existing entries from work_hours.txt
        // and show them in taOutput when the program starts

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Work Hours Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // event handler for the Submit button
    class SubmitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // TODO (Member 2): check the input fields,
            // show error messages or add the new entry to taOutput,
            // and save the entry to the file
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
