Create Db:
	- auction
	- id, pass : gla 

Create connection pool
	Pool Name: auctionPool
	Resource Type: javax.sql.DataSource
	Driver Vendor: JavaDB
	
	Ping: enabled
	User: gla
	DatabaseName: auction
	Password: gla
Create JDBC Resource
	JNDI Name: jdbc/auction
	Pool Name: auctionPool


Project Work:
Create Entities - (models)
Users:
	- id, userId, pass, name
Item:
	- id, name, description, starting price, duration_of_auction (/end date)
Category:
	- id, categoryName, itemId
Subcategory:
	- id, subcategoryName, itemId
Bid:
	- id, itemId, bidAmount, bidder
Generate Getter & setter and blah blah


Create DAO (data access object) Components - 
“OR” EJB EntityManagerBean
	EnterpriseJavaBeans >  sessionBeans 
	(business logic for models)
	UserDAO, ItemDAO, CategoryDAO, SubcategoryDAO, BidDAO
	Add, Edit, Delete, get, getAll

Create Persistence unit
	- JNDI Name: jdbc/auction
	- select database - auction
	- drop and create
	
Add @PersistenceContext, EntityManager in DAO class to manage the entities from DB
Add, edit, delete, get, getAll

Create ControllerServlet
	Add Servlet
	- ItemServlet
		- @EJB => DAOLocal (***Dependency Injection***)
			
Create WebPage - index.jsp (view)
