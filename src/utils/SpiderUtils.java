package utils;

import java.io.IOException;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderUtils {
	
	/*
	 		"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
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
            "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0",
	 */
	
	static String[] ua = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/83.0.4103.88 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (iPad; CPU OS 13_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/83.0.4103.88 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (iPod; CPU iPhone OS 13_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/83.0.4103.88 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; SM-A205U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; SM-A102U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; SM-G960U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; SM-N960U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; LM-Q720) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; LM-X420) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; LM-Q710(FGN)) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:77.0) Gecko/20100101 Firefox/77.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:77.0) Gecko/20100101 Firefox/77.0",
            "Mozilla/5.0 (X11; Linux i686; rv:77.0) Gecko/20100101 Firefox/77.0",
            "Mozilla/5.0 (Linux x86_64; rv:77.0) Gecko/20100101 Firefox/77.0",
            "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:77.0) Gecko/20100101 Firefox/77.0",
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:77.0) Gecko/20100101 Firefox/77.0",
            "Mozilla/5.0 (X11; Fedora; Linux x86_64; rv:77.0) Gecko/20100101 Firefox/77.0",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 10_15_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) FxiOS/26.0 Mobile/15E148 Safari/605.1.15",
            "Mozilla/5.0 (iPad; CPU OS 10_15_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) FxiOS/26.0 Mobile/15E148 Safari/605.1.15",
            "Mozilla/5.0 (iPod touch; CPU iPhone OS 10_15_5 like Mac OS X) AppleWebKit/604.5.6 (KHTML, like Gecko) FxiOS/26.0 Mobile/15E148 Safari/605.1.15",
            "Mozilla/5.0 (Android 10; Mobile; rv:68.0) Gecko/68.0 Firefox/68.0",
            "Mozilla/5.0 (Android 10; Mobile; LG-M255; rv:68.0) Gecko/68.0 Firefox/68.0",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:68.0) Gecko/20100101 Firefox/68.0",
            "Mozilla/5.0 (X11; Linux i686; rv:68.0) Gecko/20100101 Firefox/68.0",
            "Mozilla/5.0 (Linux x86_64; rv:68.0) Gecko/20100101 Firefox/68.0",
            "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:68.0) Gecko/20100101 Firefox/68.0",
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:68.0) Gecko/20100101 Firefox/68.0",
            "Mozilla/5.0 (X11; Fedora; Linux x86_64; rv:68.0) Gecko/20100101 Firefox/68.0",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36 Edg/83.0.478.37",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36 Edg/83.0.478.37",
            "Mozilla/5.0 (Linux; Android 10; HD1913) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36 EdgA/45.3.4.4958",
            "Mozilla/5.0 (Linux; Android 10; SM-G973F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36 EdgA/45.3.4.4958",
            "Mozilla/5.0 (Linux; Android 10; Pixel 3 XL) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36 EdgA/45.3.4.4958",
            "Mozilla/5.0 (Linux; Android 10; ONEPLUS A6003) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.96 Mobile Safari/537.36 EdgA/45.3.4.4958",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0 EdgiOS/45.3.19 Mobile/15E148 Safari/605.1.15",
            "Mozilla/5.0 (Windows Mobile 10; Android 10.0; Microsoft; Lumia 950XL) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Mobile Safari/537.36 Edge/40.15254.603",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; Xbox; Xbox One) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36 Edge/44.18363.8131",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/83.0.4103.88 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (iPad; CPU OS 13_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/83.0.4103.88 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (iPod; CPU iPhone OS 13_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/83.0.4103.88 Mobile/15E148 Safari/604.1",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; WOW64; Trident/4.0;)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)",
            "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.0)",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2)",
            "Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (Windows NT 6.2; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (Windows NT 10.0; Trident/7.0; rv:11.0) like Gecko"
            };
	
	public static PageBean getText(String url) throws IOException {
		//Document doc = Jsoup.connect(url).get();
		Random r = new Random();
		int maxUserAgentSize = ua.length;
		int i = r.nextInt(maxUserAgentSize);
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
		int maxUserAgentSize = ua.length;
		int i = r.nextInt(maxUserAgentSize);
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
		int maxUserAgentSize = ua.length;
		int i = r.nextInt(maxUserAgentSize);
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
