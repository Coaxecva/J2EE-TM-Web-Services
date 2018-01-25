//A JSE Using the JNDI ENC to Get a JDBC Connection

package com.jwsbook.jaxrpc;
public class BookQuote_Impl_2 implements BookQuote {
  public float getBookPrice(String isbn){
    java.sql.Connection jdbcConnection = null;
    java.sql.Statement sqlStatement = null;
    java.sql.ResultSet resultSet;
    try {
      javax.naming.InitialContext jndiEnc =  new javax.naming.InitialContext();
      javax.sql.DataSource dataSource = (javax.sql.DataSource)
      jndiEnc.lookup("java:comp/env/jdbc/DataSource");
      jdbcConnection = dataSource.getConnection();
      sqlStatement = jdbcConnection.createStatement();
      resultSet = sqlStatement.executeQuery(
      "SELECT wholesale FROM CATALOG WHERE isbn = \'"+isbn+"\'");
      if(resultSet.next()){
        float price = resultSet.getFloat("wholesale");
        return price;
      }
      return 0;// zero means it's not stocked.
    } catch (java.sql.SQLException se) {
      throw new RuntimeException("JDBC access failed");
    } catch (javax.naming.NamingException ne){
      throw new RuntimeException("JNDI ENC access failed");
    }
  }
}
