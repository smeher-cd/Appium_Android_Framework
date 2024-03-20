package com.ikea.homesmart.utils;

import com.ikea.homesmart.helpers.TestConfig;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class CucumberReport {

    public static void generateCucumberHtmlReport() {
        File reportOutputDirectory = new File(TestConfig.baseDir, TestConfig.CucumberHtmlReport);
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(new File(TestConfig.baseDir, TestConfig.CucumberJsonReport + "/cucumber-json-report.json").getPath());

        Configuration configuration = new Configuration(reportOutputDirectory, "IKEA Home Smart App");
        configuration.setSortingMethod(SortingMethod.NATURAL);
        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);

        configuration.addClassifications("Environment", "Test");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();

    }
}



