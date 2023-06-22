package co.iaf.controller.identification;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.iaf.service.report.IdentificationReportService;
import net.sf.jasperreports.engine.JRException;

@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/hims")
public class IdenitificationReportResource {

	private IdentificationReportService identificationReportService;

	public IdenitificationReportResource(IdentificationReportService identificationReportService) {
		this.identificationReportService = identificationReportService;
	}

	@GetMapping(path = "/patients/report/{format}")
	public String generateReport(@PathVariable String format) throws JRException, IOException {
		return this.identificationReportService.exportReport(format);
	}
}
