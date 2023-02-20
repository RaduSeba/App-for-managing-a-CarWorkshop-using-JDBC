package uo.ri.cws.application.ui.manager;

import menu.BaseMenu;
import menu.NotYetImplementedAction;
import uo.ri.cws.application.ui.manager.action.contractTypeManagement.AddContractTypeAction;
import uo.ri.cws.application.ui.manager.action.contractTypeManagement.UpdateContractTypeAction;

public class ContractTypeMenu extends BaseMenu {

    public ContractTypeMenu() {
	menuOptions = new Object[][] {
		{ "Manager > contract type type management", null },

		{ "Add contract type ", AddContractTypeAction.class },
		{ "Update contract type ", UpdateContractTypeAction.class },
		{ "Delete contract type ", NotYetImplementedAction.class },
		{ "List all contract types", NotYetImplementedAction.class }, };
    }

}
