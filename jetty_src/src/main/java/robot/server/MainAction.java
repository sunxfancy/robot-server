package robot.server;

import com.iflytek.cloud.speech.*;
import com.opensymphony.xwork2.ActionSupport;

import java.util.concurrent.CountDownLatch;

/**
 * Created by sxf on 15/04/2017.
 */
public class MainAction extends ActionSupport {
    private static TextUnderstander tu = null;

    static {
        System.load("/var/lib/jetty/webapps/libmsc64.so");
        SpeechUtility.createUtility( SpeechConstant.APPID +"=58f23cc6");
    }

    private int err;
    private String msg = null;
    private String q;
    private String data;
    public  String execute() throws Exception {
        err = 0;
        msg = "";
        if (q == null) return SUCCESS;
        CountDownLatch cdl = new CountDownLatch(1);

        tu = TextUnderstander.createTextUnderstander();
        tu.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        tu.understandText(q, new TextUnderstanderListener() {
            @Override
            public void onResult(UnderstanderResult understanderResult) {
                data = q + "：" + understanderResult.getResultString();
                cdl.countDown();
            }

            @Override
            public void onError(SpeechError speechError) {
                err = 1;

                msg = speechError.getErrorDescription(true);
                cdl.countDown();
            }
        });

        cdl.await();
        tu.destroy();
        tu = null;

        return SUCCESS;
    }

    public String getQ() {
        return q;
    }

    public int getErr() {

        return err;
    }

    public String getMsg() {
        return msg;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getData() {
        return data;
    }
}

