package org.xulang.test;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xulang.maventool.MavenTool;
import org.xulang.maventool.constant.MavenServerApiConstant;
import org.xulang.maventool.model.MavenServer;
import org.xulang.maventool.model.Response;
import org.xulang.maventool.request.JarUploadRequest;
import org.xulang.maventool.request.JarWithPomUploadRequest;

import junit.framework.TestCase;

public class Test extends TestCase {

	private MavenServer mavenServer;

	private Logger logger = Logger.getLogger(Test.class.getName());

	@Override
	protected void setUp() throws Exception {
		mavenServer = new MavenServer("username", "password", "ip", "port");
	}

	public void testUploadJarWithoutPom() throws Exception {
		JarUploadRequest jarUploadEnty = new JarUploadRequest("asdg44", "daf44", "0.0.1-version", "jar",
				new File("D://junrar-0.7.jar"), "bank_dept3_hosted_snapshot");
		Response response = MavenTool.getInstance().doMavenPost(mavenServer, MavenServerApiConstant.ARTIFACT_UPLOAD_API,
				jarUploadEnty);
		logger.log(Level.INFO, "code:" + response.getRespCode() + "    msg:" + response.getRespMsg());
		assertNotNull(response);
	}

	public void testUploadJarWithPom() throws Exception {
		JarWithPomUploadRequest jarWithPomUploadRequest = new JarWithPomUploadRequest("bank_dept3_hosted_release",
				new File("D:\\0.0.1-RELEASE\\com.hundsun.gaps.flow.data.model-0.0.1-RELEASE.pom"),
				new File("D:\\0.0.1-RELEASE\\com.hundsun.gaps.flow.data.model-0.0.1-RELEASE.jar"));
		Response response = MavenTool.getInstance().doMavenPost(mavenServer, MavenServerApiConstant.ARTIFACT_UPLOAD_API,
				jarWithPomUploadRequest);
		logger.log(Level.INFO, "code:" + response.getRespCode() + "    msg:" + response.getRespMsg());
		assertNotNull(response);
	}

	@Override
	protected void tearDown() throws Exception {

	}
}
