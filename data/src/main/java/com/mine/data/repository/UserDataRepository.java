/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mine.data.repository;

import android.content.Context;

import com.mine.data.net.api.Api;
import com.mine.domain.model.GetCaptheResult;
import com.mine.domain.model.GetRecipeHomeResult;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.GetCaptchaParams;
import com.mine.domain.repository.params.GetRecipeHomeParams;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final Context mContext;
    @Inject
    protected Api userApi;

    @Inject
    public UserDataRepository(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.mContext = context.getApplicationContext();

    }

    public Observable<GetCaptheResult> getCaptcha(GetCaptchaParams getCaptchaParams) {
        return RepositoryUtils.extractData(this.userApi.getCaptcha(getCaptchaParams), GetCaptheResult.class);
    }

    @Override
    public Observable<GetRecipeHomeResult> getRecipeHome(GetRecipeHomeParams getRecipeHomeParams) {

        return RepositoryUtils.extractData(this.userApi.getRecipeHome(getRecipeHomeParams), GetRecipeHomeResult.class);
    }


}
