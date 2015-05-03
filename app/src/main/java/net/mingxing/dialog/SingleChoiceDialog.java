package net.mingxing.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


/**
 * 单选dialogFragment
 */
public class SingleChoiceDialog extends DialogFragment {

    public static final String TAG = "SingleChoiceDialog";

    private static final int DEFAULT_CHECKED = 0;

    private static String[] mDataSource;

    private String mTitle = "";

    private OnSelectListener mOnSelectListener;

    private static SingleChoiceDialog mSingleChoiceDialog = new SingleChoiceDialog();

    public interface OnSelectListener{

        void onSelectItem(int index);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener){
        mOnSelectListener = onSelectListener;
    }

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     * first recevice data , then create execute lifecycle method;
     */
    public static SingleChoiceDialog newInstance(String[] dataSource) {
        // 这里有待实践
        mDataSource  = dataSource;
        return mSingleChoiceDialog;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setTitle(mTitle).setSingleChoiceItems(mDataSource, DEFAULT_CHECKED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if(mOnSelectListener != null) {
                    mOnSelectListener.onSelectItem(i);
                }
            }
        }).create();
    }

}
