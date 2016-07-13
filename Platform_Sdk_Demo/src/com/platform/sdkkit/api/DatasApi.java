package com.platform.sdkkit.api;

import com.hjr.sdkkit.framework.mw.entity.DataTypes;
import com.hjr.sdkkit.framework.mw.entity.ParamsContainer;
import com.hjr.sdkkit.framework.mw.entity.ParamsKey;
import com.hjr.sdkkit.framework.mw.openapi.HJRSDKKitPlateformCore;


/**
 * <li>File Name: DatasApiHelper.java</li>
 * <li>File Description: 数据统计类接口帮助类</li>
 * <li>Company: </li>
 * <li>Create Time: 2015-12-3 上午10:23:13</li>
 * @version 1.0.0
 * @author  HooRang
 * 
 */
public class DatasApi  {
	private HJRSDKKitPlateformCore sdkObj ;

	
	
	public DatasApi(HJRSDKKitPlateformCore core) {
		sdkObj = core; 
	}
	

	public void onEnterGame(){
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
		
		
		/**
		 * 角色创建时间
		 * 1.需要发uc九游渠道则必传，long类型 时间戳，十位数，特别注意不能取系统当前时间，需要传服务器的角色创建时间，服务端需要保存该值
		 *   详情可查看uc官方要求说明：http://bbs.9game.cn/thread-5370208-1-1.html
		 * 2.不需要上uc九游渠道，则传0L
		 */
		pc.put(ParamsKey.KEY_ROLE_CREATETIME, 0L);

		
		/**
		 * 角色升级时间时间
		 * 1.需要发uc九游渠道则必传，要求与以上
		 * 2.不需要上uc九游渠道，则传0L
		 */
		pc.put(ParamsKey.KEY_ROLE_UPGRADETIME, 0L);
		

		sdkObj.Collections.onDatas(DataTypes.DATA_ENTER_GAME, pc);
		
		
	}
	
	public void onPay(){
		ParamsContainer pc = new ParamsContainer();
		// 充值金额
		pc.putInt(ParamsKey.KEY_AMOUNT, 6);
		// 订单号 ,可以传递sdk payCallback的payKitOrderId ,也可以传递cp自己的订单号
		pc.putString(ParamsKey.KEY_ORDERNUMBER, "51000003266");
		// 商品描述
		pc.putString(ParamsKey.KEY_PRODUCT_DESC, "这里是我的商品描述");

		sdkObj.Collections.onDatas(DataTypes.DATA_PAY, pc);
		
		
	}
	
	
	
	public void onUpgrade(){
		
		ParamsContainer pc = new ParamsContainer();
		// 玩家升级后等级
		pc.putString(ParamsKey.KEY_ROLE_LEVEL, "3");
		/**
		 * 角色创建时间
		 * 1.需要发uc九游渠道则必传，long类型 时间戳，十位数，特别注意不能取系统当前时间，需要传服务器的角色创建时间，服务端需要保存该值
		 *   详情可查看uc官方要求说明：http://bbs.9game.cn/thread-5370208-1-1.html
		 * 2.不需要上uc九游渠道，则传0L
		 */
		pc.put(ParamsKey.KEY_ROLE_CREATETIME, 0L);

		
		/**
		 * 角色升级时间时间
		 * 1.需要发uc九游渠道则必传，要求与以上
		 * 2.不需要上uc九游渠道，则传0L
		 */
		pc.put(ParamsKey.KEY_ROLE_UPGRADETIME, 0L);
		sdkObj.Collections.onDatas(DataTypes.DATA_ROLE_UPGRADE, pc);
		
	}
	
	
	
	public void onCreateRole(){
		
		ParamsContainer pc = new ParamsContainer();
		// 角色标识
		pc.putString(ParamsKey.KEY_ROLE_ID, "1");
		// 角色昵称
		pc.putString(ParamsKey.KEY_ROLE_NAME, "角色升级昵称");
		// 服务器编号
		pc.putString(ParamsKey.KEY_SERVER_ID, "2");
		// 服务器名称
		pc.putString(ParamsKey.KEY_SERVER_NAME, "服务器名称");

		sdkObj.Collections.onDatas(DataTypes.DATA_CREATE_ROLE, pc);
		
		
	}
	
	
	
}


