package com.jousterlabs.dailyroutine.commonutils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

public class AlertDialogBoxClass {
	Context context;

	public AlertDialogBoxClass(Context mContext) {
		this.context = mContext;

	}

	@SuppressLint("InlinedApi")
	@SuppressWarnings("deprecation")
	public void AlertDialogBoxCheck(String stringTitle, String alertMsg) {

		final AlertDialog alertDialog;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			alertDialog = new AlertDialog.Builder(context,
					AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
		} else {
			alertDialog = new AlertDialog.Builder(context).create();
		}

		// Setting Dialog Title
		alertDialog.setTitle(stringTitle);
		// alertDialog.setIcon(R.drawable.ic_delete);

		// Setting Dialog Message
		alertDialog.setMessage(alertMsg);

		// --- Set Yes Button

		alertDialog.setButton("ok", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				alertDialog.cancel();

			}
		});

		alertDialog.show();
	}

	// ---Yes No Dialog Box---

	@SuppressLint("InlinedApi")
	@SuppressWarnings("deprecation")
	public void AlertDialogBoxCheckYesNo(String stringTitle, String alertMsg) {

		final AlertDialog alertDialog;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			alertDialog = new AlertDialog.Builder(context,
					AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
		} else {
			alertDialog = new AlertDialog.Builder(context).create();
		}

		// Setting Dialog Title
		alertDialog.setTitle(stringTitle);
		// alertDialog.setIcon(R.drawable.ic_delete);

		// Setting Dialog Message
		alertDialog.setMessage(alertMsg);

		// --- Set Yes Button

		alertDialog.setButton("ok", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				alertDialog.cancel();
				// ((Activity)context).finish();

			}
		});

		// --- Set No Button

		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				alertDialog.cancel();
			}

		});

		// Showing Alert Message
		alertDialog.show();
	}

	// ---Make a call AlertDialog---

	@SuppressLint("InlinedApi")
	@SuppressWarnings("deprecation")
	public void AlertDialogBoxMakeaCall(String stringTitle, final String phNumber) {

		final AlertDialog alertDialog;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			alertDialog = new AlertDialog.Builder(context,
					AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
		} else {
			alertDialog = new AlertDialog.Builder(context).create();
		}

		// Setting Dialog Title
		alertDialog.setTitle(stringTitle);
		// alertDialog.setIcon(R.drawable.ic_delete);

		// Setting Dialog Message
		alertDialog.setMessage(phNumber);

		// --- Set Yes Button

		alertDialog.setButton("No", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				alertDialog.cancel();
				// ((Activity)context).finish();

			}
		});

		// --- Set No Button

		alertDialog.setButton2("Yes", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 String uri = "tel:" + phNumber.trim() ;
				 Intent intent = new Intent(Intent.ACTION_CALL);
				 intent.setData(Uri.parse(uri));
				 context.startActivity(intent);
				 alertDialog.cancel();
			}

		});

		// Showing Alert Message
		alertDialog.show();
	}

}
