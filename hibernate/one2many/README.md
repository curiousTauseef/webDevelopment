#One to Many relation

one user can have many tags:

```
public Class User{
    @OneToMany(cascade = {CascadeType.ALL},
               targetEntity = Tag.class)
    @JoinColumn(name = "USER_EMAIL")
    private List<Tag> tags = new ArrayList<Tag>();
    ...
```

table content:

```
mysql> select * from user;
+---------------------+------------+-----------+-----------+-----------------------+---------------------+
| EMAIL               | BIRTH      | COUNTRY   | NAME      | PASSWORD              | REGISTEREDTIME      |
+---------------------+------------+-----------+-----------+-----------------------+---------------------+
| hibernate@gmail.com | 2015-01-14 | hibernate | Hibernate | hi's password updated | 2015-10-25 21:53:41 |
+---------------------+------------+-----------+-----------+-----------------------+---------------------+
```



one tag can only have one user:

````
public Class Tag{
    @ManyToOne()
    private User user;
    ...
````

table content: 

```
mysql> select * from tag;
+----+-------------+---------------------+
| ID | LABEL       | user_EMAIL          |
+----+-------------+---------------------+
|  1 | Hibernate   | hibernate@gmail.com |
|  2 | hibernation | hibernate@gmail.com |
+----+-------------+---------------------+
```

no need to have the user_tag middle table, since a tag can only have one user so we can easily save user id at tag table; 
