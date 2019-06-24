package com.rationaleemotions.web;

import static com.rationaleemotions.web.DriverFactory.JVM_ARG;

import java.util.logging.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NoOperationalBrowserCreator implements IBrowserCreator {
  private static final Logger LOG = Logger.getLogger(NoOperationalBrowserCreator.class.getName());

  @Override
  public RemoteWebDriver createDriver() {
    String msg = "Please wire in an implementation of "
        + IBrowserCreator.class.getName()
        + " via the JVM argument -D"
        + JVM_ARG;
    LOG.severe(msg);
    throw new UnsupportedOperationException(msg);
  }
}
