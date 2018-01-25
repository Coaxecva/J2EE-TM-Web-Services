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

//Using the JNDI ENC to Access a JMS ConnectionFactory
javax.naming.InitialContext jndiEnc = new javax.naming.InitialContext();
javax.jms.ConnectionFactory conFactory =(javax.jms.ConnectionFactory)
                      jndiEnc.lookup("java:comp/env/jms/ConnectionFactory");
javax.jms.Connection connection = conFactory.createConnection(username,password);

//Using the JNDI ENC for Access to a JavaMail Session Object
javax.naming.InitialContext jndiEnc = new javax.naming.InitialContext();
javax.mail.Session session = (javax.mail.Session)
                      jndiEnc.lookup("java:comp/env/mail/Session");
javax.mail.internet.MimeMessage email = new
javax.mail.internet.MimeMessage(session);

//Using the JNDI ENC for Access to an Arbitrary J2EE Connector
javax.naming.InitialContext jndiEnc = new javax.naming.InitialContext()
javax.resource.cci.ConnectionFactory factory = (javax.resource.cci.ConnectionFactory)
                      jndiEnc.lookup("java:comp/env/connector/VendorX");
javax.resource.cci.Connection con = factory.getConnection();
