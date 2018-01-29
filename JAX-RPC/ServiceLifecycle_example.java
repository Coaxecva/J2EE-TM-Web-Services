package com.jwsbook.jaxrpc;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.rpc.server.ServletEndpointContext;
import javax.xml.rpc.server.ServiceLifecycle;
import javax.xml.rpc.ServiceException;

public class BookQuote_Impl_5 implements BookQuote, ServiceLifecycle {
  javax.sql.DataSource dataSource;
  ServletEndpointContext endPtCntxt;

  public void init(Object context) throws ServiceException{
    try{
      endPtCntxt = (ServletEndpointContext)context;
      InitialContext jndiEnc = new InitialContext();
      javax.sql.DataSource dataSource = (javax.sql.DataSource)
                           jndiEnc.lookup("java:comp/env/jdbc/BookDatabase");
    }catch(NamingException ne){
      throw new ServiceException("Cannot initialize JNDI ENC", ne);
    }
  }
  public float getBookPrice(String isbn){
    java.sql.Connection jdbcConnection = null;
    java.sql.Statement sqlStatement = null;
    java.sql.ResultSet resultSet;
    try {
      jdbcConnection = dataSource.getConnection();
      sqlStatement = jdbcConnection.createStatement();
      resultSet = sqlStatement.executeQuery(
        "SELECT wholesale FROM CATALOG WHERE isbn = \'"+isbn+"\'");

      if(resultSet.next()){
        float price = resultSet.getFloat("wholesale");
        return price;
      }
      return 0;// zero means it's not stocked.
    }catch (java.sql.SQLException se) {
      throw new RuntimeException("JDBC access failed");
    }
  }
  public void destroy( ){
    dataSource = null;
  }
}
