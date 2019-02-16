package com.raman.flightreservation.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReportUtilImpl implements ReportUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportUtilImpl.class);


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
			LOGGER.error("Error while generating chart: ", e);
		}
	}

}
