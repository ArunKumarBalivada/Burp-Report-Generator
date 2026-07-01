package com.arun.burpreport;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import com.arun.burpreport.common.AppConstants;
import com.arun.burpreport.ui.MainPanel;

public class BurpReportGenerator implements BurpExtension {

    @Override
    public void initialize(MontoyaApi api) {

        // Extension Information
        api.extension().setName(AppConstants.EXTENSION_NAME);

        logStartupBanner(api);

        // TODO:
        // ApplicationInitializer.initialize(api);

        registerUserInterface(api);

    }

    private void registerUserInterface(MontoyaApi api) {

        api.userInterface().registerSuiteTab(
                AppConstants.TAB_NAME,
                new MainPanel()
        );

    }

    private void logStartupBanner(MontoyaApi api) {

        api.logging().logToOutput("========================================");
        api.logging().logToOutput(AppConstants.EXTENSION_NAME);
        api.logging().logToOutput("Version : " + AppConstants.EXTENSION_VERSION);
        api.logging().logToOutput("Author  : " + AppConstants.EXTENSION_AUTHOR);
        api.logging().logToOutput("========================================");

    }

}