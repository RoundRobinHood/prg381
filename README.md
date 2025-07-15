# PRG381 Project

This repository contains both **Milestone 1** (WebApp) and **Milestone 2** (Desktop).  
These two components will likely interoperate, so we may also consider setting up shared API docs later.

## 🔧 Project Setup

Milestone 1 (`webapp`) is fully set up with **Maven** to handle:
- Dependency management
- Simple builds
- Testing integration
- Dev server with hot reloading (Jetty)

We're targeting:
- **JDK 24**
- **Maven 3.9.10**

Make sure you're using these versions, or things might break unexpectedly.

---

## 🚀 Running the WebApp

You don't need Apache Tomcat to test the JSP server!  
We're using **Jetty** (via Maven), which handles JSP hosting and supports hot-reloading.

```bash
mvn clean install # so that ./shared gets compiled into ./webapp
cd webapp
mvn jetty:run
```

## Folder structure
Here's a quick visualization of our current folder structure:

```text
├── LICENSE
├── pom.xml
├── README.md
├── shared
│   ├── pom.xml
│   └── src/main/java/com/roundrobinhood/shared
│       ├── Session.java
│       └── Student.java
└── webapp
    ├── docker-compose.yml
    ├── Dockerfile
    ├── pom.xml
    └── src/main
        ├── java/com/roundrobinhood/webapp
        │   ├── Config.java
        │   ├── DashboardServlet.java
        │   ├── db
        │   │   ├── DBConnection.java
        │   │   └── SessionStorage.java
        │   ├── LoginServlet.java
        │   └── RegisterServlet.java
        └── webapp
            ├── index.jsp
            ├── styling
            │   └── common.css
            └── WEB-INF
                ├── pages
                │   ├── dashboard.jsp
                │   ├── login.jsp
                │   └── register.jsp
                └── web.xml

```

The project is MIT-licensed.

We loosely follow MVC for the webapp already (though not explicitly), as:
1. The `model` consists of relevant classes, such as:
    - DBConnection
    - SessionStorage
    - Student
    - Session
2. The `controller` consists of Servlets, which are:
    - LoginServlet
    - RegisterServlet
    - DashboardServlet
3. The `view` consists of the files used directly for rendering to the browser, such as:
    - common.css
    - dashboard.jsp
    - login.jsp
    - register.jsp

Furthermore, you can also see some redundant folders in the Servlet path for the placeholder Servlet (`com/roundrobinhood/prg381`). This is because
the `java` folder would act as the class path for our project, so our folder path to our classes has to correspond to how we would like to organize our namespace.
As such, it's good practice to do this as identifier (especially because `pom.xml` also identifies that as our groupId for disambiguation).

`pom.xml` files are mostly Google and ChatGPT work to maintain, and to the best of our ability should be kept as unchanging as possible. It describes
which Maven plugins and dependencies are used by our project. It includes all this info right now:

- Our Servlets use Servlet 5.0.0, and our web server needs the postgresql driver for DB connections later
- Our build target is JDK 24, and the versions of specific plugins are nailed down.
- Jetty sweep-checks your code for changes once every 2 seconds to decide whether it needs to hot-reload anything

As such, handle `webapp` as you would a normal static site, except the fact that `.jsp` files can access backend stuff, and handle `java` as you
would a REST API configured by `web.xml`, if you have experience with that from the past.

Until actual schema documentation is ready, read the SQL commands in `DBConnection.java` (under `checkSchema()`) for the schema of the web server.
