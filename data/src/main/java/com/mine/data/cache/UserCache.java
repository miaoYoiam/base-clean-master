package com.mine.data.cache;

public interface UserCache {
    void evictAll();


    boolean isCached(String str);

    boolean isExpired();


}
