/**
 * 
 */
package com.cking.phss.util;

import java.io.File;

import net.xinhuaxing.eshow.constants.Constants;
import net.xinhuaxing.util.FileFactory;
import net.xinhuaxing.util.Md5Factory;

import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.xml.util.XmlSerializerUtil;

/**
 * 用户工具
 * 
 * @author mm
 * 
 */
public class UserUtil {
    public final static String ADMIN = "admin";
    public final static String CUSTOMER = "customer";
    public final static String[] USER_GROUP = new String[] { ADMIN, CUSTOMER };

    public static void setUser(String user, String password, String userGroup, Login1 bean) {
        // 保存本地xml文件供本地登录使用
        String xmlData = XmlSerializerUtil.getInstance().beanToXml(bean);
        // 删除原有文件
        String passwordMd5 = Md5Factory.md5sum(password.getBytes());
        for (String right : USER_GROUP) {
            String xmlFile = right + "-" + user + "-" + passwordMd5 + ".xml";
            File file = new File(Constants.PHSS_USERS_DIR + xmlFile);

            if (file.exists()) {
                file.delete();
            }
        }
        String xmlFile = userGroup + "-" + user + "-" + passwordMd5 + ".xml";
        File file = new File(Constants.PHSS_USERS_DIR + xmlFile);
        FileFactory.write(xmlData.getBytes(), file);
    }

    /**
     * 使用原有的权限保存
     * 
     * @param user
     * @param password
     * @param bean
     */
    public static void setUser(String user, String password, Login1 bean) {
        // 保存本地xml文件供本地登录使用
        String xmlData = XmlSerializerUtil.getInstance().beanToXml(bean);
        // 保存到原有文件
        String passwordMd5 = Md5Factory.md5sum(password.getBytes());
        for (String right : USER_GROUP) {
            String xmlFile = right + "-" + user + "-" + passwordMd5 + ".xml";
            File file = new File(Constants.PHSS_USERS_DIR + xmlFile);

            if (file.exists()) {
                FileFactory.write(xmlData.getBytes(), file);
                return;
            }
        }
        // 新用户则自动为customer
        String xmlFile = USER_GROUP[1] + "-" + user + "-" + passwordMd5 + ".xml";
        File file = new File(Constants.PHSS_USERS_DIR + xmlFile);
        FileFactory.write(xmlData.getBytes(), file);
    }

    public static boolean validUser(String user, String password) {
        String passwordMd5 = Md5Factory.md5sum(password.getBytes());
        for (String right : USER_GROUP) {
            String xmlFile = right + "-" + user + "-" + passwordMd5 + ".xml";
            File file = new File(Constants.PHSS_USERS_DIR + xmlFile);

            if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    public static Login1 getUserBean(String user, String password) {
        String passwordMd5 = Md5Factory.md5sum(password.getBytes());
        for (String right : USER_GROUP) {
            String xmlFile = right + "-" + user + "-" + passwordMd5 + ".xml";
            File file = new File(Constants.PHSS_USERS_DIR + xmlFile);

            if (file.exists()) {
                byte[] xmlData = FileFactory.read(file);
                if (xmlData != null) {
                    IDto responseBean = (IDto) XmlSerializerUtil.getInstance().beanFromXML(
                            Login1.class, new String(xmlData));
                    return (Login1) responseBean;
                }
            }
        }
        return null;
    }

    public static String getUserGroup(String user) {
        // 遍历文件
        File usersDir = new File(Constants.PHSS_USERS_DIR);
        if (!usersDir.exists()) {
            // 没有任何用户
            return null;
        }
        String[] userFiles = usersDir.list();
        if (userFiles == null || userFiles.length == 0) {
            // 没有任何用户
            return null;
        }

        for (String userFile : userFiles) {
            String[] userItems = userFile.split("-");
            String right = userItems[0]; // 权限
            String userName = userItems[1]; // 用户
            if (userName.equals(user)) {
                return right;
            }
        }
        return null;
    }

    /**
     * @param customer2
     * @param customer3
     */
    public static boolean setUserGroup(String user, String userGroup) {
        // 遍历文件
        File usersDir = new File(Constants.PHSS_USERS_DIR);
        if (!usersDir.exists()) {
            // 没有任何用户
            return false;
        }
        String[] userFiles = usersDir.list();
        if (userFiles == null || userFiles.length == 0) {
            // 没有任何用户
            return false;
        }

        for (String userFile : userFiles) {
            String[] userItems = userFile.split("-");
            String userName = userItems[1]; // 用户
            String passwordMd5 = userItems[2]; // 密码Md5
            if (userName.equals(user)) {
                String newFileName = userGroup + "-" + userName + "-" + passwordMd5 + ".xml";
                // File oldFile = new File(userFile);
                // oldFile.renameTo(new File(Constants.PHSS_USERS_DIR +
                // newFileName));
                FileFactory.renameFile(Constants.PHSS_USERS_DIR + userFile,
                        Constants.PHSS_USERS_DIR + newFileName);
                return true;
            }
        }
        return false;
    }
}
