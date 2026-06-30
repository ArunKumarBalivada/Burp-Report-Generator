package com.arun.burpreport;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import com.arun.burpreport.ui.MainPanel;
import com.arun.burpreport.util.Constants;

public class BurpReportGenerator implements BurpExtension {

    @Override
    public void initialize(MontoyaApi api) {

        // Extension Name
        api.extension().setName(Constants.EXTENSION_NAME);

        // Log to Burp Output
        api.logging().logToOutput("=====================================");
        api.logging().logToOutput(Constants.EXTENSION_NAME);
        api.logging().logToOutput("Version : " + Constants.EXTENSION_VERSION);
        api.logging().logToOutput("Author  : " + Constants.AUTHOR);
        api.logging().logToOutput("=====================================");

        // Register the UI Tab
        api.userInterface().registerSuiteTab(
                Constants.TAB_NAME,
                new MainPanel()
        );
    }
}