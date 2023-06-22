package co.iaf.service.report;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

public interface IdentificationReportService {

	public String exportReport(String reportFormat) throws JRException, IOException;
}
