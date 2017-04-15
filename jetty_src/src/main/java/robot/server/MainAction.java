package robot.server;

import org.apache.struts2.dispatcher.DefaultActionSupport;

/**
 * Created by sxf on 15/04/2017.
 */
public class MainAction extends DefaultActionSupport {
    private String name;

    public  String execute() throws Exception {
        return "myview";
    }
}
