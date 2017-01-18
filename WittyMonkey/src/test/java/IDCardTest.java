import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wittymonkey.util.IDCardValidate;
import com.wittymonkey.vo.IDCardInfo;


public class IDCardTest {
	@Test
	public void validate() {
		if (IDCardValidate.validate("511181199409084218"))
			System.out.println("合法身份证");
		else System.out.println("非法身份证");
	}
	@Test
	public void getInfo(){
		IDCardInfo idCard = IDCardValidate.getIDCardInfo("511181199409084218");
		System.out.println(idCard);
	}
}
