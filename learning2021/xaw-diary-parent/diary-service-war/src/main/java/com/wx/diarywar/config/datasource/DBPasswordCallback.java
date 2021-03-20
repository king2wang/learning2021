package com.wx.diarywar.config.datasource;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.wx.common.utils.PropertiesUtil;
import com.wx.common.utils.RSAEncrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Properties;
//@Component
public class DBPasswordCallback extends DruidPasswordCallback {
//    @Value("${RSA.PRIVATEKEY}")
//    private String privateKey;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String passwordEn = properties.getProperty("password");
        String privateKey = PropertiesUtil.getValue("RSA.PRIVATEKEY");
        if (!StringUtils.isEmpty(passwordEn)){
            String password = null;
            try {
                System.out.println("-------------------------------"+privateKey);
                password = RSAEncrypt.decrypt(passwordEn,privateKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            setPassword(password.toCharArray());
        }

    }
}
