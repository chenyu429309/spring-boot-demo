package com.felix.springbootdemo.designPatterns.proxy;

public class StaticProxyTest {
	/**
	 * JDK动态代理的代理对象只能通过接口去接收，如果用原对象接收，会报类型转换异常。
	 * cglib不能拦截final修饰的方法，调用时只会执行原有方法。
	 * cglib是在运行时通过操作字节码来完成类的扩展和改变，除了代理，
	 * 还支持很多强大的操作，比如bean的生成和属性copy，
	 * 动态创建接口以及融合多个对象等，
	 * 具体见https://github.com/cglib/cglib/wiki/Tutorial。
	 * @param args
	 *
	 *
	 * JDK动态代理只能对实现了接口的类生成代理，而不能针对类 。
	 * CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法 。
	 * 因为是继承，所以该类或方法最好不要声明成final ，final可以阻止继承和多态。
	 */
	public static void main(String[] args) {
//		Browser browser = new ChromeBrowserProxy(new ChromeBrowser());
//		browser.visitInternet();
//		Browser browser = new JdkBrowserProxy(new ChromeBrowser()).getProxy();
//		browser.visitInternet();
//		ChromeBrowser browser = CglibBrowserProxy.getInstance().getProxy(ChromeBrowser.class);
//		browser.visitInternet();
//		browser.listenToMusic();
		Browser browser = CglibIntroductionBrowserProxy.getInstance().getProxy(ChromeBrowser.class);
		browser.visitInternet();

		Game game = (Game) browser;
		game.start();



	}
}
