// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

import java.lang.reflect.*;
import study.chenji.spring.aop.jdk.TestJDKAOP;

public class $Proxy0 extends Proxy
	implements TestJDKAOP
{

	private static Method m1;
	private static Method m2;
	private static Method m0;
	private static Method m3;

	public $Proxy0(InvocationHandler invocationhandler)
	{
		super(invocationhandler);
	}

	public final boolean equals(Object obj)
	{
		try
		{
			return ((Boolean)super.h.invoke(this, m1, new Object[] {
				obj
			})).booleanValue();
		}
		catch (Error ) { }
		catch (Throwable throwable)
		{
			throw new UndeclaredThrowableException(throwable);
		}
	}

	public final String toString()
	{
		try
		{
			return (String)super.h.invoke(this, m2, null);
		}
		catch (Error ) { }
		catch (Throwable throwable)
		{
			throw new UndeclaredThrowableException(throwable);
		}
	}

	public final int hashCode()
	{
		try
		{
			return ((Integer)super.h.invoke(this, m0, null)).intValue();
		}
		catch (Error ) { }
		catch (Throwable throwable)
		{
			throw new UndeclaredThrowableException(throwable);
		}
	}

	public final String getName(String s)
	{
		try
		{
			return (String)super.h.invoke(this, m3, new Object[] {
				s
			});
		}
		catch (Error ) { }
		catch (Throwable throwable)
		{
			throw new UndeclaredThrowableException(throwable);
		}
	}

	static 
	{
		try
		{
			m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] {
				Class.forName("java.lang.Object")
			});
			m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
			m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
			m3 = Class.forName("study.chenji.spring.aop.jdk.TestJDKAOP").getMethod("getName", new Class[] {
				Class.forName("java.lang.String")
			});
		}
		catch (NoSuchMethodException nosuchmethodexception)
		{
			throw new NoSuchMethodError(nosuchmethodexception.getMessage());
		}
		catch (ClassNotFoundException classnotfoundexception)
		{
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
	}
}
