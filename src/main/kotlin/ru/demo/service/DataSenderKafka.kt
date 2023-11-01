package ru.demo.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import ru.demo.model.StringValue

@Service
class DataSenderKafka(
    private val topicName: String,
    private val template: KafkaTemplate<String, StringValue>,
) : DataSender {
    override fun send(value: StringValue) {
        try {
            log.info("value:{}", value)
            template.send(topicName, value)
                .whenComplete { result: SendResult<String, StringValue>, ex: Throwable? ->
                    if (ex == null) {
                        log.info(
                            "message id:{} was sent, offset:{}",
                            value.id,
                            result.recordMetadata.offset()
                        )
                    } else {
                        log.error("message id:{} was not sent", value.id, ex)
                    }
                }
        } catch (ex: Exception) {
            log.error("send error, value:{}", value, ex)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(DataSenderKafka::class.java)
    }
}
