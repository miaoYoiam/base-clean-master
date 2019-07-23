package com.mine.data.cache;

import android.content.Context;

import com.mine.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserCacheImpl implements UserCache {
    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 10 * 60 * 1000;
    private static final String SETTINGS_FILE_NAME = "com.bingdou.tv.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";
    private final File mCacheDir;
    private final Context mContext;
    private final FileManager mFileManager;
    private final JsonSerializer mSerializer;
    private final ThreadExecutor mThreadExecutor;

    private static class CacheEvictor implements Runnable {
        private final File mCacheDir;
        private final FileManager mFileManager;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.mFileManager = fileManager;
            this.mCacheDir = cacheDir;
        }

        public void run() {
            this.mFileManager.clearDirectory(this.mCacheDir);
        }
    }

    private static class CacheWriter implements Runnable {
        private final String mFileContent;
        private final FileManager mFileManager;
        private final File mFileToWrite;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.mFileManager = fileManager;
            this.mFileToWrite = fileToWrite;
            this.mFileContent = fileContent;
        }

        public void run() {
            this.mFileManager.writeToFile(this.mFileToWrite, this.mFileContent);
        }
    }

    @Inject
    public UserCacheImpl(Context context, JsonSerializer userCacheSerializer, FileManager fileManager, ThreadExecutor executor) {
        if (context == null || userCacheSerializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.mContext = context.getApplicationContext();
        this.mCacheDir = this.mContext.getCacheDir();
        this.mSerializer = userCacheSerializer;
        this.mFileManager = fileManager;
        this.mThreadExecutor = executor;
    }

    public boolean isCached(String userId) {
        return this.mFileManager.exists(buildFile(userId));
    }

    public boolean isExpired() {
        boolean expired = System.currentTimeMillis() - getLastCacheUpdateTimeMillis() > EXPIRATION_TIME;
        if (expired) {
            evictAll();
        }
        return expired;
    }

    public void evictAll() {
        executeAsynchronously(new CacheEvictor(this.mFileManager, this.mCacheDir));
    }

    private File buildFile(String userId) {
        return new File(this.mCacheDir.getPath() + File.separator + DEFAULT_FILE_NAME + userId);
    }

    private void setLastCacheUpdateTimeMillis() {
        this.mFileManager.writeToPreferences(this.mContext, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE, System.currentTimeMillis());
    }

    private long getLastCacheUpdateTimeMillis() {
        return this.mFileManager.getFromPreferences(this.mContext, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    private void executeAsynchronously(Runnable runnable) {
        this.mThreadExecutor.execute(runnable);
    }
}
