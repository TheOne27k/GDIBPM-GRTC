package com.grtc.gdibpm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grtc.gdibpm.displacement.Displacement
import com.grtc.gdibpm.displacement.DisplacementDAO
import com.grtc.gdibpm.heritage_asset.HeritageAsset
import com.grtc.gdibpm.heritage_asset.HeritageDAO
import com.grtc.gdibpm.users.User
import com.grtc.gdibpm.users.UserDAO

@Database(entities = [HeritageAsset::class, User::class, Displacement::class], version = 2)
abstract class GrtcDatabase: RoomDatabase() {
    abstract fun heritageDao(): HeritageDAO
    abstract fun userDao(): UserDAO
    abstract fun displacementDAO(): DisplacementDAO

    companion object {
        const val DATABASE_NAME = "grtc_displacement"
        @Volatile
        private var INSTANCE: GrtcDatabase? = null
        fun getInstance(context: Context):GrtcDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }
        private fun buildDatabase(context: Context):GrtcDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                GrtcDatabase::class.java,
                DATABASE_NAME)
                .build()
        }
    }
}