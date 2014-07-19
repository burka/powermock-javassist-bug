package powermock;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ MockedClass.class})
@PowerMockIgnore({ "org.mockito.cglib.*"})
public class PowermockedConstructorTest
{
	@org.junit.Test
	public void testWithPowermockConstructorSupression() throws InstantiationException, IllegalAccessException
	{
		suppress(constructor(MockedClass.class));

		mockStatic(MockedClass.class);
		final MockedClass mock = mock(MockedClass.class);
	}
}
