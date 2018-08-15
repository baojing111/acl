package study.chenji.spring.ioc;

public class IOCTwoServiceImpl implements IOCTwoService {
    private IOCOneService iocOneService;
    @Override
    public IOCOneService getIOCOneService() {
        return iocOneService;
    }

    public IOCOneService getIocOneService() {
        return iocOneService;
    }

    public void setIocOneService(IOCOneService iocOneService) {
        this.iocOneService = iocOneService;
    }
}