package com.water.nvgtor.watermanegement.tool;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by dell on 2015/7/23.
 */
public class BaseViewHolder {
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id){
        SparseArray<View> viewHolder = (SparseArray<View>)view.getTag();
        if (viewHolder == null){
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null){
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
