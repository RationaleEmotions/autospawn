package com.rationaleemotions.testng;

import com.rationaleemotions.web.Browser;
import com.rationaleemotions.web.DriverFactory;
import java.util.logging.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class BrowserSpawningTestNGListener implements IInvokedMethodListener {
  private static final Logger LOG = Logger.getLogger(BrowserSpawningTestNGListener.class.getName());

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    if (method.isConfigurationMethod()) {
      return;
    }
    ITestNGMethod tm = method.getTestMethod();
    if (needsBrowser(tm)) {
      LOG.info("Auto Spawning a browser for the test method : [" + prettify(tm) + "]");
      DriverFactory.createDriver();
    }
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    if (method.isConfigurationMethod()) {
      return;
    }
    cleanup(testResult);
  }

  private void cleanup(ITestResult tr) {
    ITestNGMethod tm = tr.getMethod();
    if (needsBrowser(tm)) {
      LOG.info("Cleaning-up the Auto Spawned browser for the test method : [" + prettify(tm) + "]");
      DriverFactory.quitDriver();
    }
  }

  private static boolean needsBrowser(ITestNGMethod method) {
    if (!method.isTest()) {
      return false;
    }
    Browser browser = method.getConstructorOrMethod().getMethod().getAnnotation(Browser.class);
    return browser != null;
  }

  private static String prettify(ITestNGMethod method) {
    return method.getQualifiedName() + "()";
  }
}
