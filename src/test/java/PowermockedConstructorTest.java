import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ MockedClass.class})
@PowerMockIgnore({ "org.mockito.cglib.*"})
public class PowermockedConstructorTest
{
	@Mock
	private MockedClass mocked;

	@org.junit.Test
	public void testWithPowermock() throws InstantiationException, IllegalAccessException
	{
		MockitoAnnotations.initMocks(this);

		mockStatic(MockedClass.class);
	}
}
