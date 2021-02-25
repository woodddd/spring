package sample04;

import java.io.FileWriter;
import java.io.IOException;

//@Setter
public class FileOutputter implements Outputter {
	private String filePath, fileName;
	
	public FileOutputter() {
		System.out.println("FileOutputter() 기본생성자");
	}

	public void setFilePath(String filePath) {
		System.out.println("setFilePath(String filePath)");
		this.filePath = filePath;
	}
	
	public void setFileName(String fileName) {
		System.out.println("setFileNath(String fileNath)");
		this.fileName = fileName;
	}

	@Override
	public void output(String message) {
		try {
			FileWriter fileWriter = new FileWriter(filePath+fileName);
			fileWriter.write(message);
			fileWriter.close();//파일을 닫아줘야 출력을 할 수 있다.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
