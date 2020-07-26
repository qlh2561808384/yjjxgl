package com.datanew.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpRequester {
	private String defaultContentEncoding;

	public HttpRequester() {
		this.defaultContentEncoding = "UTF-8";
	}

	public String sendGet(String urlString) throws IOException {
		return this.send(urlString, "GET", null, null);
	}

	public String sendGet(String urlString, Map params) throws IOException {
		return this.send(urlString, "GET", params, null);
	}

	public String sendGet(String urlString, Map params, Map propertys)
			throws IOException {
		return this.send(urlString, "GET", params, propertys);
	}

	public String sendPost(String urlString) throws IOException {
		return this.send(urlString, "POST", null, null);
	}

	public String sendPost(String urlString, Map params) throws IOException {
		return this.send(urlString, "POST", params, null);
	}

	public String sendPost(String urlString, Map params, Map propertys)
			throws IOException {
		return this.send(urlString, "POST", params, propertys);
	}

	private String send(String urlString, String method, Map parameters,
			Map propertys) throws IOException {
		HttpURLConnection urlConnection = null;

		if (method.equalsIgnoreCase("GET") && parameters != null) {
			StringBuffer param = new StringBuffer();
			Set set = parameters.keySet();
			int i = 0;
			/*
			 * for (String key : parameters.keySet()) { if (i == 0)
			 * param.append("?"); else param.append("&");
			 * param.append(key).append("=").append(parameters.get(key)); i++; }
			 */
			Iterator ite = parameters.keySet().iterator();
			while (ite.hasNext()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				String str = (String) ite.next();
				param.append(str).append("=").append(parameters.get(str));
				i++;
			}
			urlString += param;
		}
		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod(method);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setRequestProperty("Accept-Charset", defaultContentEncoding);
		urlConnection.setRequestProperty("contentType", defaultContentEncoding);

		/*
		 * if (propertys != null) for (String key : propertys.keySet()) {
		 * urlConnection.addRequestProperty(key, propertys.get(key)); }
		 */

		if (method.equalsIgnoreCase("POST") && parameters != null) {
			StringBuffer param = new StringBuffer();
			/*
			 * for (String key : parameters.keySet()) { param.append("&");
			 * param.append(key).append("=").append(parameters.get(key)); }
			 */

			Iterator ite = parameters.keySet().iterator();
			while (ite.hasNext()) {
				param.append("&");
				String str = (String) ite.next();
				param.append(str).append("=").append(parameters.get(str));

			}
			urlConnection.getOutputStream().write(param.toString().getBytes());
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();
		}

		return this.makeContent(urlString, urlConnection);
	}

	private String makeContent(String urlString, HttpURLConnection urlConnection)
			throws IOException {
		// HttpRespons httpResponser = new HttpRespons();
		try {
			InputStream in = urlConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,defaultContentEncoding));
			StringBuffer temp = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				temp.append(line).append("\r\n");
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			
			return temp.toString();
		} catch (IOException e) {
			throw e;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}

	public String getDefaultContentEncoding() {
		return this.defaultContentEncoding;
	}

	public void setDefaultContentEncoding(String defaultContentEncoding) {
		this.defaultContentEncoding = defaultContentEncoding;
	}
}