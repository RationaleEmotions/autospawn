package com.rationaleemotions.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import java.lang.annotation.Annotation;

public abstract class AbstractCucumberOptions implements CucumberOptions {

  private final CucumberOptions options;

  public AbstractCucumberOptions(CucumberOptions options) {
    this.options = options;
  }

  @Override
  public boolean dryRun() {
    return options.dryRun();
  }

  @Override
  public boolean strict() {
    return options.strict();
  }

  @Override
  public String[] features() {
    return options.features();
  }

  @Override
  public String[] glue() {
    return options.glue();
  }

  @Override
  public String[] tags() {
    return options.tags();
  }

  /**
   * @return what formatter(s) to use
   * @deprecated use {@link #plugin()}
   */
  @Override
  @Deprecated
  public String[] format() {
    return options.format();
  }

  @Override
  public String[] plugin() {
    return options.plugin();
  }

  @Override
  public boolean monochrome() {
    return options.monochrome();
  }

  @Override
  public String[] name() {
    return options.name();
  }

  @Override
  public SnippetType snippets() {
    return options.snippets();
  }

  @Override
  public Class<? extends Annotation> annotationType() {
    return options.annotationType();
  }

  @Override
  public String[] junit() {
    return options.junit();
  }

}
