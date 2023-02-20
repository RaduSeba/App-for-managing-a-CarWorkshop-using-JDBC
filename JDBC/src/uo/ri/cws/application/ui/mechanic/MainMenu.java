package uo.ri.cws.application.ui.mechanic;

import menu.BaseMenu;
import menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Mechanic", null },
			{ "List work orders ", 		NotYetImplementedAction.class }, 
			{ "Add spare part to a repair", 		NotYetImplementedAction.class },
			{ "Delete spare part from a repair", 	NotYetImplementedAction.class },
			{ "Close work order", 				NotYetImplementedAction.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
