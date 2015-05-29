package com.kfmaster.sdkkit;

import android.util.Log;

import com.hjr.sdkkit.framework.mw.openapi.callback.HJRSDKKitPlateformCallBack;


/**
 * <li>文件名称: KFMasterSDKCallBack.java</li>
 * <li>文件描述: 回调接口示例代码</li>
 * <li>公    司: 快发大师</li>
 * <li>内容摘要: 无</li>
 * <li>新建日期: 2014-12-3 下午4:27:38</li>
 * <li>修改记录: 无</li>
 * @version 产品版本: 2.0
 * @author  作者姓名: HooRang
 */
public class KFMasterSDKCallBack implements HJRSDKKitPlateformCallBack {
	
	private static final String TAG = "KFMasterSDKCallBack";
	/**
	 * **说明： 
	 * 每个接口的回调状态分为两种
	 * 
	 * 1.HJRSDKKitPlateformCallBack.STATUS_SUCCESS ： 成功
	 * 2.HJRSDKKitPlateformCallBack.STATUS_FAIL    ： 失败
	 * 
	 * 
	 * 
	 * 以下代码仅仅为回调处理的一个简单示例 ，具体每个接口的回调需要游戏方自行处理成功或失败的逻辑
	 * 
	 * 请仔细阅读在线接入文档http://www.haojieru.com
	 * 必接接口务必全部接入，愿你接入顺利！
	 * 
	 * 
	 */
	
	@Override
	public  void initCallBack(int retStatus, String retMessage) {
		Log.i(TAG, "initCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage);
		if (retStatus == HJRSDKKitPlateformCallBack.STATUS_SUCCESS) {
			//成功， 只有在sdk初始化成功之后才能调用sdk的登录
		}else {
			//失败
		}
	}
	
	

	@Override
	public void exitGameCallBack(int retStatus, String retMessage) {
		Log.i(TAG, "exitGameCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage);
		if (retStatus == HJRSDKKitPlateformCallBack.STATUS_SUCCESS) {
			// 退出提示框点击了确定，做资源回收，退出应用
		}else {
			//点击了取消
		}
	}

	@Override
	public void loginCallBack(String loginUserId, String loginUserName,
			String loginAuthToken, String loginOpenId, int retStatus,
			String retMessage) {

		Log.i(TAG, "loginCallBack-->retStatus#" + retStatus + ",retMessage#" 
					+ retMessage+",loginUserId#"+loginUserId+",loginUserName#"
					+loginUserName+",loginAuthToken#"+loginAuthToken+",loginOpenId#"+loginOpenId);
		if (retStatus == HJRSDKKitPlateformCallBack.STATUS_SUCCESS) {
			//登录成功 ， 通常在这里可以调用sdk的登录统计接口了:hjrSDK.Collections.onDatas(DataTypes.DATA_LOGIN, pc);
		}else {
			//登录失败：游戏这里必须做处理，可以做失败提示或者再次激活登录按钮让玩家可以再次发起登录操作
		}
	}

	@Override
	public void logoutCallBack(int retStatus, String retMessage) {

		Log.i(TAG, "logoutCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage);
		if (retStatus == HJRSDKKitPlateformCallBack.STATUS_SUCCESS) {
			//注销成功， 调用sdk的登录
		}
	}

	@Override
	public void payCallBack(String payKitOrderId, int retStatus,String retMessage) {
		Log.i(TAG, "payCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage+",payKitOrderId#" + payKitOrderId);
		KFMasterActivity.cacheOrderId = payKitOrderId;
		if (retStatus == HJRSDKKitPlateformCallBack.STATUS_SUCCESS) {
			//支付成功 ，确认到账后就可以调用sdk的支付统计接口：hjrSDK.Collections.onDatas(DataTypes.DATA_PAY, pc);
			
		}
	}

	@Override
	public void getOrderResultCallBack(String orderStatus,int retStatus, String retMessage) {
		Log.i(TAG, "getOrderResultCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage);
		if (retStatus == HJRSDKKitPlateformCallBack.STATUS_SUCCESS) {
			/**
			 * orderStatus : 
			 * 0：整个充值流程已经成功走完;
			 * 4：标识充值成功但是添加道具失败；
			 * 否则表示失败
			 */
		}
	}

	


}


