package uk.gov.justice.digital.hmpps.interventionscatalogue.deliusconsumer.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsConfiguration {
    @Bean
    @ConditionalOnProperty(name = "sqs.provider", havingValue = "aws")
    @Primary
        public AmazonSQSAsync awsSqsClient(@Value("${sqs.aws.access.key.id}") String accessKey, @Value("${sqs.aws.secret.access.key}") String secretKey,
                                           @Value("${cloud.aws.region.static}") String region) {
        var creds = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(creds))
                .withRegion(region)
                .build();
    }
}
