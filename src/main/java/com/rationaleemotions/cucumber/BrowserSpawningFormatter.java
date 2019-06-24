package com.rationaleemotions.cucumber;

import com.rationaleemotions.web.DriverFactory;
import com.rationaleemotions.web.Browser;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;
import java.util.List;
import java.util.logging.Logger;

public class BrowserSpawningFormatter implements Formatter {

  private static final Logger LOG = Logger.getLogger(BrowserSpawningFormatter.class.getName());

  private static boolean needsBrowser(Scenario scenario) {
    return scenario.getTags().contains(new Tag("@" + Browser.class.getSimpleName(), 0));
  }

  @Override
  public void syntaxError(
      String state, String event, List<String> legalEvents, String uri, Integer line) {}

  @Override
  public void uri(String uri) {}

  @Override
  public void feature(Feature feature) {}

  @Override
  public void scenarioOutline(ScenarioOutline scenarioOutline) {}

  @Override
  public void examples(Examples examples) {}

  @Override
  public void startOfScenarioLifeCycle(Scenario scenario) {
    if (needsBrowser(scenario)) {
      LOG.info("Auto Spawning a browser for the scenario : [" + scenario.getName() + "]");
      DriverFactory.createDriver();
    }
  }

  @Override
  public void background(Background background) {}

  @Override
  public void scenario(Scenario scenario) {}

  @Override
  public void step(Step step) {}

  @Override
  public void endOfScenarioLifeCycle(Scenario scenario) {
    if (needsBrowser(scenario)) {
      LOG.info(
          "Cleaning up the Auto Spawned browser for the scenario : [" + scenario.getName() + "]");
      DriverFactory.quitDriver();
    }
  }

  @Override
  public void done() {}

  @Override
  public void close() {}

  @Override
  public void eof() {}
}
