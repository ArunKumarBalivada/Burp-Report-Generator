# Burp Report Generator - Architecture

## Version

v1.0 (Initial Design)

---

# 1. Objective

Burp Report Generator is a Burp Suite extension that automates penetration testing report generation while allowing security testers to manage projects, findings, and reusable finding libraries from a single interface.

The primary goal is to reduce report preparation time without reducing report quality.

---

# 2. High-Level Workflow

```
Create Project
      │
      ▼
Enter Project Details
      │
      ▼
Load Finding Library (Excel)
      │
      ▼
Add / Edit Findings
      │
      ▼
Dashboard Updates Automatically
      │
      ▼
Save Project (JSON)
      │
      ▼
Generate Word Report
```

---

# 3. Project Structure

```
com.arun.burpreport
│
├── BurpReportGenerator.java
│
├── model
│     ├── Project.java
│     ├── Finding.java
│     └── enums
│           ├── Severity.java
│           ├── Environment.java
│           ├── TestingApproach.java
│           ├── Classification.java
│           ├── FindingType.java
│           └── FindingStatus.java
│
├── ui
│     ├── MainPanel.java
│     ├── ProjectDetailsPanel.java
│     ├── DashboardPanel.java
│     ├── FindingPanel.java
│     └── StatusBarPanel.java
│
├── service
│     ├── ProjectService.java
│     ├── FindingService.java
│     ├── DashboardService.java
│     └── ReportService.java
│
├── library
│     ├── FindingLibrary.java
│     └── ExcelLibraryLoader.java
│
├── json
│     ├── JsonProjectReader.java
│     └── JsonProjectWriter.java
│
├── word
│     └── WordReportGenerator.java
│
└── util
      └── Constants.java
```

---

# 4. Core Data Model

## Project

Stores:

* Application Name
* Vendor Name
* Consultant
* Classification
* Start Date
* End Date
* Environment
* Testing Approach
* Finding Type
* Scope
* Assumptions
* List of Findings

Dashboard values are **not stored**. They are calculated dynamically.

---

## Finding

Stores:

* Finding ID
* Title
* Severity
* CVSS Score
* CWE
* OWASP
* Affected Components
* Description
* Impact
* Likelihood
* Recommendation
* References
* Proof of Concept
* Status

---

# 5. Finding Library

The Excel Finding Library acts as a template source.

Workflow:

```
Excel Library
      │
      ▼
Select Finding
      │
      ▼
Copy Into Project
      │
      ▼
Modify Description / Component / POC
```

The Excel library is **never modified**.

---

# 6. Project Storage

Projects will be saved as JSON.

Example:

```
CustomerPortal.json
```

Advantages:

* Human-readable
* Easy to edit
* Easy to back up
* Fast to load
* Version-friendly

---

# 7. Report Generation

Input:

* Project
* Findings
* Word Template

Output:

```
PenTest_Report.docx
```

The existing Word template will be populated automatically.

Proof of Concept images will initially be added manually.

---

# 8. Dashboard

Dashboard values are calculated automatically.

Displays:

* Total Findings
* Critical
* High
* Medium
* Low
* Info
* Overall Risk

No manual counting is required.

---

# 9. Future Features

Version 2.x may include:

* Request/Response attachment
* Screenshot management
* Auto-save
* Search
* Finding filters
* Report preview
* PDF export
* Multiple report templates

---

# 10. Design Principles

* Clean Architecture
* Separation of Concerns
* Single Responsibility Principle
* Modular Design
* JSON-based persistence
* Excel as a reusable finding library
* Word template-based report generation
* No hardcoded project data
* Maintainable and extensible codebase

---

# Current Development Status

* ✅ Environment Setup
* ✅ Git Repository
* ✅ Maven Project
* ✅ Montoya API Integration
* ✅ Burp Extension Loaded
* ✅ Initial UI
* 🚧 Domain Model (In Progress)
* ⏳ Project Details UI
* ⏳ Finding Management
* ⏳ Excel Library
* ⏳ Word Report Generation
