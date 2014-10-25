package com.xebia.exercice.mower.services;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

import com.xebia.exercice.mower.enums.EOrientation;
import com.xebia.exercice.mower.model.Lawn;
import com.xebia.exercice.mower.model.Mower;

/**
 * @author ahamraoui
 *both automatic test and file test mower
 */
public class MowerServiceTest {

	String injectedData = "src/test/resources/data.csv";
	String resultData = "src/test/resources/result.csv";

	MowerService mowerService;

	Mower mower1;
	Mower mower2;
	Lawn lawn;

	final static int lawnCsdx = 5;
	final static int lawnCsdy = 5;

	final static int mower1x = 1;
	final static int mower1y = 2;
	final static String mower1Orientation = "N";

	final static int mower2x = 3;
	final static int mower2y = 3;
	final static String mower2Orientation = "E";

	final static String mower1Instruction = "GAGAGAGAA";
	final static String mower2Instruction = "AADAADADDA";

	final static String resultMower1 = "1 3 N";
	final static String resultMower2 = "5 1 E";

	@Before
	public void setUp() throws Exception {
		mower1 = new Mower();
		mower1.setX(mower1x);
		mower1.setY(mower1y);
		mower1.setOrientation(EOrientation.valueOf(mower1Orientation));

		mower2 = new Mower();
		mower2.setX(mower2x);
		mower2.setY(mower2y);
		mower2.setOrientation(EOrientation.valueOf(mower2Orientation));

		lawn = new Lawn();
		lawn.setCsdx(lawnCsdx);
		lawn.setCsdy(lawnCsdy);

		mowerService = new MowerService(lawn);
	}

	@Test
	public void testGetPositionDataInjectedByAutoTest() throws Exception {
		System.out
				.println("======testGetPositionDataInjectedByAutoTest======\n");
		System.out
				.println("==================================================");
		System.out.println("\nMower1 initial position : "
				+ mowerService.getPosition(mower1));
		mowerService.navigate(mower1, mower1Instruction);
		System.out.println("Mower2 initial position : "
				+ mowerService.getPosition(mower2));
		mowerService.navigate(mower2, mower2Instruction);
		assertTrue(resultMower1.equals(mowerService.getPosition(mower1)));
		assertTrue(resultMower2.equals(mowerService.getPosition(mower2)));
	}

	@Test
	public void testGetPositionDataInjectedByFile() throws IOException {
		System.out
				.println("==================================================\n");
		System.out
				.println("========testGetPositionDataInjectedByFile=========\n");
		System.out
				.println("==================================================");
		StringBuilder result = new StringBuilder();
		CSVReader reader = new CSVReader(new FileReader(injectedData));
		String[] nextLine;
		int i = 0;
		Mower mower = null;
		while ((nextLine = reader.readNext()) != null) {
			if (i == 0) {
				String[] lawnCsd = nextLine[0].split(" ");
				lawn.setCsdx(Integer.valueOf(lawnCsd[0]));
				lawn.setCsdy(Integer.valueOf(lawnCsd[0]));
				System.out.println("Lawn max right top position : " + lawn);
				System.out.println("Deployed mowers : ");
			} else {
				if (i % 2 == 1) {
					if (mower == null) {
						mower = new Mower();
					}
					String[] xyp = nextLine[0].split(" ");
					mower.setX(Integer.valueOf(xyp[0]));
					mower.setY(Integer.valueOf(xyp[1]));
					mower.setOrientation(EOrientation.valueOf(xyp[2]));
					System.out.println("Mower_" + i + " initial position : "
							+ mowerService.getPosition(mower));
				} else {
					mowerService.navigate(mower, nextLine[0]);
					result.append(mowerService.getPosition(mower)).append("\n");
					mower = null;
				}
			}
			i++;
		}
		result.setLength(result.length() - 1);
		System.out.println("Result : \n" + result);
		String expectedContent = FileUtils
				.readFileToString(new File(resultData));
		String stringResult = result.toString();
		String expected = expectedContent.replace("\r", "");
		Assert.assertEquals(expected, stringResult);
	}
}
