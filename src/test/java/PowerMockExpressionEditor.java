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

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewConstructor;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import javassist.expr.ConstructorCall;
import javassist.expr.ExprEditor;

public class PowerMockExpressionEditor extends ExprEditor
{
	private final CtClass clazz;

	public PowerMockExpressionEditor(CtClass clazz)
	{
		this.clazz = clazz;
	}

	@Override
	public void edit(ConstructorCall c) throws CannotCompileException
	{
		try
		{
			ClassPool classPool = this.clazz.getClassPool();
			CtClass constructorType = classPool.get(ConstructorClass.class.getName());
			this.clazz.addConstructor(CtNewConstructor.make(new CtClass[] { constructorType}, new CtClass[0], "{super();}", this.clazz));
			c.replace("if (1 != 2){ super();}");
		}
		catch (DuplicateMemberException | NotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}
}