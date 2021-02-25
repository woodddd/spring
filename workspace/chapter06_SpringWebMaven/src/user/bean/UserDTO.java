package user.bean;

import lombok.Data;

@Data
public class UserDTO {
	private String name;
	private String id;
	private String pwd;
}

//빈생성 - XML
//    - /WEB-INF/applicationContext.xml ---> /WEB-INF/spring/root-context.xml (웹과 관계X - 대표적인 예로> DTO와 DAO 클래스가 있다.)
//             디폴트
