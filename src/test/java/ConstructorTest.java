import javassist.ClassPool;
import javassist.CtClass;

import org.powermock.core.transformers.impl.MainMockTransformer;

public class ConstructorTest
{
	@org.junit.Test
	public void testConstructorManipulationFailure() throws InstantiationException, IllegalAccessException
	{
		final Class<?> clazz = new MockingClassloader().loadMockClass("MockedClass");
		//This fails
		clazz.getDeclaredConstructors();
	}

	public static class MockingClassloader extends ClassLoader
	{
		private ClassPool classPool = ClassPool.getDefault();

		public Class<?> loadMockClass(String name)
		{
			ClassPool.doPruning = false;
			try
			{
				CtClass clazz = this.classPool.get(name);
				clazz = new MainMockTransformer().transform(clazz);
				byte[] clazzBytes = clazz.toBytecode();
				return defineClass(name, clazzBytes, 0, clazzBytes.length);
			}
			catch (Exception e)
			{
				throw new IllegalStateException("Failed to transform class with name " + name + ". Reason: " + e.getMessage(), e);
			}
		}
	}
}
