/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.iotdb.tsfile.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class Loader {
  private Loader() {}

  /** function for getting resources. */
  public static Set<URI> getResources(String resource, ClassLoader classLoader)
      throws IOException, URISyntaxException {
    Set<URI> uriSet = new HashSet<>();
    Enumeration<URL> urlEnum = classLoader.getResources(resource);
    while (urlEnum.hasMoreElements()) {
      uriSet.add(urlEnum.nextElement().toURI());
    }
    return uriSet;
  }

  public static URL getResource(String resource, ClassLoader classLoader) {
    return classLoader.getResource(resource);
  }

  /** function for getting the class loader of the given object. */
  public static ClassLoader getClassLoaderOfObject(Object o) {
    if (o == null) {
      throw new NullPointerException("Input object cannot be null");
    }
    return getClassLoaderOfClass(o.getClass());
  }

  public static ClassLoader getClassLoaderOfClass(final Class<?> clazz) {
    ClassLoader classLoader = clazz.getClassLoader();
    return classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
  }
}
