#Hibernate

[hibernate ORM documentation](http://hibernate.org/orm/documentation/5.0/)

[Hibernate Domain Model Mapping Guide](http://docs.jboss.org/hibernate/orm/5.0/mappingGuide/en-US/html_single/)

## hibernate.hbm2ddl.auto
-     Automatically validates or exports schema DDL to the database when the SessionFactory is created. With create-drop, the database schema will be dropped when the SessionFactory is closed explicitly.

```
e.g. validate | update | create | create-drop
    validate:       validate the schema, makes no changes to the database.
    update:         update the schema. {Not best for production}
    create:         creates the schema, destroying previous data.
    create-drop:    drop the schema at the end of the session.
```
## hibernate.show_sql
-    which specifies whether the sql queries will be shown in the console or the logger

##set tables to UFT-8
```
        properties.put("hibernate.connection.charSet", "UTF-8");
        properties.put("hibernate.connection.characterEncoding", "UTF-8");
        properties.put("hibernate.connection.useUnicode", "true");
```

##Set column:

```
@Column(
    name="columnName";
    boolean unique() default false;
    boolean nullable() default true;
    boolean insertable() default true;
    boolean updatable() default true;
    String columnDefinition() default "";
    String table() default "";
    int length() default 255;
    int precision() default 0; // decimal precision
    int scale() default 0; // decimal scale
```

> 1.	name (optional): the column name (default to the property name)
> 2.	unique (optional): set a unique constraint on this column or not (default false)
> 3.	nullable (optional): set the column as nullable (default true).
> 4.	insertable (optional): whether or not the column will be part of the insert statement (default true)
> 5.	updatable (optional): whether or not the column will be part of the update statement (default true)
> 6.	columnDefinition (optional): override the sql DDL fragment for this particular column (non portable)
> 7.	table (optional): define the targeted table (default primary table)
> 8.	length (optional): column length (default 255)
> 9.	precision (optional): column decimal precision (default 0)
> 10.	scale (optional): column decimal scale if useful (default 0)


#example:
class name User with annotation to define the table, which will be auto build 

```java
    @Column(name = "NAME", nullable = false, columnDefinition = "varchar(128)")
    private String name;
    @Id
    @Column(name = "EMAIL", nullable = false, columnDefinition = "varchar(128)")
    private String email;
    @Column(name = "PASSWORD", nullable = false, columnDefinition = "varchar(128)")
    private String password;
    @Column(name = "COUNTRY", nullable = true, columnDefinition = "varchar(128)")
    private String country;
    @Column(name = "BIRTH", nullable = true, columnDefinition = "date")
    private Date birth;
    @Column(name = "REGISTEREDTIME", nullable = false, columnDefinition = "datetime")
    private Date registeredTime;
```

##check result from database:
first drop old table user:

```
mysql> drop table user;
Query OK, 0 rows affected (0.01 sec)
mysql> show tables;
+---------------------+
| Tables_in_tsag_test |
+---------------------+
| person              |
+---------------------+
1 row in set (0.00 sec)
```

- TABLE user has been deleted

run the server to create new table: we got a new USER table

```
mysql> show tables;
+---------------------+
| Tables_in_tsag_test |
+---------------------+
| USER                |
| person              |
+---------------------+
2 rows in set (0.00 sec)
```

but without contents:

```
mysql> select * from user;
Empty set (0.00 sec)
```

run the test: add some users and show tables again,

```
mysql> select * from user;
+----------------+------------+---------+-------+--------------------------+---------------------+
| EMAIL          | BIRTH      | COUNTRY | NAME  | PASSWORD                 | REGISTEREDTIME      |
+----------------+------------+---------+-------+--------------------------+---------------------+
| jonas@gmail.co | 2015-10-15 |         | Jonas | jo's password            | 2015-10-15 01:19:40 |
| ola@a.a        | 2015-10-15 |         | Ola   | ola's password           | 2015-10-15 01:19:38 |
| sarah@a.a      | 2011-01-14 | Norway  | Sarah | sarah's password updated | 2015-10-15 01:19:40 |
+----------------+------------+---------+-------+--------------------------+---------------------+
3 rows in set (0.00 sec)
```

we got items to our tabe;

####check our database: display information about the columns:

```
mysql> show columns from user;
+----------------+--------------+------+-----+---------+-------+
| Field          | Type         | Null | Key | Default | Extra |
+----------------+--------------+------+-----+---------+-------+
| EMAIL          | varchar(128) | NO   | PRI | NULL    |       |
| BIRTH          | date         | YES  |     | NULL    |       |
| COUNTRY        | varchar(128) | YES  |     | NULL    |       |
| NAME           | varchar(128) | NO   |     | NULL    |       |
| PASSWORD       | varchar(128) | NO   |     | NULL    |       |
| REGISTEREDTIME | datetime     | NO   |     | NULL    |       |
+----------------+--------------+------+-----+---------+-------+
6 rows in set (0.00 sec)
```

with {hibernate.hbm2ddl.auto -> update: update the schema} the table will be build when there is no table, but not destroy the table if it already exist.

##show full columns from table:

```
mysql> show full columns from user;
+----------------+--------------+-------------------+------+-----+---------+-------+---------------------------------+---------+
| Field          | Type         | Collation         | Null | Key | Default | Extra | Privileges                      | Comment |
+----------------+--------------+-------------------+------+-----+---------+-------+---------------------------------+---------+
| EMAIL          | varchar(128) | latin1_swedish_ci | NO   | PRI | NULL    |       | select,insert,update,references |         |
| BIRTH          | date         | NULL              | YES  |     | NULL    |       | select,insert,update,references |         |
| COUNTRY        | varchar(128) | latin1_swedish_ci | YES  |     | NULL    |       | select,insert,update,references |         |
| NAME           | varchar(128) | latin1_swedish_ci | NO   |     | NULL    |       | select,insert,update,references |         |
| PASSWORD       | varchar(128) | latin1_swedish_ci | NO   |     | NULL    |       | select,insert,update,references |         |
| REGISTEREDTIME | datetime     | NULL              | NO   |     | NULL    |       | select,insert,update,references |         |
+----------------+--------------+-------------------+------+-----+---------+-------+---------------------------------+---------+
6 rows in set (0.01 sec)
```

##UTF-8
####latin1_swedish_ci is not what we want, we need utf8_general_ci
find the solution from this [blog](http://blog.tremend.ro/2007/08/14/how-to-set-the-default-charset-to-utf-8-for-create-table-when-using-hibernate-with-java-persistence-annotations/)

build a custom mySQL dialect:

```
extends MySQL5InnoDBDialect {
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
```

and change the configuration from 

```
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
```

to 

```
        properties.put("hibernate.dialect", "com.junjunguo.tsag.util.CustomMysqlDialect");
```

1. delete the table user
2. run the server again 
3. we got a net table 

```
mysql> show full columns from user;
+----------------+--------------+-----------------+------+-----+---------+-------+---------------------------------+---------+
| Field          | Type         | Collation       | Null | Key | Default | Extra | Privileges                      | Comment |
+----------------+--------------+-----------------+------+-----+---------+-------+---------------------------------+---------+
| EMAIL          | varchar(128) | utf8_general_ci | NO   | PRI | NULL    |       | select,insert,update,references |         |
| BIRTH          | date         | NULL            | YES  |     | NULL    |       | select,insert,update,references |         |
| COUNTRY        | varchar(128) | utf8_general_ci | YES  |     | NULL    |       | select,insert,update,references |         |
| NAME           | varchar(128) | utf8_general_ci | NO   |     | NULL    |       | select,insert,update,references |         |
| PASSWORD       | varchar(128) | utf8_general_ci | NO   |     | NULL    |       | select,insert,update,references |         |
| REGISTEREDTIME | datetime     | NULL            | NO   |     | NULL    |       | select,insert,update,references |         |
+----------------+--------------+-----------------+------+-----+---------+-------+---------------------------------+---------+
6 rows in set (0.00 sec)
```
with 'utf8_general_ci' :)
