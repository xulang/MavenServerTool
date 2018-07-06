# MavenServerTool 为nexus开发的java sdk
#使用方法
public class Test extends TestCase {

	private MavenServer mavenServer;

	private Logger logger = Logger.getLogger(Test.class.getName());

	@Override
	protected void setUp() throws Exception {
		mavenServer = new MavenServer("nexus用户名", "nexus密码", "nexus host", "port");
	}

	public void testUploadJarWithoutPom() throws Exception {
		JarUploadRequest jarUploadEnty = new JarUploadRequest("group id", "artifact id", "版本号", "包类型",
				new File("xxx.jar"), "repository名称");
		Response response = MavenTool.getInstance().doMavenPost(mavenServer, MavenServerApiConstant.ARTIFACT_UPLOAD_API,
				jarUploadEnty);
		logger.log(Level.INFO, "code:" + response.getRespCode() + "    msg:" + response.getRespMsg());
		assertNotNull(response);
	}

	public void testUploadJarWithPom() throws Exception {
		JarWithPomUploadRequest jarWithPomUploadRequest = new JarWithPomUploadRequest("repository名称",
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
