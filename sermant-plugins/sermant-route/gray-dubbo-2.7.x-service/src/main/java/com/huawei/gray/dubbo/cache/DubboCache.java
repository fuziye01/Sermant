/*
 * Copyright (C) 2021-2021 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.gray.dubbo.cache;

/**
 * dubbo缓存
 *
 * @author pengyuyi
 * @date 2021/11/3
 */
public class DubboCache {
    // dubbo应用名
    private static String APP_NAME;

    // dubbo应用灰度标签缓存名
    private static final String GRAY_LABEL_CACHE_NAME = "DUBBO_GRAY_LABEL";

    public static String getAppName() {
        return APP_NAME;
    }

    public static void setAppName(String appName) {
        APP_NAME = appName;
    }

    /**
     * 获取dubbo应用灰度标签缓存名
     *
     * @return dubbo应用灰度标签缓存名
     */
    public static String getLabelName() {
        return GRAY_LABEL_CACHE_NAME;
    }
}