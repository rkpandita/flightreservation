package com.raman.flightreservation.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ReportUtilImpl implements ReportUtil {

	private Log logger = LogFactory.getLog(ReportUtilImpl.class);

	// Please add "/*" in 'WebSecurityConfig' for 'Generating reports'
	@Override
	public void generatePieChart(String path, List<Object[]> data) {

		DefaultPieDataset dataset = new DefaultPieDataset();

		for (Object[] object : data) {
			dataset.setValue(object[0].toString(), new Double(object[1].toString()));
		}

		JFreeChart chart = ChartFactory.createPieChart3D("Airlines report", dataset, true, true, false);

		try {
			ChartUtilities.saveChartAsJPEG(new File(path + "/AirlinesPieChart.jpeg"), chart, 300, 300);
		} catch (IOException e) {
			logger.error("Error while generating chart: ", e);
		}
	}

}
