/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package sm;

import services.AuthenticationService;
import services.UserCredential;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class LoginController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	//wire components
	@Wire
	Textbox account;
	@Wire
	Textbox password;
	@Wire
	Label message;
	
	//services
	AuthenticationService authService = new AuthenticationServiceImpl();

	
	@Listen("onClick=#login; onOK=#loginWin")
	public void doLogin(){
		String nm = account.getValue();
		String pd = password.getValue();
		
		if(!authService.login(nm,pd)){
			message.setValue("account or password are not correct.");
			return;
		}
		UserCredential cre= authService.getUserCredential();
		message.setValue("Welcome, "+cre.getName());
		message.setSclass("");		
		Executions.sendRedirect("/index.zul");
		
	}
}
