package de.gravitex.processing.testing.appliance;

import org.apache.log4j.PropertyConfigurator;

public class TestApplianceMain {

	public static void main(String[] args) {

		// log4j
		// PropertyConfigurator.configure("C:\\log4j_props\\processing_log4j.properties");
		PropertyConfigurator.configure("/Users/stefan/log4j_props/log4j.properties");

		new TestApplianceGUI();
	}
}
