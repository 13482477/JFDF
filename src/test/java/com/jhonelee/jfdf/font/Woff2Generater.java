package com.jhonelee.jfdf.font;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class Woff2Generater {
	
	private static Log LOGGER = LogFactory.getLog(Woff2Generater.class);

	@Test
	public void wgetWoff2FromFontCss() {
		String content = readerString();

		List<String> urls = this.parseUrl(content);

		this.downloadFiles(urls);
	}

	private String readerString() {
		StringBuffer stringBuffer = new StringBuffer();

		File file = new File(this.getClass().getResource("").getPath() + "/font.css");

		FileInputStream fileInputStream = null;
		BufferedReader bufferReader = null;
		InputStreamReader inputStreamReader = null;
		try {
			fileInputStream = new FileInputStream(file);

			inputStreamReader = new InputStreamReader(fileInputStream);
			bufferReader = new BufferedReader(inputStreamReader);

			String line;
			while ((line = bufferReader.readLine()) != null) {
				stringBuffer.append(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				bufferReader.close();
				inputStreamReader.close();
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return stringBuffer.toString();
	}

	private List<String> parseUrl(String content) {
		List<String> result = new ArrayList<String>();

		String patternStr = "(https://\\S*.woff2)";

		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(content);

		while (matcher.find()) {
			int count = matcher.groupCount();
			for (int i = 0; i < count; i++) {
				String url = matcher.group(i);
				LOGGER.debug(url);
				result.add(url);
			}
		}
		return result;
	}

	private void downloadFiles(List<String> urls) {
		for (String url : urls) {
			this.downloadFile(url);
		}
	}

	private TrustManager[] createCertificates() {
		return new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
	}

	private String resolveFileNameFromUrl(String url) {
		return StringUtils.substringAfterLast(url, "/");
	}

	private InputStream getRemoteFileInputStreamByHttps(String urlStr) {
		TrustManager[] trustAllCerts = this.createCertificates();
		SSLContext sc;
		try {
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			URL url = new URL(urlStr);
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			return is;
		} catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private File initFileByUrl(String urlStr) {
		String fileName = this.resolveFileNameFromUrl(urlStr);

		File file = new File(this.getClass().getResource("/").getPath() + "../../src/main/resources/public/font/" + fileName);
		try {

			if (file.exists()) {
				file.delete();
				file.createNewFile();
			} else {
				file.createNewFile();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return file;
	}

	private void downloadFile(String urlStr) {
		File file = this.initFileByUrl(urlStr);

		InputStream inputStream = this.getRemoteFileInputStreamByHttps(urlStr);
		FileOutputStream fileOutputStream = null;

		try {
			byte[] byteTemp = new byte[1024];
			fileOutputStream = new FileOutputStream(file);
			int i = -1;
			while ((i = inputStream.read(byteTemp)) > 0) {
				fileOutputStream.write(byteTemp, 0, i);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				fileOutputStream.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
