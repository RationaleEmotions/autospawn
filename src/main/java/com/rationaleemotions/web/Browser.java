package com.rationaleemotions.web;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 *
 * <p>This marker annotation's literal alone i.e., when <code>@Browser</code> is added as one of
 * the tags on a scenario in a Cucumber feature file, then it is an indication that the user
 * would like a browser to be spawned and kept available in the current scenario's context.
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.METHOD)
public @interface Browser {}
