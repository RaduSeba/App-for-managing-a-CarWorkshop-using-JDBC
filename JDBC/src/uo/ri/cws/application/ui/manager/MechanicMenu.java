package uo.ri.cws.application.ui.manager;

import menu.BaseMenu;
import menu.NotYetImplementedAction;
import uo.ri.cws.application.ui.manager.action.mechanicManagement.AddMechanicAction;
import uo.ri.cws.application.ui.manager.action.mechanicManagement.DeleteMechanicAction;
import uo.ri.cws.application.ui.manager.action.mechanicManagement.FindAllMechanicsAction;
import uo.ri.cws.application.ui.manager.action.mechanicManagement.FindMechanicAction;
import uo.ri.cws.application.ui.manager.action.mechanicManagement.FindMechanicByIdAction;
import uo.ri.cws.application.ui.manager.action.mechanicManagement.UpdateMechanicAction;

public class MechanicMenu extends BaseMenu {

    public MechanicMenu() {
	menuOptions = new Object[][] {
		{ "Manager > Mechanics management", null },

		{ "Add mechanic", AddMechanicAction.class },
		{ "Update mechanic", UpdateMechanicAction.class },
		{ "Delete mechanic", DeleteMechanicAction.class },
		{ "List mechanic by dni", FindMechanicAction.class },
		{"List mechanic by id",FindMechanicByIdAction.class},
		{ "List mechanics", FindAllMechanicsAction.class },
		{ "List mechanics with contract in force",
			NotYetImplementedAction.class }, };
    }

}