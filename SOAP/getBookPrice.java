package com.jwsbook.soap;
import java.rmi.RemoteException;
public interface BookQuote extends java.rmi.Remote {
  // Get the wholesale price of a book
  public float getBookPrice(String ISBN)
  throws RemoteException, InvalidISBNException;
}

/* An RPC/Literal SOAP Request Message */
/*
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope
  xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:mh="http://www.Monson-Haefel.com/jwsbook/BookQuote">
  <soap:Body>
    <mh:getBookPrice>
      <isbn>0321146182</isbn>
    </mh:getBookPrice>
  </soap:Body>
</soap:Envelope>
*/

/* An RPC/Literal SOAP Response Message */
/*
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope
  xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:mh="http://www.Monson-Haefel.com/jwsbook/BookQuote" >
    <soap:Body>
      <mh:getBookPriceResponse>
        <result>24.99</result>
      </mh:getBookPriceResponse>
    </soap:Body>
</soap:Envelope>
*/
