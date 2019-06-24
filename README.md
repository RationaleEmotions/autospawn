# Auto-Spawn

This library helps with managing browser instantiation and clean-up automatically for Cucumber based tests.

## Pre-requisites
* Cucumber ( Compatibility testing has only been done for `group: 'info.cukes', name: 'cucumber-java', version: '1.2.5'`)
* JDK-8 ( This implementation uses a reflection based mechanism to alter annotations values at runtime. This has been tested only on JDK8)

## Maven dependency

Add the below maven dependency to get started

```xml
<dependency>
  <groupId>com.rationaleemotions</groupId>
  <artifactId>autospawn</artifactId>
  <version>1.0.0</version>
</dependency>
```

## How to use
1. Create an implementation of `com.rationaleemotions.web.IBrowserCreator`.
2. Wire in this implementation via the JVM argument `-Dcreator=`
3. Annotate all cucumber scenarios that automatically need a browser instance to be created using the tag `@Browser`
4. Now within your scenario you can query the browser instance using `DriverFactory.getDriver()` [ Here `DriverFactory` belongs to the package `com.rationaleemotions.web.DriverFactory` ] 
