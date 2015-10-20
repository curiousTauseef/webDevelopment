#Hibernate
- Simple example test

###hibernate many to many bidirectional
- hibernate many to many bidirectional Relation

###full annotation hibernate configuration
- no xml settings, everything in java class

###Auto build mysql tables:

```
mysql> show tables;
+---------------------+
| Tables_in_tsag_test |
+---------------------+
| TAG                 |
| USER                |
| hibernate_sequence  |
| user_tag            |
+---------------------+
```

show tag table

```
mysql> select * from Tag;
+----+-------------+
| ID | LABEL       |
+----+-------------+
|  1 | Hibernate   |
|  2 | hibernation |
+----+-------------+
```

show user table

```
mysql> select * from user;
+---------------------+------------+-----------+-----------+-----------------------+---------------------+
| EMAIL               | BIRTH      | COUNTRY   | NAME      | PASSWORD              | REGISTEREDTIME      |
+---------------------+------------+-----------+-----------+-----------------------+---------------------+
| hibernate@gmail.com | 2015-01-14 | hibernate | Hibernate | hi's password updated | 2015-10-20 19:18:56 |
+---------------------+------------+-----------+-----------+-----------------------+---------------------+
```

show user_tag table

```
mysql> select * from user_tag;
+---------------------+--------+
| user_id             | tag_id |
+---------------------+--------+
| hibernate@gmail.com |      1 |
| hibernate@gmail.com |      2 |
+---------------------+--------+
```

show full columns from user

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
```
