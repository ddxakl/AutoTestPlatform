package com.agree.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.agree.aat.service.impl.ScriptCaseServiceImpl;
import com.agree.framework.helper.ApplicationContentHelper;
import com.agree.system.entity.ExecuteCase;

import cn.com.agree.abcAutoTest.test.ExecuteTest;
import net.sf.json.JSONObject;
import cn.com.agree.abcAutoTest.test.IAble;

public class ClassUtils {
	
	
	private static final Logger log=LogManager.getLogger(ClassUtils.class);


	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
			
		}
		if (cl == null) {
			
			cl = PropertiesLoaderUtils.class.getClassLoader();
			if (cl == null) {
				
				try {
					cl = ClassLoader.getSystemClassLoader();
				}
				catch (Throwable ex) {
					
				}
			}
		}
		return cl;
	}
	  
	
   /**
    * 
    * <B>方法名称：</B><BR>
    * <B>概要说明：合并属性</B><BR>
    * @param a
    * @param b
    * @return
    */
    private static Object[] mergeArray(Object[] a,Object[] b) {
        Object[] c = Arrays.copyOf(a, a.length+b.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
    
	    
	 /**
	  * 
	  * <B>方法名称：</B><BR>
	  * <B>概要说明：首字母大写</B><BR>
	  * @param fieldName
	  * @return
	  */
    private static String firstToBig(String fieldName) {
        if (fieldName != null && fieldName != "") {
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        return fieldName;
    }
    
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param obj
	 * @param cobj
	 */
	public static void Json2Bean(JSONObject obj, Object cobj) {		
		 // 取得po对象的所有属性
        Field[] Fields = cobj.getClass().getDeclaredFields();
        //取父类的所有属性
        Field[] superFields = cobj.getClass().getSuperclass().getDeclaredFields();

        Fields = (Field[]) mergeArray(Fields,superFields);
        
        for (Field f : Fields) {
            String fieldName = f.getName();
            Class<?> type = f.getType();
            String poSetMethodName = "set" + firstToBig(fieldName);
       
            
            if(obj.containsKey(fieldName)) {
            	Object value = null;
            	if(type.getName().equals("[B")) {
            		continue;
            	}else {
            		value = obj.get(fieldName)+"";
            	}
            	Method method;
				try {
					method = cobj.getClass().getMethod(poSetMethodName, type);

					if(type.getName().equals("java.lang.Integer")) {
						value = Integer.parseInt((String)value);
					}else if(type.getName().equals("java.lang.Long")) {
						value = Long.parseLong((String)value);
					}
					
					method.invoke(cobj, value);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
            }else {
            	continue;
            }
        }
	}
	

	 /**  
         * 对象转数组  
     * @param obj  
     * @return  
     */  
    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }   
       
    /**  
         * 数组转对象  
     * @param bytes  
     * @return  
     */  
    public static Object toObject (byte[] bytes) {  
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
    
    /**
     * 获取class文件中，类的全限定名
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;
    
    private static final int CONSTANT_UTF8_INFO = 1;

    private static final int CONSTANT_CLASS_INFO = 7;
    private static final int CONSTANT_LONG_INFO = 5;
    private static final int CONSTANT_DOUBLE_INFO = 6;
    
    private static final int[] CONSTANT_ITEM_LENGTH= {0,-1,0,5,5,9,9,3,3,5,5,5,5,0,0,4,3,0,5};
    
    private static final int u1 = 1;
    
    private static final int u2 = 2;
    
    /**
     * 
     * <B>方法名称：</B><BR>
     * <B>概要说明：获取常量池大小</B><BR>
     * @param clsByte
     * @return
     */
    public static int getConstantPoolCount(byte[] clsByte) {
    	if(clsByte.length==0) {
    		return 0;
    	}
   	
    	return ByteUitl.byte2int(clsByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }
    
    /**
     * 
     * <B>方法名称：</B><BR>
     * <B>概要说明：获取类全限定名</B><BR>
     * @param clsByte
     * @return
     */
	public static String getClassName(byte[] clsByte) {
		
		int cindex= getClassNameindex(clsByte);
		if(cindex == 0) {
			return "";
		}
		int cpl = getConstantPoolCount(clsByte);
		int offset = CONSTANT_POOL_COUNT_INDEX + 2;
		// 常量池从1 开始计数
		for(int i=1;i<cpl;i++) {
			
			int tag = ByteUitl.byte2int(clsByte, offset, u1);
			if(tag==CONSTANT_LONG_INFO || tag==CONSTANT_DOUBLE_INFO) {
				offset += CONSTANT_ITEM_LENGTH[tag];
				i+=1;
			}else if(tag==CONSTANT_UTF8_INFO){
				int len = ByteUitl.byte2int(clsByte, offset + u1, u2);
				offset +=(u1+u2);
				
				if (cindex== i) {
					 String str = ByteUitl.byte2String(clsByte, offset, len);
					 return str.replaceAll("/", ".");
				}
				offset += len;
			}else {
				offset += CONSTANT_ITEM_LENGTH[tag];
			}
			
		}
		
		return "";
	}
	
	private static int getClassNameindex(byte[] clsByte) {
		
		int cpl = getConstantPoolCount(clsByte);
		
		int offset = CONSTANT_POOL_COUNT_INDEX + 2;
		for(int i=1;i<cpl;i++) {			
			
			int tag = ByteUitl.byte2int(clsByte, offset, u1);
			
			if(tag==CONSTANT_LONG_INFO || tag==CONSTANT_DOUBLE_INFO) {
				offset += CONSTANT_ITEM_LENGTH[tag];
				i+=1;
			}else if(tag==CONSTANT_UTF8_INFO){
				int len = ByteUitl.byte2int(clsByte, offset + u1, u2);
				offset +=(u1+u2);
				offset += len;
			}else {	
				offset += CONSTANT_ITEM_LENGTH[tag];
			}
			
		}
		
		//跳过权限
		offset +=u2;
		//类全限定名路径
		int inx = ByteUitl.byte2int(clsByte, offset, u2);
		
		return getClassStringindex(clsByte,inx);
		
	}
	
	private static int getClassStringindex(byte[] clsByte,int idx) {
		int cpl = getConstantPoolCount(clsByte);
		
		int offset = CONSTANT_POOL_COUNT_INDEX + 2;
		for(int i=1;i<cpl;i++) {			
			
			int tag = ByteUitl.byte2int(clsByte, offset, u1);
			
			if(tag==CONSTANT_LONG_INFO || tag==CONSTANT_DOUBLE_INFO) {
				offset += CONSTANT_ITEM_LENGTH[tag];
				i+=1;
			}else if(tag==CONSTANT_CLASS_INFO && i==idx) {
				int len = ByteUitl.byte2int(clsByte, offset + u1, u2);
				return len;
			}else if(tag==CONSTANT_UTF8_INFO){
				int len = ByteUitl.byte2int(clsByte, offset + u1, u2);
				offset +=(u1+u2);
//				String str = ByteUitl.byte2String(clsByte, offset, len);
				offset += len;
			}else {	
				offset += CONSTANT_ITEM_LENGTH[tag];
			}
			
		}
		return 0;
	}


	public static void loadScriptCase(ExecuteCase executeCase) {
		byte[] scriptclass = executeCase.getScriptclass();
		if(scriptclass==null) {
			throw new IllegalArgumentException("脚本CLASS错误....");
		}
		if(scriptclass.length==0) { 
			log.debug("脚本没有编译");
			return ;
		}
		IAble able = null;
		JSONObject args = putFillArgs(executeCase);
		ScriptClassLoader scl = new ScriptClassLoader();
		try {
			String className = getClassName(scriptclass);
			
			if("".equals(className)) {
				log.info("脚本加载失败！");
				return ;
			}
			
			Class<?> cls = null;
			
			synchronized (ApplicationContentHelper.class) {
				try{
					cls = scl.getLoadClass(className.trim());
				}catch (Throwable e) {

				}finally {
					if (cls==null) {
						cls = scl.loadScript(scriptclass);
					}
				}		
			}
		able = (IAble)cls.newInstance();
		able.initContext(args);
		able.start();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static JSONObject putFillArgs(ExecuteCase executeCase) {
		
		JSONObject json = JSONObject.fromObject(executeCase);
		json.remove("planid");
		json.remove("tradecodes");
		json.remove("scriptclass");
		return json;
	}


	/**
	 * 加载案例class数据
	 * @param cpath
	 * @return
	 */
	public static byte[] loadByteArray(String cpath) {
		
		if(cpath==null||"".equals(cpath)) {
			return null;
		}
		
		FileInputStream fin = null;
		byte[] blen = null;
				
		try {
			//找到目录下class
			fin = new FileInputStream(cpath);
			blen = new byte[fin.available()];
			fin.read(blen);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fin!=null) 
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return blen;
		
	}
	
	
	public static void main(String[] args) {
//		String path="E:\\software\\eclipse-oxy-jee\\workspace\\AT_Platform_2019A\\target\\classes\\com\\agree\\framework\\publish\\";
//
//		File file = new File(path);
//		File[] tempList = file.listFiles();
//		System.out.println(tempList[0].getName());
		
		String path="E:\\software\\eclipse-oxy-jee\\workspace\\AT_Platform_2019A\\target\\classes\\com\\agree\\framework\\publish\\CaseTest.class";
		byte[] loadByteArray = loadByteArray(path);
//		loadScriptCase(loadByteArray);
		
		
	}
	
    
}
