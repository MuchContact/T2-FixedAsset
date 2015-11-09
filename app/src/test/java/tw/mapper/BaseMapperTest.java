package tw.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;

public class BaseMapperTest {
    SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
        sqlSession.close();

    }
}