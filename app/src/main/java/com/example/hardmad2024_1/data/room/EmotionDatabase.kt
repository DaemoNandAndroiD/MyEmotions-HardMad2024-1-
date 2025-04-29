package com.example.hardmad2024_1.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hardmad2024_1.data.room.dao.EmotionDao
import com.example.hardmad2024_1.data.room.dao.NotificationDao
import com.example.hardmad2024_1.data.room.dao.RecordDao
import com.example.hardmad2024_1.data.room.dao.UserDao
import com.example.hardmad2024_1.data.room.entities.EmotionEntity
import com.example.hardmad2024_1.data.room.entities.NotificationEntity
import com.example.hardmad2024_1.data.room.entities.RecordEntity
import com.example.hardmad2024_1.data.room.entities.UserEntity
import com.example.hardmad2024_1.data.room.entities.emotionsList
import com.example.hardmad2024_1.data.room.entities.type_converters.RecordsTypeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [UserEntity::class, EmotionEntity::class, RecordEntity::class, NotificationEntity::class],
    version = 3
)
@TypeConverters(RecordsTypeConverter::class)
abstract class EmotionDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
    abstract fun notificationDao() : NotificationDao
    abstract fun recordDao() : RecordDao
    abstract fun emotionDao() : EmotionDao

    companion object{
        private var instance: EmotionDatabase? = null

        fun getInstance(context: Context) : EmotionDatabase{
            return instance ?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context) : EmotionDatabase{
            return Room.databaseBuilder(
                context,
                EmotionDatabase::class.java,
                "emotion_database"
            )
                .fallbackToDestructiveMigration(false)
                .addCallback(object : Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        CoroutineScope(Dispatchers.IO).launch {
                            getInstance(context).emotionDao().addEmotions(emotionsList)
                        }
                    }
                })
                .build()
        }
    }
}