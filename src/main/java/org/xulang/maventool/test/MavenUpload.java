package org.xulang.maventool.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.xulang.maventool.MavenTool;
import org.xulang.maventool.constant.MavenServerApiConstant;
import org.xulang.maventool.model.MavenServer;
import org.xulang.maventool.model.Response;
import org.xulang.maventool.request.JarUploadRequest;
import org.xulang.maventool.request.JarWithPomUploadRequest;

public class MavenUpload {
	

	public static void main(String[] args) {
		MavenServer mavenServer = new MavenServer("bank_dept3_hosted_release_user", "bank.dept3@123",
				"repos.hundsun.com", "6060");
		JarUploadRequest jarUploadEnty = new JarUploadRequest("asdg", "daf", "0.0.1-version", "jar",
				new File("D://junrar-0.7.jar"), "bank_dept3_hosted_release");
	//	Response response = MavenTool.getInstance().doMavenPost(mavenServer, MavenServerApiConstant.ARTIFACT_UPLOAD_API,jarUploadEnty);
//		System.out.println(response);

	//	Response response2 = MavenTool.getInstance().doMavenGet(mavenServer,MavenServerApiConstant.LIST_USER_REPOSITORIES, null);
	//	System.out.println(response2);

		JarWithPomUploadRequest jarWithPomUploadRequest = new JarWithPomUploadRequest("bank_dept3_hosted_release",
				new File(
						"D:\\0.0.1-RELEASE\\com.hundsun.gaps.flow.data.model-0.0.1-RELEASE.pom"),
				new File(
						"D:\\0.0.1-RELEASE\\com.hundsun.gaps.flow.data.model-0.0.1-RELEASE.jar"));
		Response response3 = MavenTool.getInstance().doMavenPost(mavenServer,
				MavenServerApiConstant.ARTIFACT_UPLOAD_API, jarWithPomUploadRequest);
		System.out.println(response3);
	}

}
