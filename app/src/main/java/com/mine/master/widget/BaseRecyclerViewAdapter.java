package com.mine.master.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mine.master.ui.base.view.BaseActivity;

import java.util.ArrayList;

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<Holder> {

    public static int typeCount = 0;
    public LayoutInflater inflater;

    public static final int TYPE_NORMAL = 0;

    public ArrayList<Integer> typeList = new ArrayList<Integer>();
    public ArrayList<Object> itemList = new ArrayList<Object>();

    public void clearData() {
        typeList.clear();
        itemList.clear();
    }


    public void remove(int index) {
        typeList.remove(index);
        itemList.remove(index);
    }

    public boolean containType(int index) {
        return typeList.indexOf(index) > -1;
    }


    /**
     * 增加数据类型
     *
     * @param typeList
     */
    public void setTypeList(ArrayList<Integer> typeList) {
        this.typeList = typeList;
    }

    private BaseActivity context;

    public BaseRecyclerViewAdapter(BaseActivity context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addElements(Object bean, int type) {
        addElements(bean, type, -1);
    }


    public void addElements(Object bean, int type, int index) {
        if (index < 0 || index > itemList.size()) {
            typeList.add(type);
            itemList.add(bean);
        } else {
            typeList.add(index, type);
            itemList.add(index, bean);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {

        } else {
            return extensionOnCreateViewHolder(parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (holder.getItemViewType() == TYPE_NORMAL) {

        } else {
            extensionOnBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return typeList.get(position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    /**
     * 创建扩展的视图
     *
     * @param parent
     * @param viewType
     */
    public Holder extensionOnCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 绑定扩展的视图
     *
     * @param holder
     * @param position
     */
    public void extensionOnBindViewHolder(Holder holder, int position) {

    }

}
