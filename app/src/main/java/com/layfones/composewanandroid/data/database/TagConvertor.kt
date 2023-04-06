package com.layfones.composewanandroid.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.layfones.composewanandroid.data.services.model.Tag

class TagConvertor {

    private val gson = Gson()

    @TypeConverter
    fun objectToString(tags: List<Tag>): String {
        return gson.toJson(tags)
    }

    @TypeConverter
    fun stringToObject(json: String): List<Tag> {
        val type = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(json, type)
    }

}