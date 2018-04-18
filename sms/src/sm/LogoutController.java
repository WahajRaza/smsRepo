/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package sm;


import services.AuthenticationService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class LogoutController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	//services
	AuthenticationService authService = new AuthenticationServiceImpl();
	
	@Listen("onClick=#logout")
	public void doLogout(){
		authService.logout();		
		Executions.sendRedirect("/index.zul");
	}
}
