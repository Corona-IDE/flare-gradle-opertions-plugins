# Migrating Major Plug-in Versions

## 0.x to 1.x

No changes required - major version was a change to the implementing language

## 1.x to 2.x

### Required

- Update to Gradle 4.x or later

### Recommended

- Remove workaround for GH-20:
```
mergeCoverageReports {
    reports {
        xml.enabled true
        html.enabled false
        csv.enabled false
        
        xml.destination = "${buildDir}/reports/jacoco/report.xml"
    }
}
```
- Migrate from `org.starchartlabs.flare.dependency-insight` plug-in to `org.starchartlabs.flare.dependency-reporting`
  - This does not require re-configuration, but will change the names of the tasks added by the plug-in:
    - `dependencyReport` -> `dependencyFullReport`
    - `dependencyInsightReport` -> `dependencyDetailReport`
    - `dependencyProjectReport` -> `dependencyProjectDetailReport`
    
### 2.x to 3.x

### Required

- Update to Gradle 5.x or later
    