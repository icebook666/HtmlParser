package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Novel extends Thread {
	
	Parameter myConfig;
    public Novel(Parameter myConfig){
        this.myConfig = myConfig;
    }
    
	// 覆寫Thread方法run()
	public void run() {
		try {
			
			if (!myConfig.getUrl().equals(""))
			{
				OutputStreamWriter writer = null;
				
				System.out.println("============== "+ getName() +" Start ==============");
				
				File dir = new File(myConfig.getFile_path());
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				File temp_file = new File(myConfig.getFile_path(), myConfig.getFile_name());
				if (temp_file.exists()) {
					temp_file.delete();
				}

				writer = new OutputStreamWriter(new FileOutputStream(new File(
						myConfig.getFile_path(), myConfig.getFile_name())), StandardCharsets.UTF_8);
				
				PageBean myPage = new PageBean();
				writer.write(SpiderUtils.getIntro(myConfig.getUrl()));
				writer.write("\r\n\r\n");
				Elements urlOrigins = SpiderUtils.getURLList(myConfig.getUrl());
				int i = 1;
				String href = "";
				
				int maxRetry = 30; //重試次數
				int sleepTime = 1000; //等待ms

				for (Iterator iter = urlOrigins.iterator(); iter.hasNext(); ++i) {
					Element element = (Element) iter.next();
					href = element.select("a").attr("href");
					System.out.println(getName() + ": Read " + i + " page -> " + myConfig.getUrl() + href);
					
					for (int j = 0; j < maxRetry; j++) {
						try {
			                if (j != 0) {
			                	System.out.println(getName() + ": Read " + i + " page -> " + myConfig.getUrl() + href + "(Retry "+j+")");
			                	Thread.sleep(sleepTime);
			                }
			                myPage = SpiderUtils.getText(myConfig.getUrl() + href);
			                
			                writer.write(myPage.getTitle() + "\r\n\r\n");
							writer.write(myPage.getContent());
							writer.write("\r\n\r\n");
							writer.flush();
			                // normal finish situation,loop is broken.
			                break;

			            } catch (Exception e) {
			                // throw new Exception(ex); dead code is occurred
			            	if (j >= (maxRetry - 1))
			            	{
			            		//Output to info log.
			            		e.printStackTrace();
			            	}
			            }
					} //Retry Loop
				}
				writer.close();
				System.out.println("============== "+ getName() +" Finish ==============");
			}
			else
			{
				System.out.println("============== "+ getName() +" <No URL> Finish ==============");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}