package com.coolweather.app.model;

import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {

	/**
	* 数据库名
	*/
	public static final String DB_NAME = "cool_weather";
	/**
	* 数据库版本
	*/
	public static final int VERSION = 1;
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;
	
	/**
	* 将构造方法私有化
	*/
	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
		DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	
	/**
	* 获取CoolWeatherDB的实例。
	*/
	public synchronized static CoolWeatherDB getInstance(Context context) {
		
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		
		return coolWeatherDB;
	}
	
	/**
	* 从数据库读取全国所有的省份信息。
	*/
	public List<Province> loadProvinces() {
	List<Province> list = new ArrayList<Province>();
	Cursor cursor = db
	.query("Province", null, null, null, null, null, null);
	if (cursor.moveToFirst()) {
	do {
	Province province = new Province();
	province.setId(cursor.getInt(cursor.getColumnIndex("id")));
	province.setProvinceName(cursor.getString(cursor
	.getColumnIndex("province_name")));
	province.setProvinceCode(cursor.getString(cursor
	.getColumnIndex("province_code")));
	list.add(province);
	} while (cursor.moveToNext());
	}
	return list;
	}
	
}
