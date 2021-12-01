/*
 *  Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package io.ballerina.graphql.generators;

import io.ballerina.graphql.cmd.mappers.Extension;
import io.ballerina.graphql.exceptions.BallerinaGraphqlDocumentPathValidationException;
import io.ballerina.graphql.exceptions.BallerinaGraphqlIntospectionException;
import io.ballerina.graphql.exceptions.BallerinaGraphqlSchemaPathValidationException;

import java.io.IOException;

/**
 * Interface for code generator. Any code generator implementation should implement this.
 */
public interface ICodeGenerator {

    /**
     * Generates the code for a given GraphQL project queries and schema.
     *
     * @param schema            the schema value of the Graphql config file
     * @param document          the document value of the Graphql config file
     * @param extensions        the extensions value of the Graphql config file
     * @param outputPath        the target output path for the code generation
     */
    void generate(String schema, String document, Extension extensions, String outputPath)
            throws BallerinaGraphqlIntospectionException, IOException,
            BallerinaGraphqlSchemaPathValidationException, BallerinaGraphqlDocumentPathValidationException;
}
