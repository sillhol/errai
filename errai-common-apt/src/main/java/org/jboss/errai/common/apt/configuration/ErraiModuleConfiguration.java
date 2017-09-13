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

package org.jboss.errai.common.apt.configuration;

import org.jboss.errai.codegen.meta.MetaAnnotation;
import org.jboss.errai.codegen.meta.MetaClass;
import org.jboss.errai.common.apt.ErraiAptExportedTypes;
import org.jboss.errai.common.configuration.ErraiModule;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static org.jboss.errai.common.configuration.ErraiModule.Property.BINDABLE_TYPES;
import static org.jboss.errai.common.configuration.ErraiModule.Property.IOC_ALTERNATIVES;
import static org.jboss.errai.common.configuration.ErraiModule.Property.IOC_BLACKLIST;
import static org.jboss.errai.common.configuration.ErraiModule.Property.NON_SERIALIZABLE_TYPES;
import static org.jboss.errai.common.configuration.ErraiModule.Property.SERIALIZABLE_TYPES;
import static org.jboss.errai.common.configuration.ErraiModule.Property.USER_ON_HOST_PAGE_ENABLED;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
public class ErraiModuleConfiguration {

  private final Set<MetaAnnotation> erraiModules;

  public ErraiModuleConfiguration(final ErraiAptExportedTypes exportedTypes) {
    erraiModules = exportedTypes.findAnnotatedMetaClasses(ErraiModule.class)
            .stream()
            .map(module -> module.getAnnotation(ErraiModule.class))
            .map(Optional::get)
            .collect(toSet());
  }

  public Set<MetaClass> getBindableTypes() {
    return getConfiguredArrayProperty(a -> stream(a.valueAsArray(BINDABLE_TYPES, MetaClass[].class)));
  }

  public Set<MetaClass> getSerializableTypes() {
    return getConfiguredArrayProperty(a -> stream(a.valueAsArray(SERIALIZABLE_TYPES, MetaClass[].class)));
  }

  public Set<MetaClass> getNonSerializableTypes() {
    return getConfiguredArrayProperty(a -> stream(a.valueAsArray(NON_SERIALIZABLE_TYPES, MetaClass[].class)));
  }

  public Set<MetaClass> getIocAlternatives() {
    return getConfiguredArrayProperty(a -> stream(a.valueAsArray(IOC_ALTERNATIVES, MetaClass[].class)));
  }

  public Set<MetaClass> getIocBlacklist() {
    return getConfiguredArrayProperty(a -> stream(a.valueAsArray(IOC_BLACKLIST, MetaClass[].class)));
  }

  public Set<MetaClass> isUserEnabledOnHostPage() {
    return getConfiguredProperty(s -> s.value(USER_ON_HOST_PAGE_ENABLED));
  }

  private <V> Set<V> getConfiguredArrayProperty(final Function<MetaAnnotation, Stream<V>> getter) {
    return erraiModules.stream().flatMap(getter).collect(toSet());
  }

  private <V> Set<V> getConfiguredProperty(final Function<MetaAnnotation, V> getter) {
    return erraiModules.stream().map(getter).collect(toSet());
  }

}
