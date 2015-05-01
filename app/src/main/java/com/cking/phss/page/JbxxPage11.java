package com.cking.phss.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.FamilyMember;
import com.cking.phss.bean.FamilyMember.Member;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.JbxxJtcyLayout;
import com.cking.phss.widget.ListItemJbxxJtcy;

/**
 * 
 * @author tao
 * 
 */
public class JbxxPage11 extends LinearLayout implements IPage {
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    private Button xzButton = null;
    private JbxxJtcyLayout mJbxxJtcyLayout = null;

    /**
     * @param context
     */
    public JbxxPage11(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxPage11(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
        super(context, attrs);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_archives_jtcy_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        xzButton = (Button) findViewById(R.id.xzButton);
        mJbxxJtcyLayout = (JbxxJtcyLayout) findViewById(R.id.jbxxJtcyLayout);

        xzButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.jtcyTitleLinearLayout);
                mJbxxJtcyLayout.addItem(titleLinearLayout);
            }
        });
    }

    @Override
    public void setValue() {
        Jmjtxx mJmjtkxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        if (mJmjtkxx == null) {
            return;
        }

        FamilyMember familyMember = mJmjtkxx.getFamilyMember();
        mJbxxJtcyLayout.clear();// 清掉所有项
        if (familyMember != null) {
            List<Member> members = familyMember.members;
            if (members != null && members.size() > 0) {
                for (int i = 0; i < members.size(); i++) {
                    Member hd = members.get(i);
                    if (StringUtil.isEmptyString(hd.memberName) || StringUtil.isEmptyString(hd.credentialsNo)) {
                        continue;
                    }
                    String relationToHouseholderCD = null;
                    String sex = null;
                    String educationCD = null;
                    String occupationCD = null;
                    String maritalStatusCD = null;
                    String archivesStatusCD = null;
                    relationToHouseholderCD = hd.relationToHouseholderCD.getcD();
                    sex = hd.sex.getcD();
                    educationCD = hd.educationCD.getcD();
                    occupationCD = hd.occupationCD.getcD();
                    maritalStatusCD = hd.maritalStatusCD.getcD();
                    archivesStatusCD = hd.archivesStatusCD.getcD();
                    mJbxxJtcyLayout.addItem(hd.residentID, relationToHouseholderCD, hd.memberID, hd.memberName,
                            sex, hd.birthDay, educationCD, occupationCD, maritalStatusCD,
                            archivesStatusCD,
                            hd.credentialsNo, hd.remark, hd.address);
                }
            }
        }
    }

    @Override
    public boolean getValue() {
        Jmjtxx mJmjtkxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjtkxx == null || jmjbxx == null) {
            return false;
        }

        FamilyMember familyMember = mJmjtkxx.getFamilyMember();
        if (familyMember != null) {
            List<Member> members = familyMember.members;
            if (members != null && members.size() > 0) {
                members.clear();
            } else {
                familyMember.members = new ArrayList<Member>();
            }
        } else {
            familyMember = new FamilyMember();
            familyMember.members = new ArrayList<Member>();
            mJmjtkxx.setFamilyMember(familyMember);
        }

        if (mJbxxJtcyLayout.mListView.size() > 0) {
            for (ListItemJbxxJtcy ljc : mJbxxJtcyLayout.mListView) {
                Member member = new Member();
                
                member.memberID = "";
                member.residentID = ljc.getMyId();
                member.memberName = ljc.getXm();
                String sex = ljc.getXb();
                String sexCD = ResourcesFactory.findId(mContext, "xb", sex);
                member.sex = new BeanCD(sexCD, sex);
                member.birthDay = ljc.getCsrq();
                String credentials = "居民身份证";
                String credentialsCD = ResourcesFactory.findId(mContext, "zjlx", credentials);
                member.credentialsCD = new BeanCD(credentialsCD, credentials);
                member.credentialsNo = ljc.getSfzhm();
                String education = ljc.getWhcd();
                String educationCD = ResourcesFactory.findId(mContext, "whcd", education);
                member.educationCD = new BeanCD(educationCD, education);
                String maritalStatus = ljc.getHyzk();
                String maritalStatusCD = ResourcesFactory.findId(mContext, "hyzk", maritalStatus);
                member.maritalStatusCD = new BeanCD(maritalStatusCD, maritalStatus);
                member.telephone = "";
                member.mobilePhone = "";
                member.householder = mJmjtkxx.getHouseholder();
                String relationToHouseholder = ljc.getYhzgx();
                String relationToHouseholderCD = ResourcesFactory.findId(mContext, "yhzgx",
                        relationToHouseholder);
                member.relationToHouseholderCD = new BeanCD(relationToHouseholderCD, relationToHouseholder);
                member.address = ljc.getJzdz();
                member.industryCD = null;
                String occupation = ljc.getZy();
                String occupationCD = ResourcesFactory.findId(mContext, "zy", occupation);
                member.occupationCD = new BeanCD(occupationCD, occupation);
                String archivesStatus = ljc.getGrzk();
                String archivesStatusCD = ResourcesFactory.findId(mContext, "grzt", archivesStatus);
                member.archivesStatusCD = new BeanCD(archivesStatusCD, archivesStatus);
                member.remark = ljc.getBz();
                familyMember.members.add(member);
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
