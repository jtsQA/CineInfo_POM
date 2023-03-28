/**
 * 
 */
package cine.info.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author Jithin Srinivasan
 */
public class RetryAnalyser implements IRetryAnalyzer {

	int counter = 0, retriesLimit = 2;

	public boolean retry(ITestResult result) {
		if (counter < retriesLimit) {
			counter++;
			return true;
		}
		return false;
		// boolean value = counter < retriesLimit;
		// counter++;
		// return value;
	}

}
