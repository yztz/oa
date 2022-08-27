package top.yztz.oa.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class FileUtils {

	public static String PIC_PATH = "./pics";
	public static File pic = new File(PIC_PATH);

	static {
		if(!pic.exists()){
			boolean res = pic.mkdirs();
			if(!res) {
				System.out.println("资源目录创建失败");
				System.exit(-1);
			}
		}
	}

	public static void deletePicture(String name) {
		if(StringUtils.isEmpty(name))
			return;

		File file = new File(PIC_PATH + File.separator + name);
		if(file.exists()) {
			boolean res = file.delete();
			if(!res) {
				System.out.println("file del fail");
				System.exit(-1);
			}
		}
	}

}
