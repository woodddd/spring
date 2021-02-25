package member.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO implements Serializable{
	private String name, id, pwd, gender, email1, email2, tel1, tel2, tel3, zipcode, addr1, addr2;


}
