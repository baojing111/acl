package study.chenji.poi;



/**
 * 测试类
 */
public class TestAccount {

    @ExcelColumn("主键")
    private Long id;

    @ExcelColumn("用户名")
    private String name;

    @ExcelColumn("密码")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
