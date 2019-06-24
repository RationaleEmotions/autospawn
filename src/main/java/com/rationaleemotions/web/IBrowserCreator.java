package com.rationaleemotions.web;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This interface represents the capabilities of a browser instantiating mechanism which end-users
 * can plugin via a JVM argument.
 */
public interface IBrowserCreator {

  /** @return - A {@link RemoteWebDriver} variant that represents the actual webdriver object. */
  RemoteWebDriver createDriver();
}
