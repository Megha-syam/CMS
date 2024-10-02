package com.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.model.SendBean;
import com.model.SendManager;


@ManagedBean(name="sd",eager=true)
public class SendData{

    private List<SendBean> list;

//    public List<SendBean> getList() {
//            try {
//                SendManager sm = new SendManager();
//                list = sm.readData();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//  
//        return list;
//}
    public List<SendBean> getList() {
        if (list == null) {
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
                String currentUsername = (String) session.getAttribute("username");
                
                SendManager sm = new SendManager();
                list = sm.readData(currentUsername);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public void setList(List<SendBean> list) {
        this.list = list;
    }
}