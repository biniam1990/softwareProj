package print;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Control {
	private GUI gui;
	
	public Control(GUI gui) {
		this.gui = gui;
		
	}
	private class PrintHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			String enteredText = gui.getText();
			new Printer().printText(enteredText);
			gui.displayMessage("Data printed to console");
			
		}
	}
	public PrintHandler getPrintHandler() {
		return new PrintHandler();
	}
}
