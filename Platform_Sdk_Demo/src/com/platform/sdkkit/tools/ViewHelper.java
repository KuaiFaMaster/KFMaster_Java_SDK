package com.platform.sdkkit.tools;

import android.view.View;


/**
 * <li>File Name: ViewHelper.java</li>
 * <li>File Description: </li>
 * <li>Company: </li>
 * <li>Create Time: 2015-12-3 上午11:47:59</li>
 * @version 1.0.0
 * @author  HooRang
 */
public class ViewHelper {

	public static void gone(View... v){
		
		if (null == v) {
			return ;
		}
		
		for(View child : v){
			if (null != child) {
				child.setVisibility(View.GONE);
			}
			
		}
		
	}
	
	
	public static void invisible(View... v){
		
		if (null == v) {
			return ;
		}
		
		for(View child : v){
			if (null != child) {
				child.setVisibility(View.INVISIBLE);
			}
			
		}
		
	}
	
	public static void visible(View... v){
		
		if (null == v) {
			return ;
		}
		
		for(View child : v){
			if (null != child) {
				child.setVisibility(View.VISIBLE);
			}
			
		}
		
	}
	
}


