package com.rationaleemotions.cucumber;

import com.rationaleemotions.internal.utils.AnnotationHelper;
import cucumber.api.CucumberOptions;
import java.lang.annotation.Annotation;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class CucumberOptionsInjectingListener implements IInvokedMethodListener {

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    Class clazz = method.getTestMethod().getTestClass().getRealClass();
    Annotation annotation = clazz.getAnnotation(CucumberOptions.class);
    if (annotation == null) {
      return;
    }
    CucumberOptions options = (CucumberOptions) annotation;
    CucumberOptions decorator = PluginAwareCucumberOptions.newInstance(options);
    AnnotationHelper.alterAnnotationOn(clazz, CucumberOptions.class, decorator);
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {}
}
