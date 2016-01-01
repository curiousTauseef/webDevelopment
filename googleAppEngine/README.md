# Google App Engine 
Platform as a Service

> Google App Engine is a platform for building scalable web applications and mobile backends. App Engine provides you with built-in services and APIs such as NoSQL datastores, memcache, and a user authentication API, common to most applications.

> App Engine will scale your application automatically in response to the amount of traffic it receives so you only pay for the resources you use. Just upload your code and Google will manage your app's availability. There are no servers for you to provision or maintain.

-- [appengine](https://cloud.google.com/appengine/)

**Google App Engine** (**GAE**)

## 1 Introducing Google App Engine

- GAE is a web application hosting service.
- GAE can serve Traditional website content too.
- When an application can serve many simultaneous users without degrading performance, we say it scales. 
	- Applications written for App Engine scale automatically.
- every developer gets a certain amount of resources for free, enough for small applications with low traffic.
- App Engine is part of Google Cloud Platform.
- An App Engine web application can be described as having three major parts: 
	1. application instances
	2. scalable data storage
	3. scalable services

- App Engine allows runtime environments to outlive request handlers, and will reuse environments as much as possible to avoid unnecessary initialization. 
- Each instance of your application has local memory for caching imported code and initialized data structures. 
- App Engine creates and destroys instances as needed to accommodate your app’s traffic. 

- App Engine provides a separate set of servers dedicated to delivering static files.

**Cloud datastore**

- Google Cloud SQL, a full-featured relational database service based on MySQL. Cloud SQL is a feature of Google Cloud Platform, and can be called directly from App Engine using standard database APIs.
- Cloud Datastore scales automatically: with proper data design, it can handle as many simultaneous users as App Engine’s server instances can.


**Transactions**

- an update of a single entity occurs in a transaction. Each transaction is atomic: the transaction either succeeds completely or fails completely,
- An application can read or update multiple entities in a single transaction, but it must tell Cloud Datastore which entities will be updated together when it creates the enti‐ ties. The application does this by creating entities in entity groups. Cloud Datastore uses entity groups to control how entities are distributed across servers

**Services**

- Google Cloud Platform provides another storage service specifically for very large values, called Google Cloud Storage.2 Your app can use Cloud Storage to store, man‐ age, and serve large files, such as images, videos, or file downloads. 
- Google Cloud SQL provides full- featured MySQL database hosting. Unlike Cloud Datastore or Cloud Storage, Cloud SQL does not scale automatically. 

- App Engine applications can access other web resources using the URL Fetch service.

- can use Google Accounts as your app’s user authentication system, so you don’t have to build your own.

- can always build your own account system, or use an OpenID provider.

- App Engine includes built-in support for OAuth, a protocol that makes it possible for users to grant permission to third-party applications to access personal data in another service, without having to share their account credentials with the third party. 
 
- Endpoints make it especially easy for a mobile or rich web client to call methods on the server. Endpoints includes libraries and tools for generating server functionality from a set of methods in Python and Java, and generating client code for Android, iOS, and browser-based JavaScript. The tools can also generate a “discovery document” that works with the Google APIs Cli‐ ent Libraries for many client languages. And OAuth support is built in, so you don’t have to worry about authentication and can just focus on the application logic.

- Task queues let you describe work to be done at a later time, outside the scope of the web request. 
- There are two kinds of task queues: push queues and pull queues:
	- push queues, each task record represents an HTTP request to a request handler. App Engine issues these requests itself as it processes a push queue.
	- pull queues, you provide the mechanism, such as a custom computational engine, that takes task records off the queue and does the work.

-  storage services provide this partitioning feature at the infrastructure level. An app can declare it is acting in a namespace by calling an API.

- **Google Cloud SDK**
	- Google Cloud SDK contains tools and libraries that enable you to easily create and manage resources on Google Cloud Platform, including App Engine, Compute Engine, Cloud Storage, BigQuery, Cloud SQL, and Cloud DNS.
	- [https://cloud.google.com/sdk/](https://cloud.google.com/sdk/)

- creating app engine app
	- [https://console.developers.google.com/start/appengine](https://console.developers.google.com/start/appengine)
- App Engine documentation [https://cloud.google.com/appengine/docs](https://cloud.google.com/appengine/docs)







##Sources:

Programming Google App Engine with Java
- Build & Run Scalable Java Applications on Google's Infrastructure


