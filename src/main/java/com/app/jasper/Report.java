package com.app.jasper;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Report {

	public static void main(String[] args) {
		
		try {
			String path = "C:\\Users\\USER\\JaspersoftWorkspace\\MyReports\\JasperReport.jrxml";
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("studentName", "John Smith");
			
			Student s1 = new Student(1L, "Thaw Zin", "Aung", "Yangon", "Kawhmu");
			Student s2 = new Student(2L, "Hla Nan", "Tin", "Yangon", "Kawhmu");
			
			List<Student> students = List.of(s1, s2);
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);
			
			JasperReport jasperReport = JasperCompileManager.compileReport(path);
			
			JRBaseTextField textField = (JRBaseTextField) jasperReport.getTitle().getElementByKey("name");
			
			textField.setForecolor(Color.RED);
			
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			
			JasperExportManager.exportReportToPdfFile(print, "D:\\KBZ\\Udemy\\Jasper\\workspace\\exports\\FirstReport.pdf");
			
			System.out.println("Report Created");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
