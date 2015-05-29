package com.kfmaster.sdkkit;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.hjr.sdkkit.framework.mw.entity.DataTypes;
import com.hjr.sdkkit.framework.mw.entity.ParamsContainer;
import com.hjr.sdkkit.framework.mw.entity.ParamsKey;
import com.hjr.sdkkit.framework.mw.openapi.HJRSDKKitPlateformCore;
/**
 * 快发助手 调用示例类
 * @author HooRang
 * @version 2.0.2
 * @data 2015-05-29
 *
 */
public class KFMasterActivity extends Activity {

	static HJRSDKKitPlateformCore hjrSDK;

	public static String cacheOrderId = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getResources().getIdentifier("hjr_framework_middleware_demo", "layout", this.getPackageName()));

		//传入回调接口的实现类
		hjrSDK = HJRSDKKitPlateformCore.initHJRPlateform(new KFMasterSDKCallBack());
		hjrSDK.Business.init(this);
	}

	

	/**
	 * sdk login
	 * 
	 * @param v
	 */
	public void hjrSdkLogin(View v) {

		hjrSDK.Business.login(this);

	}

	/**
	 * sdk pay
	 * 
	 * @param v
	 */
	public void hjrSdkPay(View v) {
		
		int amount = 1; // amount 充值金额(必须传int类型)
		String orderId = ""; // 游戏方自定义订单号，可为空
		String serverId = "1"; // 服务区编号（若没有分区或只有一个服务器的游戏可传 1）
		String serverName = "区服"; // 服务器名称
		String productName ="元宝"; //商品名称
		String userId = "213";
		String userName = "快发大师";
		String extinfo = ""; // 透传参数
		
		ParamsContainer pc = new ParamsContainer();
		// 所购买商品金额, 以元为单位。
		pc.putInt(ParamsKey.KEY_PAY_AMOUNT, amount);
		// 购买数量 
		pc.putInt(ParamsKey.KEY_PAY_PRODUCT_NUM, 1);
		// 订单号， 没有传""
		pc.putString(ParamsKey.KEY_PAY_ORDER_ID, orderId);
		//商品ID
		pc.putInt(ParamsKey.KEY_PAY_PRODUCT_ID, 10112);
		// 所购买商品名称
		pc.putString(ParamsKey.KEY_PAY_PRODUCT_NAME, productName);
		// 区服ID 
		pc.putString(ParamsKey.KEY_PAY_SERVER_ID, serverId);
		// 区服名
		pc.putString(ParamsKey.KEY_PAY_SERVER_NAME, serverName);
		// 角色ID
		pc.putString(ParamsKey.KEY_PAY_ROLE_ID, "");
		// 角色名
		pc.putString(ParamsKey.KEY_PAY_ROLE_NAME, "");
		// 角色等级
		pc.putString(ParamsKey.KEY_PAY_ROLE_LEVEL, "");
		// 用户ID
		pc.putString(ParamsKey.KEY_PAY_USER_ID, userId);
		// 用户名
		pc.putString(ParamsKey.KEY_PAY_USER_NAME, userName);
		// 扩展参数
		pc.putString(ParamsKey.KEY_EXTINFO, extinfo);
		
		hjrSDK.Business.pay(pc);

	}

	/**
	 * sdk 注销
	 * 
	 * @param v
	 */
	public void hjrSdkLogout(View v) {
		hjrSDK.Business.logout();
	}

	/**
	 * sdk 获取订单结果
	 * 
	 * @param v
	 */
	public void hjrSdkGetOrderResult(View v) {
		ParamsContainer pc = new ParamsContainer();
		pc.put(ParamsKey.KEY_PAY_ORDER_ID, cacheOrderId);
		hjrSDK.Business.getOrderInfo(pc);
	}

	/**
	 * 进入用户中心
	 * 
	 * @param v
	 */
	public void hjrSdkUserCenter(View v) {
		hjrSDK.Business.userCenter();
	}

	/**
	 * 
	 * @param v
	 */
	public void hjrSdkExit(View v) {
		hjrSDK.Business.exitGame(this);
	}

	// /
	//以下接口，无需做任何修改，拷贝进游戏的主Activity即可
	// ------------------------------生命周期函数 开始-------------------------
	// /
	@Override
	protected void onResume() {
		super.onResume();
		if (hjrSDK != null) {
			hjrSDK.LifeCycle.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (hjrSDK != null) {
			hjrSDK.LifeCycle.onPause();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (hjrSDK != null) {
			hjrSDK.LifeCycle.onStop();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (hjrSDK != null) {
			hjrSDK.LifeCycle.onDestroy();
		}
		
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (hjrSDK != null) {
			hjrSDK.LifeCycle.onConfigurationChanged(newConfig);
		}
	}

	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (hjrSDK != null) {
			hjrSDK.LifeCycle.onSaveInstanceState(outState);
		}
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (hjrSDK != null) {
			hjrSDK.LifeCycle.onNewIntent(intent);
		}
	}

	// /
	// ------------------------------生命周期函数 结束-------------------------
	// /

	// /
	// ------------------------------统计部分接口 开始-------------------------
	// /

	/**
	 * 统计登录
	 * 接入点：登录成功之后调用
	 * 
	 * @param view
	 */
	public void hjrSdkLoginData(View view) {
		ParamsContainer pc = new ParamsContainer();
		// 用户标识
		pc.putString(ParamsKey.KEY_USER_ID, "123");
		// 服务器编号
		pc.putString(ParamsKey.KEY_SERVER_ID, "123");

		hjrSDK.Collections.onDatas(DataTypes.DATA_LOGIN, pc);

	}
	
	
	/**
	 * 上传角色信息
	 * 接入点：登录成功，并且成功选择区服拿到绝色信息之后调用，通常在“进入游戏”时调用
	 * @param view
	 */
	public void hjrSdkServerRoleInfoData(View view) {
		
		ParamsContainer pc = new ParamsContainer();
		// 角色id
		pc.putString(ParamsKey.KEY_ROLE_ID, "1");
		// 角色昵称
		pc.putString(ParamsKey.KEY_ROLE_NAME, "角色昵称");
		// 角色等级
		pc.putInt(ParamsKey.KEY_ROLE_LEVEL, 15);
		// 服务器编号
		pc.putString(ParamsKey.KEY_SERVER_ID, "123");
		// 服务器名称
		pc.putString(ParamsKey.KEY_SERVER_NAME, "服务器名称");
		// 角色所在帮派或工会名称，没有可以传""
		pc.putString(ParamsKey.KEY_ROLE_PARTY_NAME, "角色所在帮派或工会名称");
		// VIP等级，没有可以传""
		pc.putString(ParamsKey.KEY_ROLE_VIP_LEVEL, "VIP等级");

		hjrSDK.Collections.onDatas(DataTypes.DATA_SERVER_ROLE_INFO, pc);

	}
	

	/**
	 * 统计支付
	 * 调用点：支付成功之后，切记是成功之后
	 * @param view
	 */
	public void hjrSdkPayData(View view) {
		onPayDatas();

	}



	private void onPayDatas() {
		ParamsContainer pc = new ParamsContainer();
		// 充值金额
		pc.putInt(ParamsKey.KEY_AMOUNT, 6);
		// 服务器ID
		pc.putString(ParamsKey.KEY_SERVER_ID, "3");
		// 服务器名称
		pc.putString(ParamsKey.KEY_SERVER_NAME, "西南一区");
		// 用户标识
		pc.putString(ParamsKey.KEY_USER_ID, "213");
		// 角色唯一标识
		pc.putString(ParamsKey.KEY_ROLE_ID, "2323"); // 取的时候要特别注意
		// 订单号
		pc.putString(ParamsKey.KEY_ORDERNUMBER, "123");
		// 玩家等级
		pc.putString(ParamsKey.KEY_ROLE_GRADE, "5");
		// 角色昵称
		pc.putString(ParamsKey.KEY_ROLE_NAME, "角色升级昵称");
		// 商品描述
		pc.putString(ParamsKey.KEY_PRODUCT_DESC, "这里是我的商品描述");

		hjrSDK.Collections.onDatas(DataTypes.DATA_PAY, pc);
	}

	/**
	 * 统计用户升级
	 * 调用点：角色升级时，必须实时的调用，数据请填写真实准确的数据
	 * @param view
	 */
	public void hjrSdkUpgradeData(View view) {
		ParamsContainer pc = new ParamsContainer();
		// 用户标识
		pc.putString(ParamsKey.KEY_USER_ID, "123");
		// 服务器编号
		pc.putString(ParamsKey.KEY_SERVER_ID, "12");
		// 玩家等级
		pc.putString(ParamsKey.KEY_ROLE_LEVEL, "3");
		// 角色id
		pc.putString(ParamsKey.KEY_ROLE_ID, "1");
		// 角色昵称
		pc.putString(ParamsKey.KEY_ROLE_NAME, "角色昵称");
		// 服务器名称
		pc.putString(ParamsKey.KEY_SERVER_NAME, "西南一区");

		hjrSDK.Collections.onDatas(DataTypes.DATA_ROLE_UPGRADE, pc);
	}

	/**
	 * 统计创建角色
	 * 调用点：创建角色时调用
	 * @param view
	 */
	public void hjrSdkCreateRoleData(View view) {

		ParamsContainer pc = new ParamsContainer();
		// 用户标识：用户id
		pc.putString(ParamsKey.KEY_USER_ID, "123");
		// 角色标识
		pc.putString(ParamsKey.KEY_ROLE_ID, "1");
		// 角色昵称
		pc.putString(ParamsKey.KEY_ROLE_NAME, "角色升级昵称");
		// 服务器编号
		pc.putString(ParamsKey.KEY_SERVER_ID, "2");
		// 服务器名称
		pc.putString(ParamsKey.KEY_SERVER_NAME, "服务器名称");

		hjrSDK.Collections.onDatas(DataTypes.DATA_CREATE_ROLE, pc);

	}

	/**
	 * 统计按钮点击事件
	 * 调用点：游戏中按钮点击时调用，该接口为非必接
	 * @param view
	 */
	public void hjrSdkBtnClickData(View view) {
		ParamsContainer pc = new ParamsContainer();
		pc.putString(ParamsKey.KEY_USER_ID, "123");
		pc.putString(ParamsKey.KEY_NAME, "快发大师按钮点击事件测试");
		hjrSDK.Collections.onDatas(DataTypes.DATA_BUTTON_CLICK, pc);

	}

	

	// /
	// ------------------------------统计部分接口 结束-------------------------
	// /

}
