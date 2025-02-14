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

package com.huawei.flowcontrol.core.util;

import com.alibaba.csp.sentinel.log.RecordLog;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;

/**
 * kafka消息生产工具类
 *
 * @author liyi
 * @since 2020-08-26
 */
public class KafkaProducerUtil {
    private KafkaProducerUtil() {
    }

    /**
     * 使用kafka发送流控数据和心跳数据
     *
     * @param topic kafka的topic
     * @param msg   流控和心跳数据
     */
    public static void sendMessage(String topic, String msg) {
        KafkaProducer<String, String> producer = KafkaProducerEnum.KAFKA_PRODUCER.getKafkaProducer();
        try {
            sendRecord(topic, msg, producer);
        } finally {
            // 此处需要保证数据实时性, 保证心跳正常
            producer.flush();
        }
    }

    private static void sendRecord(final String topic, String msg,
        KafkaProducer<String, String> producer) {
        ProducerRecord<String, String> record;
        try {
            record = new ProducerRecord<String, String>(topic, null, msg);

            // 异步回调通发送数据
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        RecordLog.error("[KafkaProducerUtil] kafka exception in " + topic + exception.getMessage());
                    }
                }
            });
        } catch (KafkaException e) {
            RecordLog.error("[KafkaProducerUtil] sendMessage() exception " + e);
        }
    }
}

