
package javassist;

/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javassist.expr.ConstructorCall;
import javassist.expr.ExprEditor;

public class ConstructorTest
{
	@org.junit.Test
	public void testForBadOperandWhenInvokingInit() throws Exception
	{
		CtClass ctClass = ClassPool.getDefault().makeClass("TestForBadOperandWhenInvokingInit");

		//		java.lang.VerifyError: Bad operand type when invoking <init>
		ctClass.addConstructor(CtNewConstructor.make(new CtClass[0], new CtClass[0], "{if (1 != 2){ super();}}", ctClass));
		final Class<?> clazz = ctClass.toClass();

		clazz.getDeclaredConstructors();
	}

	@org.junit.Test
	public void testForBadInitCallFromInsideOfABranch() throws Exception
	{
		CtClass ctClass = ClassPool.getDefault().makeClass("TestForBadInitCallFromInsideOfABranch");
		ctClass.addConstructor(CtNewConstructor.make(new CtClass[0], new CtClass[0], "{}", ctClass));

		ctClass.instrument(new ExprEditor()
		{
			@Override
			public void edit(ConstructorCall c) throws CannotCompileException
			{
				//java.lang.VerifyError: Bad <init> method call from inside of a branch
				c.replace("if (1 != 2){ super();}");
			}
		});
		final Class<?> clazz = ctClass.toClass();

		clazz.getDeclaredConstructors();
	}
}
