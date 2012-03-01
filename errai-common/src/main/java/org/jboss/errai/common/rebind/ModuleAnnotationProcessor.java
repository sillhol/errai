/*
 * Copyright 2011 JBoss, by Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.common.rebind;

import org.jboss.errai.common.client.api.annotations.Portable;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mike Brock
 */
@SupportedAnnotationTypes("*")
public class ModuleAnnotationProcessor extends AbstractProcessor {

  final Set<Element> allKnownElements = new HashSet<Element>();

  @Override
  public boolean process(Set<? extends TypeElement> typeElements, RoundEnvironment roundEnvironment) {
    allKnownElements.addAll(roundEnvironment.getRootElements());

    if (roundEnvironment.processingOver()) {
      StringBuilder builder = new StringBuilder();
      for (Element e : allKnownElements) {
        builder.append(e.toString()).append('\n');
      }

      try {
        final FileObject fo = processingEnv.getFiler().createResource(
                StandardLocation.CLASS_OUTPUT,
                "",
                "class_list.txt",
                null);

        Writer writer = fo.openWriter();
        writer.write(builder.toString());
        writer.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }

    }
    return false;

  }
}
