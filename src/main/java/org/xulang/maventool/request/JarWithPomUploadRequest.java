package org.xulang.maventool.request;

import java.io.File;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.xulang.maventool.model.RequestEnty;

/**
 * jar包带pom文件包上传到maven
 * 
 * @author xulang
 *
 */
public class JarWithPomUploadRequest implements RequestEnty {

	private String reposity;
	private Boolean hasPom = new Boolean(true);
	private File pomFile;
	private File jarFile;

	public JarWithPomUploadRequest() {
	}

	public JarWithPomUploadRequest(String reposity, File pomFile, File jarFile) {
		super();
		this.reposity = reposity;
		this.pomFile = pomFile;
		this.jarFile = jarFile;
	}

	public String getReposity() {
		return reposity;
	}

	public void setReposity(String reposity) {
		this.reposity = reposity;
	}

	public Boolean getHasPom() {
		return hasPom;
	}

	public void setHasPom(Boolean hasPom) {
		this.hasPom = hasPom;
	}

	public File getPomFile() {
		return pomFile;
	}

	public void setPomFile(File pomFile) {
		this.pomFile = pomFile;
	}

	public File getJarFile() {
		return jarFile;
	}

	public void setJarFile(File jarFile) {
		this.jarFile = jarFile;
	}

	/**
	 * POST 请求时实现
	 */
	public HttpEntity toHttpEntity() {
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA.withCharset(CHAR_SET));
		multipartEntityBuilder.addTextBody("r", reposity);
		multipartEntityBuilder.addTextBody("hasPom", hasPom.toString());
		multipartEntityBuilder.addBinaryBody("file", pomFile);
		multipartEntityBuilder.addBinaryBody("file", jarFile);
		return multipartEntityBuilder.build();
	}

	/**
	 * GET 请求时实现
	 */
	public List<NameValuePair> toNameValuePairs() {
		return null;
	}

}
