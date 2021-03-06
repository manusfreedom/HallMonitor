package org.durka.hallmonitor;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class NotificationAdapter extends BaseAdapter {

	private final String LOG_TAG = "Hall.NA";

	private StatusBarNotification[] notifs;
	private final Context that;

	public NotificationAdapter(Context ctx, StatusBarNotification[] n) {
		that = ctx;
		notifs = n;
	}

	public void update(StatusBarNotification[] n) {
		notifs = n;
		Log.d(LOG_TAG + ".upd", "update: " + Integer.toString(n.length)
				+ " notifications");
	}

	@Override
	public int getCount() {
		return notifs.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convert, ViewGroup parent) {
		ImageView view;
		if (convert != null) {
			view = (ImageView) convert;
		} else {
			view = new ImageView(that);
			// int size = (int)
			// that.getResources().getDimension(R.dimen.icon_dimension);
			// view.setLayoutParams(new GridView.LayoutParams(size, size));
			view.setScaleType(ScaleType.CENTER_INSIDE);
			try {
				view.setImageDrawable(that
						.createPackageContext(
								notifs[position].getPackageName(), 0)
						.getResources()
						.getDrawable(notifs[position].getNotification().icon));
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return view;
	}

}
