package com.jock.unmisa.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

import com.jock.unmisa.vo.AuthVO;

public class FileUtils {

	public static String uploadProfileImg(AuthVO vo, String path) throws Exception{
		
		byte[] decodedByte = Base64.getDecoder().decode(vo.getUser_profile_img());
		
		// image/jpeg, image/png
		String fileType = vo.getUser_profile_img_type().equals("image/png") ? ".png" : ".jpg";
		String fileName = vo.getUser_id().replaceAll("-", "");
		
		File file = new File(path+fileName+fileType);
		if (file.createNewFile()) {
		    FileOutputStream fos = new FileOutputStream(file);
		    fos.write(decodedByte);
		    fos.close();
		}else {
			throw new Exception("Error createNewFile");
		}
		
		
		return file.getPath();
	}
	
	public static String uploadBase64ime(String path, String name) throws Exception {
		//var date = DateUtils.now();
		
		// 월별 새로운 폴더에 생성
		// String pathDate = String.valueOf(date.getDayOfYear())+String.valueOf(date.getDayOfMonth());
		
		return null;
	}
}
