package utils;

import java.io.IOException;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderUtils {
	
	static String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
            "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};
	
	public static PageBean getText(String url) throws IOException {
		//Document doc = Jsoup.connect(url).get();
		Random r = new Random();
		int i = r.nextInt(14);
		Document doc = Jsoup.connect(url)
                .timeout(5000)
                //.proxy("xxx.com", 8080)
                .ignoreContentType(true)
                .userAgent(ua[i])
                .post();
		
		Element contentOrigin = doc.getElementById("content");
		Elements titleOrigins = doc.getElementsByTag("h1");
		String contentFinally = "";
		PageBean myPage = new PageBean();
		myPage.setContent("");
		myPage.setTitle("");
		if (contentOrigin != null) {
			String contentText = contentOrigin.html();
			contentFinally = contentText.replaceAll("<br>", "");
			contentFinally = contentFinally.replaceAll("&nbsp;&nbsp;", "　");
			myPage.setTitle(((Element) titleOrigins.get(0)).html());
			myPage.setContent(contentFinally);
		}

		return myPage;
	}

	public static String getIntro(String url) throws IOException {
		//Document doc = Jsoup.connect(url).get();
		Random r = new Random();
		int i = r.nextInt(14);
		Document doc = Jsoup.connect(url)
                .timeout(5000)
                //.proxy("xxx.com", 8080)
                .ignoreContentType(true)
                .userAgent(ua[i])
                .post();
		
		Element contentOrigin = doc.getElementById("intro");
		Element infoOrigin = doc.getElementById("info");
		String contentFinally = "";
		if (contentOrigin != null) {
			contentFinally = contentFinally + "==============\r\n";
			Elements contents = infoOrigin.select("h1");
			contentFinally = contentFinally + "《" + ((Element) contents.get(0)).text() + "》" + "\r\n";
			contents = infoOrigin.select("p");
			contentFinally = contentFinally + ((Element) contents.get(0)).text().replaceAll("&nbsp;","") + "\r\n\r\n";
			String contentText = contentOrigin.text();
			contentFinally = contentFinally + "　　內容簡介：\r\n";
			contentFinally = contentFinally + "　　" + contentText.replaceAll("<br>|&nbsp;", "");
			contentFinally = contentFinally + "\r\n==============\r\n";
		}

		return contentFinally;
	}

	public static Elements getURLList(String url) throws IOException {
		//Document doc = Jsoup.connect(url).get();
		
		Random r = new Random();
		int i = r.nextInt(14);
		Document doc = Jsoup.connect(url)
                .timeout(5000)
                //.proxy("xxx.com", 8080)
                .ignoreContentType(true)
                .userAgent(ua[i])
                .post();
		
		Elements urlOrigins = doc.getElementsByTag("dd");
		return urlOrigins;
	}

}
