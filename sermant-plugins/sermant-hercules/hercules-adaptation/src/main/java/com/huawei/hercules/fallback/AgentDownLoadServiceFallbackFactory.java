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

package com.huawei.hercules.fallback;

import com.huawei.hercules.service.agent.IAgentDownloadService;
import org.springframework.stereotype.Component;

/**
 * 功能描述：agentDownload调用feign失败回调
 *
 * 
 * @since 2021-11-24
 */
@Component
public class AgentDownLoadServiceFallbackFactory extends BaseFeignFallbackFactory<IAgentDownloadService> {
    @Override
    public IAgentDownloadService create(Throwable throwable) {
        return error(throwable);
    }
}
