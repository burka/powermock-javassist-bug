import javassist.ClassPool;
import javassist.CtClass;

import org.powermock.core.transformers.impl.MainMockTransformer;

public class ConstructorTest
{
	@org.junit.Test
	public void testConstructorManipulationFailure() throws Exception
	{
		final Class<?> clazz = new MockingClassloader().loadMockClass("MockedClass");
		//This fails
		clazz.getDeclaredConstructors();
	}

	public static class MockingClassloader extends ClassLoader
	{
		public Class<?> loadMockClass(String name) throws Exception
		{
			CtClass clazz = ClassPool.getDefault().get(name);

			//Somewhere here the bad stuff happens.
			clazz = new MainMockTransformer().transform(clazz);

			byte[] clazzBytes = clazz.toBytecode();
			return defineClass(name, clazzBytes, 0, clazzBytes.length);
		}
	}
}
