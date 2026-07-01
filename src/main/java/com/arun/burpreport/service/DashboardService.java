package com.arun.burpreport.service;

import com.arun.burpreport.model.Finding;
import com.arun.burpreport.model.Project;
import com.arun.burpreport.model.enums.Severity;

public class DashboardService {

    public int getTotalFindings(Project project) {
        return project.getFindings().size();
    }

    public int getCriticalCount(Project project) {
        return countBySeverity(project, Severity.CRITICAL);
    }

    public int getHighCount(Project project) {
        return countBySeverity(project, Severity.HIGH);
    }

    public int getMediumCount(Project project) {
        return countBySeverity(project, Severity.MEDIUM);
    }

    public int getLowCount(Project project) {
        return countBySeverity(project, Severity.LOW);
    }

    public int getInfoCount(Project project) {
        return countBySeverity(project, Severity.INFORMATIONAL);
    }

    private int countBySeverity(Project project, Severity severity) {

        int count = 0;

        for (Finding finding : project.getFindings()) {

            if (finding.getSeverity() == severity) {
                count++;
            }

        }

        return count;
    }

    public Severity getOverallRisk(Project project) {

        if (getCriticalCount(project) > 0)
            return Severity.CRITICAL;

        if (getHighCount(project) > 0)
            return Severity.HIGH;

        if (getMediumCount(project) > 0)
            return Severity.MEDIUM;

        if (getLowCount(project) > 0)
            return Severity.LOW;

        return Severity.INFORMATIONAL;
    }

}