package dusanselenium.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count = 0;
	int maxTries = 2;
	@Override
	public boolean retry(ITestResult result) {
		if(count < maxTries) {
			count++;
			return true;
		}
		return false;
	}

}
