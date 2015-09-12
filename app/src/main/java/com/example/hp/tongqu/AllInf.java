package com.example.hp.tongqu;

public class AllInf {
    private
        String version;
        Result result;
        Opt opt;
        int error;
        String msg;

    public String getVersion() {
        return version;
    }
    public void setVersionn(String version) {
        this.version = version;
    }
    public Result getResult(){
        return result;
    }
    public void setResult(Result result){
        this.result=result;
    }
    public Opt getOpt() {
        return opt;
    }
    public void setOpt(Opt opt) {
        this.opt = opt;
    }
    public int getError() {
        return error;
    }
    public void setError(int error) {
        this.error = error;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}