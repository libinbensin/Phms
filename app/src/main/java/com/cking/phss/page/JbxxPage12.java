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
import com.cking.phss.bean.FamilyMainProblems;
import com.cking.phss.bean.FamilyMainProblems.Problem;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.JbxxJtwtLayout;
import com.cking.phss.widget.ListItemJbxxJtwt;

/**
 * 
 * @author tao
 * 
 */
public class JbxxPage12 extends LinearLayout implements IPage {
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    private Button xzButton = null;
    private JbxxJtwtLayout mJbxxJtwtLayout = null;

    /**
     * @param context
     */
    public JbxxPage12(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxPage12(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_archives_jtwt_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        xzButton = (Button) findViewById(R.id.xzButton);
        mJbxxJtwtLayout = (JbxxJtwtLayout) findViewById(R.id.jbxxJtwtLayout);

        xzButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.jtwtTitleLinearLayout);
                mJbxxJtwtLayout.addItem(titleLinearLayout);
            }
        });
    }

    @Override
    public void setValue() {
        Jmjtxx mJmjtkxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        if (mJmjtkxx == null) {
            return;
        }

        FamilyMainProblems familyMainProblems = mJmjtkxx.getFamilyMainProblems();
        mJbxxJtwtLayout.clear();// 清掉所有项
        if (familyMainProblems != null) {
            List<Problem> problems = familyMainProblems.problems;
            if (problems != null && problems.size() > 0) {
                for (int i = 0; i < problems.size(); i++) {
                    Problem hd = problems.get(i);
                    if (StringUtil.isEmptyString(hd.happenedMember)) {
                        continue;
                    }

                    int stage = 0;
                    try {
                        stage = Integer.parseInt(hd.stage.getcD());
                    } catch (NumberFormatException e) {
                    }
                    mJbxxJtwtLayout.addItem(stage, hd.problemID, hd.happenedMember,
                            hd.happenDate, hd.appraise, hd.mainProblem.getTagValue(),
                            hd.handleAndResult, hd.SubjectData, hd.objectiveData,
                            hd.other, hd.managementPlan, hd.markDoctorName, hd.markDate, hd.remark);
                }
            }
        }
    }

    @Override
    public boolean getValue() {
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjtxx == null || jmjbxx == null) {
            return false;
        }

        FamilyMainProblems familyMainProblems = mJmjtxx.getFamilyMainProblems();
        if (familyMainProblems != null) {
            List<Problem> problems = familyMainProblems.problems;
            if (problems != null && problems.size() > 0) {
                problems.clear();
            } else {
                familyMainProblems.problems = new ArrayList<Problem>();
            }
        } else {
            familyMainProblems = new FamilyMainProblems();
            familyMainProblems.problems = new ArrayList<Problem>();
            mJmjtxx.setFamilyMainProblems(familyMainProblems);
        }

        if (mJbxxJtwtLayout.mListView.size() > 0) {
            for (ListItemJbxxJtwt ljc : mJbxxJtwtLayout.mListView) {
                Problem problem = new Problem();
                problem.problemID = ljc.getDisSn();
                String stage = ljc.getJd();
                String stageCD = ResourcesFactory.findId(mContext, "jd", stage);
                problem.stage = new BeanCD(stageCD, stage);
                problem.happenDate = ljc.getFsrq();
                problem.markDate = ljc.getJlrq();
                problem.markDoctorID = "";
                problem.markDoctorName = ljc.getJlys();
                problem.problemRemark = ljc.getBz();
                problem.SubjectData = ljc.getZgzl();
                problem.objectiveData = ljc.getKgzl();
                String mainProblem = ljc.getZywt();
                String mainProblemCD = ResourcesFactory.findId(mContext, "zywt", mainProblem);
                problem.mainProblem = new BeanCD(mainProblemCD, mainProblem);
                problem.appraise = ljc.getWtpg();
                problem.handleAndResult = ljc.getCljjg();
                problem.other = ljc.getQt();
                problem.happenedMember = ljc.getFsr();
                problem.managementPlan = ljc.getGljh();
                problem.remark = ljc.getBz();
                
                familyMainProblems.problems.add(problem);
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
