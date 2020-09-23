package com.agree.util;

/**
 * 
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：自定义脚本加载器，继续使用双亲委派机制，只有调用loadScript才会使用本加载器调用</B><BR>
 * @since 2019年11月13日
 */
public class ScriptClassLoader extends ClassLoader{
	
	public ScriptClassLoader() {
		super(ScriptClassLoader.class.getClassLoader());
	}
	
	public Class<?> loadScript(byte[] classByte) {
		return defineClass(null, classByte, 0, classByte.length);
	}
	
	public Class<?> getLoadClass(String name){
		return findLoadedClass(name);
	}
}
