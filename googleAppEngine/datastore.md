# Google Cloud Datastore

- [Java Datastore API](https://cloud.google.com/appengine/docs/java/datastore/)

##Compare DataStore

Object Oriented	| Relational Database	| DataStore
---		|---			|---
Class		| Table			| Kind
Object		| Record		| Entity
Attribute	| Column		| Property



Concept	    |   Datastore   |   Relational database
---|---        |---            |---
Category of object |	Kind	| Table
One object	| Entity	| Row
Individual data for an object	| Property	| Field
Unique ID for an object	| Key	| Primary key


- Datastore uses a distributed architecture to automatically manage scaling to very large data sets
- Datastore is designed to scale, allowing applications to maintain high performance as they receive more traffic
	- Datastore writes scale by automatically distributing data as necessary
	- Datastore reads scale because the only queries supported are those whose performance scales with the size of the result set (as opposed to the data set). This means that a query whose result set contains 100 entities performs the same whether it searches over a hundred entities or a million.
- all queries are served by pre-built indexes, queries are more restrictive than with SQL. not supported by Datastore:
	- Join operations
	- Inequality filtering on multiple properties
	- Filtering of data based on results of a subquery

## Entities

**Objects in the Datastore are known as `entities`.**

- An entity has one or more named **`properties`**
    - each of which can have one or more **`values`**
    - **`Property values`** can belong to a variety of data types, including:
        - integers, 
        - floating-point numbers, 
        - strings, dates, and 
        - binary data, among others.
    - A **`query`** on a **`property`** with multiple **`values`** tests whether any of the values meets the query criteria.
        - This makes such properties useful for membership testing.
- Datastore entities are `schemaless`
    - unlike traditional relational databases, 
        - Datastore does not require that all entities of a given kind have the same properties or 
        - that all of an entity's values for a given property be of the same data type.
    - If a `formal schema` is needed, 
        - the application itself is responsible for ensuring that entities conform to it.

### Kinds, keys, and identifiers

**Each Datastore entity is of a particular `kind`,**

- which categorizes the entity for the purpose of queries; for instance, a human resources application might represent each employee at a company with an entity of kind Employee.

**each entity has its own `key`,** 

- which uniquely identifies it,
- key consists of the following components:
    - The entity's kind
    - An identifier, which can be either
        - a key name string
        - an integer ID
    - An optional ancestor path locating the entity within the Datastore hierarchy.

**`identifier` is assigned when the entity is created.**

- Because it is part of the entity's key, 
- it is associated permanently with the entity and cannot be changed. 
- It can be assigned in either of two ways:
    - Your application can specify its own key name string for the entity.
    - You can have the Datastore automatically assign the entity an integer numeric ID.

###Ancestor paths
Entities in the Datastore form a hierarchically structured space similar to the directory structure of a file system.

Create entities:

- can optionally designate another entity as its parent;
	- the new entity is a child of the parent entity.
- an entity without a parent is a root entity
- The association between an entity and its parent is permanent, and cannot be changed once the entity is created.
- The Datastore will never assign the same numeric ID to two entities with the same parent, or to two root entities
- **ancestors**:
	- An entity's parent, parent's parent, and so on recursively, are its ancestors.
- **descendants**:
	- An entity's children, children's children, and so on, are its descendants.
- **Entity group**:
	- A root entity and all of its descendants belong to the same entity group

```json
[Person:GreatGrandpa, Person:Grandpa, Person:Dad, Person:Me]
```

- Root entity identifies the entity group

```
Person:GreatGrandpa
	| Person:Grandpa
		| Person:Dad
			| Person:Me
```

- The root entity and all it children are in the same entity group

### Queries and indexes

A typical query includes the following:

- An entity kind to which the query applies
- Zero or more filters based on the entities' property values, keys, and ancestors
- Zero or more sort orders to sequence the results

When executed, the query retrieves all entities of the given kind that satisfy all of the given filters, sorted in the specified order.

- To conserve memory and improve performance, a query should, whenever possible, 
	- **specify a limit on the number of results returned.**






## access DataStore

- You can access the Datastore using the low-level API described throughout the Datastore documentation, which provides direct access to all of Datastore's features, 
	- low-level Datastore API is provided and supported by Google
- or you can use one of the higher-level open-source APIs for Datastore that provide ORM-like features and a more abstract experience, such as Objectify.
	- Objectify is provided by a third-party, and Google does not provide support for it.
	- [Objectify](https://github.com/objectify/objectify)


**[mastering datastore](https://cloud.google.com/appengine/articles/datastore/overview)**




#Objectify

> Objectify is a Java data access API specifically designed for the Google App Engine datastore. It occupies a "middle ground"; easier to use and more transparent than JDO or JPA, but significantly more convenient than the Low-Level API. Objectify is designed to make novices immediately productive yet also expose the full power of the GAE datastore.


