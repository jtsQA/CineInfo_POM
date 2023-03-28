/**
 * 
 */
package cine.info.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * @author Jithin Srinivasan
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
		testAnnotation.setRetryAnalyzer(RetryAnalyser.class);
	}

}
