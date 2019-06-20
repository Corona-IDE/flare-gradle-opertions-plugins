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