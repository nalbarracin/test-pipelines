package main.leacy.client.exception.handler;

import java.io.IOException;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
	private ExceptionHandler wrapped;

	public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
		this.wrapped = exceptionHandler;
		//LogManager.getLogManager().getLogger("javax.enterprise.resource.webcontainer.jsf.lifecycle").setLevel(Level.SEVERE);
		//LogManager.getLogManager().getLogger("javax.enterprise.resource.webcontainer.jsf.application").setLevel(Level.SEVERE);
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		
		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			Throwable t = context.getException();
			
			if(t.getClass().equals(ViewExpiredException.class)){
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
					Mensaje.addMessageError("Sesion Expirada", "La sesión ha expirado. Por favor ingrese nuevamente.");
				} catch (IOException e) {
					Mensaje.addMessageError("Error: ", e.getMessage());	
				}
				return;
			}

			while ((t instanceof FacesException || t instanceof EJBException || t instanceof ELException) && t.getCause() != null) {
				t = t.getCause();
			}
			
			Mensaje.addMessageError("Error: ", t.getMessage());			
		}
	}
}
