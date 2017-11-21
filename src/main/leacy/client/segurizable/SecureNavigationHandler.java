package main.leacy.client.segurizable;

import java.io.Serializable;

import javax.el.ELContext;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

public class SecureNavigationHandler extends NavigationHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6646898769992583382L;
	private NavigationHandler handler;

	public SecureNavigationHandler(NavigationHandler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public void handleNavigation(FacesContext context, String fromAction, String outcome) {
		ELContext elContext = context.getELContext();
		Autorizador autorizador = (Autorizador) context.getApplication().getELResolver()
				.getValue(elContext, null, "autorizador");
//		handler.handleNavigation(context, fromAction, outcome);
		
		if (outcome == null
				|| "".contentEquals(outcome)
				|| "logout".contentEquals(outcome)
				|| "sessionExpired".contentEquals(outcome)
				|| "inicio".contentEquals(outcome)
				|| "about".contentEquals(outcome)
				|| "login".contentEquals(outcome)
				|| "navegacionNoAutorizadaSeguridad".contentEquals(outcome)
				|| autorizador.autoriza(outcome)) {
			handler.handleNavigation(context, fromAction, outcome);
		} /*else { TODO
			throw new NavegacionNoAutorizadaException();
		}	*/
	}
}
