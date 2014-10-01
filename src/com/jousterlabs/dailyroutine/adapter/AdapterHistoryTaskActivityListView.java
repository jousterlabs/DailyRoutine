package com.jousterlabs.dailyroutine.adapter;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jousterlabs.dailyroutine.R;
import com.jousterlabs.dailyroutine.commonutils.GetterSetter;
import com.jousterlabs.dailyroutine.database.DailyroutineDataBaseClass;

public class AdapterHistoryTaskActivityListView extends BaseAdapter {

	Context context;
	LayoutInflater layoutInflater;
	ArrayList<GetterSetter> arrayList_getterSetter;
	DailyroutineDataBaseClass dailyroutineDataBaseClass;
	GetterSetter getterSetter;
	LinearLayout linearLayout_buttomLay;
	ImageView imageView_AdapterViewTaskActivityListView_grid,
			imageView_AdapterViewTaskActivityListView_delete;
	AsynTaskFatchTaskDetailsDBAllChecked asynTaskFatchTaskDetailsDBAllChecked;
	AsynTaskDeleteCheckedItem asynTaskDeleteCheckedItem;
	int position_item;

	public AdapterHistoryTaskActivityListView(Context mContext,
			ArrayList<GetterSetter> mArrayList_getterSetter,
			ImageView mImageView_AdapterViewTaskActivityListView_delete) {
		this.context = mContext;
		this.layoutInflater = LayoutInflater.from(mContext);
		this.arrayList_getterSetter = mArrayList_getterSetter;
		this.imageView_AdapterViewTaskActivityListView_delete = mImageView_AdapterViewTaskActivityListView_delete;

		if (arrayList_getterSetter.size() <= 0) {
			imageView_AdapterViewTaskActivityListView_delete
					.setVisibility(View.GONE);
		}

		dailyroutineDataBaseClass = new DailyroutineDataBaseClass(context);
		dailyroutineDataBaseClass.db_Write();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList_getterSetter.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		// string=""+position;
		return arrayList_getterSetter.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// boolean_Clicked=true;
		final ViewHolder viewHolder;
		convertView = layoutInflater.inflate(
				R.layout.viewtaskactivity_list_items, null);

		if (convertView != null) {
			viewHolder = new ViewHolder();
			viewHolder.textView_workName = (TextView) convertView
					.findViewById(R.id.txt_viewtaskactivity_list_items_workName);
			viewHolder.textView_workPlace = (TextView) convertView
					.findViewById(R.id.txt_viewtaskactivity_list_items_workPlace);
			viewHolder.textView_workDate = (TextView) convertView
					.findViewById(R.id.txt_viewtaskactivity_list_items_workDate);
			viewHolder.textView_workTime = (TextView) convertView
					.findViewById(R.id.txt_viewtaskactivity_list_items_workTime);
			viewHolder.textView_workRemark = (TextView) convertView
					.findViewById(R.id.txt_viewtaskactivity_list_items_remark);
			viewHolder.linearLayout_AdapterViewTaskActivityListView = (LinearLayout) convertView
					.findViewById(R.id.linlay_viewtaskactivity_list_items);
			viewHolder.imageView_checkBox_checkedItems = (ImageView) convertView
					.findViewById(R.id.cheb_viewtaskactivity_list_items_checkedItems);
			viewHolder.imageView_checkBox_checkedItems.setVisibility(View.GONE);
			
			position_item=position;

			viewHolder.textView_workName.setText(arrayList_getterSetter.get(
					position).getString_ViewTaskActivity_work());
			viewHolder.textView_workPlace.setText(arrayList_getterSetter.get(
					position).getString_ViewTaskActivity_place());
			viewHolder.textView_workDate.setText(arrayList_getterSetter.get(
					position).getString_ViewTaskActivity_workDate());
			viewHolder.textView_workTime.setText(arrayList_getterSetter.get(
					position).getString_ViewTaskActivity_workTime());
			viewHolder.textView_workRemark.setText(arrayList_getterSetter.get(
					position).getString_ViewTaskActivity_remark());

			// ---End CheckBox Checked---

			// ---Start Grid Button Click Event---
			imageView_AdapterViewTaskActivityListView_delete
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							asynTaskDeleteCheckedItem = new AsynTaskDeleteCheckedItem();
							if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
								asynTaskDeleteCheckedItem
										.executeOnExecutor(
												AsyncTask.THREAD_POOL_EXECUTOR,
												"async");
							} else {
								asynTaskDeleteCheckedItem.execute("async");
							}
						}
					});

		}

		return convertView;
	}

	public class ViewHolder {

		TextView textView_workName, textView_workPlace, textView_workTime,
				textView_workDate, textView_workRemark;
		LinearLayout linearLayout_AdapterViewTaskActivityListView;
		ImageView imageView_checkBox_checkedItems;
	}

	// ---Start AsynTask to Checked All Items---

	protected class AsynTaskFatchTaskDetailsDBAllChecked extends
			AsyncTask<String, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub

			loadDatafrDataBase();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

	// ---End AsynTask to Checked All Items---

	// ---Start AsynTask to Delete Checked Items---

	protected class AsynTaskDeleteCheckedItem extends
			AsyncTask<String, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub

			try {
				for(int i=0;i<arrayList_getterSetter.size();i++)
				{
					String string_timeStamp=arrayList_getterSetter.get(i).getString_ViewTaskActivity_timeStamp();
					dailyroutineDataBaseClass.db_delete_dailyRoutine_history(string_timeStamp);	
				}

				

			} catch (Exception e) {
				// TODO: handle exception

			}

			loadDatafrDataBase();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

	// ---End AsynTask to Delete Checked Items---

	// ---Start Load Data From DataBase---
	public void loadDatafrDataBase() {
		String string_ViewTaskActivity_work = null, string_ViewTaskActivity_place, string_ViewTaskActivity_remark, string_ViewTaskActivity_workTime, string_ViewTaskActivity_workDate, string_ViewTaskActivity_timeStamp;
		Cursor cursor_resDetails;
		if (arrayList_getterSetter != null && arrayList_getterSetter.size() > 0) {
			arrayList_getterSetter.clear();
		}
		try {
			cursor_resDetails = dailyroutineDataBaseClass
					.db_fatch_dailyRoutine("deleted");
			if (cursor_resDetails != null && cursor_resDetails.getCount() > 0) {
				while (cursor_resDetails.moveToNext()) {
					string_ViewTaskActivity_work = cursor_resDetails
							.getString(2);
					string_ViewTaskActivity_workDate = cursor_resDetails
							.getString(3);
					string_ViewTaskActivity_workTime = cursor_resDetails
							.getString(4);
					string_ViewTaskActivity_place = cursor_resDetails
							.getString(5);
					string_ViewTaskActivity_remark = cursor_resDetails
							.getString(6);
					string_ViewTaskActivity_timeStamp = cursor_resDetails
							.getString(9);

					getterSetter = new GetterSetter();
					getterSetter
							.setString_ViewTaskActivity_work(string_ViewTaskActivity_work);
					getterSetter
							.setString_ViewTaskActivity_workDate(string_ViewTaskActivity_workDate);
					getterSetter
							.setString_ViewTaskActivity_workTime(string_ViewTaskActivity_workTime);
					getterSetter
							.setString_ViewTaskActivity_place(string_ViewTaskActivity_place);
					getterSetter
							.setString_ViewTaskActivity_remark(string_ViewTaskActivity_remark);
					getterSetter
							.setString_ViewTaskActivity_timeStamp(string_ViewTaskActivity_timeStamp);
					arrayList_getterSetter.add(getterSetter);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			// Log.d("resName", "resName"+e.getMessage());
		}
	}
	// ---End Load Data From DataBase---
}
