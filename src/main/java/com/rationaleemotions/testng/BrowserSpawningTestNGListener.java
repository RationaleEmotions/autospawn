package com.rationaleemotions.testng;

import com.rationaleemotions.web.Browser;
import com.rationaleemotions.web.DriverFactory;
import java.util.logging.Logger;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class BrowserSpawningTestNGListener extends TestListenerAdapter {
  private static final Logger LOG = Logger.getLogger(BrowserSpawningTestNGListener.class.getName());

  @Override
  public void onTestStart(ITestResult result) {
    ITestNGMethod tm = result.getMethod();
    if (needsBrowser(tm)) {
      LOG.info("Auto Spawning a browser for the test method : [" + prettify(tm) + "]");
      DriverFactory.createDriver();
    }
  }

  @Override
  public void onTestSuccess(ITestResult tr) {
    cleanup(tr);
  }

  @Override
  public void onTestFailure(ITestResult tr) {
    cleanup(tr);
  }

  @Override
  public void onTestSkipped(ITestResult tr) {
    cleanup(tr);
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
    super.onTestFailedButWithinSuccessPercentage(tr);
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
    String clazzname = method.getTestClass().getRealClass().getName();
    String methodname = method.getMethodName();
    return clazzname + "." + methodname + "()";
  }
}
