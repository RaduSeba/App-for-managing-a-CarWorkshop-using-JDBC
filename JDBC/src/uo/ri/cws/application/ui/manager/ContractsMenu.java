
  package uo.ri.cws.application.ui.manager;
  
  import menu.BaseMenu;
import
  uo.ri.cws.application.ui.manager.action.contractManagement.AddContractAction;
/*import uo.ri.cws.application.ui.manager.action.contractManagement.
	DeleteContractAction; import
	uo.ri.cws.application.ui.manager.action.contractManagement.
	ListAllContractsAction; import
	uo.ri.cws.application.ui.manager.action.contractManagement.
	ListContractByIdAction; import
	uo.ri.cws.application.ui.manager.action.contractManagement.
	ListContractsByMechanicDniAction; import
	uo.ri.cws.application.ui.manager.action.contractManagement.
	UpdateContractAction;*/
import uo.ri.cws.application.ui.manager.action.contractManagement.DeleteContractAction;
import uo.ri.cws.application.ui.manager.action.contractManagement.FinishContractAction;
import uo.ri.cws.application.ui.manager.action.contractManagement.ListAllContractsAction;
import uo.ri.cws.application.ui.manager.action.contractManagement.ListContractByIdAction;
import uo.ri.cws.application.ui.manager.action.contractManagement.ListContractsByMechanicDniAction;
import uo.ri.cws.application.ui.manager.action.contractManagement.UpdateContractAction;
  
 /**
	 * Clase que muestra por pantalla las distintas operaciones que puedes hacer con
	 * los contratos
	 * 
	 * @author Carlos
	 *
	 */

  public class ContractsMenu extends BaseMenu {
  
 /**
	 * Menu que muestra las distintas operaciones que puedes hacer con los contratos
	 */
		  public ContractsMenu() { menuOptions = new Object[][] { {
		  "Manager > Contracts management", null },
		  
		  { "Create contract", AddContractAction.class },
		  { "Update contract", UpdateContractAction.class },
		 { "Delete contract", DeleteContractAction.class
		  }, { "Display all contracts", ListAllContractsAction.class },
		 {"Display contract by mechanic", ListContractsByMechanicDniAction.class },
		 {"Terminate a contract",FinishContractAction.class},
		 {	"Display contract by id", ListContractByIdAction.class } }; } }
		 