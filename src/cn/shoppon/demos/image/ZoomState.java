/**
 * 
 */
package cn.shoppon.demos.image;

import java.util.Observable;

/**
 * 
 * <p>
 * Description:
 * 
 * ***************************************** * * * * * * * * * * * * * ********
 * * * * * * * * * * * ******** * * * * * * * * * * * * * * * * * * * * *
 * *****************************************
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012. All rights reserved.
 * </p>
 * 
 * @author <a href="www.shoppon.cn">Shoppon</a>
 * @version
 * @see
 */
public class ZoomState extends Observable {
	private float mZoom;
	private float mPanX;
	private float mPanY;

	public ZoomState() {
		mZoom = 1;
		mPanX = 0;
		mPanY = 0;
	}

	public float getZoom() {
		return mZoom;
	}

	public float getPanx() {
		return mPanX;
	}

	public float getPany() {
		return mPanY;
	}

	public void setZoom(float zoom) {
		this.mZoom = zoom;
	}

	public void setPanx(float panX) {
		this.mPanX = panX;
	}

	public void setPany(float panY) {
		this.mPanY = panY;
	}

}
