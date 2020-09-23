/**
 * 
 */
package matatis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author xp
 * @date 2019年8月21日
 * 
 */
public class test {
	
	@Test
	public void test1() {
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		
		//ctx.get
		
		//DatabaseTest userService=(DatabaseTest) ctx.getBean("databasetest");
		
		//userService.ttt();
		
	}

}
