package com.jwsbook.jaxrpc;
import javax.ejb.SessionContext;

public class BookQuoteBean_1 implements javax.ejb.SessionBean {
  public void ejbCreate( ){}
  
  public float getBookPrice(String isbn) {
    return 24.99f;    
  }
  
  public void setSessionContext(SessionContext cntxt) {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void ejbRemove(){}
}
