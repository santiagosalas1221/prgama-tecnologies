package com.pragma.pragma_tecnologies.infrastructure.adapter;

import com.pragma.pragma_tecnologies.application.ports.out.TechnologyRepository;
import com.pragma.pragma_tecnologies.domain.model.TechnologiesDTO;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DynamoDBTechnologyRepository implements TechnologyRepository {

    private final DynamoDbAsyncClient dynamoDbClient;

    private String tableName = "Technologies";

    public DynamoDBTechnologyRepository(DynamoDbAsyncClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public Mono<TechnologiesDTO> save(TechnologiesDTO technologiesDTO) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(technologiesDTO.getId()).build());
        item.put("name", AttributeValue.builder().s(technologiesDTO.getName()).build());
        item.put("description", AttributeValue.builder().s(technologiesDTO.getDescription()).build());

        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tableName) // TODO establecer nombre de la tabla
                .item(item)
                .build();

        return Mono.fromFuture(() -> dynamoDbClient.putItem(putItemRequest))
                .thenReturn(technologiesDTO);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":nameVal", AttributeValue.builder().s(name).build());

        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("name = :nameVal")
                .expressionAttributeValues(expressionValues)
                .limit(1)
                .build();

        return Mono.fromFuture(() -> dynamoDbClient.query(queryRequest))
                .map(QueryResponse::count)
                .map(count -> count > 0);
    }
}
