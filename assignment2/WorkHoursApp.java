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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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

        try {
        	//This is to point it to the work file txt
        	File file = new File("src/work_files.txt");
        	//This checks if the file exist before trying to read it
        	if (file.exists()) {
        		//This creates a scanner to read the data from the file
        		Scanner input = new Scanner(file);
        		//This reads and displays each line from the file
        		while (input.hasNextLine()) {
        			taOutput.appendText(input.nextLine() + "\n");
        		}
        		//This closes the scanner when its done reading 
        		input.close();
        	}
        }catch (FileNotFoundException e) {
        	//This displays if the file is not found
            taOutput.appendText("[ERROR] File not found.\n");
        }
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
        	
        	//Set a trimmed version of hours for later use
        	String hours = tfHours.getText().trim();
        	//Following order of 
        	if(tfName.getText().trim().isEmpty()) {
        		taOutput.appendText("[ERROR] Enter a invalid name");
        		return;
        	} else if(tfRole.getText().trim().isEmpty()) {
        		taOutput.appendText("[ERROR] Enter a invalid Role");
        		return;
        		
        	} else if(hours.isEmpty()) {//
        		taOutput.appendText("[ERROR] Hours are Invalid");
        		return;
        	}
        	
        	//try-catch block to validate number of hours employee has
        	try {
        		int hour = Integer.parseInt(hours);
        		
        		if (hour <= 0) {
        			taOutput.appendText("[ERROR] Must have more then 0 hours");
        			return;
        		}
        		//This creates a formatted string that contains the name, role and hours 
        		String entry = tfName.getText().trim()+", "+tfRole.getText().trim()+", "+hour;
        		//This prints the input into the work files txt and the true is to make sure the append mode exist
        		PrintWriter output = new PrintWriter(new FileWriter("src/work_files.txt", true));
        		//This writes entry into the file txt
        	    output.println(entry);
        	    //This closes the txt so it can save and release the resources
        	    output.close();
        	    taOutput.appendText(entry + "\n");
        		taOutput.appendText("EMPLOYEE DATA LOGGED. . .");
        		tfName.clear(); //This clears the fields so that we don't have to delete the characters 
        		tfRole.clear(); //Same as above but for roles
        		tfHours.clear(); //Same as above but for hours
        	} catch (NumberFormatException e) {
        		taOutput.appendText("[ERROR] hour must be a valid number.\n");
        	}
        	 catch (IOException e) {
        	    taOutput.appendText("[ERROR] Could not write to file.\n");
        	}

        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
