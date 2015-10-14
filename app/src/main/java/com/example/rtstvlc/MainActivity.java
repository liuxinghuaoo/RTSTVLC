package com.example.rtstvlc;

import org.videolan.libvlc.EventHandler;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.LibVlcException;
import org.videolan.libvlc.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity/Vlc";
	private LibVLC mLibVLC = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			// LibVLC.init(getApplicationContext());
			EventHandler em = EventHandler.getInstance();
			em.addHandler(handler);

			mLibVLC = Util.getLibVlcInstance();

			if (mLibVLC != null) {
//				String pathUri = "rtsp://192.168.1.1/MJPG?W=640&H=360&Q=50&BR=3000000";
				String pathUri = "http://120.198.201.105/tencentapp/tencent/resources/resource/915/2015031715031449693.mp4";
//				String pathUri = "file:///storage/emulated/0/MustCapture/video/winmax/2014-01-27 115155.mp4";
				mLibVLC.playMyMRL(pathUri);
			}

		} catch (LibVlcException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Log.d(TAG, "Event = " + msg.getData().getInt("event"));
			switch (msg.getData().getInt("event")) {
			case EventHandler.MediaPlayerPlaying:

			case EventHandler.MediaPlayerPaused:

				break;
			case EventHandler.MediaPlayerStopped:

				break;
			case EventHandler.MediaPlayerEndReached:

				break;
			case EventHandler.MediaPlayerVout:
				if (msg.getData().getInt("data") > 0) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(),
							VideoPlayerActivity.class);
					startActivity(intent);
//					MainActivity.this.finish();
				}
				break;
			case EventHandler.MediaPlayerPositionChanged:
				break;
			case EventHandler.MediaPlayerEncounteredError:
//				AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
//						.setTitle("提示信息")
//						.setMessage("无法连接到摄像头，请确保手机已经连接到摄像头所在的wifi热点")
//						.setNegativeButton("知道了",
//								new DialogInterface.OnClickListener() {
//
//									@Override
//									public void onClick(DialogInterface dialog,
//											int which) {
//										MainActivity.this.finish();
//									}
//								}).create();
//				dialog.setCanceledOnTouchOutside(false);
//				dialog.show();
				Toast.makeText(getApplicationContext(), "视频播放失败，请检查视频路径和网络", Toast.LENGTH_LONG).show();
				break;
			default:
				Log.d(TAG, "Event not handled ");
				break;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
//		if(handler!=null) {
//			EventHandler em = EventHandler.getInstance();
//			em.removeHandler(handler);
//		}
//		if(mLibVLC!=null) {
//			mLibVLC.stop();
//			mLibVLC.clearBuffer();
//			mLibVLC.destroy();
//			mLibVLC.closeAout();
//			mLibVLC.detachSurface();
//			mLibVLC.stopDebugBuffer();
//			android.os.Process.killProcess(android.os.Process.myPid());
//			mLibVLC = null;
//		}
		finish();

		super.onDestroy();
	}

}
