public interface BookQuote extends java.rmi.Remote {
  public float getBookPrice(String isbn)
    throws java.rmi.RemoteException;
}

public class BookQuote_Impl_1 implements BookQuote {
  // Given the ISBN of a book, get its wholesale price.
  public float getBookPrice(String isbn){
    return 24.99f;
  }
}

// An EJB Endpoint's Stateless Session Bean
public class BookPriceWS implements javax.ejb.SessionBean, BookQuote {
  public void setSessionContext(javax.ejb.SessionContext ctx){}
  public void ejbCreate(){}
  public float getBookPrice(String isbn){
    return 24.99f;
  }
  public void ejbRemove(){}
  public void ejbActivate(){}
  public void ejbPassivate(){}
}


/////////////////////////////////////////////////////////////////////////////////////////////
//a J2EEcomponent uses JAX-RPC DII to get the wholesale price of a book from someWeb service.
/////////////////////////////////////////////////////////////////////////////////////////////
InitialContext jndiContext = new InitialContext ( );
javax.xml.rpc.Service service = (javax.xml.rpc.Service)
                                jndiContext.lookup("java:comp/env/service/DynamicService")
QName port = new QName("http://www.xyz.com/BookQuote ","BookQuote");
QName operation = new QName("http://www.xyz.com/BookQuote","getBookPrice");
Call callObject = service.createCall(port, operation);
Object [] parameters = new Object[1];
parameters[0] = isbn;
Float price = (Float) callObject.invoke( parameters );
