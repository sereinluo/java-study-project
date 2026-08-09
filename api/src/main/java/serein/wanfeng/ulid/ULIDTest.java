package serein.wanfeng.ulid;

import com.github.f4b6a3.ulid.UlidCreator;
import org.junit.jupiter.api.Test;

/**
 * @date: 2024-05-07 14:35
 * @author: luozh
 * @description:
 * @since:
 */
public class ULIDTest {

    @Test
    public void test() {
        System.out.println(UlidCreator.getUlid());
    }
}
