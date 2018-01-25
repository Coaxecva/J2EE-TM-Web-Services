InitialContext jndiContext = new InitialContext ( );
javax.xml.rpc.Service service = (javax.xml.rpc.Service)
                                 jndiContext.lookup("java:comp/env/service/DynamicServi
QName port = new QName("http://www.xyz.com/BookQuote ","BookQuote");
QName operation = new QName("http://www.xyz.com/BookQuote","getBookPrice");
Call callObject = service.createCall(port, operation);
Object [] parameters = new Object[1];
parameters[0] = isbn;
Float price = (Float) callObject.invoke( parameters );
