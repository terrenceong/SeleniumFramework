package terrenceong.link.reporters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

    public static ExtentReports getReportObject(){
//        String path = System.getProperty("user.dir") + "\\reports\\report.html";
        String path = "/reports/report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("e2e Test");
        reporter.config().setDocumentTitle("Test Report");

        ExtentReports extent =  new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Terrence Ong");
        return extent;
    }
}
