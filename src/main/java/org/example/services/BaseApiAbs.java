package org.example.services;

import org.apache.commons.lang3.StringUtils;
import org.example.annotations.Path;

public abstract class BaseApiAbs<T> {

  private String getBaseUrl(){
    return StringUtils.stripEnd(System.getProperty("base.url"), "/");
  }

  private String getPath() {
    Path path = getClass().getAnnotation(Path.class);
    if (path != null) {
      return path.value().replaceAll("/+$","");
    }
    return "";
  }


  protected String getUrl() {
    return getBaseUrl() + getPath();
  }
}
