package com.wy.synthetic.audit;

import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuditAspect {
    private static final Logger log = LoggerFactory.getLogger(AuditAspect.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final boolean auditEnabled;
    private final String auditMode;

    public AuditAspect(KafkaTemplate<String, String> kafkaTemplate,
                       @Value("${audit.enabled}") boolean enabled,
                       @Value("${audit.mode}") String mode) {
        this.kafkaTemplate = kafkaTemplate;
        this.auditEnabled = enabled;
        this.auditMode = mode;
    }

    @Around("@annotation(com.wy.synthetic.audit.WeylandWatchingYou)")
    public Object audit(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().toShortString();
        Object[] args = pjp.getArgs();
        if (auditEnabled) {
            String msg = method + " args=" + Arrays.toString(args);
            if ("kafka".equals(auditMode)) {
                kafkaTemplate.send("audit_topic", msg);
            } else {
                log.info("AUDIT: {}", msg);
            }
        }
        Object result = pjp.proceed();
        if (auditEnabled) {
            String msg = method + " returned=" + result;
            if ("kafka".equals(auditMode)) {
                kafkaTemplate.send("audit_topic", msg);
            } else {
                log.info("AUDIT: {}", msg);
            }
        }
        return result;
    }
}
