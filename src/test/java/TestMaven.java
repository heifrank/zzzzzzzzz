import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.yidian.cpp.component.writer.morpheus.MorpheusSmartWriter;
import org.junit.Test;

/**
 * Created by heifrank on 16/7/22.
 */
public class TestMaven {
    @Test
    public void test() throws Exception {
        MorpheusSmartWriter morpheusSmartWriter = new MorpheusSmartWriter();
        Config config = ConfigFactory.empty();
        morpheusSmartWriter.init(config);
        System.out.println("YEP!");
    }
}
