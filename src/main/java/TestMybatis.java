import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.dong.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 
public class TestMybatis {
 
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
         
        List<User> cs=session.selectList("listUser");
        for (User c : cs) {
            System.out.println(c.getId()+"、"+c.getUser_name()+"、"+c.getPassword()+"、"+c.getAge());
        }
    }
}