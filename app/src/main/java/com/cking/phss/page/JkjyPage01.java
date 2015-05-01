package com.cking.phss.page;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.JkjyResultUtil;
import com.cking.phss.util.TispToastFactory;

public class JkjyPage01 extends LinearLayout implements IPage {
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();

    public TextView mJkjyText;// 健康建议
    private TextView sgTextView;
    private TextView tzTextView;
    private TextView bmiTextView;
    private TextView ywTextView;
    private TextView twTextView;//
    private TextView ytbTextView;
    private TextView zkTextView;
    private TextView tzflTextView;

    private TextView nzzfdjTextView;
    private ImageView txImageView;//
    private TextView txTextView;//
    private TextView txmsTextView;//
    private TextView jcdxTextView;

    public JkjyPage01(Context context, Map<String, IBean> beanMap) {
        super(context);
        init(context);
        this.beanMap = beanMap;
    }

    public JkjyPage01(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_jkjy_01_layout, this);
        mJkjyText = (TextView) findViewById(R.id.jkpfmsTextView);
        sgTextView = (TextView) findViewById(R.id.sgTextView);
        tzTextView = (TextView) findViewById(R.id.tzTextView);
        bmiTextView = (TextView) findViewById(R.id.bmiTextView);
        ywTextView = (TextView) findViewById(R.id.ywTextView);
        twTextView = (TextView) findViewById(R.id.twTextView);
        ytbTextView = (TextView) findViewById(R.id.ytbTextView);
        zkTextView = (TextView) findViewById(R.id.zkTextView);
        tzflTextView = (TextView) findViewById(R.id.tzflTextView);
        nzzfdjTextView = (TextView) findViewById(R.id.nzzfdjTextView);
        txImageView = (ImageView) findViewById(R.id.txImageView);
        txTextView = (TextView) findViewById(R.id.txTextView);
        txmsTextView = (TextView) findViewById(R.id.txmsTextView);
        jcdxTextView = (TextView) findViewById(R.id.jcdxTextView);
    }

    @Override
    public void setValue() {
        clear();
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mJkjyText.setText(R.string.toast_info_none_resident);
            return;
        }

        Jktj_kstj jktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        //Log.i(TAG, jktj_kstj.getbMI());
        // 判断阻抗值存不存在
        // String imp = jktj_kstj.getiMP();
        // if (imp.trim().equals("")||Float.parseFloat(imp.trim())<=0) {//
        // 如果不存在，那么只显示身高体重 bmi等数据
        // String height=jktj_kstj.getHeight().trim();
        // String weight=jktj_kstj.getWeight().trim();
        // String bmi=jktj_kstj.getbMI().trim();
        // int rank=-1;
        // double bmiValue=0;
        // if(!bmi.equals("")&&Double.parseDouble(bmi)>0){
        // bmiValue=Double.parseDouble(bmi);
        // rank=getTzjl(bmiValue);
        // }else{
        // if(!height.trim().equals("")&&!weight.trim().equals("")
        // &&Float.parseFloat(height.trim())>0&&Float.parseFloat(weight.trim())>0){
        // bmiValue=Float.parseFloat(weight)/Math.sqrt(Float.parseFloat(height)/100);
        // rank=getTzjl(bmiValue);
        // }
        // }
        // if(rank==-1){
        // mJkjyText.setText("您没有做体重相关检测，无法对您评估!");
        // }else{
        // sgTextView.setText(height);
        // tzTextView.setText(weight);
        // bmiTextView.setText(bmiValue+"");
        // mJkjyText.setText("缺少更多人体成分数据，暂不能进行健康评分。");
        // }
        // return;
        // }

        
        //如果IMP阻抗值存在
        sgTextView.setText(jktj_kstj.getHeight());
        tzTextView.setText(jktj_kstj.getWeight());
        ywTextView.setText(jktj_kstj.getWaist());
        twTextView .setText(jktj_kstj.gethIP());
        zkTextView.setText(jktj_kstj.getiMP());
        tzflTextView.setText(jktj_kstj.getFat());
        nzzfdjTextView.setText(jktj_kstj.getVisceralFat());
        jcdxTextView.setText(jktj_kstj.getbMR());
        int sexCD = jmjbxx.getSexCD();
        float bmi = 0;
        int age = CalendarUtil.getAge(jmjbxx.getBirthDay());
        float zfl = 0;
        float fyw = 0;
        float ftw = 0;
        try {
            bmi = Float.parseFloat(jktj_kstj.getbMI());
        } catch (NumberFormatException e) {
        }
        try {
            zfl = Float.parseFloat(jktj_kstj.getFat());
        } catch (NumberFormatException e) {
        }
        try {
            fyw = Float.parseFloat(jktj_kstj.getWaist());
        } catch (NumberFormatException e) {
        }
        try {
            ftw = Float.parseFloat(jktj_kstj.gethIP());
        } catch (NumberFormatException e) {
        }
        int tzlxbh = JkjyResultUtil.getTzlxbhResult(sexCD == 1, bmi);
        int tz2lxbh = JkjyResultUtil.getTzlxbhResult(sexCD == 1, age, zfl);
        int ytbbh = JkjyResultUtil.getYtbhResult(sexCD == 1, fyw, ftw);
        // TmpResult tzlxResult = findTmpResultById(tzlxbh, tzlxs);
        bmiTextView.setText(jktj_kstj.getbMI());
        // TmpResult ytbResult = findTmpResultById(ytbbh, ytblxs);
        ytbTextView.setText(jktj_kstj.getYtb());
        int pdjlbh = JkjyResultUtil.getPdjlbhResult(tzlxbh, tz2lxbh, ytbbh);
        Pdjl pdjl = findPdjlByPdjlbh(pdjlbh);
        
        if (pdjl != null) {
            txTextView.setText(pdjl.bdjlmc);
            txmsTextView.setText(pdjl.jkjy);
            txImageView.setImageResource(pdjl.imageId);
        }
    }

    /**
     * 
     */
    @Override
    public void clear() {
    }

    @Override
    public boolean getValue() {
        return false;
    }

    private int getTzjl(double bmiValue){
        if(bmiValue>0&&bmiValue<18.5){
            return 0;
        }else if (bmiValue<25) {
            return 1;
        }else if (bmiValue<28) {
            return 2;
        }else if (bmiValue<100) {
            return 3;
        }
        return 3;
    }
    
    public class Pdjl {
        public int pdjlbh; // 判定结论编号
        public int imageId; // 图片资源Id
        public String bdjlmc; // 判定结论名称
        public String jkjy; // 健康建议 
        public Pdjl(int pdjlbh, int imageId, String bdjlmc, String jkjy) {
            this.pdjlbh = pdjlbh;
            this.imageId = imageId;
            this.bdjlmc = bdjlmc;
            this.jkjy = jkjy;
        }
    }
    Pdjl[] pdjls = new Pdjl[] {
        new Pdjl(1, R.drawable.jkjy_dtz, "低体重", "请注意营养。适当的脂肪组织可以保护神经、血管、组织、脏器、使它们减轻震动、免受撞击。脂肪过少会引发贫血、记忆衰退、胃下垂、胆结石、骨质疏松等病症。皮下脂肪可以保持人体体温恒定，使体温不易向外扩散。（你能通过良好的营养和有计划的锻炼达到适当范围体重及范围脂肪。请咨询专业医师意见。）" ),
                new Pdjl(2, R.drawable.jkjy_bztz, "标准体重", "请以健康适当的脂肪为目标，限制热量的摄入，坚持运动。（你能通过良好的营养和有计划的锻炼达到适当范围体重及范围脂肪。请咨询专业医师意见。）"   ),
                new Pdjl(3, R.drawable.jkjy_gtz, "高体重", "请积极地进行运动和限制饮食以减低脂肪量。饮食要均衡，营养全面足够。食物多样，以谷类为主，多吃蔬菜、水果、菌类、海藻类等清淡的食物，少吃肉类，高脂，高糖的食物。适当的运动，运动并不意味着你必须每天去跑步或骑自行车几千米，只要中等程度地增加运动量，就可以使你脂肪控制，而且健康得到明显的改善。（你能通过良好的营养和有计划的锻炼达到适当范围体重及范围脂肪。请咨询专业医师意见。）" ), 
                new Pdjl(4, R.drawable.jkjy_fbxfp, "腹部型肥胖", "腹部型肥胖，也称为向心性肥胖、内脏型肥胖、上身性肥胖、苹果型、男性样肥胖，所伴随动脉硬化、脑卒中、高血压、糖尿病、冠心病、高血脂等各种并发症的危险性较高，而且其危险性随腰围增大而变高。请严格控制油腻、油炸、高糖高脂类、啤酒、碳酸饮料等摄入。另外，在加强全身性有氧运动的同时，着重四肢力量练习的局部运动也有利于减少腹部脂肪，比如体操、游泳、跑步、仰卧起坐等。（你能通过良好的营养和有计划的锻炼达到适当范围体重及范围脂肪。请咨询专业医师意见。）"),
                new Pdjl(5, R.drawable.jkjy_zwxfp, "周围型肥胖", "周围型肥胖，也称为全身匀称性肥胖、臀型肥胖、下身型肥胖、梨型、女性样肥胖，相比腹部型肥胖的危险性较低。但仍然要注意限制肉类、洋芋片、油炸物、甜点类食物等摄入，同时请加强爬楼梯、腹肌运动、站起蹲下、瑜伽等有利于增加肌肉负荷的下半身肌力运动。（你能通过良好的营养和有计划的锻炼达到适当范围体重及范围脂肪。请咨询专业医师意见。）"), 
                new Pdjl(6, R.drawable.jkjy_ckx, "参考型", "您的测量结果仅供参考。（请咨询专业医师意见。）"),
                new Pdjl(7, R.drawable.jkjy_jrx, "肌肉型" , "您的检测结果非常好，请继续保持均衡的饮食和适量的运动吧！" )
    };
    
    private Pdjl findPdjlByPdjlbh(int pdjlbh) {
        for (Pdjl pdjl : pdjls) {
            if (pdjlbh == pdjl.pdjlbh) {
                return pdjl;
            }
        }
        return null;
    }
    
    public class TmpResult { // 临时结论
        public int id; // 编号
        public String name; // 名称
        
        public TmpResult(int tzlxbh, String tzlxmc) {
            this.id = tzlxbh;
            this.name = tzlxmc;
        }
    }
    private TmpResult findTmpResultById(int id, TmpResult[] tmpResults) {
        for (TmpResult tmpResult : tmpResults) {
            if (id == tmpResult.id) {
                return tmpResult;
            }
        }
        return null;
    }
    
    private TmpResult[] tzlxs = new TmpResult[] {
            new TmpResult(1, "体脂率低 "),
            new TmpResult(2, "标准体脂 "),
            new TmpResult(3, "体脂率高 "),
            new TmpResult(6, "参考型"),
            new TmpResult(7, "没有体脂数据  ")
    };
    private TmpResult[] ytblxs = new TmpResult[] {
            new TmpResult(1, "腰臀比低 "),
            new TmpResult(2, "腰臀比正常 "),
            new TmpResult(3, "腰腹部脂肪偏多")
    };
    private TmpResult[] tz2lxs = new TmpResult[] {
            new TmpResult(1, "低体重 "),
            new TmpResult(2, "标准体重"),
            new TmpResult(3, "高体重")
    };

}
