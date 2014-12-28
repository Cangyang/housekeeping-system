package com.ytf.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
 
public class ImageResult implements Result {
 
    public void execute(ActionInvocation ai) throws Exception {
      
       ImageAction action = (ImageAction)ai.getAction();
      
       HttpServletResponse response = ServletActionContext.getResponse();
       response.setHeader("Cash", "no cash");
       response.setContentType(action.getContentType());
       response.setContentLength(action.getContentLength());
       response.getOutputStream().write(action.getImageBytes());
       response.getOutputStream().flush();
    }
}