package study.chenji.spring.ioc;

public class IOCOneServiceImpl implements IOCOneService {
    private IOCTwoService iocTwoService;
    @Override
    public IOCTwoService getIOCTwoService() {
        return iocTwoService;
    }

    public IOCTwoService getIocTwoService() {
        return iocTwoService;
    }

    public void setIocTwoService(IOCTwoService iocTwoService) {
        this.iocTwoService = iocTwoService;
    }
}
