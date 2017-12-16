package com.zoe.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mList;
    protected View mParent;
    private int mLayoutId;
    public CommonAdapter(Context ctx, @NonNull List<T> list, @LayoutRes int layoutId){
        mList = list;
        mContext = ctx;
        mLayoutId = layoutId;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(mContext, mLayoutId, null);
        }
        mParent = convertView;
        bindData(getItem(position));
        return convertView;
    }

    public abstract void bindData(T data);

    @SuppressWarnings("unchecked")
    protected View findViewById(@IdRes int id) {
        SparseArray<View> sa = (SparseArray<View>) mParent.getTag();
        if (sa == null) {
            sa = new SparseArray<View>();
            mParent.setTag(sa);
        }
        View view = sa.get(id);
        if (view == null) {
            view = mParent.findViewById(id);
            sa.put(id, view);
        }
        return view;
    }
}
