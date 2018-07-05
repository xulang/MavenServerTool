package org.xulang.maventool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.xulang.maventool.model.MavenServer;
import org.xulang.maventool.model.RequestEnty;
import org.xulang.maventool.model.Response;

public class MavenTool {
	private Response errorResponse = new Response(99999, "异常！");
	private static HttpClientBuilder builder = HttpClientBuilder.create();
	private static MavenTool mavenTool;

	private MavenTool() {
	}

	public static MavenTool getInstance() {
		if (mavenTool == null) {
			synchronized (MavenTool.class) {
				if (mavenTool == null)
					mavenTool = new MavenTool();
			}
		}

		return mavenTool;
	}

	public Response doMavenPost(MavenServer server, String api, RequestEnty requestEnty) {
		CloseableHttpClient httpClient = builder.build();
		String reqUrl = "http://" + server.getHost() + ":" + server.getPort() + api;
		String encoding = new String(
				Base64.getEncoder().encode((server.getUserName() + ":" + server.getPassword()).getBytes()));
		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(reqUrl);
			httpPost.addHeader("Authorization", "Basic " + encoding);
			httpPost.setEntity(requestEnty.toHttpEntity());
			httpResponse = httpClient.execute(httpPost);
			int status = httpResponse.getStatusLine().getStatusCode();
			HttpEntity entity = httpResponse.getEntity();
			if (null == entity) {
				return new Response(status, "");
			}
			BufferedReader isr = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = isr.readLine()) != null) {
				sb.append(line);
			}
			return new Response(status, sb.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return errorResponse;
	}

	public Response doMavenGet(MavenServer server, String api, RequestEnty requestEnty) {
		CloseableHttpClient httpClient = builder.build();
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme("http");
		uriBuilder.setHost(server.getHost());
		uriBuilder.setPort(Integer.parseInt(server.getPort()));
		uriBuilder.setPath(api);
		if (requestEnty != null)
			uriBuilder.setParameters(requestEnty.toNameValuePairs());
		URI reqUri = null;
		try {
			reqUri = uriBuilder.build();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			return null;
		}
		String encoding = new String(
				Base64.getEncoder().encode((server.getUserName() + ":" + server.getPassword()).getBytes()));
		CloseableHttpResponse httpResponse = null;
		try {
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(reqUri);
			httpGet.addHeader("Authorization", "Basic " + encoding);
			httpResponse = httpClient.execute(httpGet);
			int status = httpResponse.getStatusLine().getStatusCode();
			HttpEntity entity = httpResponse.getEntity();
			if (null == entity) {
				return new Response(status, "");
			}
			BufferedReader isr = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = isr.readLine()) != null) {
				sb.append(line);
			}
			return new Response(status, sb.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return errorResponse;
	}
}
