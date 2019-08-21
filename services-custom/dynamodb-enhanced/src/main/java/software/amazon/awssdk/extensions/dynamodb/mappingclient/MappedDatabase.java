/*
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.extensions.dynamodb.mappingclient;

import software.amazon.awssdk.annotations.SdkPublicApi;
import software.amazon.awssdk.extensions.dynamodb.mappingclient.core.DynamoDbMappedDatabase;

/**
 * Interface for running commands against a DynamoDb database.
 *
 * An implementation for this interface can be instantiated by using the default builder:
 *
 * MappedDatabase.builder()
 *               .dynamoDbClient(dynamoDbClient)
 *               .extendWith(mapperExtension)       // Optional. See 'extensions' package.
 *               .build();
 */
@SdkPublicApi
public interface MappedDatabase {
    /**
     * Executes a command against the database.
     *
     * Example: mappedDatabase.execute(BatchGetItem.of(...));
     *
     * @param operation The operation to be performed in the context of the database.
     * @param <T> The expected return type from the operation. This is typically inferred by the compiler.
     * @return The result of the operation being executed. The documentation on the operation itself should have more
     * information.
     */
    <T> T execute(DatabaseOperation<?, ?, T> operation);

    /**
     * Returns a mapped table that can be used to execute commands that work with mapped items against that table.
     *
     * @param tableName The name of the physical table persisted by DynamoDb.
     * @param tableSchema A {@link TableSchema} that maps the table to a modelled object.
     * @return A {@link MappedTable} object that can be used to execute table operations against.
     * @param <T> THe modelled object type being mapped to this table.
     */
    <T> MappedTable<T> table(String tableName, TableSchema<T> tableSchema);

    /**
     * Constructs a builder for the default approved implementation of this interface.
     * @return A builder for a {@link DynamoDbMappedDatabase}.
     */
    static DynamoDbMappedDatabase.Builder builder() {
        return DynamoDbMappedDatabase.builder();
    }
}
