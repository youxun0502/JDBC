package tw.ispan.jdbc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetDataUtil {

	public List<String> getURLContent() {
		String url = "https://data.tainan.gov.tw/dataset/c17151c7-9efc-42bb-8cb8-5ffe6ec9f134/resource/1d6b83e6-1223-4570-b601-484c5357add4/download/aqi.csv";
		try {
			URL urlObj = new URL(url);
			InputStream inputStream = urlObj.openStream();
			
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader bfr = new BufferedReader(isr);
			
			//String content = "";
			
			bfr.readLine();
			List<String> AQI = new ArrayList<>();
			
			
			while (bfr.ready()) {
				AQI.add(bfr.readLine());
			}
			return AQI;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
