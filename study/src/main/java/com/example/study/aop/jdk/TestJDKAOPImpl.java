package study.chenji.spring.aop.jdk;

/**
 * 测试接口实现类
 */
public class TestJDKAOPImpl implements TestJDKAOP {
    @Override
    public String getName(String name) {
        System.out.println("getName======");
        return name;
    }
}
