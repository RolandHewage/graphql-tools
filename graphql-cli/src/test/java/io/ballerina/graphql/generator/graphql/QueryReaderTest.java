/*
 *  Copyright (c) 2022, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package io.ballerina.graphql.generator.graphql;

import graphql.language.Document;
import graphql.schema.GraphQLSchema;
import io.ballerina.graphql.cmd.GraphqlProject;
import io.ballerina.graphql.cmd.Utils;
import io.ballerina.graphql.cmd.pojo.Extension;
import io.ballerina.graphql.common.GraphqlTest;
import io.ballerina.graphql.common.TestUtils;
import io.ballerina.graphql.exception.CmdException;
import io.ballerina.graphql.exception.ParseException;
import io.ballerina.graphql.exception.ValidationException;
import io.ballerina.graphql.generator.ballerina.AuthConfigGenerator;
import io.ballerina.graphql.generator.graphql.components.ExtendedOperationDefinition;
import io.ballerina.graphql.generator.model.AuthConfig;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class is used to test the functionality of the GraphQL query reader.
 */
public class QueryReaderTest extends GraphqlTest {

    @Test
    public void testGetExtendedOperationDefinitions()
            throws ValidationException, CmdException, IOException, ParseException {
        List<GraphqlProject> projects = TestUtils.getValidatedMockProjects(
                this.resourceDir.resolve(Paths.get("specs",
                        "graphql-config-to-test-arguments.yaml")).toString(),
                this.tmpDir);

        Extension extensions = projects.get(0).getExtensions();
        List<String> documents = projects.get(0).getDocuments();
        GraphQLSchema schema = projects.get(0).getGraphQLSchema();

        AuthConfig authConfig = new AuthConfig();
        AuthConfigGenerator.getInstance().populateAuthConfigTypes(extensions, authConfig);
        AuthConfigGenerator.getInstance().populateApiHeaders(extensions, authConfig);

        Document queryDocument = Utils.getGraphQLQueryDocument(documents.get(0));
        QueryReader queryReader = new QueryReader(queryDocument);

        ExtendedOperationDefinition queryOperation1Definition = queryReader.getExtendedOperationDefinitions().get(0);

    }

    @Test
    public void testGetExtendedFragmentDefinitions() {
    }
}
