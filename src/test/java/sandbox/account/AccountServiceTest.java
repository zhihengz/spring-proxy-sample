package sandbox.account;

import static org.junit.Assert.*;

import org.junit.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountServiceTest {

    private ApplicationContext context = null;
    private AccountService accountService = null;

    @Before
    public void setUp() {

        context = new ClassPathXmlApplicationContext( new String[] {
                "account-context.xml"
            } );
        
        accountService = (AccountService) context.getBean( "accountService" );
    }

    @Test
    public void testCreate() {
        
        Account account = new Account();
        account.setName( "jason" );
        accountService.create( 1L, account );
        assertNotNull( accountService.read( 1L ) );
    }
}
