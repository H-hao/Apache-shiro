package cn.hh.study.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroTest {

	private static Logger logger = LoggerFactory.getLogger(ShiroTest.class);

	// TODO 测试出错
	public static void main(String[] args) {
		// Using the IniSecurityManagerFactory, which will use the an INI file
		// as the security file.
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("auth.ini");

		// Setting up the SecurityManager...
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject user = SecurityUtils.getSubject();

		logger.info("User is authenticated:  " + user.isAuthenticated());
	}
}