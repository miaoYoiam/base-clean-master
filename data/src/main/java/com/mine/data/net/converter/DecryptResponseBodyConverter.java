/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mine.data.net.converter;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class DecryptResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    DecryptResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        Reader reader = value.charStream();
        try {
            String encryptResponse = readerContent(reader);
            String decryptResponse = decryptResponse(encryptResponse);
            return adapter.fromJson(decryptResponse);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ignored) {

            }
        }
    }

    public String readerContent(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private String decryptResponse(String encryptContent) {
        String decrypContent = null;
        try {
            String des = "";
//            String des =  CryptoUtils.AES.decryptData(encryptContent)/*DesUtil.decryptDES(encryptContent, CommonUtil.getPreC(1) + BaseRequestHandler.p + CommonUtil.getPreC(2) + DataErrorHandler.m + CommonUtil.getPreC(3))*/;
            decrypContent = new JsonParser().parse(des).toString();
//            Log.e("DecryptResponse", "decryptResponse:"+decrypContent );
        } catch (Exception e) {
//            L.e(e.getMessage());

        }
        return encryptContent;
    }
}
