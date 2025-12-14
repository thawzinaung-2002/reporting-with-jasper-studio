package com.app.jasper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class StudentReport {

	public static void main(String[] args) {
		
		try {
			String path = "C:\\Users\\USER\\JaspersoftWorkspace\\MyReports\\Student.jrxml";
			
			Subject s1 = new Subject("Myan", 73);
			Subject s2 = new Subject("Eng", 69);
			Subject s3 = new Subject("Maths", 84);
			Subject s4 = new Subject("Chemist", 88);
			Subject s5 = new Subject("Physics", 84);
			
			List<Subject> list = new ArrayList<>();
			list.add(s1);
			list.add(s2);
			list.add(s3);
			list.add(s4);
			list.add(s5);
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
			JRBeanCollectionDataSource chart = new JRBeanCollectionDataSource(list);

			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("studentName", "Thaw Zin Aung");
			parameters.put("tableData", dataSource);
			parameters.put("subReport", getSubReport());
			parameters.put("subDatasource", getSubDataSource());
			parameters.put("subParameters", getSubParameters());
			
			JasperReport report = JasperCompileManager.compileReport(path);
			
			JasperPrint print = JasperFillManager.fillReport(report, parameters, chart);
			
			//PDF File Export
			JasperExportManager.exportReportToPdfFile(print, "D:\\KBZ\\Udemy\\Jasper\\workspace\\jasper-demo\\src\\main\\resources\\exports\\Student.pdf");
			
			
			//HTML File Export
			JasperExportManager.exportReportToHtmlFile(print, "D:\\KBZ\\Udemy\\Jasper\\workspace\\jasper-demo\\src\\main\\resources\\exports\\Student.html");

			//Excel File Export
			JRXlsxExporter xlsExport = new JRXlsxExporter();
			xlsExport.setExporterInput(new SimpleExporterInput(print));
			xlsExport.setExporterOutput(
					new SimpleOutputStreamExporterOutput(
							new FileOutputStream(
									new File("D:\\KBZ\\Udemy\\Jasper\\workspace\\jasper-demo\\src\\main\\resources\\exports\\Student.xlsx"))));
			xlsExport.exportReport();
			
			System.out.println("Report Created......");
			
		}
		catch(Exception e) {
			System.out.println("Error while exporting....");
		}
		
	}
	
	public static JasperReport getSubReport() {
		
		try {
			String path = "C:\\Users\\USER\\JaspersoftWorkspace\\MyReports\\JasperReport.jrxml";
			
			return JasperCompileManager.compileReport(path);
			
		} catch (JRException e) {
			System.out.println("Error while Exporting Sub-Report");
		}
		return null;
	}
	
	public static JRBeanCollectionDataSource getSubDataSource() {
			
		Student s1 = new Student(1L, "Thaw Zin", "Aung", "Yangon", "Kawhmu");
		Student s2 = new Student(2L, "Hla Nan", "Tin", "Yangon", "Kawhmu");
		
		List<Student> students = List.of(s1, s2);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);
		
		return dataSource;
	}
	
	public static Map<String, Object> getSubParameters() {
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("studentName", "John Smith");
		
		return parameters;
	}
	
}
