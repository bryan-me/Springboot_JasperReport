package com.example.springbootjasperreports.service;

import com.example.springbootjasperreports.model.User;
import com.example.springbootjasperreports.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j

public class UserService {
    //Fix this
//    @Value("${report.path}")
    private String reportPath;

    public final UserRepo userRepo;
    //Get all users
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(UUID id){
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        log.info("User with id: {} doesn't exist", id);
        return null;
    }

    public User saveUser(User user){
//        employee.setCreatedAt(ZonedDateTime.now());
//        employee.setUpdatedAt(ZonedDateTime.now());
        User savedUser = userRepo.save(user);

        log.info("User with id: {} saved successfully", user.getId());
        return savedUser;
    }

    public User updateUser (User user) {
        Optional<User> existingUser = userRepo.findById(user.getId());
//        user.setCreatedAt(existingEmployee.get().getCreatedAt());
//        user.setUpdatedAt(ZonedDateTime.now());

        User updatedUser = userRepo.save(user);

        log.info("User with id: {} updated successfully", user.getId());
        return updatedUser;
    }
    public void deleteUserById (UUID id){
        userRepo.deleteById(id);
    }

    public String generateReport() {
        List<User> users = new ArrayList<>();
        userRepo.findAll().stream().forEach(e -> users.add(e));
        try {
            File file = ResourceUtils.getFile("classpath:Report.jrxml");
            InputStream input = new FileInputStream(file);
            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(input);
            // Get your data source
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(users);
            // Add parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "JavaHelper.org");
            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\User.pdf");
            System.out.println("PDF File Generated !!");
            JasperExportManager.exportReportToXmlFile(jasperPrint, reportPath + "\\User.xml", true);
            System.out.println("XML File Generated !!");
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "\\User.html");
            System.out.println("HTML Generated");
            xlsx(jasperPrint);
            csv(jasperPrint);
            return "Report successfully generated @path= " + reportPath;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    private void csv(JasperPrint jasperPrint) throws JRException {
        JRCsvExporter exporter = new JRCsvExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(reportPath + "\\User.csv"));
        SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        configuration.setFieldDelimiter(",");
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }
    // Ref: https://www.programcreek.com/java-api-examples/?class=net.sf.jasperreports.export.SimpleXlsxReportConfiguration&method=setOnePagePerSheet
    private void xlsx(JasperPrint jasperPrint) throws JRException {
        // Exports a JasperReports document to XLSX format. It has character output type and exports the document to a grid-based layout.
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath + "\\User.xlsx"));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setDetectCellType(true);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }
}

