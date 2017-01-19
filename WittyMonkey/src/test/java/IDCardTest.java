import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wittymonkey.util.IDCardValidate;
import com.wittymonkey.vo.IDCardInfo;


public class IDCardTest {
	@Test
	public void validate() {
		if (IDCardValidate.validate("110102197810272321"))
			System.out.println("合法身份证");
		else System.out.println("非法身份证");
	}

}
