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

package com.huawei.sermant.core.lubanops.integration.access.inbound;

import com.huawei.sermant.core.lubanops.integration.access.MessageType;

/**
 * @author
 * @since 2020/5/7
 **/
public class EventDataResponse extends CommonResponse {
    public EventDataResponse(long msgId) {
        super(MessageType.TRACE_EVENT_RESPONSE, msgId);
    }
}
