package imageboard.bean;

import java.sql.Date;

import lombok.Data;

@Data
public class ImageboardDTO {
	private int seq;
	private String imageId;
	private String imageName;
	private int imagePrice;
	private int imageQty;
	private String imageContent;
	private String image1;//이미지 파일명
	private String image2;
	private Date logtime;
	
}
