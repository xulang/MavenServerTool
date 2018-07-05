package org.xulang.maventool.request;

import java.io.File;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.xulang.maventool.model.RequestEnty;

/**
 * jar包无pom文件包上传到maven
 * 
 * @author xulang
 *
 */
public class JarUploadRequest implements RequestEnty {
	private String groupId;
	private String artifactId;
	private String version;
	private String packaging;
	private String reposity;
	private File jarFile;

	public JarUploadRequest() {
	}

	public JarUploadRequest(String groupId, String artifactId, String version, String packaging, File jarFile,
			String reposity) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.packaging = packaging;
		this.jarFile = jarFile;
		this.reposity = reposity;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public File getJarFile() {
		return jarFile;
	}

	public void setJarFile(File jarFile) {
		this.jarFile = jarFile;
	}

	public String getReposity() {
		return reposity;
	}

	public void setReposity(String reposity) {
		this.reposity = reposity;
	}

	public HttpEntity toHttpEntity() {
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA.withCharset(CHAR_SET));
		multipartEntityBuilder.addTextBody("r", reposity);
		multipartEntityBuilder.addTextBody("g", groupId);
		multipartEntityBuilder.addTextBody("a", artifactId);
		multipartEntityBuilder.addTextBody("v", version);
		multipartEntityBuilder.addTextBody("p", packaging);
		multipartEntityBuilder.addBinaryBody("file", jarFile);
		return multipartEntityBuilder.build();
	}

	public List<NameValuePair> toNameValuePairs() {
		return null;
	}

}
