Powermock / Javassist creates illegal constructors for JDK 1.7.0u65 and 1.8.0u11 with -target 7

If you remove -target 7 it works for both JDK 1.7.0u65 and 1.8.0u11.

```
Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.003 sec <<< FAILURE! - in ConstructorTest
testConstructorManipulationFailure(ConstructorTest)  Time elapsed: 0.002 sec  <<< ERROR!
java.lang.VerifyError: Bad <init> method call from inside of a branch
Exception Details:
  Location:
    MockedClass.<init>()V @32: invokespecial
  Reason:
    Error exists in the bytecode
  Bytecode:
    0000000: 2a4c 120f b800 1503 bd00 0d12 16b8 001a
    0000010: b800 204e 2db2 0024 a500 0e2a 01c0 0026
    0000020: b700 29a7 0009 2bb7 002b 0157 b1       
  Stackmap Table:
    full_frame(@38,{UninitializedThis,UninitializedThis,Top,Object[#13]},{})
    full_frame(@44,{Object[#2],Object[#2],Top,Object[#13]},{})

	at java.lang.Class.getDeclaredConstructors0(Native Method)
	at java.lang.Class.privateGetDeclaredConstructors(Class.java:2658)
	at java.lang.Class.getDeclaredConstructors(Class.java:2007)
	at ConstructorTest.testConstructorManipulationFailure(ConstructorTest.java:13)
```




