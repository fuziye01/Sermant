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

package com.huawei.gray.dubbo.strategy.type;

import com.huawei.gray.dubbo.strategy.TypeStrategy;

import org.junit.Assert;
import org.junit.Test;

/**
 * 数组匹配策略测试
 *
 * @author pengyuyi
 * @date 2021/12/1
 */
public class ArrayTypeStrategyTest {
    @Test
    public void testValue() {
        TypeStrategy strategy = new ArrayTypeStrategy();
        String[] arr = new String[]{"foo", null};
        // 正常情况
        Assert.assertEquals("foo", strategy.getValue(arr, "[0]"));
        // 测试null
        Assert.assertNotEquals("foo", strategy.getValue(arr, "[1]"));
        // 测试越界
        Assert.assertNotEquals("foo", strategy.getValue(arr, "[2]"));
        // 测试非数组
        Assert.assertNotEquals("foo", strategy.getValue("bar", "[0]"));
        // 测试不等于
        Assert.assertNotEquals("bar", strategy.getValue(arr, "[0]"));
    }
}
