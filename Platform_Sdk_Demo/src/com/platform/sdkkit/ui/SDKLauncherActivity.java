package com.platform.sdkkit.ui;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.hjr.sdkkit.framework.mw.entity.ParamsContainer;
import com.hjr.sdkkit.framework.mw.entity.ParamsKey;
import com.hjr.sdkkit.framework.mw.openapi.HJRSDKKitPlateformCore;
import com.platform.sdk.demo.R;
import com.platform.sdkkit.api.DatasApi;
import com.platform.sdkkit.tools.DataSources;
import com.platform.sdkkit.tools.DemoListViewAdapter;
import com.platform.sdkkit.tools.ViewHelper;
/**
 * 平台sdk 调用示例类
 * @author HooRang
 *
 */
public class SDKLauncherActivity extends Activity implements OnClickListener {

	static HJRSDKKitPlateformCore sdkObj = null;
	static String cacheOrderId = "";
	
	private DatasApi dataApi  = null ;
	
	
	public Button btn_data_enterGame , btn_bussines_login; 
	public TextView lbl_message ;
	
	public ExpandableListView exp_lv_tree ;
	private DemoListViewAdapter expTreeAdapter ; 
	
	public static boolean hasInitSuccess = false ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getResources().getIdentifier("platform_sdk_demo", "layout", this.getPackageName()));

		initButtons();
		
		bindClicklistener();
		
		
		//SDK初始化，传入回调接口的实现类
		sdkObj = HJRSDKKitPlateformCore.initHJRPlateform(this,new PlatformSDKCallBack(this));
		dataApi = new DatasApi(sdkObj);
		

	}


	private void initButtons() {
		
		btn_data_enterGame 	= 	(Button)findViewById(R.id.btn_data_entergame);
		btn_bussines_login 	= 	(Button)findViewById(R.id.btn_bussines_login);
		
		
		lbl_message = (TextView)findViewById(R.id.lbl_callback_message);
		
		exp_lv_tree = (ExpandableListView)findViewById(getResources().getIdentifier("exp_lv_function_tree", "id", getPackageName()));
		
		expTreeAdapter = new DemoListViewAdapter(this, DataSources.sParentObj, DataSources.sChildrenObj);
		exp_lv_tree.setAdapter(expTreeAdapter);
	
	}
	

	
	private void bindClicklistener() {
		
		btn_data_enterGame.setOnClickListener(this);
		btn_bussines_login.setOnClickListener(this);
		
		
		
		exp_lv_tree.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {
				
				switch (groupPosition) {
				case DataSources.FUNCTION_CODE_BUSSINES:
					if (childPosition == 0) { 		// 支付
						if (!isFastDoubleClick()) {
							sdkPay();
						}
					}else if (childPosition == 1) { // 注销
						sdkObj.User.logout();
					}else if (childPosition == 2) {//用户中心
						sdkObj.User.userCenter();
					}else if (childPosition == 3) { //获取当前登录用户信息
						JSONObject userInfo = sdkObj.Base.getCurrentLoginedUserInfo();
						if (userInfo.isNull("loginOpenId") ) {
							Toast.makeText(SDKLauncherActivity.this, "尚未登录", Toast.LENGTH_SHORT).show();
						}else {
							Toast.makeText(SDKLauncherActivity.this, userInfo.toString(), Toast.LENGTH_SHORT).show();
							
						}
					}
					
					break;
				case DataSources.FUNCTION_CODE_DATAS:
					if (childPosition == 0) { //统计支付：在支付成功之后调用
						dataApi.onPay();
					}else if (childPosition == 1) { //统计创建角色：在创建角色时调用
						dataApi.onCreateRole();
					}else if (childPosition == 2) { //统计角色升级：在角色升级时调用
						dataApi.onUpgrade();
					}
					break;
				case DataSources.FUNCTION_CODE_SHARE:
					if (childPosition == 0) { // 分享，该接口为扩展插件，非必接接口
						ParamsContainer pc = new ParamsContainer();
						//分享标题
						pc.putString(ParamsKey.KEY_SHARE_TITLE, ""); 
						//分享内容描述
						pc.putString(ParamsKey.KEY_SHARE_DESCRIPTION, "");
						//分享的文字
						pc.putString(ParamsKey.KEY_SHARE_TEXT, "");
						//分享点击跳转连接
						pc.putString(ParamsKey.KEY_SHARE_TARGETURL, "");
						//分享图标链接
						pc.putString(ParamsKey.KEY_SHARE_IMAGEURL, "");
						sdkObj.Share.share(pc);
					}
					break;
				case DataSources.FUNCTION_CODE_PUSH:
					if (childPosition == 0) { 		// 开启推送，该接口为扩展插件，非必接接口
						sdkObj.Push.startWork(SDKLauncherActivity.this);
					}else if (childPosition == 1) { //打推送tag，该接口为扩展插件，非必接接口
						sdkObj.Push.setTags("女性玩家");
					}
					break;

				default:
					break;
				}
				
				
				return true;
			}
		});
	}
	
	
	
	
	
	@Override
	public void onClick(View v) {
		int sfocusButtonId = v.getId();
		switch (sfocusButtonId) {
		case R.id.btn_bussines_login: // 登录
			if (!isFastDoubleClick()) {
				sdkObj.User.login(this);
			}
			break;
	
		case R.id.btn_data_entergame://进入游戏，在进入游戏时调用
			
			ViewHelper.gone(btn_data_enterGame);
			ViewHelper.visible(exp_lv_tree);
			
			dataApi.onEnterGame();
			break;

		default:
			break;
		}
	}

	
	/**
	 * sdk pay
	 */
	public void sdkPay() {
		
		ParamsContainer pc = new ParamsContainer();
		// 所购买商品金额, 以元为单位。
		pc.putInt(ParamsKey.KEY_PAY_AMOUNT, 6);
		// 购买数量 ，通常都是1
		pc.putInt(ParamsKey.KEY_PAY_PRODUCT_NUM, 1);
		// 订单号， 没有传""
		pc.putString(ParamsKey.KEY_PAY_ORDER_ID, "51000003266");
		//商品ID，请注意值一定是整型
		pc.putInt(ParamsKey.KEY_PAY_PRODUCT_ID, 63);
		// 所购买商品名称
		pc.putString(ParamsKey.KEY_PAY_PRODUCT_NAME, "6元宝");
		//商品描述
		pc.putString(ParamsKey.KEY_PRODUCT_DESC, "");
		// 扩展参数, 会作为透传给cp服务端，可以为""
		pc.putString(ParamsKey.KEY_EXTINFO, "");
		
		sdkObj.Pay.pay(pc);
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (hasInitSuccess) {
			
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				sdkObj.Base.exitGame(this);
			}
		}else {
			this.finish();
			Process.killProcess(Process.myPid());
		}
		return super.onKeyDown(keyCode, event);
	}
	

	// /
	//以下接口，无需做任何修改，拷贝进游戏的主Activity即可
	// ------------------------------生命周期函数 开始-------------------------
	// /
	@Override
	protected void onResume() {
		super.onResume();
		if (sdkObj != null) {
			sdkObj.LifeCycle.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sdkObj != null) {
			sdkObj.LifeCycle.onPause();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (sdkObj != null) {
			sdkObj.LifeCycle.onStop();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (sdkObj != null) {
			sdkObj.LifeCycle.onDestroy();
		}
		
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (sdkObj != null) {
			sdkObj.LifeCycle.onConfigurationChanged(newConfig);
		}
	}

	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (sdkObj != null) {
			sdkObj.LifeCycle.onSaveInstanceState(outState);
		}
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (sdkObj != null) {
			sdkObj.LifeCycle.onNewIntent(intent);
		}
	}

	// /
	// ------------------------------生命周期函数 结束-------------------------
	// /

	
	/**
	 * 是否是快速的连续点击按钮
	 */
	private static long lastClickTime;
	public static boolean isFastDoubleClick() {
		long time =System.currentTimeMillis() - lastClickTime;
		if ((0L < time) && (time < 500L))
			return true;
		lastClickTime = time;
		return false;
	}


}
