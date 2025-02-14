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

package com.huawei.flowcontrol.core.datasource.kie.rule.system;

import com.alibaba.csp.sentinel.datasource.AbstractDataSource;
import com.alibaba.csp.sentinel.property.SentinelProperty;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.huawei.flowcontrol.core.datasource.kie.rule.RuleWrapper;

import java.util.List;

/**
 * 系统规则包装类
 *
 * @author hanpeng
 * @since 2020-10-14
 */
public class SystemRuleWrapper extends RuleWrapper {
    /**
     * 注册系统规则到系统规则管理器
     *
     * @param dataSource 数据源
     */
    @Override
    public void registerRuleManager(AbstractDataSource<?, ?> dataSource) {
        SentinelProperty property = dataSource.getProperty();
        if (property != null) {
            SystemRuleManager.register2Property((SentinelProperty<List<SystemRule>>) property);
        }
    }

    /**
     * 获取规则数据的类信息
     *
     * @return 返回class对象
     */
    @Override
    protected Class<?> getRuleClass() {
        return SystemRule.class;
    }
}
