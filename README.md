a sample project to show how to use spring aop to provide generic operation 
over domain objects.

`GenericCRUDService` provide a generic CRUD operation over objects with 
primary key. It can be any operation as long as there is a need over
different domain objects.

`GenericCRUDServiceImpl` is an implemenation on CRUD service, it does not
care about what domain object T and primary key K it is.

`AccountService` is the domain specific CRUD service interface on `Account`
object.

We are using spring `ProxyFactoryBean` to create a proxy to bridge call on
`AccountService` to `GenericCRUDServiceImpl`, A finder method interceptor
is injected so that we can provide findByXXX functions where XXX is the
bean property name

`account-context.xml` is spring context to wire up everything.

`AccountServiceTest` is sample code on how it works


--Jason

