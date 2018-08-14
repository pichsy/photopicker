package com.dena.lcm.photopicker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;


/**
 */
public class FolderPopupWindow extends PopupWindow {

    private ListView listView;
    private OnItemClickListener onItemClickListener;
    private final View masker;
    private View transTitle;
    private LCMResource R;

    public FolderPopupWindow(Context context, BaseAdapter adapter) {
        super(context);
        R = LCMResource.getInstance(context);
        final View view = View.inflate(context, R.getLayoutForId("cs_pop_folder"), null);
        masker = view.findViewById(R.getId("cs_picker_masker"));
        transTitle = view.findViewById(R.getId("cs_trans_title"));
        listView = (ListView) view.findViewById(R.getId("cs_picker_listView"));
        listView.setAdapter(adapter);
        int divWidth = context.getResources().getDisplayMetrics().widthPixels;
        setContentView(view);
        setWidth(divWidth * 3 / 4);  //如果不设置，就是 AnchorView 的宽度
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(0));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                dismiss();
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(adapterView, view, position, l);
            }
        });

        transTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setSelection(int selection) {
        listView.setSelection(selection);
    }


    public interface OnItemClickListener {
        void onItemClick(AdapterView<?> adapterView, View view, int position, long l);
    }


    public void showAtLocation(View parent) {
        showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
    }
}
