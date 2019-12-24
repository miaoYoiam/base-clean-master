package com.mine.master.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mine.domain.result.CompanyInviteInfoListReuslt;
import com.mine.domain.result.PersonalResumeListResult;
import com.mine.master.R;
import com.mine.master.ui.base.view.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<Holder> {

    public static int typeCount = 0;
    public LayoutInflater inflater;

    public static final int TYPE_NORMAL = 0;
    public final int TYPE_PERSONAL = TYPE_NORMAL + 1;
    public final int TYPE_COMPANY = TYPE_PERSONAL + 1;

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
            return null;
        } else if (viewType == TYPE_PERSONAL) {
            return new PersonalResumeHolder(inflater.inflate(R.layout.item_personal_resume, parent, false));
        } else if (viewType == TYPE_COMPANY) {
            return new CompanyResumeHolder(inflater.inflate(R.layout.item_company_resume, parent, false));
        } else {
            return extensionOnCreateViewHolder(parent, viewType);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (holder.getItemViewType() == TYPE_NORMAL) {

        } else if (holder.getItemViewType() == TYPE_PERSONAL) {
            handlePersonalResumeData((PersonalResumeListResult.PersonalResumeBean) itemList.get(position), (PersonalResumeHolder) holder, position);
        } else if (holder.getItemViewType() == TYPE_COMPANY) {
            handleCompanyResumeData((CompanyInviteInfoListReuslt.ListBean) itemList.get(position),(CompanyResumeHolder) holder, position);
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

    public class PersonalResumeHolder extends Holder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.expect_salary)
        TextView expectSalary;
        @BindView(R.id.personal_name)
        TextView personalName;
        @BindView(R.id.work_years)
        TextView workYears;
        @BindView(R.id.create_date)
        TextView createDate;
        @BindView(R.id.phone)
        TextView phone;

        public PersonalResumeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void handleCompanyResumeData(CompanyInviteInfoListReuslt.ListBean data,CompanyResumeHolder holder, int position) {
        if (data != null && holder != null) {
            holder.title.setText(data.getTitle());
            holder.expectSalary.setText(data.getExpectSalary());
            holder.companyName.setText(data.getCompanyName());
            holder.workYears.setText(data.getWorkYears());
            holder.createDate.setText(data.getCreateDate());
            holder.phone.setText("联系电话：" + data.getPhone());
        }
    }

    private void handlePersonalResumeData(PersonalResumeListResult.PersonalResumeBean data, PersonalResumeHolder holder, int position) {
        if (data != null && holder != null) {
            holder.title.setText(data.getTitle());
            holder.expectSalary.setText(data.getExpectSalary());
            holder.personalName.setText(data.getAuthor());
            holder.workYears.setText(data.getWorkYears());
            holder.createDate.setText(data.getCreateDate());
            holder.phone.setText("联系电话：" + data.getPhone());
        }
    }

    public class CompanyResumeHolder extends Holder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.expect_salary)
        TextView expectSalary;
        @BindView(R.id.company_name)
        TextView companyName;
        @BindView(R.id.work_years)
        TextView workYears;
        @BindView(R.id.create_date)
        TextView createDate;
        @BindView(R.id.phone)
        TextView phone;

        public CompanyResumeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
