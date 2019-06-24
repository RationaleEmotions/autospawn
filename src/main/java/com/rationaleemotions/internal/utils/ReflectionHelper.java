package com.rationaleemotions.internal.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** A utility class for housing all reflection related functionalities. */
public final class ReflectionHelper {
  private ReflectionHelper() {
    // Utility class. Defeat instantiation
  }

  /**
   * This method attempts at invoking a <b>zero arguments</b> method.
   *
   * @param methodName - The name of the method to be invoked.
   * @param objectToInvokeUpon - The object upon which the invocation is to be done.
   * @return - The return value of the invocation via Reflection.
   */
  public static Object invokeNoArgumentMethod(String methodName, Object objectToInvokeUpon) {
    try {
      Method method = objectToInvokeUpon.getClass().getMethod(methodName);
      method.setAccessible(true);
      return method.invoke(objectToInvokeUpon);
    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      throw new ReflectionInvocationException(e);
    }
  }


  @SuppressWarnings("unchecked")
  public static <T> T newInstance(String classname ) {
    try {
      Class<?> clazz = Class.forName(classname);
      return (T) clazz.newInstance();
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /** Marker exception for identifying all the failures related to reflection. */
  private static class ReflectionInvocationException extends RuntimeException {
    ReflectionInvocationException(Throwable t) {
      super(t);
    }
  }
}
