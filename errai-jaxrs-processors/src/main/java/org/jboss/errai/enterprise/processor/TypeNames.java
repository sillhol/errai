/*
 * Copyright (C) 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.enterprise.processor;

/**
 *
 * @author Max Barkley <mbarkley@redhat.com>
 */
public interface TypeNames {
  static final String PATH = "javax.ws.rs.Path";
  static final String PROVIDER = "javax.ws.rs.ext.Provider";
  static final String FEATURE_INTERCEPTOR = "org.jboss.errai.common.client.api.interceptor.FeatureInterceptor";
  static final String INTERCEPTED_CALL = "org.jboss.errai.common.client.api.interceptor.InterceptedCall";
}