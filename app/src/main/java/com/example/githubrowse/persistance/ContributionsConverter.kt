package com.example.githubrowse.persistance

import androidx.room.TypeConverter
import com.example.githubrowse.model.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContributionsConverter
{

    @TypeConverter
    fun fromCountryLangList(value: List<Owner>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Owner>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<Owner> {
        val gson = Gson()
        val type = object : TypeToken<List<Owner>>() {}.type
        return gson.fromJson(value, type)
    }

}