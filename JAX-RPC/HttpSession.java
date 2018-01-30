// Using an HttpSession to Cache Session-SpecificData

package com.jwsbook.jaxrpc;
...
public class InternationalJSE_Impl implements InternationalJSE, javax.xml.rpc.server
graphics/ccc.gif.ServiceLifecycle {
  javax.sql.DataSource dataSource;
  ServletEndpointContext servletEndpointContext;

  public void init(Object context) throws ServiceException{
    ...
  }
  public void destroy(){
    ...
  }
  public void someMethod( ) {
    HttpSession httpSession = servletEndpointContext.getHttpSession();
    Principal principal = servletEndpointContext.getUserPrincipal();
    if( httpSession != null){
      // Get preferences from HttpSession object
      String language_preference = (String)
                                httpSession.getAttribute("language");
      String currency_preference = (String)
                                httpSession.getAttribute("currency");
      if( language_preference == null || currency_preference == null) {
        // Get preferences from database and initialize session data
        java.sql.Connection jdbcConnection = null;
        java.sql.Statement sqlStatement = null;
        java.sql.ResultSet resultSet;
        try {
          jdbcConnection = dataSource.getConnection();
          sqlStatement = jdbcConnection.createStatement();
          resultSet = sqlStatement.executeQuery(
                     "SELECT language, currancy FROM PREFERENCES "+
                     "WHERE Principal = "+principal.getName());
          if(resultSet.next()){
            language_preference = resultSet.getString("language");
            currency_preference = resultSet.getString("currency");
            // set attributes on HttpSession to avoid DB access.
            httpSession.setAttribute("language", language_preference);
            httpSession.setAttribute("language", currency_preference);
          }
        }catch (java.sql.SQLException se) {
            // handle SQLException
        }
      }
    }
    // Use the language and currency preferences in further processing
  }
}
