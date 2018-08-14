package com.dena.lcm.photopicker;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;


public class LCMResource {
	private final static String TAG = LCMResource.class.getSimpleName();
	private final static String DRAWABLE_CLASS_NAME = "drawable";
	private final static String ID_CLASS_NAME = "id";
	private final static String LAYOUT_CLASS_NAME = "layout";
	private final static String XML_CLASS_NAME = "xml";
	private final static String ANIM_CLASS_NAME = "anim";
	private final static String STYLE_CLASS_NAME = "style";
	private final static String STRING_CLASS_NAME = "string";
	private final static String COLOR_CLASS_NAME = "color" ;
	private final static String RAW_CLASS_NAME = "raw";
	private final static String ARRAY_CLASS_NAME = "array";

	private static LCMResource mInstance = null;
	private Context mContext = null ;
	
	
	private Resources mResources = null ;
	
	private LayoutInflater mInflater = null ;
	
	private int ErrorCode =  0;

	private LCMResource(Context context) {
		LCMLog.i(TAG, "Initiate LCMResource.");
		mContext = context ;
		mResources = mContext.getResources() ;
		mInflater = LayoutInflater.from(mContext) ;
	}
	
	public static LCMResource getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new LCMResource(context);
		}
		return mInstance;
	}
	
	/*
	 * 取Color资源
	 */
	public int getColor(String colorname) {
		if(mResources !=null) {
			int id = mResources.getIdentifier(colorname, COLOR_CLASS_NAME, mContext.getPackageName());
			return mResources.getColor(id);
		}
		else {
			LCMLog.e(TAG,  "The specified resource '" + colorname + "' not found ") ;
			return ErrorCode ;
		}
	}
	
	/*
	 * 取 String 资源
	 */
	public String getString(String strname){
		if(mResources != null ) {
			int id = mResources.getIdentifier(strname, STRING_CLASS_NAME, mContext.getPackageName()) ;
			if(id == 0 ){
                LCMLog.e(TAG, "The specified resource '" + strname + "' not found") ;
				return null ;
			}
			return mResources.getString(id);	
		} else {
            LCMLog.e(TAG, "The specified resource '" + strname + "' not found") ;
			return null ;
		}

	}
	
	public int getStringForId(String strname){
		if(mResources != null ) {
			int id = mResources.getIdentifier(strname, STRING_CLASS_NAME, mContext.getPackageName()) ;
			return id;	
		} else {
            LCMLog.e(TAG, "The specified resource '" + strname + "' not found") ;
			return ErrorCode ;
		}

	}

	/*
	 * 取Drawable资源
	 */
	
	public Drawable getDrawable(String drawname) {
		if (mResources != null) {
			int id = mResources.getIdentifier(drawname, DRAWABLE_CLASS_NAME, mContext.getPackageName()) ;
			if(id == 0 ){
                LCMLog.e(TAG, "The specified resource '" + drawname + "' not found");
				return null ;
			}
			return mResources.getDrawable(id);	
		} else {
            LCMLog.e(TAG, "The specified resource '" + drawname + "' not found");
			return null;
		}
	}
	
	public int getDrawableForId(String drawname) {
		if (mResources != null) {
			int id = mResources.getIdentifier(drawname, DRAWABLE_CLASS_NAME, mContext.getPackageName()) ;
			return id ;
		} else {
            LCMLog.e(TAG, "The specified resource '" + drawname + "' not found");
			return ErrorCode;
		}		
	}
	
	public int getLayoutForId(String layoutname) {
		
		if(mResources != null ) {
			int id = mResources.getIdentifier(layoutname, LAYOUT_CLASS_NAME, mContext.getPackageName()) ;
			return id ;	
		}
		else {
            LCMLog.e(TAG, "LayoutInflater does not init" );
			return ErrorCode ;
		}
		
	}
	
	/*
	 * 取Layout资源
	 */
	public View getLayoutForView(String layoutname) {
		if (mResources != null) {
			int id = mResources.getIdentifier(layoutname, LAYOUT_CLASS_NAME, mContext.getPackageName()) ;
			if( (mInflater != null) && (id != 0)) {
				return mInflater.inflate(id, null);
			} else {
                LCMLog.e(TAG, "The specified resource '" + layoutname + "' does not init" );
				return null ;
			}
		} else {
            LCMLog.e(TAG, "The specified resource '" + layoutname + "' not found");
			return null;
		}
	}
	
	/*
	 * 取id资源
	 */
	public int getId(String idname) {
		if (mResources != null) {
			int id = mResources.getIdentifier(idname, ID_CLASS_NAME, mContext.getPackageName()) ;
			return id ;
		}
		else {
            LCMLog.e(TAG, "The specified resource '" + idname + "' not found");
			return ErrorCode;
		}
	}
	
	/*
	 * 获取Raw资源
	 */
	public int getRaw(String rawName) {
		if (mResources != null) {
			int id = mResources.getIdentifier(rawName, RAW_CLASS_NAME, mContext.getPackageName()) ;
			return id ;
		}
		else {
            LCMLog.e(TAG, "The specified resource '" + rawName + "' not found");
			return ErrorCode;
		}
	}
	
	/*
	 * 取Bitmap 资源
	 */
	public Bitmap getBitmap(String imgname) {
		if (mResources != null) {	
			int id = mResources.getIdentifier(imgname, DRAWABLE_CLASS_NAME, mContext.getPackageName()) ;
			if(id == 0 ){
                LCMLog.e(TAG, "The specified resource '" + imgname + "' not found");
				return null;
			}
			Bitmap bitmap = BitmapFactory.decodeResource(mResources,id) ;
			return bitmap ;
		}
		else {
            LCMLog.e(TAG, "The specified resource '" + imgname + "' not found");
			return null;			
		}
	}
	
	public int getStyle(String stylename) {
		if (mResources != null) {	
			int id = mResources.getIdentifier(stylename, STYLE_CLASS_NAME, mContext.getPackageName()) ;
			return id ;
		}
		else {
            LCMLog.e(TAG, "The specified resource '" + stylename + "' not found");
			return ErrorCode;			
		}		
	}
	
	
	public int getAnim(String animname) {
		if (mResources != null) {	
			int id = mResources.getIdentifier(animname, ANIM_CLASS_NAME, mContext.getPackageName()) ;
			return id ;
		}
		else {
            LCMLog.e(TAG, "The specified resource '" + animname + "' not found");
			return ErrorCode;			
		}	
	}
	
	public XmlResourceParser getXmlForParser(String xmlname) {
		if (mResources != null) {	
			int id = mResources.getIdentifier(xmlname, XML_CLASS_NAME, mContext.getPackageName()) ;
			if(id == 0 ){
                LCMLog.e(TAG, "The specified resource '" + xmlname + "' not found");
				return null;
			}
			return mResources.getXml(id) ;
 		}
		else {
            LCMLog.e(TAG, "The specified resource '" + xmlname + "' not found");
			return null; 			
		}			
	}
	
	public int  getXmlForId(String xmlname) {
		if (mResources != null) {	
			int id = mResources.getIdentifier(xmlname, XML_CLASS_NAME, mContext.getPackageName()) ;
			return id ;
		}
		else {
            LCMLog.e(TAG, "The specified resource '" + xmlname + "' not found");
			return ErrorCode ; 			
		}			
	}

	public int getArrayId(String arrayName) {
		if (mResources != null) {
			int id = mResources.getIdentifier(arrayName, ARRAY_CLASS_NAME, mContext.getPackageName());
			return id;
		} else {
			LCMLog.e(TAG, "The specified resource '" + arrayName + "' not found");
			return  ErrorCode;
		}
	}

	public int[] getIntArray(String arrayName) {
		int arrayId = getArrayId(arrayName);
		return mResources.getIntArray(arrayId);
	}

	public String[] getStringArray(String arrayName) {
		int arrayId = getArrayId(arrayName);
		return mResources.getStringArray(arrayId);
	}
}
