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
