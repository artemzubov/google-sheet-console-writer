package com.zubov.ident.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SqlWithProperties {

  private String sqlPath;
  private String googleDocId;
  private String googleDocPageId;
}
