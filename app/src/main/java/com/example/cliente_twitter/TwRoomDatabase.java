package com.example.cliente_twitter;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = TweetEntity.class,version = 1, exportSchema = true)
//@Database(entities = {TweetEntity.class}, version = 1)
public abstract class TwRoomDatabase extends RoomDatabase {

    public abstract TweetDAO tweetDAO();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile TwRoomDatabase INSTANCE;

    static TwRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TwRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TwRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TweetDAO mDao;


        PopulateDbAsync(TwRoomDatabase db) {
            mDao = db.tweetDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            TweetEntity tweeEntity = new TweetEntity("TallyGM","Hola que tal");
            mDao.insert(tweeEntity);
            return null;
        }
    }
}
