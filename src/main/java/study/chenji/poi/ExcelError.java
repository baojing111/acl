package study.chenji.poi;

public enum ExcelError {
    INVAILDSYMBO("invaild_symbol","无效字符");
    private String code;
    private String message;

    ExcelError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
