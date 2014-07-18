
/**
 *
 * This software is written by arcus(x) GmbH and subject
 * to a contract between arcus(x) and its customer.
 *
 * This software stays property of arcus(x) unless differing
 * arrangements between arcus(x) and its customer apply.
 *
 * arcus(x) GmbH
 * Hamburg, Germany
 *
 * Tel.: +49 (0)40.333 102 92
 * Fax.: +49 (0)40.333 102 93
 * http://www.arcusx.com
 * mailto:info@arcusx.com
 *
 */

public class MockMockGateway
{
	public static final Object PROCEED = new Object();
	public static final Object SUPPRESS = new Object();

	public static Object constructorCall(Class<?> type, Object[] args, Class<?>[] sig) throws Throwable
	{
		return PROCEED;
	}
}
