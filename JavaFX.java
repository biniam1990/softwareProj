import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Lab6_prob1 extends Application {

	@Override
	public void start(Stage st) {
		st.setTitle("Address Form");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(5);
		grid.setPadding(new Insets(25, 25, 25, 25));


		Label name = new Label("Name");
		grid.add(name, 0, 0);
		TextField nameField = new TextField();
		grid.add(nameField, 0, 1);

		Label street = new Label("Street");
		grid.add(street, 2, 0);
		TextField streetField = new TextField();
		grid.add(streetField, 2, 1);
		
		Label city = new Label("City");
		grid.add(city, 4, 0);
		TextField cityField = new TextField();
		grid.add(cityField, 4, 1);
		
		Label state = new Label("State");
		grid.add(state, 0, 3);
		TextField stateField = new TextField();
		grid.add(stateField, 0, 4);
		
		Label zip = new Label("Zip");
		grid.add(zip, 2, 3);
		TextField zipField = new TextField();
		grid.add(zipField, 2, 4);

		Button btn = new Button("Submit");
        HBox hbxBtn = new HBox(10);
        hbxBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbxBtn.getChildren().add(btn);
        grid.add(hbxBtn, 2, 14);
        btn.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				System.out.println(nameField.getText());
				System.out.println(streetField.getText());
				System.out.print(cityField.getText());
				System.out.print(" " + stateField.getText());
				System.out.print(" " + zipField.getText());
			}
		});
        
        
		Scene scene = new Scene(grid);
		st.setScene(scene);
		st.show();
	}
	public static void main (String [] args){
		Application.launch(args);
	}

}
