package cn.hh.study.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 它的作用只是为了能够在这里运行这个示例来检查代码是否正常工作（来自IBM与shiro quick start）
public class QuickStart {

	private static Logger logger = LoggerFactory.getLogger(QuickStart.class);

	public static void main(String[] args) {
		// Using the IniSecurityManagerFactory, which will use the an INI file
		// as the security file.
		// 通过 ini 文件作为安全配置文件来创建 安全管理工厂(IniSecurityManagerFactory)
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

		// Setting up the SecurityManager...
		SecurityManager securityManager = factory.getInstance();
		// SecurityUtils 对象是一个 singleton，这意味着不同的对象可以使用它来获得对当前用户的访问。
		// 一旦成功地设置了这个 SecurityManager，
		// 就可以在应用程序不同部分调用 SecurityUtils.getSubject() 来获得当前用户的信息。
		SecurityUtils.setSecurityManager(securityManager);

		// get the currently executing user:
		Subject currentUser = SecurityUtils.getSubject();

		logger.info("User is authenticated:  " + currentUser.isAuthenticated());

		// Do some stuff with a Session (no need for a web or EJB container!!!)
		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");
		String value = (String) session.getAttribute("someKey");
		if (value.equals("aValue")) {
			logger.info("Retrieved the correct value! [" + value + "]");
		}

		// let's login the current user so we can check against roles and
		// permissions:
		if (!currentUser.isAuthenticated()) {
			// 登录的令牌
			UsernamePasswordToken token = new UsernamePasswordToken("presidentskroob", "12345");
			token.setRememberMe(true);
			try {
				currentUser.login(token);// 进行登录
			} catch (UnknownAccountException uae) {
				logger.info("There is no user with username of " + token.getPrincipal());
			} catch (IncorrectCredentialsException ice) {
				logger.info("Password for account " + token.getPrincipal() + " was incorrect!");
			} catch (LockedAccountException lae) {
				logger.info("The account for username " + token.getPrincipal() + " is locked.  "
						+ "Please contact your administrator to unlock it.");
			}
			// ... catch more exceptions here (maybe custom ones specific to
			// your application?
			catch (AuthenticationException ae) {
				// unexpected condition? error?
			}
		}

		// say who they are:
		// print their identifying principal (in this case, a username):
		logger.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

		// test a role:
		if (currentUser.hasRole("schwartz")) {
			logger.info("May the Schwartz be with you!");
		} else {
			logger.info("Hello, mere mortal.");
		}
		if (currentUser.hasRole("goodguy")) {
			logger.info("May the goodguy be with you!");
		} else {
			logger.info("Hello, mere mortal.");
		}

		// test a typed permission (not instance-level)
		if (currentUser.isPermitted("lightsaber:weild")) {
			logger.info("You may use a lightsaber ring.  Use it wisely.");
		} else {
			logger.info("Sorry, lightsaber rings are for schwartz masters only.");
		}

		// a (very powerful) Instance Level permission:
		if (currentUser.isPermitted("winnebago:drive:eagle5")) {
			logger.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  "
					+ "Here are the keys - have fun!");
		} else {
			logger.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
		}

		// all done - log out!
		currentUser.logout();// 注销

	}
}