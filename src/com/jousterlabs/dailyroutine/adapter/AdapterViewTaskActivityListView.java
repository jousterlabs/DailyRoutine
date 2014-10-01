package com.jousterlabs.dailyroutine.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jousterlabs.dailyroutine.R;
import com.jousterlabs.dailyroutine.ViewTaskActivity;
import com.jousterlabs.dailyroutine.commonutils.GetterSetter;
import com.jousterlabs.dailyroutine.database.DailyroutineDataBaseClass;

public class AdapterViewTaskActivityListView extends BaseAdapter {

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

	public AdapterViewTaskActivityListView(Context mContext,
			ArrayList<GetterSetter> mArrayList_getterSetter,
			ImageView mImageView_AdapterViewTaskActivityListView_grid,
			ImageView mImageView_AdapterViewTaskActivityListView_delete) {
		this.context = mContext;
		this.layoutInflater = LayoutInflater.from(mContext);
		this.arrayList_getterSetter = mArrayList_getterSetter;
		this.imageView_AdapterViewTaskActivityListView_grid = mImageView_AdapterViewTaskActivityListView_grid;
		this.imageView_AdapterViewTaskActivityListView_delete = mImageView_AdapterViewTaskActivityListView_delete;

		if(arrayList_getterSetter.size()<=0)
		{
			imageView_AdapterViewTaskActivityListView_grid.setVisibility(View.GONE);
			imageView_AdapterViewTaskActivityListView_delete.setVisibility(View.GONE);
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
			viewHolder.imageView_checkBox_checkedItems.setTag(0);

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

			// --------------------Start Selected Items---

			try {
				// Log.d("sudhir Cursor", "sudhir top");
				Cursor cursor = dailyroutineDataBaseClass
						.db_fatechSpecficItem_dailyRoutineCheckedItem("yes");

				if (cursor != null && cursor.getCount() > 0) {
					while (cursor.moveToNext()) {
						String string_itemTimeStamp = cursor.getString(9);

						if (arrayList_getterSetter.get(position)
								.getString_ViewTaskActivity_timeStamp()
								.equalsIgnoreCase(string_itemTimeStamp)) {
							viewHolder.imageView_checkBox_checkedItems
									.setBackgroundResource(R.drawable.checked);
							viewHolder.imageView_checkBox_checkedItems.setTag(1);
						}

					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				// Log.d("sudhir Cursor", "sudhir" + e.getMessage());
			}
			// --------------------End Selected Items---

			// ---Start CheckBox Checked---

			viewHolder.imageView_checkBox_checkedItems
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							int tag = (Integer) v.getTag();
							if (tag == 0) {
								viewHolder.imageView_checkBox_checkedItems
										.setBackgroundResource(R.drawable.checked);
								v.setTag(1);
								// ---Start Update Checked Items in Db---
								try {
									ContentValues contentValues = new ContentValues();
									String string_timeStamp = arrayList_getterSetter
											.get(position)
											.getString_ViewTaskActivity_timeStamp();
									contentValues.put("work_listCheckedStatus",
											"yes");
									dailyroutineDataBaseClass
											.db_update_dailyRoutineCheckedItemStatus(
													contentValues,
													string_timeStamp);
								} catch (Exception e) {
									// TODO: handle exception
								}
								// ---End Update Checked Items in Db---
							}
							if (tag == 1) {

								viewHolder.imageView_checkBox_checkedItems
										.setBackgroundResource(R.drawable.unchecked);
								v.setTag(0);
								// ---Start Update Checked Items in Db---
								try {
									ContentValues contentValues = new ContentValues();
									String string_timeStamp = arrayList_getterSetter
											.get(position)
											.getString_ViewTaskActivity_timeStamp();
									contentValues.put("work_listCheckedStatus",
											"no");
									dailyroutineDataBaseClass
											.db_update_dailyRoutineCheckedItemStatus(
													contentValues,
													string_timeStamp);
								} catch (Exception e) {
									// TODO: handle exception
								}
								// ---End Update Checked Items in Db---

							}
							imageView_AdapterViewTaskActivityListView_grid
									.setVisibility(View.VISIBLE);
							imageView_AdapterViewTaskActivityListView_delete
									.setVisibility(View.VISIBLE);
						}
					});

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
			// ---End grid Button Click Event---

			// ---Start Delete Button Click Event---
			imageView_AdapterViewTaskActivityListView_grid
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							asynTaskFatchTaskDetailsDBAllChecked = new AsynTaskFatchTaskDetailsDBAllChecked();
							if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
								asynTaskFatchTaskDetailsDBAllChecked
										.executeOnExecutor(
												AsyncTask.THREAD_POOL_EXECUTOR,
												"async");
							} else {
								asynTaskFatchTaskDetailsDBAllChecked
										.execute("async");
							}

						}
					});
			// ---End Delete Button Click Event---

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

			// ---Start Make it Select All---
			try {
				ContentValues contentValues = new ContentValues();
				for (int i = 0; i < arrayList_getterSetter.size(); i++) {
					String string_timeStamp = arrayList_getterSetter.get(i)
							.getString_ViewTaskActivity_timeStamp();
					contentValues.put("work_listCheckedStatus", "yes");
					dailyroutineDataBaseClass
							.db_update_dailyRoutineCheckedItemStatus(
									contentValues, string_timeStamp);

				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			// ---End Make it Select All---
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
				ContentValues contentValues = new ContentValues();
				Cursor cursor = dailyroutineDataBaseClass
						.db_fatechSpecficItem_dailyRoutineCheckedItem("yes");

				if (cursor != null && cursor.getCount() > 0) {
					while (cursor.moveToNext()) {
						String string_itemTimeStamp = cursor.getString(9);
						contentValues.put("work_listStatus", "deleted");
						dailyroutineDataBaseClass
								.db_update_dailyRoutineDeleteItems(
										contentValues, string_itemTimeStamp);
					}
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
					.db_fatch_dailyRoutine("ok");
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
