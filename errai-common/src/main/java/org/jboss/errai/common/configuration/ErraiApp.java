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

package org.jboss.errai.common.configuration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
@Retention(RetentionPolicy.CLASS)
public @interface ErraiApp {

  boolean userOnHostPageEnabled() default false;

  String applicationContext() default "";

  boolean autoDiscoverServices() default false;

  boolean enableWebSocketServer() default false;

  boolean asyncBeanManager() default false;

  interface Property {
    String USER_ON_HOST_PAGE_ENABLED = "userOnHostPageEnabled";
    String APPLICATION_CONTEXT = "applicationContext";
    String AUTO_DISCOVER_SERVICES = "autoDiscoverServices";
    String ENABLE_WEB_SOCKET_SERVER = "enableWebSocketServer";
    String ASYNC_BEAN_MANAGER = "asyncBeanManager";
  }
}
