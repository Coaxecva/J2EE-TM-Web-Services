package javax.xml.rpc.server;
public interface ServletEndpointContext {
  public java.security.Principal getUserPrincipal();
  public boolean isUserInRole(String role);
  public javax.xml.rpc.handler.MessageContext getMessageContext();
  public javax.Servlet.http.HttpSession getHttpSession()
      throws javax.xml.rpc.JAXRPCException;
  public javax.Servlet.ServletContext getServletContext();
}
