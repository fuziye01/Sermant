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

package com.huawei.register.interceptors;

import com.huawei.register.services.RegisterCenterService;
import com.huawei.sermant.core.agent.common.BeforeResult;
import com.huawei.sermant.core.agent.interceptor.InstanceMethodInterceptor;
import com.huawei.sermant.core.service.ServiceManager;

import java.lang.reflect.Method;

/**
 * 拦截替换服务列表
 *
 * @author zhouss
 * @since 2021-12-13
 */
public class ServerListInterceptor implements InstanceMethodInterceptor {

    @Override
    public void before(Object obj, Method method, Object[] arguments, BeforeResult beforeResult) {
        final RegisterCenterService service = ServiceManager.getService(RegisterCenterService.class);
        service.replaceServerList(obj, beforeResult);
    }

    @Override
    public Object after(Object obj, Method method, Object[] arguments, Object result) {
        return result;
    }

    @Override
    public void onThrow(Object obj, Method method, Object[] arguments, Throwable t) {

    }
}
