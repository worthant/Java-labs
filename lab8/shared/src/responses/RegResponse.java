package responses;

public class RegResponse extends BaseResponse{
    boolean reg;
    public RegResponse(boolean reg) {
        this.reg = reg;
    }

    public boolean isReg() {
        return reg;
    }
}
