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

    @Test
    public void testFindByName() {

        for ( int i = 0; i < 2; i++ ) {
            Account account = new Account();
            account.setName( "jason" );
            accountService.create( new Long( i ), account );
        }
        assertEquals( 2, accountService.findByName( "jason" ).size() );
    }

    @Test
    public void testFindByAge() {

        for ( int i = 0; i < 2; i++ ) {
            Account account = new Account();
            account.setName( "jason" );
            account.setAge( i + 10 );
            accountService.create( new Long( i ), account );
        }
        assertEquals( 1, accountService.findByAge( 10 ).size() );
    }
}
