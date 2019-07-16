package com.rationaleemotions.web;

import com.google.common.base.Preconditions;
import com.rationaleemotions.internal.utils.ReflectionHelper;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class DriverFactory {
  static final String JVM_ARG = "creator";
  private static final String className =
      System.getProperty(JVM_ARG, NoOperationalBrowserCreator.class.getName());
  private static ThreadLocal<RemoteWebDriver> driverThreadLocal = new ThreadLocal<>();
  private static final IBrowserCreator creator = getCreator();

  private DriverFactory() {
    // Factory like implementation. Defeat instantiation.
  }

  public static RemoteWebDriver getDriver() {
    Preconditions.checkNotNull(driverThreadLocal.get(), "No valid webdriver found for the current thread.");
    return driverThreadLocal.get();
  }

  private static void setDriver(RemoteWebDriver driver) {
    driverThreadLocal.set(driver);
  }

  public static void createDriver() {
    if (driverThreadLocal.get() == null) {
      setDriver(creator.createDriver());
    }
  }

  /** Quits the driver object if exists */
  public static void quitDriver() {
    getDriver().quit();
    setDriver(null);
  }

  private static IBrowserCreator getCreator() {
    return ReflectionHelper.newInstance(className);
  }
}
