package com.rationaleemotions.testng;

import com.rationaleemotions.web.Browser;
import com.rationaleemotions.web.DriverFactory;
import java.util.logging.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class BrowserSpawningTestNGListener implements IInvokedMethodListener {
  private static final Logger LOG = Logger.getLogger(BrowserSpawningTestNGListener.class.getName());

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    if (needsBrowser(method)) {
      LOG.info(
          "Auto Spawning a browser for the test method : [" + prettify(method) + "]");
      DriverFactory.createDriver();
    }
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    if (needsBrowser(method)) {
      LOG.info(
          "Cleaning-up the Auto Spawned browser for the test method : [" + prettify(method) + "]");
      DriverFactory.quitDriver();
    }
  }

  private static boolean needsBrowser(IInvokedMethod method) {
    if (method.isConfigurationMethod()) {
      return false;
    }
    Browser browser =
        method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(Browser.class);
    return browser != null;
  }

  private static String prettify(IInvokedMethod method) {
    String clazzname = method.getTestMethod().getTestClass().getRealClass().getName();
    String methodname = method.getTestMethod().getMethodName();
    return clazzname + "." + methodname + "()";
  }
}
