package sandbox.account;

public class Account {
    
    private String name;
    private Integer age =new Integer( 1 );

    public void setName( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge( Integer age ) {
        this.age = age;
    }

    public Integer getAge( ) {
        return age;
    }
}
