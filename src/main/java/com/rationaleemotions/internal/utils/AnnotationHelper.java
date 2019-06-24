package com.rationaleemotions.internal.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

public final class AnnotationHelper {

  private static final String ANNOTATION_DATA = "annotationData";
  private static final String ANNOTATIONS = "annotations";

  private AnnotationHelper() {
    // Utility class. So hiding the constructor.
  }

  /**
   * The implementation is inspired from <a href='http://stackoverflow.com/a/14276270'>this</a>
   * StackOverFlow post
   *
   * @param clazzObject - The class for which annotations are to be altered at runtime.
   * @param originalAnnotation - An annotation object for which alteration is to be done. The {@link
   *     java.lang.annotation.RetentionPolicy} needs to be set to {@link
   *     java.lang.annotation.RetentionPolicy#RUNTIME} for this method to work.
   * @param alteredAnnotation - An object that represents an altered annotation object which would
   *     be replacing the original annotation.
   */
  public static void alterAnnotationOn(
      Class clazzObject,
      Class<? extends Annotation> originalAnnotation,
      Annotation alteredAnnotation) {
    validateArgument(clazzObject, "Class name");
    validateArgument(originalAnnotation, "Original annotation");
    validateArgument(clazzObject, "New value for annotation");
    Map<Class<? extends Annotation>, Annotation> annotationMap =
        retrieveAnnotationsMapInJdk8(clazzObject);
    annotationMap.put(originalAnnotation, alteredAnnotation);
  }

  private static void validateArgument(Object object, String prefix) {
    if (Objects.isNull(object)) {
      throw new IllegalArgumentException(prefix + " cannot be null.");
    }
  }

  @SuppressWarnings("unchecked")
  private static Map<Class<? extends Annotation>, Annotation> retrieveMap(
      Class clazz, Object object) {
    try {
      Field annotations = clazz.getDeclaredField(ANNOTATIONS);
      annotations.setAccessible(true);
      return (Map<Class<? extends Annotation>, Annotation>) annotations.get(object);

    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private static Map<Class<? extends Annotation>, Annotation> retrieveAnnotationsMapInJdk8(
      Class clazz) {
    try {
      Method method = Class.class.getDeclaredMethod(ANNOTATION_DATA, null);
      method.setAccessible(true);
      Object object = method.invoke(clazz);
      return retrieveMap(object.getClass(), object);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
}
