package com.cking.phss.sqlite;

import java.util.List;
import java.util.UUID;

import com.cking.phss.sqlite.SqliteField.ResidentField;

public class ResidentBll {

	public static void saveBean(final Resident bean) {
		// new Thread(){
		// @Override
		// public void run() {
		if (exist(bean.getPaperNum())) {
			update(bean);
		} else {
			bean.setResidentUUID(UUID.randomUUID().toString());
			add(bean);
		}
		// }
		// }.start();
	}

	public static void add(Resident bean) {
		ResidentDal.add(bean);
	}

	public static void update(Resident bean) {
		ResidentDal.update(bean);
	}

	public static Resident query(String paperNum) {
		return ResidentDal.query(paperNum);
	}

    public static List<Resident> query(String searchString, String keyType) {
        if (keyType.equals(ResidentField.PAPER_NUM)) {
            return ResidentDal.queryByPaperNum(searchString);
        } else if (keyType.equals(ResidentField.RESIDENT_NAME)) {
            return ResidentDal.queryByResidentName(searchString);
        } else if (keyType.equals(ResidentField.CARD_ID)) {
            return ResidentDal.querytByCardId(searchString);
        } else {
            return null;
        }
    }
	public static boolean exist(String paperNum) {
        return ResidentDal.existByPaperNum(paperNum);
	}

    /**
     * @param searchString
     * @param keyType
     * @return
     */
    public static boolean exist(String searchString, String keyType) {
        if (keyType.equals(ResidentField.PAPER_NUM)) {
            return ResidentDal.existByPaperNum(searchString);
        } else if (keyType.equals(ResidentField.RESIDENT_NAME)) {
            return ResidentDal.existByResidentName(searchString);
        } else if (keyType.equals(ResidentField.CARD_ID)) {
            return ResidentDal.existByCardId(searchString);
        } else {
            return false;
        }
    }

}
