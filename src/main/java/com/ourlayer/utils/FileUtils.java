package com.ourlayer.utils;

import lombok.extern.java.Log;

import java.util.Calendar;
import java.util.logging.Level;

@Log
public class FileUtils {

	private static Calendar cal = Calendar.getInstance();

	/**
	 * 디렉토리 관리를 위해 일자별 폴더를 생성
	 * -> 디렉토리 이름 만드는 메서드
	 * @return String: YEAR+MONTH+DAY
	 */
	public static String makeDirectoryName() {
		return String.format("%04d%02d%02d"
				, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH) );
	}

	/**
	 * 파일이름의 중복 방지를 위해 ms 까지의 시간을 파일 이름으로 생성
	 * @return String: DAY+HOUR+MINUTE+SECOND+MILLISECOND
	 */
	public static String makeFilename() {
		return String.format("%02d%02d%02d%02d%04d"
				, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE)
				, cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND));
	}
	
	/**
	 * 파일이름에서 GoodsNo를 추출하는 메서드
	 * @param filename ex) 0000001-0.jpg
	 * @return String : goodsNo
	 */
	public static String extractGoodsNoFromFilename(String filename) {
		try {
			return filename.split("-")[0];
		} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
			log.warning("Illegal Filename... There may be no goodsNo in filename ");
			return null;
		}
	}
	
	/**
	 * 파일이름에서 ord (노출순서) 를 추출하는 메서드
	 * @param filename ex) 0000001-0.jpg
	 * @return String : ord
	 */
	public static Integer extractOrdFromFilename(String filename) {
		try {
			return Integer.parseInt(filename.split("-")[1].split("\\.")[0]);
		} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
			log.warning("Illegal Filename... There may be no ord in filename ");
			return null;
		}
	}

}
