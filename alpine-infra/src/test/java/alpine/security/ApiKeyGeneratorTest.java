/*
 * This file is part of Alpine.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * Copyright (c) Steve Springett. All Rights Reserved.
 */
package alpine.security;

import alpine.Config;
import alpine.model.ApiKey;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class ApiKeyGeneratorTest {

    private Pattern pattern = Pattern.compile("^[A-Za-z_0-9]*$");
    private String apiKeyPrefix = Config.getInstance().getProperty(Config.AlpineKey.API_KEY_PREFIX);
    private static final int PUBLIC_ID_LENGTH = ApiKey.PUBLIC_ID_LENGTH;

    @Test
    public void defaultGenerateTest() {
        String key = ApiKeyGenerator.generate();
        Assertions.assertEquals(apiKeyPrefix.length() + 32 + PUBLIC_ID_LENGTH + 1, key.length());
        Assertions.assertTrue(key.startsWith(apiKeyPrefix));
        Assertions.assertTrue(pattern.matcher(key).matches());
    }

    @Test
    public void generateTest() {
        String key = ApiKeyGenerator.generate(5, 4096);
        Assertions.assertEquals(apiKeyPrefix.length() + 5 + 4096 + 1, key.length());
        Assertions.assertTrue(key.startsWith(apiKeyPrefix));
        Assertions.assertTrue(pattern.matcher(key).matches());
    }
}
