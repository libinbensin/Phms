package com.cking.phss.sqlite;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.util.Log;

import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.sqlite.SqliteField.AccountField;
import com.cking.phss.xml.util.XmlSerializerUtil;

public class AccountBll {
    static private final String TAG = "AccountBll";
	
    public static SaveToDbResult saveBean(String userName, String passWord, int userGroup,
            boolean remeber, boolean status, Date lastLogin, IBean bean) {

        Account account = new Account();
        account.setAccountUUID(UUID.randomUUID().toString());
        account.setUsername(userName);
        account.setPassword(passWord);
        account.setUserGroup(userGroup);
        account.setRemember(remeber ? 1 : 0);
        account.setStatus(status ? 1 : 0);
        account.setLastLoginDateTime(lastLogin.getTime());
        account.setBean(XmlSerializerUtil.getInstance().beanToXml(
				bean));
        Log.i(TAG, account.getBean());
        boolean isAdd = false;
        if (!AccountDal.exist(userName)) {
            AccountDal.add(account);
            isAdd = true;
		}else{
            AccountDal.update(account);
            isAdd = false;
		}
        account = AccountDal.query(userName);
        if (account != null) {
            SaveToDbResult result = new SaveToDbResult(isAdd, account.getAccountUUID(),
                    account.getAccountUUID(),
                    bean);
            return result;
        } else {
            return null;
        }
	}
	
    public static Account query(String userName) {
        Account account = AccountDal.query(userName);
        if (account != null) {
            Log.i(TAG, account.getBean());
		}else{
            Log.i(TAG, "account=null");
		}
        return account;
	}

    public static List<Account> list() {
        return AccountDal.list();
    }

    public static Account queryLastRemember(boolean remember) {
        Account account = AccountDal.query(AccountField.REMEMBER, remember ? "1" : "0");
        if (account != null) {
            Log.i(TAG, account.getBean());
        } else {
            Log.i(TAG, "account=null");
        }
        return account;
    }

    public static Account queryLastRemember() {
        Account account = AccountDal.queryLast(AccountField.REMEMBER, "1");
        if (account != null) {
            Log.i(TAG, account.getBean());
        } else {
            Log.i(TAG, "account=null");
        }
        return account;
    }

    public static void updateStatus(String userName, boolean status) {
        String[] setFieldList = new String[] { AccountField.STATUS };
        String[] setValueList = new String[] { status ? "1" : "0" };
        String[] whereFieldList = new String[] { AccountField.USERNAME };
        String[] whereValueList = new String[] { userName };
        AccountDal.update(setFieldList, setValueList, whereFieldList, whereValueList);
    }

    public static void updateUserGroup(String userName, int userGroup) {
        String[] setFieldList = new String[] { AccountField.USERGROUP };
        String[] setValueList = new String[] { userGroup + "" };
        String[] whereFieldList = new String[] { AccountField.USERNAME };
        String[] whereValueList = new String[] { userName };
        AccountDal.update(setFieldList, setValueList, whereFieldList, whereValueList);
    }
	
    public static boolean exist(String userName) {
        return AccountDal.exist(userName);
	}

    /**
     * @param name
     * @param residentUUID
     */
    public static void delete(String userName) {
        AccountDal.delete(userName);
    }
}
