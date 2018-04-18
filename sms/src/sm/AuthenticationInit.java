/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package sm;

import java.util.Map;

import services.AuthenticationService;
import services.UserCredential;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class AuthenticationInit implements Initiator {

	// services
	AuthenticationService authService = new AuthenticationServiceImpl();

	public void doInit(Page page, Map<String, Object> args) throws Exception {

		UserCredential cre = authService.getUserCredential();
		if(page.getRequestPath().equals("/index.zul")) {
		if ((cre == null || cre.isAnonymous())) {
			Executions.sendRedirect("/login.zul");
			return;
		}
	}
		else if(page.getRequestPath().equals("/login.zul")) {
			if ((cre != null && !cre.isAnonymous())) {
				authService.logout();
				Executions.sendRedirect("/index.zul");
				return;
			}
		}
		}
}