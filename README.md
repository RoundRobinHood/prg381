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
cd webapp
mvn jetty:run
```

## Folder structure
Here's a quick visualization of our current folder structure:

```text
├── desktop # Milestone 2
├── LICENSE
└── webapp # Milestone 1
    ├── pom.xml # Maven configuration for Milestone 1
    └── src
        └── main
            ├── java
            │   └── com/roundrobinhood/prg381
            │       └── HelloServlet.java
            └── webapp
                ├── index.jsp
                └── WEB-INF
                    └── web.xml
```

As you can see, desktop does not have anything yet, and probably doesn't even show up on the repository 
(to be added later when the web app is more developed and we have played with Swing and stuff).
The project is also MIT-licensed.
And Maven's archetype for jsp-based web-apps expects the normal web-app stuff to be in `src/main/webapp`, and Servlet code to be in `src/main/java`.

Furthermore, you can also see some redundant folders in the Servlet path for the placeholder Servlet (`com/roundrobinhood/prg381`). This is because
the `java` folder would act as the class path for our project, so our folder path to our classes has to correspond to how we would like to organize our namespace.
As such, it's good practice to do this as identifier (especially because `pom.xml` also identifies that as our groupId for disambiguation).

`pom.xml` is mostly Google and ChatGPT work to maintain, and to the best of our ability should be kept as unchanging as possible. It describes
which Maven plugins and dependencies are used by our project. It includes all this info right now:

- Our Servlets use Servlet 5.0.0, and our web server needs the postgresql driver for DB connections later
- Our build target is JDK 24, and the versions of specific plugins are nailed down.
- Jetty sweep-checks your code for changes once every 2 seconds to decide whether it needs to hot-reload anything

As such, handle `webapp` as you would a normal static site, except the fact that `.jsp` files can access backend stuff, and handle `java` as you
would a REST API configured by `web.xml`, if you have experience with that from the past.
