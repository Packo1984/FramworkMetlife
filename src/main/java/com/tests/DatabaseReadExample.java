package main.java.com.tests;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import main.java.com.corecomponents.GenericClass;

public class DatabaseReadExample {

	@Test(groups = "database")
	public void readDatabaseExample() {
		GenericClass coreMethods = new GenericClass("DatabaseReadExample");
		
		coreMethods.test = coreMethods.report.startTest("Test-" + ": TC " + "databaseReadExample");

		ResultSet rs = null;
		Connection conn = null;

		String query = "select * from Broker_Dealer_Comp where full_policy_number like 'Adj%'";
		try {

			String dbURL = "jdbc:sqlserver://USAZEMETU05FF\\USAZEMETU05FF;databaseName=USLStatements_Dev";
			String user = "Aq1116601";
			String pass = coreMethods.decryptText("U1FMUlJZWTFmMHk=");
			conn = DriverManager.getConnection(dbURL, user, pass);
			DatabaseMetaData dm = conn.getMetaData();
			coreMethods.LOGGER.info("Driver name: " + dm.getDriverName());
			coreMethods.LOGGER.info("Driver version: " + dm.getDriverVersion());
			coreMethods.LOGGER.info("Product name: " + dm.getDatabaseProductName());
			coreMethods.LOGGER.info("Product version: " + dm.getDatabaseProductVersion());

			rs = conn.createStatement().executeQuery(query);

			int numCols = rs.getMetaData().getColumnCount();

			coreMethods.assertEquals(numCols, 38);

		} catch (SQLException ex) {
			coreMethods.LOGGER.severe(ex.getMessage());
			coreMethods.logFailedStepToReport(coreMethods.test, ex.getMessage());

		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException ex) {
				coreMethods.LOGGER.severe(ex.getLocalizedMessage());
			}
		}
	}
}
