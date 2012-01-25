package sandbox.account;

import java.util.List;
import sandbox.GenericCRUDService;

public interface AccountService extends GenericCRUDService<Account, Long> {

    public List<Account> findByName( String name );

    public List<Account> findByAge( Integer age );
}
