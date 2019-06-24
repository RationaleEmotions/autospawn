package com.rationaleemotions.cucumber;

import cucumber.api.CucumberOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginAwareCucumberOptions extends AbstractCucumberOptions {
  private List<String> plugins;

  private PluginAwareCucumberOptions(CucumberOptions options, String[] plugins) {
    super(options);
    this.plugins = new ArrayList<>(Arrays.asList(options.plugin()));
    this.plugins.addAll(Arrays.asList(plugins));
  }

  static PluginAwareCucumberOptions newInstance(CucumberOptions options) {
    return new PluginAwareCucumberOptions(
        options, new String[] {BrowserSpawningFormatter.class.getName()});
  }

  @Override
  public String[] plugin() {
    List<String> pluginsToReturn = new ArrayList<>(this.plugins);
    pluginsToReturn.addAll(Arrays.asList(super.plugin()));
    return pluginsToReturn.toArray(new String[0]);
  }

}
