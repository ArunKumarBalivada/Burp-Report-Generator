package com.arun.burpreport.common;

public final class AppConstants {

    private AppConstants() {
        // Prevent instantiation
    }

    /* ==========================================================
       Application Information
       ========================================================== */

    public static final String APP_NAME = "Burp Report Workspace";
    public static final String APP_VERSION = "1.0.0";

    /* ==========================================================
       Burp Extension Information
       ========================================================== */

    public static final String EXTENSION_NAME = APP_NAME;
    public static final String EXTENSION_VERSION = APP_VERSION;
    public static final String EXTENSION_AUTHOR = "Arun Kumar Balivada";
    public static final String TAB_NAME = "Report Workspace";

    /* ==========================================================
       Default Settings
       ========================================================== */

    public static final String DEFAULT_VENDOR = "CGI";

    public static final String DEFAULT_REPORTS_FOLDER = "reports";

    public static final String DEFAULT_REPORT_NAMING =
            "<ApplicationName>_Pentest_Report";

    /* ==========================================================
       Workspace Folder Names
       ========================================================== */

    public static final String CONFIG_FOLDER = "config";

    public static final String PROJECTS_FOLDER = "projects";

    public static final String REPORTS_FOLDER = "reports";

    public static final String TEMPLATES_FOLDER = "templates";

    public static final String LOGS_FOLDER = "logs";

    public static final String BACKUPS_FOLDER = "backups";

    /* ==========================================================
       Configuration Files
       ========================================================== */

    public static final String SETTINGS_FILE = "settings.json";

    /* ==========================================================
       File Extensions
       ========================================================== */

    public static final String PROJECT_FILE_EXTENSION = ".json";

    public static final String WORD_FILE_EXTENSION = ".docx";

    public static final String EXCEL_FILE_EXTENSION = ".xlsx";

    /* ==========================================================
       Date Formats
       ========================================================== */

    public static final String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy";

    public static final String DEFAULT_DATE_TIME_FORMAT =
            "dd-MMM-yyyy HH:mm:ss";

}