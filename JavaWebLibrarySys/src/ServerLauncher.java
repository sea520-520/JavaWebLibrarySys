import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class ServerLauncher {
    public static void main(String[] args) throws Exception {
        String webappDirLocation = "web";
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/Library", new File(webappDirLocation).getAbsolutePath());

        File additionWebInfClasses = new File("out/production/Test_test");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        System.out.println("======================================");
        System.out.println("服务器启动成功!");
        System.out.println("访问地址: http://localhost:" + webPort + "/Library/");
        System.out.println("======================================");
        tomcat.getServer().await();
    }
}
