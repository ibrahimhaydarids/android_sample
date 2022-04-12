package myappnew.com.conserve.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytesting.data.local.Dao.ItemsDao

import com.example.mytesting.model.Item

@Database(entities = [Item::class] , version = 2, exportSchema = false)
public  abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemsDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Note_Database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}