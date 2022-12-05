package runtimePT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import Runner.GameRunner;
import Utilities.BrawlOutputter;

public class RunTimeTrendCalculator {
	
	protected static final int TEST_COUNT = 100;

	public static void main(String args[]) {

		double[] runtimesBase = new double[TEST_COUNT];
		double[] runtimes300 = new double[TEST_COUNT];
		double[] runtimes500 = new double[TEST_COUNT];
		double[] runtimes700 = new double[TEST_COUNT];
		double[] runtimes1000 = new double[TEST_COUNT];

		File performanceOutput = new File("./performanceTest/performance_output/performance.csv");

		try {

			if (performanceOutput.createNewFile()) {
				System.out.println("File created: " + performanceOutput.getName());
			} else {
				System.out.println("File already exists.");
			}

			FileWriter writer = new FileWriter(performanceOutput, false);

			writer.append("id, combatant_count, run_number, seconds_taken\n");

			// BASE

			String[] testArgs = new String[] { "behir", "red", "2", "balor", "blue", "1" };

			for (int i = 0; i < TEST_COUNT; i++) {

				GameRunner.main(testArgs);

				JSONObject currentOutput = BrawlOutputter.getBrawlOutputter().getBrawlOutput();

				long runTime = currentOutput.getLong("runtime");

				runtimesBase[i] = runTime / 1000.0;
			}

			// 300

			testArgs = new String[] { "skeleton", "red", "150", "goblin", "blue", "150" };

			for (int i = 0; i < TEST_COUNT; i++) {

				GameRunner.main(testArgs);

				JSONObject currentOutput = BrawlOutputter.getBrawlOutputter().getBrawlOutput();

				long runTime = currentOutput.getLong("runtime");

				runtimes300[i] = runTime / 1000.0;
			}

			// 500

			testArgs = new String[] { "skeleton", "red", "150", "goblin", "blue", "150", "direWolf", "blue", "100",
					"blackBear", "red", "100" };

			for (int i = 0; i < TEST_COUNT; i++) {

				GameRunner.main(testArgs);

				JSONObject currentOutput = BrawlOutputter.getBrawlOutputter().getBrawlOutput();

				long runTime = currentOutput.getLong("runtime");

				runtimes500[i] = runTime / 1000.0;
			}

			// 700

			testArgs = new String[] { "skeleton", "red", "200", "goblin", "blue", "200", "direWolf", "blue", "150",
					"blackBear", "red", "150" };

			for (int i = 0; i < TEST_COUNT; i++) {

				GameRunner.main(testArgs);

				JSONObject currentOutput = BrawlOutputter.getBrawlOutputter().getBrawlOutput();

				long runTime = currentOutput.getLong("runtime");

				runtimes700[i] = runTime / 1000.0;
			}

			// 1000

			testArgs = new String[] { "skeleton", "red", "250", "goblin", "blue", "250", "direWolf", "blue", "250",
					"blackBear", "red", "250" };

			for (int i = 0; i < TEST_COUNT; i++) {

				GameRunner.main(testArgs);

				JSONObject currentOutput = BrawlOutputter.getBrawlOutputter().getBrawlOutput();

				long runTime = currentOutput.getLong("runtime");

				runtimes1000[i] = runTime / 1000.0;
			}

			// Print
			
			int totalCount = 0;
			
			for (int i = 0; i < TEST_COUNT; i++) {
				writer.append(
						+ totalCount++ + ", "
						+ "3, "
						+ i + ", "
						+ runtimesBase[i]
						+ "\n"
				);
			}

			for (int i = 0; i < TEST_COUNT; i++) {
				writer.append(""
						+ totalCount++ + ", "
						+ "300, "
						+ i + ", "
						+ runtimes300[i]
						+ "\n"
				);
			}

			for (int i = 0; i < TEST_COUNT; i++) {
				writer.append(""
						+ totalCount++ + ", "
						+ "500, "
						+ i + ", "
						+ runtimes500[i]
						+ "\n"
				);
			}

			for (int i = 0; i < TEST_COUNT; i++) {
				writer.append(""
						+ totalCount++ + ", "
						+ "700, "
						+ i + ", "
						+ runtimes700[i]
						+ "\n"
				);
			}

			for (int i = 0; i < TEST_COUNT; i++) {
				writer.append(""
						+ totalCount++ + ", "
						+ "1000, "
						+ i + ", "
						+ runtimes1000[i]
						+ "\n"
				);
			}
			
			writer.close();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
