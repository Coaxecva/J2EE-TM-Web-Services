package jwsed1.support;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import java.util.Date;
import java.security.Principal;
public class MoneyTransferEJB implements javax.ejb.SessionBean {
  SessionContext ejbContext;
  public void create( ){}
  public void transfer(int acctA, int acctB, double amount){
    try{
      if(amount > 100000.0d && !ejbContext.isCallerInRole("BankManager")){
        Principal principal = ejbContext.getCallerPrincipal();
        throw new AuthorizationException(principal.getName() + " is not authorized to transfer over $100,000.00");
        InitialContext jndiEnc = new InitialContext( );
      }
      AccountHomeLocal acctHome = (AccountHomeLocal) jndiEnc.lookup("java:comp/env/ejb/AccountHome")
      AccountLocal accountA = acctHome.findByPrimarykey( acctA );
      AccountLocal accountB = acctHome.findByPrimarykey( acctB );
      accountA.withdraw( amount );
      accountB.deposit( amount );      
      TransferAuditHomeLocal taHome = (TransferAuditHomeLocal) jndiEnc.lookup("java:comp/env/ejb/TransferAuditHomeLocal")
      TransferAuditLocal audit = taHome.createAudit( accountA, accountB, amount, new Date());
    }catch(Exception e){
      throw new javax.ejb.EJBException(e);
    }
  }
...
}

// The MoneyTransfer EJB finds the entity beans that represent Accounts A and B, and then proceeds to withdraw $100.00 from A 
// and deposit $100.00 in B. After transferring the funds, the MoneyTransfer EJB creates a new TransferAudit EJB to represent
// the transfer. Each of the entity beans used in this example represents a row in a relational database table, which is how 
// entities usually map to databases. Withdrawing or depositing money in an account effectively performs a database UPDATE, 
// subtracting an amount from or adding it to the balance column of the ACCOUNT table. Creating a new TransferAudit EJB causes 
// a database INSERT to add a new row in the TRANSFER_AUDIT table.
