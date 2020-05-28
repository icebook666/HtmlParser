package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import utils.Novel;
import utils.Parameter;

public class ThreadMain {

	public static void main(String[] args) {
		if (args.length > 0) {
			String config_path = args[0];
			try {

				Properties prop = new Properties();
				InputStream is = new FileInputStream(config_path);
				prop.load(new InputStreamReader(is, "UTF-8"));
				
				// 產生Novel物件並啟動執行緒
				Parameter[] arr_parameter = new Parameter[10];
				
				for (int i=0; i < 10; i++)
				{
					arr_parameter[i] = new Parameter();
					arr_parameter[i].setFile_path(getNvl(prop.getProperty("save_file_path"+String.valueOf(i+1))));
					arr_parameter[i].setFile_name(getNvl(prop.getProperty("save_file_name"+String.valueOf(i+1))));
					arr_parameter[i].setUrl(getNvl(prop.getProperty("novel_url"+String.valueOf(i+1))));
				}

				System.out.println("============== Main Start ==============");
				
				Novel[] arr_novel = new Novel[10];
				
				for (int i=0; i < 10; i++)
				{
					arr_novel[i] = new Novel(arr_parameter[i]);
					arr_novel[i].setName("Novel"+String.valueOf(i+1));
					arr_novel[i].start();
				}

				try {
					for (int i=0; i < 10; i++)
					{
						arr_novel[i].join();
					}
				} catch (InterruptedException e) {
					System.out.println("執行緒被中斷");
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				System.out.println("============== Main Finish ==============");
			}

		} else {
			System.out.println("請輸入設定檔所在位置!!");
		}
	}
	
	public static String getNvl(String input)
	{
		if (input != null)
			return input;
		else
			return "";
	}
	
	/*
	public static void main(String[] args) {
		if (args.length > 0) {
			OutputStreamWriter writer = null;
			String config_path = args[0];

			try {
				Properties prop = new Properties();
				InputStream is = new FileInputStream(config_path);
				prop.load(new InputStreamReader(is, "UTF-8"));
				String File_path = prop.getProperty("save_file_path");
				String File_name = prop.getProperty("save_file_name");
				String url = prop.getProperty("novel_url");
				String start_txt = "Writing for " + File_path + File_name;
				System.out.println(start_txt);
				File dir = new File(File_path);
				if (!dir.exists()) {
					dir.mkdir();
				}

				File temp_file = new File(File_path, File_name);
				if (temp_file.exists()) {
					temp_file.delete();
				}

				writer = new OutputStreamWriter(new FileOutputStream(new File(
						File_path, File_name)), StandardCharsets.UTF_8);
				
				PageBean myPage = new PageBean();
				writer.write(SpiderUtils.getIntro(url));
				writer.write("\r\n\r\n");
				Elements urlOrigins = SpiderUtils.getURLList(url);
				int i = 1;
				String href = "";

				for (Iterator var16 = urlOrigins.iterator(); var16.hasNext(); ++i) {
					Element element = (Element) var16.next();
					href = element.select("a").attr("href");
					System.out.println("Read " + i + " page -> " + url + href);
					myPage = SpiderUtils.getText(url + href);
					writer.write(myPage.getTitle() + "\r\n\r\n");
					writer.write(myPage.getContent());
					writer.write("\r\n\r\n");
					writer.flush();
				}
				writer.close();
			} catch (IOException var20) {
				var20.printStackTrace();
			} finally {
				System.out.println("==============Finish==============");
			}
		} else {
			System.out.println("請輸入設定檔所在位置!!");
		}

	}
	*/
	
	/*
	public static void main(String[] args) {
		String url = "https://news.google.com.tw/news";
		try {
			Document doc = Jsoup.connect(url).get();
			System.out.println(doc.title());
			//Elements h1s = doc.select("span.titletext");
			Elements h1s = doc.getElementsByClass("xBbh9");

			Element thisOne = null;
			for (Iterator it = h1s.iterator(); it.hasNext();) {
				thisOne = (Element) it.next();
				System.out.println(thisOne.html());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	*/

}
