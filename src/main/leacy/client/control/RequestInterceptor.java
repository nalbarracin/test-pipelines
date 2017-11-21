package main.leacy.client.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import leacy.modelo.Usuario;
import main.leacy.client.bean.LoginBean;
import main.leacy.client.segurizable.Autorizador;

public class RequestInterceptor implements PhaseListener, Serializable {

	private static final long serialVersionUID = -8060053020563585360L;

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	
	public boolean sessionServidorExpirada(PhaseEvent event) {
		
		if (event.getFacesContext().getExternalContext().getSession(false) == null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			RequestContext rc = RequestContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) ec.getResponse();
			HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	
			if (ec.isResponseCommitted()) {
				// redirect is not possible
				return false;
			}
	
			try {
	
				if (((rc != null && RequestContext.getCurrentInstance()
						.isAjaxRequest()) || (fc != null && fc
						.getPartialViewContext().isPartialRequest()))
						&& fc.getResponseWriter() == null
						&& fc.getRenderKit() == null) {
	
					response.setCharacterEncoding(request.getCharacterEncoding());
	
					RenderKitFactory factory = (RenderKitFactory) FactoryFinder
							.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
	
					RenderKit renderKit = factory.getRenderKit(fc, fc
							.getApplication().getViewHandler()
							.calculateRenderKitId(fc));
	
					ResponseWriter responseWriter = renderKit.createResponseWriter(
							response.getWriter(), null,
							request.getCharacterEncoding());
					responseWriter = new PartialResponseWriter(responseWriter);
					fc.setResponseWriter(responseWriter);
										
					ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
					return true;
				}
	
			} catch (IOException e) {
				throw new FacesException(e);
			}
		}
		return false;
	}
	
	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext context = event.getFacesContext();
		
		if (sessionServidorExpirada(event)) {
			return;
		}

		if (context.getViewRoot() == null) {
			return;
		}
		
		// Check to see if they are on the login page.
		boolean loginPage = context.getViewRoot().getViewId().lastIndexOf("index") > -1 ? true : false;
		boolean expiredPage = context.getViewRoot().getViewId().lastIndexOf("sessionExpired") > -1 ? true : false;
		boolean aboutPage = context.getViewRoot().getViewId().lastIndexOf("about") > -1 ? true : false;
		
		ELContext elContext = FacesContext.getCurrentInstance()
				.getELContext();
		LoginBean loginBean = (LoginBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "loginBean");
		Usuario user = loginBean.getUsuario();

		if (!loginPage && !expiredPage && !aboutPage) {

			NavigationHandler nh = context.getApplication()
					.getNavigationHandler();
			
			if (user == null){
				nh.handleNavigation(context, null, "logout");
			}
			
			if (!loggedIn(user)) {
				
				nh.handleNavigation(context, null, "logout");
								
			} else if (isExpired(user)) {
				try {
					loginBean.logout();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				nh.handleNavigation(context, null, "logout");
				
			} else {
				loginBean.getUsuario().setLogin(new Date());
				
				HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				Autorizador autorizador = (Autorizador) context
						.getApplication().getELResolver()
						.getValue(elContext, null, "autorizador");
				
				if (!autorizador.autoriza(origRequest.getRequestURL().toString())){
					
					String viewId = "/navegacionNoAutorizadaSeguridad.xhtml";
					ViewHandler viewHandler = context.getApplication().getViewHandler();
					context.setViewRoot(viewHandler.createView(context, viewId));
					context.getPartialViewContext().setRenderAll(true);
					context.renderResponse();
				}
			}
		} else {
			if (loginPage && loggedIn(user)){
				context.getApplication().getNavigationHandler().handleNavigation(context, null, "login");
			}
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
		
		sessionServidorExpirada(event);
		
		FacesContext facesContext = event.getFacesContext();
        HttpServletResponse response = (HttpServletResponse) facesContext
                .getExternalContext().getResponse();
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        // Stronger according to blog comment below that references HTTP spec
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "must-revalidate");
        // some date in the past
        response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
		
	}

	private Boolean loggedIn(Usuario user) {
		return user != null;
	}

	private Boolean isExpired(Usuario user) {
		return user.isExpired();
	}
}
