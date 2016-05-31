package com.br.emploeyes.listener;
 
import com.br.emploeyes.model.Employee; 
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler; 
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class AuthPhaseListener implements PhaseListener {

  private static final String RESTRICTION_PATTERN = "^/secured/.*";

  // array of strings forthe pages allowed only for admins
  private static final String RESTRICTION_PATTERN_ADMIN[] = {"^/secured/admin.xhtml", "^/secured/company_form.xhtml"};
  
  private static final String RESTRICTION_PATTERN_USER[] = {""};

  @Override
  public PhaseId getPhaseId() {
    return PhaseId.RESTORE_VIEW;
  }

  @Override
  public void beforePhase(PhaseEvent event) {
  }

  @Override
  public void afterPhase(PhaseEvent event) {
    FacesContext context = event.getFacesContext();
    String viewId = context.getViewRoot().getViewId();

    Employee user = (Employee) context.getExternalContext()
            .getSessionMap().get("user"); 

    boolean isAuthenticated = checkAuthentication(user, viewId);
    boolean isAuthorized = true;

    if (isAuthenticated && user != null) {
      isAuthorized = checkAuthorization(user, viewId);
    }

    if (!isAuthenticated || !isAuthorized) {
      NavigationHandler navigator = context
              .getApplication()
              .getNavigationHandler();

      ResourceBundle bundle = ResourceBundle.getBundle(
              "com.br.emploeyes.bundles.MessageBundle",
              context.getViewRoot().getLocale());

      if (!isAuthenticated) {
        context.addMessage(null, new FacesMessage(bundle.getString("error.loginrequired.message"))); 
      } else {
        context.addMessage(null, new FacesMessage(bundle.getString("error.notallowed.message"))); 
      }

      navigator.handleNavigation(context, null, "/index");
    }
  }

  private boolean checkAuthentication(Employee user, String viewId) {
    // check autentication
    boolean urlProtegida
            = Pattern.matches(RESTRICTION_PATTERN, viewId);

    return !(urlProtegida && user == null);
  }

  private boolean checkAuthorization(Employee user, String viewId) {
      System.out.print("user.getProfile():"+user.getProfile());
    switch (user.getProfile()) {
      case USUARIO:
        // Check restriction for other profiles than USUARIO        
        for (String urlPattern : RESTRICTION_PATTERN_ADMIN) {
          if (Pattern.matches(urlPattern, viewId)) {
            return false;
          }
        }
        break;
      case ADMIN:
        // Check restriction for other profiles than ADMIN
        for (String urlPattern : RESTRICTION_PATTERN_USER) {
          if (Pattern.matches(urlPattern, viewId)) {
            return false;
          }
        }
        break;
      default:
        // do nothing
        break;
      }

    // if not returned yet, so the user is allowed
    return true;
  }
}
