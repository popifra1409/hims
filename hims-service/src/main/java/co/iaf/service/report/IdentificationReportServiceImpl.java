package co.iaf.service.report;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import co.iaf.entity.identification.Patient;
import co.iaf.service.identification.IdentificationService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional
public class IdentificationReportServiceImpl implements IdentificationReportService {

	@Autowired
	private IdentificationService identificationService;

	@Override
	public String exportReport(String reportFormat) throws JRException, IOException {
		String path = "C:\\Users\\NOLLEN\\Desktop\\report";
		List<Patient> patients = identificationService.getAllPatients();
		// load file and compile it
		Resource resource = new ClassPathResource("reports/identification/lists_patients.jrxml");
		File file = resource.getFile();
		//File file = ResourceUtils.getFile("classpath:lists_patients.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		// mapping with data source
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(patients);
		// printing results
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("Created By", "HIMASYS");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		// checking report format
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\liste_patients" + "html");
		}

		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\liste_patients" + "pdf");
		}

		return "Etat généré à l'emplacement :" + path;
	}

}
